package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.CommentDao;
import jdbc.connector.ConnectionProvider;
import model.Comment;

public class CommentListService {
	private static CommentListService instance = new CommentListService();

	private CommentListService() {
	}

	public static CommentListService getInstance() {
		return instance;
	}

	private final static int COMMENT_PER_PAGE = 5;
	private final static int PAGE_BLOCK = 5;

	public CommentPage getCommentPage(int foodId, int currentPage) {
		List<Comment> comments = null;
		int totalComment = 0;
		
		try {
			CommentDao commentDao = CommentDao.getInstance();
			Connection conn = ConnectionProvider.getConnection();
			int start = (currentPage - 1) * COMMENT_PER_PAGE;
			comments = commentDao.select(conn, foodId, start, COMMENT_PER_PAGE);
			totalComment = commentDao.getCommentCount(conn, foodId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new CommentPage(currentPage, totalComment, COMMENT_PER_PAGE, PAGE_BLOCK, comments);
	}
}
