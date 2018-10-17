package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CommentDao;
import jdbc.connector.ConnectionProvider;
import model.Comment;

public class CommentEnrollService {
	private static CommentEnrollService instance = new CommentEnrollService();
	private CommentEnrollService() {}
	public static CommentEnrollService getInstance() {
		return instance;
	}
	
	public void enroll(Comment comment) {
		CommentDao commentDao = CommentDao.getInstance();
		try (Connection conn = ConnectionProvider.getConnection()) {
			commentDao.insert(conn, comment);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
