package model;

import java.time.LocalDateTime;

public class ArticleFoodApply {
	private int applyId;
	private String loginId;
	private String title;
	private String content;
	private LocalDateTime wdate;
	private int views;
	
	public ArticleFoodApply(int applyId, String loginId, String title, String content, LocalDateTime wdate, int views) {
		this.applyId = applyId;
		this.loginId = loginId;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.views = views;
	}
	public ArticleFoodApply(int applyId, String loginId, String title, String content) {
		this.applyId = applyId;
		this.loginId = loginId;
		this.title = title;
		this.content = content;
	}
	
	public ArticleFoodApply(String loginId, String title, String content) {
		this.loginId = loginId;
		this.title = title;
		this.content = content;
	}


	@Override
	public String toString() {
		return "ArticleFoodApply [applyId=" + applyId + ", loginId=" + loginId + ", title=" + title + ", content="
				+ content + ", wdate=" + wdate + ", views=" + views + "]";
	}
	
	public LocalDateTime getWdate() {
		return wdate;
	}
	public void setWdate(LocalDateTime wdate) {
		this.wdate = wdate;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
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
	
}
