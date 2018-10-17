package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.UserDao;
import exception.UserNotFoundException;
import jdbc.connector.ConnectionProvider;
import model.User;

public class UserDeleteService {
	private static UserDeleteService instance = new UserDeleteService();
	private UserDeleteService() {}
	public static UserDeleteService getInstance() {
		return instance;
	}
	
	public void delete(String loginId) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			UserDao userDao = UserDao.getInstance();
			User user = userDao.selectByLoginId(conn, loginId);
			
			if(user == null) {
				throw new UserNotFoundException("사용자를 찾지 못하였습니다.");
			}
			
			userDao.delete(conn, loginId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
