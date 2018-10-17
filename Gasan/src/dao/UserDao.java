package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;



public class UserDao {
	private static UserDao instance = new UserDao();
	private UserDao() {}
	public static UserDao getInstance() {
		return instance;
	}
	
	// 회원 가입시 필요한 쿼리 메소드를 작성.
		// insert(등록을 위해서),select(중복체크를 위해서)
		// loginId가 중복인가?를 확인하려면 당연히 loginId 값이 전달되야 함.
		// loginId를 넣고 user객체를 반환.
		public User selectByLoginId(Connection conn, String loginId) throws SQLException { // ++ 여기서 user가 중복되었을때 처리할 소스도 필요
			String sql = "select*from user where loginId=?";

			try (PreparedStatement pst = conn.prepareStatement(sql);) {
				pst.setString(1, loginId);
				User user = null;

				// 결과 값을 받아와야함. 결과값은 ResultSet에 받음.
				// 그 아이 역시 마찬가지로 close를 해줘야함.

				try (ResultSet rs = pst.executeQuery();) {

					if (rs.next()) {

						// 생성자에 넣어준 것.
						user = new User(rs.getInt("userId"), rs.getString("loginId"), rs.getString("nickname"),
								rs.getString("password"));
					}

				}
				return user;
			}

		}
		
		// 안에 내용물이 있는 user객체를 받아서 데이터베이스에 user를 삽입.
		public void insert(Connection conn, User user) throws SQLException {// conn 어떤 디비에 쿼리를 받아야할지 정보가 필요하기 때문에 정보가 필요한
																			// 객체를 받아와야 함.

			String sql = "insert into user(loginId,nickname,password)" // ID는 auto므로 굳이 안 써도 됨.
					+ "value(?,?,?)";
			try (PreparedStatement pst = conn.prepareStatement(sql);) {
				pst.setString(1, user.getLoginId());
				pst.setString(2, user.getNickname());
				pst.setString(3, user.getPassword());
				pst.executeUpdate(); // 쿼리 날려주기. 주의 !!executeQuery는 select에서 사용하는 것임. Update랑 헷갈리면 안됨!!

			}
		}
		
		// 사용자 정보 수정
		public void update(Connection conn, User user) throws SQLException {

			// 여기서는 실질적으로 수정하는 부분만 씀
			String sql = "update user set nickname =?, password =? where userId  = ?";
			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				pst.setString(1, user.getNickname());
				pst.setString(2, user.getPassword());
				pst.setInt(3, user.getUserId());
				pst.executeUpdate();

			}

		}
		
		public void delete(Connection conn, String loginId) throws SQLException {
			String sql = "delete from user where loginId = ?";
			
			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				pst.setString(1, loginId);
				pst.executeUpdate();
			}
		}
	
}
