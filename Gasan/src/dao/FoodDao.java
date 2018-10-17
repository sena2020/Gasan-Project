package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Food;

public class FoodDao {
	private static FoodDao instance = new FoodDao();

	private FoodDao() {
	}

	public static FoodDao getInstance() {
		return instance;
	}

	public int countAll(Connection conn) {
		String sql = "select count(*) from food";
		try (Statement st = conn.createStatement()) {
			try (ResultSet rs = st.executeQuery(sql)) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int countByCategory(Connection conn, String category) {
		String sql = "select count(*) from food where category = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, category);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int countByName(Connection conn, String restName) {
		String sql = "select count(*) from food where restName = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, restName);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Food selectByFoodId(Connection conn, int foodId) {
		String sql = "select *, round(avg(score), 1) scoreAvr from food f left outer join comment c on f.foodId = c.foodId group by f.foodId having f.foodId = ?";
		Food food = null;

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, foodId);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					food = createFood(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return food;
	}

	public List<Food> selectAll(Connection conn, int start, int cnt) {
		String sql = "select *, round(avg(score), 1) scoreAvr from food f left outer join comment c on f.foodId = c.foodId group by f.foodId order by f.foodId desc limit ?, ?";
		List<Food> foods = new ArrayList<>();

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, start);
			pst.setInt(2, cnt);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					foods.add(createFood(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			foods = Collections.emptyList();
		}
		return foods;
	}

	public List<Food> selectByScore(Connection conn, int start, int cnt, String category) {
		String sql = "select *, round(avg(score), 1) scoreAvr from food f left outer join comment c on f.foodId = c.foodId group by f.foodId having category = ? order by scoreAvr desc limit ?, ?";
		List<Food> foods = new ArrayList<>();

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			if(category == null || category.isEmpty()) {
				pst.setInt(1, 0);
			} else {
				pst.setString(1, category);
			}
			pst.setInt(2, start);
			pst.setInt(3, cnt);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					foods.add(createFood(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			foods = Collections.emptyList();
		}
		return foods;
	}

	public List<Food> selectByViews(Connection conn, int start, int cnt, String category) {
		String sql = "select *, round(avg(score), 1) scoreAvr from food f left outer join comment c on f.foodId = c.foodId group by f.foodId having category = ? order by views desc limit ?, ?";
		List<Food> foods = new ArrayList<>();

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			if(category == null || category.isEmpty()) {
				pst.setInt(1, 0);
			} else {
				pst.setString(1, category);
			}
			pst.setInt(2, start);
			pst.setInt(3, cnt);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					foods.add(createFood(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			foods = Collections.emptyList();
		}
		return foods;
	}

	public List<Food> selectByReviewCount(Connection conn, int start, int cnt, String category) {
		String sql = "select *, round(avg(score), 1) scoreAvr, count(*) reviewCount from food f left outer join comment c on f.foodId = c.foodId group by f.foodId having category = ? order by reviewCount desc limit ?, ?";
		List<Food> foods = new ArrayList<>();

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			if(category == null || category.isEmpty()) {
				pst.setInt(1, 0);
			} else {
				pst.setString(1, category);
			}
			pst.setInt(2, start);
			pst.setInt(3, cnt);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					foods.add(createFood(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			foods = Collections.emptyList();
		}
		return foods;
	}

	public List<Food> selectById(Connection conn, int start, int cnt, String category) {
		String sql = "select *, round(avg(score), 1) scoreAvr, count(*) reviewCount from food f left outer join comment c on f.foodId = c.foodId group by f.foodId having category = ? order by f.foodId desc limit ?, ?";
		List<Food> foods = new ArrayList<>();

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			if(category == null || category.isEmpty()) {
				pst.setInt(1, 0);
			} else {
				pst.setString(1, category);
			}
			pst.setInt(2, start);
			pst.setInt(3, cnt);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					foods.add(createFood(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			foods = Collections.emptyList();
		}
		return foods;
	}

	public List<Food> selectByCategory(Connection conn, int start, int cnt, String category) {
		String sql = "select *, round(avg(score), 1) scoreAvr from food f left outer join comment c on f.foodId = c.foodId group by f.foodId having category = ? order by f.foodId desc limit ?, ?";
		List<Food> foods = new ArrayList<>();

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, category);
			pst.setInt(2, start);
			pst.setInt(3, cnt);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					foods.add(createFood(rs));
				}
			}
		} catch (SQLException e) {
			foods = Collections.emptyList();
		}

		return foods;
	}

	public List<Food> selectByName(Connection conn, int start, int cnt, String name) {
		String sql = "select *, replace(restName, ' ', '') searchName, round(avg(score), 1) scoreAvr from food f left outer join comment c on f.foodId = c.foodId group by f.foodId having searchName like ? order by f.foodId desc limit ?, ?";
		List<Food> foods = new ArrayList<>();

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, "%" + name + "%");
			pst.setInt(2, start);
			pst.setInt(3, cnt);
			System.out.println(pst);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					foods.add(createFood(rs));
				}
			}
		} catch (SQLException e) {
			foods = Collections.emptyList();
		}

		return foods;
	}

	private Food createFood(ResultSet rs) throws SQLException {
		int foodId = rs.getInt("foodId");
		String restName = rs.getString("restName");
		String addr = rs.getString("addr");
		String restHours = rs.getString("restHours");
		String site = rs.getString("site");
		String category = rs.getString("category");
		LocalDateTime rdate = rs.getTimestamp("rdate").toLocalDateTime();
		int views = rs.getInt("views");
		String tel = rs.getString("tel");
		double pointerX = rs.getDouble("pointerX");
		double pointerY = rs.getDouble("pointerY");
		float scoreAvr = rs.getFloat("scoreAvr");

		return new Food(foodId, restName, addr, restHours, site, category, rdate, views, tel, pointerX, pointerY,
				scoreAvr);
	}

	public void increaseViews(Connection conn, int foodId) {
		String sql = "update food set views = views + 1 where foodId = ?";

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, foodId);
			pst.executeUpdate();
		} catch (SQLException e) {
		}

	}
}
