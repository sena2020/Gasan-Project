package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.FoodApplyDao;
import jdbc.connector.ConnectionProvider;
import model.ArticleFoodApply;

public class FoodApplyDeleteService {
	public static FoodApplyDeleteService getInstance() {
		return instance;
	}

	private static FoodApplyDeleteService instance = new FoodApplyDeleteService();

	private FoodApplyDeleteService() {}

	public void delete(int applyId) {
		try {
			Connection conn = ConnectionProvider.getConnection();
			FoodApplyDao foodApplyDao = FoodApplyDao.getInstance();
			conn.setAutoCommit(false);
			
			ArticleFoodApply foodApply = foodApplyDao.select(conn, applyId);
			
			if(foodApply == null) {
				throw new RuntimeException();
			}
			
			foodApplyDao.delete(conn, applyId);
			
			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
