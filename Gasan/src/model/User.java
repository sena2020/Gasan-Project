package model;

public class User {
	private int userId;
	private String loginId;
	private String nickname;
	private String password;
	
	public User(int userId, String loginId, String nickname, String password) {
		this.userId = userId;
		this.loginId = loginId;
		this.nickname = nickname;
		this.password = password;
	}
	
	public User(String loginId, String nickname, String password) {
		this.loginId = loginId;
		this.nickname = nickname;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	//입력받은 데이터와 필드의 비밀번호가 같은지 판단하는 메소드.
	public boolean matchPassword(String pwd) {
		return password.equals(pwd);
	}
	
}
