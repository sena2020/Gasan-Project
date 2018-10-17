package handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AuthUser;
import service.FoodApplyEnrollRequest;
import service.FoodApplyEnrollService;


public class FoodApplyEnrollHandler implements CommandHandler{
	private static final String FORM_VIEW = "/apply.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub

		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, resp);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, resp);
		} else {
			resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}

	}

	private String processForm(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) {

		// 사용자한테 입력받은 게시글 내용의 무결성을 체크하기 위해
		// writeRequest객체에 담아서 비어있는지 체크하고
		// 이상이 있으면 form_view로 다시 돌리고 없으면,
		// 서비스를 이용해서 게시글 삽입 로직을 수행한다.

		// 서비스를 사용하기 위해서 에러부분을 찾아야 하므로 객체 생성
		Map<String, Boolean> errors = new HashMap<String, Boolean>();

		req.setAttribute("errors", errors);
		AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");

		// writeRequset에 필요한 정보는 Writer라는 객체의 정보와 title,content이다.
		// Writer객체의 정보는 세션에 담겨 있으므로 그걸 통해 받아오고
		// 다른 것들은 사용자로부터 form에 입력받아서 파라미터로 값을 가져옴.
		FoodApplyEnrollRequest fRequest = new FoodApplyEnrollRequest(authUser.getLoginId(),
				req.getParameter("title"), req.getParameter("content"));

		// 문제가 있으면 errors가 나옴.
		fRequest.validate(errors);

		// 에러가 비어있지않다? => 에러가 있다 => 폼 페이지로 반환?????
		if (!errors.isEmpty()) {
			return FORM_VIEW;

		}

		// 뭔가 들어있지 않다면

		FoodApplyEnrollService articleService = FoodApplyEnrollService.getInstance();
		articleService.write(fRequest);

		return "/foodApplySuccess.jsp";
	}
}
