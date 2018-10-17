package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Comment;

public class CommentDao {
	private static CommentDao instance = new CommentDao();

	private CommentDao() {
	}

	public static CommentDao getInstance() {
		return instance;
	}

	public int getCommentCount(Connection conn, int foodId) {
		String sql = "select count(*) reviewCount from comment where foodId = ?";

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, foodId);
			try (ResultSet rs = pst.executeQuery()) {
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public Comment select(Connection conn, int commentId) {
		String sql = "select * from comment where commentId = ?";
		Comment comment = null;

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, commentId);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					comment = createComment(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comment;
	}

	public List<Comment> select(Connection conn, int foodId, int start, int cnt) {
		String sql = "select * from comment where foodId = ? order by commentId desc limit ?, ?";
		List<Comment> comments = new ArrayList<>();

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, foodId);
			pst.setInt(2, start);
			pst.setInt(3, cnt);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					comments.add(createComment(rs));
				}
			}
		} catch (SQLException e) {
			comments = Collections.emptyList();
			e.printStackTrace();
		}

		return comments;
	}

	public void insert(Connection conn, Comment comment) {
		String sql = "insert into comment(foodId, loginId, comm, score) values(?, ?, ?, ?)";

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, comment.getFoodId());
			pst.setString(2, comment.getLoginId());
			pst.setString(3, comment.getComm());
			pst.setInt(4, comment.getScore());

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Connection conn, int commentId) {
		String sql = "delete from comment where commentId = ?";

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, commentId);

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Comment createComment(ResultSet rs) throws SQLException {
		int commentId = rs.getInt("commentId");
		int foodId = rs.getInt("foodId");
		String loginId = rs.getString("loginId");
		String comm = rs.getString("comm");
		int score = rs.getInt("score");
		LocalDateTime udate = rs.getTimestamp("udate").toLocalDateTime();

		return new Comment(commentId, foodId, loginId, comm, score, udate);
	}
}
