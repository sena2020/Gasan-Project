package model;

import java.time.LocalDateTime;

public class Food {
	private int foodId;
	private String restName;
	private String addr;
	private String restHour;
	private String site;
	private String category;
	private LocalDateTime rdate;
	private int views;
	private String tel;
	private double pointerX;
	private double pointerY;
	private float scoreAvr;
	private int reviewCount;

	public Food(int foodId, String restName, String addr, String restHour, String site, String category,
			LocalDateTime rdate, int views, String tel, double pointerX, double pointerY, float scoreAvr) {
		this.foodId = foodId;
		this.restName = restName;
		this.addr = addr;
		this.restHour = restHour;
		this.site = site;
		this.category = category;
		this.rdate = rdate;
		this.views = views;
		this.tel = tel;
		this.pointerX = pointerX;
		this.pointerY = pointerY;
		this.scoreAvr = scoreAvr;
	}
	
	public float getScoreAvr() {
		return scoreAvr;
	}

	public void setScoreAvr(float scoreAvr) {
		this.scoreAvr = scoreAvr;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getRestHour() {
		return restHour;
	}

	public void setRestHour(String restHour) {
		this.restHour = restHour;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDateTime getRdate() {
		return rdate;
	}

	public void setRdate(LocalDateTime rdate) {
		this.rdate = rdate;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public double getPointerX() {
		return pointerX;
	}

	public void setPointerX(double pointerX) {
		this.pointerX = pointerX;
	}

	public double getPointerY() {
		return pointerY;
	}

	public void setPointerY(double pointerY) {
		this.pointerY = pointerY;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
}
