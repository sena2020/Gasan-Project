package handler;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AuthUser;
import service.UserDeleteService;

public class UserDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
		if(authUser == null) {
			pw.println("<script>alert('로그인 여부를 확인해주세요.'); location.href = '" + req.getContextPath() + "/login' </script>");
		}
		
		String loginId = authUser.getLoginId();
		UserDeleteService deleteService = UserDeleteService.getInstance();
		
		deleteService.delete(loginId);
		pw.println("<script>alert('회원 탈퇴가 완료되었습니다.'); location.href = '" + req.getContextPath() + "/foodList' </script>");
		
		req.getSession().invalidate();
		return null;
	}

}
