package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CommentDao;
import jdbc.connector.ConnectionProvider;
import model.Comment;

public class CommentViewService {
	private static CommentViewService instance = new CommentViewService();
	private CommentViewService() {}
	public static CommentViewService getInstance() {
		return instance;
	}
	
	public Comment select(int commentId) {
		CommentDao commentDao = CommentDao.getInstance();
		try (Connection conn = ConnectionProvider.getConnection()) {
			return commentDao.select(conn, commentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
