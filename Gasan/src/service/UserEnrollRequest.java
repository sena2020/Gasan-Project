package service;

import java.util.Map;

public class UserEnrollRequest {

	private String loginId;
	private String nickname;
	private String password;
	private String confirmPassword;
	
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	//패스워드가 같은지 확인하는 메소드
	public boolean isEqPassword() {
		return password != null && password.equals(confirmPassword);
	}
	
		// 입력받은 데이터가 비어있는지 확인하는 메소드
		// 결과를 보여줄 에러맵, 값, 폼 필드 이름을 인자로 받는 메소드.
		public void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
			if (value == null || value.isEmpty()) {// value가 null이거나 비어있다면
				// 비거나 문제가 있디면 그 결과를 화면으로 다시 전송하기 위해서
				// 객체에 담아서 결과 화면에 보내줌

				// 어떤 필드가 비었는지 필드 네임을 넣고 에러에 true를 넣음
				errors.put(fieldName, true);//fieldName는 화면에서 출력하기 위한 변수명이라 생각하면 됨.
				//에러라는 맵에 값이 없을때

			}
		}

		// 입력받은 데이터가 제대로 들어왔는지 검사하는 메소드
		public void validate(Map<String, Boolean> errors) {

			// 입력 받은 값이 비어있는게 있는지 확인해서 있다면 errors true를 넣음.
			checkEmpty(errors, loginId, "loginId");
			checkEmpty(errors, nickname, "nickname");
			checkEmpty(errors, password, "password");
			checkEmpty(errors, confirmPassword, "confirmPassword");

			// 패스워드 확인 부분이 입력되어 있다면
			if (!errors.containsKey("confirmPassword")) {// confirmPassword가 에러에 없다면.즉, 입력을 받은 상태이긴 하다면???
				// 두 개의 패스워드를 비교
				if (!isEqPassword()) {//같지 않다면
					errors.put("notMatch", true);// 필드 네임이 아니니까 새로운걸로 채워줌.
				}

			}
		}

	}
