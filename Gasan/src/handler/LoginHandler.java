package handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.LoginFailException;
import model.AuthUser;
import service.LoginService;


public class LoginHandler implements CommandHandler{

	// 반환하는 주소를 상수로 만듦.
		private static final String FORM_VIEW = "/WEB-INF/view/loginForm.jsp";

		// get과 post를 나눠줌.
		// get은 로그인 화면 요청일 것이고
		// post는 로그인 요청일 것이고
		@Override
		public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

			if (req.getMethod().equalsIgnoreCase("GET")) {
				return processForm(req, resp);
			} else if (req.getMethod().equalsIgnoreCase("post")) {
				return processSubmit(req, resp);
			} else {
				resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); // 질문
				return null;
			}

		}

		private String processForm(HttpServletRequest req, HttpServletResponse resp) {

			return FORM_VIEW;
		}

		private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
			// 서비스 객체 받기
			LoginService loginService = LoginService.getInstance();
			// request에서 파라미터로 받음.
			String loginId = ((req.getParameter("loginId") == null) ? null : req.getParameter("loginId").trim());
			// 파라미터로 받은  게 null인지 확인을하고 trim()는 공백을 지워줌 . 오ㅐ...?이 부분 다시 ㅜㅜ
																													
			String password = ((req.getParameter("password") == null) ? null : req.getParameter("password").trim());

			Map<String, Boolean> errors = new HashMap<String, Boolean>();
			// errors의 속성 셋팅
			req.setAttribute("errors", errors);

			// 아이디가 비었는지 확인하고 , 비었으면 키값과 true를 넣어줌.
			if (loginId == null || loginId.isEmpty()) {
				errors.put("loginId", true);
			}

			// 비밀번호가 비었는지 확인하고
			if (password == null || password.isEmpty()) {
				errors.put("password", true);
			}

			if (!errors.isEmpty()) {

				return FORM_VIEW;

			}

			try {
				// 서비스를 통해서 로그인을 실행 및 검증
				// 문제가 발생 시 ex가 발생하고 바로 catch로 들어감.

				// 아이디와 비밀번호를 제대로 채웠다면? 객체를 생성해서 담음
				AuthUser authUser = loginService.login(loginId, password);

				// 성공시 세션에 로그인한 사용자의 정보를 담음. //리퀘스트에서 겟셋션을 해서 정보를 저장.
				req.getSession().setAttribute("authUser", authUser);

				// 첫 화면으로 돌려줌.
				resp.sendRedirect(req.getContextPath() + "/foodList");
				System.out.println("aa");

			} catch (LoginFailException e) {// 로그인 실패의 상황에 대해서
				// 메시지 출력
				System.out.println(e.getMessage());

				// 에러 맵에 아이디나 패스워드가 알맞지 않다는 의미의 키값과 true를 넣어줌.
				errors.put("idOrPwNotMatch", true);
				// 그리고 보여줄 페이지에 반환
				return FORM_VIEW;

			}

			System.out.println("요기");

			// 서비스를 통해서 인증을 하고
			// 그리고 성공하면 auth객체를 만들어서 세션에 넣고
			// 실패하면 에러에 실패 정보를 넣고 로그인 화면으로 다시 보냄.

			return null;
		}
}
