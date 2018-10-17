package handler;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AuthUser;
import model.Comment;
import service.CommentDeleteService;
import service.CommentViewService;

public class CommentDeleteHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		CommentDeleteService commentDeleteService = CommentDeleteService.getInstance();
		CommentViewService commentViewService = CommentViewService.getInstance();

		String commNoStr = req.getParameter("commNo");
		String pageNoStr = req.getParameter("pageNo");
		
		if(commNoStr == null) {
			throw new RuntimeException();
		}
		
		int commentId = Integer.parseInt(commNoStr);
		
		Comment comment = commentViewService.select(commentId);
		AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
		
		if(comment == null || authUser == null || !comment.getLoginId().equals(authUser.getLoginId())) {
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.print("<script>alert('접근 권한이 음슴 :('); location.href='" + req.getContextPath() + "/foodView?no="+ pageNoStr +"'</script>");
			throw new RuntimeException();
		}
		
		commentDeleteService.delete(commentId);
		
		resp.sendRedirect(req.getContextPath() + "/foodView?no=" + pageNoStr);
		
		return null;
	}
}