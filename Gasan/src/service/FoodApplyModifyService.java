package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.FoodApplyDao;
import jdbc.connector.ConnectionProvider;
import model.ArticleFoodApply;

public class FoodApplyModifyService {
	public static FoodApplyModifyService getInstance() {
		return instance;
	}

	private static FoodApplyModifyService instance = new FoodApplyModifyService();

	private FoodApplyModifyService() {
	}

	public void modify(FoodApplyModifyRequest foodApply) {
		try {
			FoodApplyDao foodApplyDao = FoodApplyDao.getInstance();
			Connection conn = ConnectionProvider.getConnection();
			foodApplyDao.update(conn, foodApply.getFoodApply());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public ArticleFoodApply getArticle(int applyId) {
		ArticleFoodApply foodApply = null;
		
		try {
			FoodApplyDao foodApplyDao = FoodApplyDao.getInstance();
			Connection conn = ConnectionProvider.getConnection();
			foodApply = foodApplyDao.select(conn, applyId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return foodApply;
	}
}
