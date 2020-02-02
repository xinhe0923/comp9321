package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.WishList;
import dto.Item;
import dto.User;

public class WishListDaoImpl extends JdbcDao implements WishListDao {

	ItemDaoImpl itemDao = ItemDaoImpl.getInstance();

	// singleton
	public static WishListDaoImpl instance;

	public static WishListDaoImpl getInstance() {
		if (instance == null) {
			instance = new WishListDaoImpl();
		}
		return instance;
	}

	public List<WishList> getWishList(int user_id) {
		List<WishList> WishListItems = new ArrayList<WishList>();

		Connection connection = getConnection();
		String sql = "SELECT i.*, c.* FROM " + "item i, user u, wish c WHERE "
		        + "c.user_id = u.id and c.item_id = i.id and u.id = ?";

		try {
			PreparedStatement preparedStatement = connection
			        .prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				WishList c = new WishList();
				Item item = itemDao.convertItem(rs);
				c.setItem(item);
				c.setItem_id(item.getId());
				c.setCount(rs.getInt("count"));
				c.setUser_id(user_id);
				c.setAdded(rs.getTimestamp("added"));
				c.setRemoved(rs.getTimestamp("removed"));
                c.setId(rs.getInt("c.id"));
				WishListItems.add(c);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return WishListItems;
	}

	public void saveOrUpdate(WishList WishList) {
		Connection connection = getConnection();
		WishList exist_WishList = getWishListByUserIdAndItemId(WishList.getUser_id(),
		        WishList.getItem_id());
		if (exist_WishList == null) {
			// save
			String sql = "INSERT INTO `wish` (`user_id`, `item_id`, `count`, `added`, `removed`) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement;
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, WishList.getUser_id());
				preparedStatement.setInt(2, WishList.getItem_id());
				preparedStatement.setInt(3, WishList.getCount());
				preparedStatement.setTimestamp(4, WishList.getAdded());
				preparedStatement.setTimestamp(5, WishList.getRemoved());

				preparedStatement.executeUpdate();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			// update

			String sql = "UPDATE `wish` SET `count`=?, `added`=?, `removed`=? WHERE `id`=?";

			PreparedStatement preparedStatement;
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, WishList.getCount());
				preparedStatement.setTimestamp(2, WishList.getAdded());
				preparedStatement.setTimestamp(3, WishList.getRemoved());
				preparedStatement.setInt(4, WishList.getId());

				preparedStatement.executeUpdate();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public void clearWishListByUserId(int user_id) {
		Connection connection = getConnection();
		String sql = "DELETE from `wish` where user_id = ?";

		try {
			PreparedStatement preparedStatement = connection
			        .prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	public WishList getWishListByUserIdAndItemId(int user_id, int item_id) {

		Connection connection = getConnection();
		String sql = "SELECT i.*, c.* FROM "
		        + "item i, user u, wish c where "
		        + "c.user_id = u.id and c.item_id = i.id and u.id = ? and i.id = ? and c.removed is NULL ";

		try {
			PreparedStatement preparedStatement = connection
			        .prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			preparedStatement.setInt(2, item_id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				WishList c = new WishList();
				Item item = itemDao.convertItem(rs);
				c.setItem(item);
				c.setItem_id(item.getId());
				c.setCount(rs.getInt("count"));
				c.setUser_id(user_id);
				c.setAdded(rs.getTimestamp("added"));
				c.setRemoved(rs.getTimestamp("removed"));
				c.setId(rs.getInt("c.id"));
				connection.close();
				return c;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public WishList getWishListById(int id) {

		Connection connection = getConnection();
		String sql = "SELECT i.*, c.* FROM "
				+ "item i, user u, wish c where "
				+ "c.user_id = u.id and c.item_id = i.id and c.id = ? and c.removed is NULL ";

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				WishList rsWishList = convertWishList(rs);
				connection.close();
                return rsWishList;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

    public void deletWishList(int id){
        Connection connection = getConnection();
        String sql = "DELETE from `wish` where id = ?";

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
			connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	private WishList convertWishList(ResultSet rs) throws SQLException{
        WishList c = new WishList();
        Item item = itemDao.convertItem(rs);
        c.setItem(item);
        c.setItem_id(item.getId());
        c.setCount(rs.getInt("count"));
        c.setUser_id(rs.getInt("user_id"));
        c.setAdded(rs.getTimestamp("added"));
        c.setRemoved(rs.getTimestamp("removed"));
        c.setId(rs.getInt("c.id"));
        return c;
    }

}
