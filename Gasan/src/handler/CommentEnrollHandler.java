package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AuthUser;
import model.Comment;
import service.CommentEnrollService;

public class CommentEnrollHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (req.getMethod().equals("POST")) {
			AuthUser authUser = (AuthUser) req.getSession(false).getAttribute("authUser");

			if (authUser == null) {
				throw new RuntimeException();
			}

			String foodIdStr = req.getParameter("foodId");
			String scoreStr = req.getParameter("score");
			if (foodIdStr == null || scoreStr == null) {
				throw new RuntimeException();
			}

			int foodId = Integer.parseInt(foodIdStr);
			int score = Integer.parseInt(scoreStr);
			String comm = req.getParameter("comm");

			Comment comment = new Comment(foodId, authUser.getLoginId(), comm, score);

			CommentEnrollService commentEnrollService = CommentEnrollService.getInstance();

			commentEnrollService.enroll(comment);

			resp.sendRedirect(req.getContextPath() + "/foodView?no=" + foodId + "&page=1");
		} else {
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

		return null;
	}

}
