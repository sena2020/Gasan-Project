package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CommentDao;
import jdbc.connector.ConnectionProvider;

public class CommentDeleteService {
	private static CommentDeleteService instance = new CommentDeleteService();
	private CommentDeleteService() {}
	public static CommentDeleteService getInstance() {
		return instance;
	}
	
	public void delete(int commentId) {
		CommentDao commentDao = CommentDao.getInstance();
		try (Connection conn = ConnectionProvider.getConnection()) {
			commentDao.delete(conn, commentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
