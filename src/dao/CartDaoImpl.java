package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import dto.Cart;
import dto.Item;
import dto.User;
import util.JdbcUtil;

public class CartDaoImpl extends JdbcDao implements CartDao {

	ItemDaoImpl itemDao = ItemDaoImpl.getInstance();

	// singleton
	public static CartDaoImpl instance;

	public static CartDaoImpl getInstance() {
		if (instance == null) {
			instance = new CartDaoImpl();
		}
		return instance;
	}

	public List<Cart> getCart(int user_id) {
		List<Cart> cartItems = new ArrayList<Cart>();

		Connection connection = getConnection();
		String sql = "SELECT i.*, c.* FROM " + "item i, user u, cart c WHERE "
		        + "c.user_id = u.id and c.item_id = i.id and u.id = ?";

		try {
			PreparedStatement preparedStatement = connection
			        .prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Cart c = new Cart();
				Item item = itemDao.convertItem(rs);
				c.setItem(item);
				c.setItem_id(item.getId());
				c.setCount(rs.getInt("count"));
				c.setUser_id(user_id);
				c.setAdded(rs.getTimestamp("added"));
				c.setRemoved(rs.getTimestamp("removed"));
                c.setId(rs.getInt("c.id"));
				cartItems.add(c);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cartItems;

		
	}

	public void saveOrUpdate(Cart cart) {
		Connection connection = getConnection();
		Cart exist_cart = getCartByUserIdAndItemId(cart.getUser_id(),
		        cart.getItem_id());
		if (exist_cart == null) {
//			// save
//			String sql = "INSERT INTO `cart` (`user_id`, `item_id`, `count`, `added`, `removed`) VALUES (?, ?, ?, ?, ?)";
//			PreparedStatement preparedStatement;
//			try {
//				preparedStatement = connection.prepareStatement(sql);
//				preparedStatement.setInt(1, cart.getUser_id());
//				preparedStatement.setInt(2, cart.getItem_id());
//				preparedStatement.setInt(3, cart.getCount());
//				preparedStatement.setTimestamp(4, cart.getAdded());
//				preparedStatement.setTimestamp(5, cart.getRemoved());
//
//				preparedStatement.executeUpdate();
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			String sql = "INSERT INTO `cart` (`user_id`, `item_id`, `count`, `added`, `removed`) VALUES (?, ?, ?, ?, ?)";
			try {
				JdbcUtil.getQueryRunner().update(sql,cart.getUser_id(), cart.getItem_id(),cart.getCount(),cart.getAdded(),cart.getRemoved());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		} else {
//			// update
//
//			String sql = "UPDATE `cart` SET `count`=?, `added`=?, `removed`=? WHERE `id`=?";
//
//			PreparedStatement preparedStatement;
//			try {
//				preparedStatement = connection.prepareStatement(sql);
//				preparedStatement.setInt(1, cart.getCount());
//				preparedStatement.setTimestamp(2, cart.getAdded());
//				preparedStatement.setTimestamp(3, cart.getRemoved());
//				preparedStatement.setInt(4, cart.getId());
//
//				preparedStatement.executeUpdate();
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			String sql = "UPDATE `cart` SET `count`=?, `added`=?, `removed`=? WHERE `id`=?";
			try {
				JdbcUtil.getQueryRunner().update(sql,cart.getCount(),cart.getAdded(),cart.getRemoved(), cart.getId());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}

	}

	public void clearCartByUserId(int user_id) {
//		Connection connection = getConnection();
//		String sql = "DELETE from `cart` where user_id = ?";
//
//		try {
//			PreparedStatement preparedStatement = connection
//			        .prepareStatement(sql);
//			preparedStatement.setInt(1, user_id);
//			preparedStatement.executeUpdate();
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		String sql = "DELETE from `cart` where user_id = ?";
		try {
			JdbcUtil.getQueryRunner().update(sql,user_id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}



	public Cart getCartByUserIdAndItemId(int user_id, int item_id) {

		Connection connection = getConnection();
		String sql = "SELECT i.*, c.* FROM "
		        + "item i, user u, cart c where "
		        + "c.user_id = u.id and c.item_id = i.id and u.id = ? and i.id = ? and c.removed is NULL ";

		try {
			PreparedStatement preparedStatement = connection
			        .prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			preparedStatement.setInt(2, item_id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Cart c = new Cart();
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

	public Cart getCartById(int id) {

		Connection connection = getConnection();
		String sql = "SELECT i.*, c.* FROM "
				+ "item i, user u, cart c where "
				+ "c.user_id = u.id and c.item_id = i.id and c.id = ? and c.removed is NULL ";

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Cart rsCart = convertCart(rs);
				connection.close();
                return rsCart;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

    public void deletCart(int id){
//        Connection connection = getConnection();
//        String sql = "DELETE from `cart` where id = ?";
//
//        try {
//            PreparedStatement preparedStatement = connection
//                    .prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//			connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    	String sql = "DELETE from `cart` where id = ?";
    	try {
			JdbcUtil.getQueryRunner().update(sql,id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    	
    }

	private Cart convertCart(ResultSet rs) throws SQLException{
        Cart c = new Cart();
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
