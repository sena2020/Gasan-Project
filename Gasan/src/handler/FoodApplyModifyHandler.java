package handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ArticleFoodApply;
import model.AuthUser;
import service.FoodApplyModifyRequest;
import service.FoodApplyModifyService;

public class FoodApplyModifyHandler implements CommandHandler{
	private static final String FORM_VIEW = "/applyModify.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equals("GET")) {
			return processForm(req, resp);
		} else if (req.getMethod().equals("POST")) {
			return processSubmit(req, resp);
		} else {
			resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		FoodApplyModifyService applyModifyService = FoodApplyModifyService.getInstance();
		
		int applyId = Integer.parseInt(req.getParameter("no"));
		AuthUser authUser = (AuthUser)req.getSession().getAttribute("authUser");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		Map<String, Boolean> errors = new HashMap<>();

		FoodApplyModifyRequest foodApply = new FoodApplyModifyRequest(applyId, authUser.getLoginId(), title, content);
		// 문제가 있으면 errors가 나옴.
		foodApply.validate(errors);

		// 에러가 비어있지않다? => 에러가 있다 => 폼 페이지로 반환?????
		if (!errors.isEmpty()) {
			return FORM_VIEW;

		}
		System.out.println(foodApply);
		applyModifyService.modify(foodApply);
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print("<script>alert('수정을 완료하였습니다. :3'); location.href='" + req.getContextPath() + "/listApply'</script>");
		pw.flush();
		return null;
	}

	private String processForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=utf-8");
		FoodApplyModifyService applyModifyService = FoodApplyModifyService.getInstance();
		
		String noStr = req.getParameter("no");
		AuthUser authUser = (AuthUser)req.getSession().getAttribute("authUser");
		ArticleFoodApply foodApply = applyModifyService.getArticle(Integer.parseInt(noStr));
		
		if(foodApply == null || authUser == null || !foodApply.getLoginId().equals(authUser.getLoginId())) {
			PrintWriter pw = resp.getWriter();
			pw.print("<script>alert('접근 권한이 음슴 :('); location.href='" + req.getContextPath() + "/listApply'</script>");
			throw new RuntimeException();
		}
		
		req.setAttribute("foodApply", foodApply);
		return "/applyModify.jsp";
	}
}