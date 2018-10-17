package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.UserDao;
import exception.LoginFailException;
import jdbc.connector.ConnectionProvider;
import model.AuthUser;
import model.User;



public class LoginService {
	private static LoginService instance = new LoginService();
	private LoginService() {}
	public static LoginService getInstance() {
		return instance;
	}
	
	// 성공시 AuthUser 객체(클래스)를 반환시켜주는 메소드
		public AuthUser login(String loginId, String password) throws SQLException, LoginFailException {

			// 얘를 이용해서
			UserDao userDao = UserDao.getInstance();

			try (Connection conn = ConnectionProvider.getConnection()) {

				// 아이디를 통해 사용자 객체를 select해서 가져옴.
				User user = userDao.selectByLoginId(conn, loginId);

				// 사용자가 없다면!(아이디가 디비에 없을 시)
				if (user == null) {
					throw new LoginFailException("없는 사용자라능");// 객체가 없으면 없는 사용자라고 예외를 날림.
				}

				// 객체가 있으면, 비밀번호를 비교하고
				// 비밀번호랑 아이디가 맞는지
				if (!user.matchPassword(password)) { // 비밀번호 일치를 체크하는 메소드 만들었었음.
					throw new LoginFailException("비밀번호가 틀렸음 ㅇㅅㅇ");
				}

				// 위의 사항들을 넘어간다면 인증이 완료되었으니 auth객체를 반환
				return new AuthUser(user.getUserId(), user.getLoginId(), user.getNickname());
			}
			// 아이디를 통해 사용자 객체를 select해서 가져옴.
			// 객체가 있으면, 비밀번호를 비교하고
			// 객체가 없으면 없는 사용자라고 예외를 날림.

		}
}
