package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.FoodApplyDao;
import jdbc.connector.ConnectionProvider;
import model.ArticleFoodApply;

public class FoodApplyReadService {
	private static FoodApplyReadService instance = new FoodApplyReadService();

	private FoodApplyReadService() {
	}

	public static FoodApplyReadService getInstance() {
		return instance;
	}
	
	public ArticleFoodApply getFoodApply(int applyId) {
		FoodApplyDao applyDao = FoodApplyDao.getInstance();
		ArticleFoodApply foodApply = null;
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			conn.setAutoCommit(false);
			applyDao.increaseReadCount(conn, applyId);
			foodApply = applyDao.select(conn, applyId);
			foodApply.getContent().replaceAll("\n", "<br>");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return foodApply;
	}
}
