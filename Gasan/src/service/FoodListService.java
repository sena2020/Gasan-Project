package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.FoodDao;
import jdbc.connector.ConnectionProvider;
import model.Food;

public class FoodListService {
	private static FoodListService instance = new FoodListService();

	private FoodListService() {
	}

	public static FoodListService getInstance() {
		return instance;
	}

	private final static int FOOD_PER_PAGE = 6;
	private final static int PAGE_BLOCK = 5;

	public FoodPage getFoodPage(int currentPage, String order) {
		List<Food> foods = null;
		int foodCount = 0;

		try {
			FoodDao foodDao = FoodDao.getInstance();
			Connection conn = ConnectionProvider.getConnection();
			int start = (currentPage - 1) * FOOD_PER_PAGE;

			foodCount = foodDao.countAll(conn);
			foods = foodDao.selectAll(conn, start, FOOD_PER_PAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new FoodPage(currentPage, FOOD_PER_PAGE, foodCount, PAGE_BLOCK, foods, order);
	}

	public FoodPage getFoodPageByCategory(int currentPage, String category, String order) {
		List<Food> foods = null;
		int foodCount = 0;

		try {
			FoodDao foodDao = FoodDao.getInstance();
			Connection conn = ConnectionProvider.getConnection();
			int start = (currentPage - 1) * FOOD_PER_PAGE;

			foodCount = foodDao.countByCategory(conn, category);
			foods = foodDao.selectByCategory(conn, start, FOOD_PER_PAGE, category);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new FoodPage(currentPage, FOOD_PER_PAGE, foodCount, PAGE_BLOCK, foods, order);
	}

	public FoodPage getFoodPageByScore(int currentPage, String category, String order) {
		List<Food> foods = null;
		int foodCount = 0;

		try {
			FoodDao foodDao = FoodDao.getInstance();
			Connection conn = ConnectionProvider.getConnection();
			int start = (currentPage - 1) * FOOD_PER_PAGE;

			foodCount = foodDao.countByCategory(conn, category);
			foods = foodDao.selectByScore(conn, start, FOOD_PER_PAGE, category);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new FoodPage(currentPage, FOOD_PER_PAGE, foodCount, PAGE_BLOCK, foods, order);
	}

	public FoodPage getFoodPageByReviews(int currentPage, String category, String order) {
		List<Food> foods = null;
		int foodCount = 0;

		try {
			FoodDao foodDao = FoodDao.getInstance();
			Connection conn = ConnectionProvider.getConnection();
			int start = (currentPage - 1) * FOOD_PER_PAGE;

			foodCount = foodDao.countByCategory(conn, category);
			foods = foodDao.selectByReviewCount(conn, start, FOOD_PER_PAGE, category);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new FoodPage(currentPage, FOOD_PER_PAGE, foodCount, PAGE_BLOCK, foods, order);
	}

	public FoodPage getFoodPageByViews(int currentPage, String category, String order) {
		List<Food> foods = null;
		int foodCount = 0;

		try {
			FoodDao foodDao = FoodDao.getInstance();
			Connection conn = ConnectionProvider.getConnection();
			int start = (currentPage - 1) * FOOD_PER_PAGE;

			foodCount = foodDao.countByCategory(conn, category);
			foods = foodDao.selectByViews(conn, start, FOOD_PER_PAGE, category);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new FoodPage(currentPage, FOOD_PER_PAGE, foodCount, PAGE_BLOCK, foods, order);
	}

	public FoodPage getFoodPageById(int currentPage, String category, String order) {
		List<Food> foods = null;
		int foodCount = 0;

		try {
			FoodDao foodDao = FoodDao.getInstance();
			Connection conn = ConnectionProvider.getConnection();
			int start = (currentPage - 1) * FOOD_PER_PAGE;

			foodCount = foodDao.countByCategory(conn, category);
			foods = foodDao.selectById(conn, start, FOOD_PER_PAGE, category);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new FoodPage(currentPage, FOOD_PER_PAGE, foodCount, PAGE_BLOCK, foods, order);
	}

	public FoodPage getFoodPageByName(int currentPage, String restName, String order) {
		List<Food> foods = null;
		int foodCount = 0;

		try {
			FoodDao foodDao = FoodDao.getInstance();
			Connection conn = ConnectionProvider.getConnection();
			int start = (currentPage - 1) * FOOD_PER_PAGE;

			foodCount = foodDao.countByName(conn, restName);
			foods = foodDao.selectByName(conn, start, FOOD_PER_PAGE, restName);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new FoodPage(currentPage, FOOD_PER_PAGE, foodCount, PAGE_BLOCK, foods, order);
	}
}
