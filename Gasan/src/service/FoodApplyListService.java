package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.FoodApplyDao;
import jdbc.connector.ConnectionProvider;
import model.ArticleFoodApply;


public class FoodApplyListService {


		private static FoodApplyListService instance = new FoodApplyListService();

		private FoodApplyListService() {
		}

		public static FoodApplyListService getInstance() {
			return instance;
		}

		private int size = 10;
		private int blockSize = 5;

		public ArticlePage getArticlePage(int pageNum) {

			try (Connection conn = ConnectionProvider.getConnection()) {

				FoodApplyDao articleDao = FoodApplyDao.getInstance();
				int total = articleDao.selectCount(conn);

				List<ArticleFoodApply> artList = articleDao.select(conn, (pageNum - 1) * size, size); // 페이지가 1일때 0이 되므로 0*10은 결국0이므로

				return new ArticlePage(artList, pageNum, total, size, blockSize); // 에에~?혼또니?
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}

		public ArticlePage getArticlePageForMain() {

			try (Connection conn = ConnectionProvider.getConnection()) {

				FoodApplyDao articleDao = FoodApplyDao.getInstance();
				int total = articleDao.selectCount(conn);

				List<ArticleFoodApply> artList = articleDao.select(conn, 0, 3); // 페이지가 1일때 0이 되므로 0*10은 결국0이므로

				return new ArticlePage(artList, 1, total, 3, 1); // 에에~?혼또니?
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}

	}
