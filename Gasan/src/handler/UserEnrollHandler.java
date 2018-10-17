package handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.DuplicateException;
import service.LoginService;
import service.UserEnrollRequest;
import service.UserEnrollService;

public class UserEnrollHandler implements CommandHandler {
	
	// 회원가입 페이지주소를 상수로 만듦.
		private static final String FORM_VIEW = "/WEB-INF/view/enrollForm.jsp";

		// 사용자는 url board/join ->
		// form에서 전송할 action 역시 board/join
		// get방식으로 요청이 오면 폼을 보여주는 뷰로 리턴을 하고
		// post방식으로 요청이 오면 회원가입을 처리하고 결과를 보여주는 뷰로 리턴.

		@Override
		public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			if (req.getMethod().equalsIgnoreCase("GET")) {// 메소드 질문
				return processForm(req, resp);
			} else if (req.getMethod().equalsIgnoreCase("POST")) {
				return processSubmit(req, resp);
			} else {
				resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);// 이것 무엇??ㅇㅅㅇ???

			}
			return null;
}
		
		private String processForm(HttpServletRequest req, HttpServletResponse resp) {
			//페이지를 다시 돌려주면 됨.
			return FORM_VIEW;
		}

		//사용자로부터 회원가입 데이터를 입력받아 submit버튼을 클릭해서 데이터가 넘어왔을 때 실행하는 메소드.
		private String processSubmit(HttpServletRequest req, HttpServletResponse resp) {
			//파라미터를 통해서 입력받은 데이터를 UserEnrollRequest 객체에 담음.
			
			UserEnrollRequest enrollRequest = new UserEnrollRequest();
			enrollRequest.setLoginId(req.getParameter("loginId"));
			enrollRequest.setNickname(req.getParameter("nickname"));
			enrollRequest.setPassword(req.getParameter("password"));
			enrollRequest.setConfirmPassword(req.getParameter("confirmPassword"));
			
			
			//UserEnrollRequest를 통해 입력받은 데이터가 제대로 입력되어 있는지,
			//잘못된 정보는 errors 라는 맵에 넣기 위해 errors라는 맵을 생성.
			Map<String,Boolean> errors = new HashMap<String,Boolean>(); //여기까지는 현재 errors는 빈 객체
			
			//errors는 view에서 표출해주기 위해 request 속성값으로 넣어줌.
			req.setAttribute("errors", errors);
			
			//데이터 검증. 무결성 체크. 문제가 있을 시 put이 되서 추가 됨. 문제가 없다면 errors는 여전히 빈 객체
			enrollRequest.validate(errors); //errors맵은 잘못된 정보를 담는 맵 객체이므로 패스워드와 패스워드 확인이 일치하지 않는다면 데이터가 넘어와서 여기에 담김.
			//validate메소드가 지나오면, errors맵에는 잘못된 데이터필드명과(키):(not Match) 과 함께 true라는 value값이 추가되어 있음.
			
			//잘못 들어왔으면(errors가 빈 객체가 아니라면 잘못된 내용이 있는 것이다.) 다시 폼화면으로 반환
			if(!errors.isEmpty()) {
				return FORM_VIEW;
			}
			
			//그리고 잘 입력됐으면 joinService를 통해서 회원가입 로직 수행.
			UserEnrollService enrollService = UserEnrollService.getInstance();
			
			
			//  try/catch를 if/else의 느낌으로 사용하고 있음.
			try {
			//joinservice.join 의 로직은 아이디가 중복일 때 예외를 여기로 던져줌.
				enrollService.enroll(enrollRequest);
			
			
			//성공화면으로 반환
			return "/WEB-INF/view/enrollSuccess.jsp";
			
			}catch(DuplicateException e) {
				//아이디가 중복일 때 service에서 발생시킨 예외를 받아서 처리해줌.
				errors.put("duplicateId", true);
				//다시 폼으로 보내기.
				return FORM_VIEW;
			}
		}
}
