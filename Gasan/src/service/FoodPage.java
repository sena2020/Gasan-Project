package service;

import java.util.List;

import model.Food;

public class FoodPage {

	private int totalFoods;
	private int startPage;
	private int endPage;
	private int currentPage;
	private int totalPages;
	private List<Food> foodList;
	private String order;

	public FoodPage(int currentPage, int FoodPerPage, int totalFoods ,int pageBlock, List<Food> foodList, String order) {
		this.currentPage = currentPage;
		this.foodList = foodList;
		this.totalFoods = totalFoods;
		this.order = order; 

		if (totalFoods == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = totalFoods / FoodPerPage;
			if ((totalFoods % FoodPerPage) > 0) {
				totalPages++;
			}
			
			startPage = currentPage - pageBlock / 2;
			if (startPage < 1) {
				startPage = 1;
			} else if (startPage > totalPages - pageBlock + 1) {
				startPage = totalPages - pageBlock + 1;
			}

			endPage = startPage + pageBlock - 1;
			if (endPage > totalPages) {
				endPage = totalPages;
			}
		}
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getTotalFoods() {
		return totalFoods;
	}

	public void setTotalFoods(int totalFoods) {
		this.totalFoods = totalFoods;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}

}
