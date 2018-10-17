package service;

import java.util.Map;

import model.ArticleFoodApply;

public class FoodApplyModifyRequest {

	private int applyId;
	private String loginId;
	private String title;
	private String content;

	public FoodApplyModifyRequest(int applyId, String loginId, String title, String content) {
		this.applyId = applyId;
		this.loginId = loginId;
		this.title = title;
		this.content = content;
	}

	public int getApplyId() {
		return applyId;
	}

	public void setApplyId(int applyId) {
		this.applyId = applyId;
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
	
	public ArticleFoodApply getFoodApply() {
		return new ArticleFoodApply(applyId, loginId, title, content);
	}

}
