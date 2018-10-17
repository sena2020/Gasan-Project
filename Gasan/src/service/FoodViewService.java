package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.FoodDao;
import jdbc.connector.ConnectionProvider;
import model.Food;

public class FoodViewService {
	private static FoodViewService instance = new FoodViewService();

	private FoodViewService() {
	}

	public static FoodViewService getInstance() {
		return instance;
	}

	public Food getFood(int foodId) {
		FoodDao foodDao = FoodDao.getInstance();
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			conn.setAutoCommit(false);
			foodDao.increaseViews(conn, foodId);
			Food food = foodDao.selectByFoodId(conn, foodId);
			conn.commit();
			return food;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
