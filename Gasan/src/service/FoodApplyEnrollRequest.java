package service;

import java.util.Map;

public class FoodApplyEnrollRequest {

	private String loginId;
	private String title;
	private String content;

	public FoodApplyEnrollRequest(String loginId, String title, String content) {

		this.loginId = loginId;
		this.title = title;
		this.content = content;
	}

	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void validate(Map<String, Boolean> errors) {

		if (title == null || title.trim().isEmpty()) {// trim 다시 질문
			errors.put("title", true);
		} else if(title.length() > 20) {
			errors.put("titleSize", true);
		} else if(content == null || content.trim().isEmpty()) {
			errors.put("content", true);
		}

	}

}
