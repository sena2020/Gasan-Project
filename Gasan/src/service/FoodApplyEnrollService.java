package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.FoodApplyDao;
import jdbc.connector.ConnectionProvider;
import model.ArticleFoodApply;

public class FoodApplyEnrollService {
	private static FoodApplyEnrollService instance = new FoodApplyEnrollService();
	private FoodApplyEnrollService() {}
	public static FoodApplyEnrollService getInstance() {
		return instance;
	}	
	
	public void write(FoodApplyEnrollRequest wr) {
		FoodApplyDao articleDao = FoodApplyDao.getInstance();

		try (Connection conn = ConnectionProvider.getConnection()) {
			try {
				ArticleFoodApply articleFood = new ArticleFoodApply(wr.getLoginId(), wr.getTitle(), wr.getContent());
				// 게시글의 테이블이 두개가 있고, 두 개의 articleId는 같아야 무결성을 해치지 않음.
				// 따라서 두개의 articleId는 동기화되어야하고
				// article이 삽입 됐을 때 articleId가 생성되기 때문에
				// 삽입 후 select를 하여 articleId를 받아온다.
				// 그리고 그 articleId를 content(내용) 삽입시 사용하여 articleId가 같게 처리함.
				
				articleDao.insert(conn, articleFood);


			} catch (RuntimeException e) {
				conn.rollback();
				throw e;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}