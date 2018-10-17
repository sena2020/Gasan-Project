package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.ArticleFoodApply;

public class FoodApplyDao {
	private static FoodApplyDao instance = new FoodApplyDao();

	private FoodApplyDao() {
	}

	public static FoodApplyDao getInstance() {
		return instance;
	}
	
	public void increaseViews(Connection conn, int applyId) {
		String sql = "update foodApply set views = views + 1 where applyId = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, applyId);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArticleFoodApply select(Connection conn, int applyId) {
		ArticleFoodApply foodapply = null;
		String sql = "select * from foodapply where applyId = ?";

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, applyId);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					String loginId = rs.getString("loginId");
					String title = rs.getString("title");
					String content = rs.getString("content");
					LocalDateTime wdate = rs.getTimestamp("wdate").toLocalDateTime();
					int views = rs.getInt("views");
					
					foodapply = new ArticleFoodApply(applyId, loginId, title, content, wdate, views);
				
				} else {
					throw new RuntimeException();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return foodapply;
	}
	
	// 게시글 전체 갯수를 가져오는 메소드 작성

	public int selectCount(Connection conn) throws SQLException {

		String sql = "select count(*) from foodapply";

		// 결과값을 받아와야하니까 resultSet이 필요함.
		try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
			// rs객체는 처음에 0번째를 가리킨다. 그렇다는건 첫번째 row가 아니라 아무것도 없는 0번째를 가리킴
			// 첫번째 rs에서 rs.getString()을 하면 아무것도 나오지않는다.
			// rs.next()는 0번째에서 1번째를 가리키가 하는 메소드이다.
			// 따라서 첫번째 줄을 가리키게 되고 그래야 getString등을 했을 때 원하는 데이타를 가져온다.
			// rs.next()는 데이타로우를 가리키게 하기 위함

			// rs.getInt(1) 에서 숫자 1은 첫번째 컬럼을 의미한다.
			// 즉,컬럼의 위치 숫자이다.
			if (rs.next()) {

				return rs.getInt(1);

			}

			return 0;// if해서 값이 없으면 없는거니까 0을 리턴해줌.

		}
	}
	
	// 리미트를 이용한 리스트를 가져오는 쿼리

	public List<ArticleFoodApply> select(Connection conn, int startRow, int size) throws SQLException {// 어디서부터 가져올지,몇 개를 가져올지

		String sql = "select*from foodapply order by applyId desc limit ?,?";

		try (PreparedStatement pst = conn.prepareStatement(sql)) {

			pst.setInt(1, startRow);
			pst.setInt(2, size);

			try (ResultSet rs = pst.executeQuery()) {// 질문질문 ㅜㅜ

				List<ArticleFoodApply> artList = new ArrayList<ArticleFoodApply>();

				while (rs.next()) {
					artList.add(convArticle(rs));
				}
				return artList;
			}

		}

	}

	// 게시글 번호로 특정 게시글을 가져오는 메소드
	public ArticleFoodApply selectById(Connection conn, int no) throws SQLException {

		// articleId를 통해 특정 게시물을 가져오도록 함.
		String sql = "select*from foodapply where applyId = ?";

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, no);

			try (ResultSet rs = pst.executeQuery()) {
				ArticleFoodApply article = null;

				if (rs.next()) {
					// 하단에 만든 ResultSet으로 나온 결과를 article 객체로 생성해서 담는 메소드 이용
					article = convArticle(rs);
					// 결과가 있을 시에는 article을 반환. 없을 시에는 null 반환
				}
				return article;

			}
		}

	}
	
	//조회수를 올리는 메소드
	public void increaseReadCount(Connection conn,int applyId) throws SQLException {
		String sql = "update foodapply set views = views+1 where applyId = ?";
		
		try(PreparedStatement pst=conn.prepareStatement(sql)){
			pst.setInt(1, applyId);
			pst.executeUpdate();
		}
	}
	

	public void update(Connection conn, ArticleFoodApply foodapply) {
		String sql = "update foodapply set loginId = ?, title = ?, content = ? where applyId = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, foodapply.getLoginId());
			pst.setString(2, foodapply.getTitle());
			pst.setString(3, foodapply.getContent());
			pst.setInt(4, foodapply.getApplyId());

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Connection conn, int applyId) {
		String sql = "delete from foodapply where applyId = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, applyId);

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArticleFoodApply insert(Connection conn, ArticleFoodApply articleFood) throws SQLException {

		String sql = "insert into foodapply(loginId,title,content) values(?,?,?)";

		try (PreparedStatement pst = conn.prepareStatement(sql); Statement st = conn.createStatement()) {
			pst.setString(1, articleFood.getLoginId());
			pst.setString(2, articleFood.getTitle());
			pst.setString(3, articleFood.getContent()); // 왜?? 
			
			pst.executeUpdate(); // ???

			return articleFood;
		}
		// 왜 여기있던 null이 이동?? ㅇㅅㅇ??
	}
	// ResultSet으로 나온 결과를 article 객체로 생성해서 담는 메소드
	private ArticleFoodApply convArticle(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub

		ArticleFoodApply article = new ArticleFoodApply(rs.getInt("applyId"),
				rs.getString("loginId"),
				rs.getString("title"),
				rs.getString("content"),
				rs.getTimestamp("wdate").toLocalDateTime(),
				rs.getInt("views"));
		return article;
	}

}