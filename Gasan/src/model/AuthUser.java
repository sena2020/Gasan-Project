package model;

public class AuthUser {
	private int userId;
	private String loginId;
	private String nickname;
	
	public AuthUser(int userId, String loginId, String nickname) {
		this.userId = userId;
		this.loginId = loginId;
		this.nickname = nickname;
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
	
	
}
