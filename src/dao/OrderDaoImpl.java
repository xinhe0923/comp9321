package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dto.Item;
import dto.Order;
import util.JdbcUtil;

public class OrderDaoImpl extends JdbcDao implements OrderDao {

	// singleton
	public static OrderDaoImpl instance;

	public static OrderDaoImpl getInstance() {
		if (instance == null) {
			instance = new OrderDaoImpl();
		}
		return instance;
	}

	@Override
	public void saveOrUpdate(Order order) {

		Connection connection = getConnection();

		if (order.getId() > 0) {
			// update

//			String sql = "UPDATE `order` SET `user_id` = ?, `created` = ? WHERE `id`=?";
//			PreparedStatement preparedStatement;
//			try {
//				preparedStatement = connection.prepareStatement(sql);
//				preparedStatement.setInt(1, order.getUser_id());
//				preparedStatement.setTimestamp(2, order.getCreated());
//				preparedStatement.setInt(3, order.getId());
//				preparedStatement.executeUpdate();
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			
			String sql = "UPDATE `order` SET `user_id` = ?, `created` = ? WHERE `id`=?";
			try {
				JdbcUtil.getQueryRunner().update(sql,order.getUser_id(),order.getCreated(),order.getId());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		} else {
			// save
			String sql = "INSERT INTO `order` (`user_id`) VALUES (?)";
			PreparedStatement preparedStatement;
			try {
				preparedStatement = connection.prepareStatement(sql,
				        PreparedStatement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, order.getUser_id());
				preparedStatement.executeUpdate();

				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					order.setId(id);
				}
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public List<Order> getAllOrderByUserId(int user_id) {
//		List<Order> resultList = new ArrayList<Order>();
//		Connection connection = getConnection();
//		String sql = "SELECT * from `order` where user_id = ?";
//
//		try {
//			PreparedStatement preparedStatement = connection
//			        .prepareStatement(sql);
//			preparedStatement.setInt(1, user_id);
//			ResultSet rs = preparedStatement.executeQuery();
//
//			while (rs.next()) {
//				Order o = new Order();
//				o.setId(rs.getInt("id"));
//				o.setUser_id(rs.getInt("user_id"));
//				o.setCreated(rs.getTimestamp("created"));
//				resultList.add(o);
//			}
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return resultList;
		List<Order> resultList = new ArrayList<Order>();
		String sql = "SELECT * from `order` where user_id = ?";
		try {
			resultList=JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<Order>(Order.class),user_id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultList;
	}

	@Override
	public Order getOrderByOrderId(int id) {

//		Connection connection = getConnection();
//		String sql = "SELECT * from `order` where id = ?";
//
//		try {
//			PreparedStatement preparedStatement = connection
//			        .prepareStatement(sql);
//			preparedStatement.setInt(1, id);
//			ResultSet rs = preparedStatement.executeQuery();
//
//			if (rs.next()) {
//				Order o = new Order();
//				o.setId(rs.getInt("id"));
//				o.setUser_id(rs.getInt("user_id"));
//				o.setCreated(rs.getTimestamp("created"));
//				connection.close();
//				return o;
//			}
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;

		Order o = new Order();
		String sql = "SELECT * from `order` where id = ?";
		try {
			o =JdbcUtil.getQueryRunner().query(sql, new BeanHandler<Order>(Order.class),id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return o;

	}

}
