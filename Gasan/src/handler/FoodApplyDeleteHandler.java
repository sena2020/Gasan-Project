package handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ArticleFoodApply;
import model.AuthUser;
import service.FoodApplyDeleteService;
import service.FoodApplyReadService;

public class FoodApplyDeleteHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equals("GET")) {
			processForm(req, resp);
		} else if (req.getMethod().equals("POST")) {
			processSubmit(req, resp);
		} else {
			resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		return null;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) {
		return null;
	}

	private String processForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=utf-8");
		FoodApplyDeleteService deleteService = FoodApplyDeleteService.getInstance();
		FoodApplyReadService readService = FoodApplyReadService.getInstance();
		
		int applyId = Integer.parseInt(req.getParameter("no"));

		AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
		
		ArticleFoodApply foodApply = readService.getFoodApply(applyId);
		
		if(foodApply == null || authUser == null || !foodApply.getLoginId().equals(authUser.getLoginId())) {
			PrintWriter pw = resp.getWriter();
			pw.print("<script>alert('접근 권한이 음슴 :('); location.href='" + req.getContextPath() + "/listApply'</script>");
			throw new RuntimeException();
		}
		
		deleteService.delete(applyId);
		PrintWriter pw = resp.getWriter();
		pw.println("<script>alert('삭제되었습니다. :9'); location.href = '" + req.getContextPath() + "/listApply';</script>");
		pw.flush();
		return null;
	}
}
