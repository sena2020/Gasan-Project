package handler;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutHandler implements CommandHandler{
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 세션을 날리고 리다이렉트로 돌려줌.
		
		HttpSession session = req.getSession(false); //HttpServletRequest 에 있는 getSession메소드를 쓰는 것.
		
		if(session!=null) {
			session.invalidate();
		}

		resp.setContentType("text/html; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print("<script>alert('로그아웃 되었습니다 : |'); location.href='" + req.getContextPath() + "/foodList'</script>");
		
		
		
		return null;
	}

	
}
