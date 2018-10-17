package model;

import java.time.LocalDateTime;

public class Comment {
	private int commentId;
	private int foodId;
	private String loginId;
	private String comm;
	private int score;
	private LocalDateTime udate;
	
	public Comment(int commentId, int foodId, String loginId, String comm, int score,
			LocalDateTime udate) {
		this.commentId = commentId;
		this.foodId = foodId;
		this.loginId = loginId;
		this.comm = comm;
		this.score = score;
		this.udate = udate;
	}

	public Comment(int foodId, String loginId, String comm, int score) {
		this.foodId = foodId;
		this.loginId = loginId;
		this.comm = comm;
		this.score = score;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public LocalDateTime getUdate() {
		return udate;
	}

	public void setUdate(LocalDateTime udate) {
		this.udate = udate;
	}

}
