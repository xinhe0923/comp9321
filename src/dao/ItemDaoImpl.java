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
import util.JdbcUtil;

public class ItemDaoImpl extends JdbcDao implements ItemDao {

	// singleton
	public static ItemDaoImpl instance;

	public static ItemDaoImpl getInstance() {
		if (instance == null) {
			instance = new ItemDaoImpl();
		}
		return instance;
	}

	@Override
	public void saveOrUpdate(Item item) {
		Connection connection = getConnection();

		// if it's new user, the do insert
		if (item.getId() == 0) {
			String sql = "INSERT INTO item (title, authors, type, publication_date, venue, price, paused, ban, quantity, imageURL, seller_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
                 JdbcUtil.getQueryRunner().update(sql,item.getTitle(),item.getAuthors(),item.getType(),item.getPublication_date() != null ? new java.sql.Date(item.getPublication_date().getTime()) : null, item.getVenue(), item.getPrice(),item.isPaused(),item.isBan(),item.getQuantity(),item.getImageURL(),item.getSeller_id());
//				String sql = "INSERT INTO item (title, authors, type, publication_date, venue, price, paused, ban, quantity, imageURL, seller_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//				PreparedStatement preparedStatement = connection
//				        .prepareStatement(sql);
//
//				preparedStatement.setString(1, item.getTitle());
//				preparedStatement.setString(2, item.getAuthors());
//				preparedStatement.setString(3, item.getType());
//				preparedStatement.setDate(4,item.getPublication_date() != null ? new java.sql.Date(item.getPublication_date().getTime()) : null);
//				preparedStatement.setString(5, item.getVenue());
//				preparedStatement.setDouble(6, item.getPrice());
//				preparedStatement.setBoolean(7, item.isPaused());
//				preparedStatement.setBoolean(8, item.isBan());
//				preparedStatement.setInt(9, item.getQuantity());
//				preparedStatement.setString(10, item.getImageURL());
//				preparedStatement.setInt(11, item.getSeller_id());
//
//				preparedStatement.executeUpdate();
//				connection.close();
				
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else {
			try {

				String sql = "UPDATE item SET title=?, authors=?, type=?, publication_date=?, venue=?, price=?, paused=?, ban=?, quantity=?, imageURL=?, seller_id=? WHERE id=?";
				JdbcUtil.getQueryRunner().update(sql,item.getTitle(),item.getAuthors(),item.getType(),item.getPublication_date() != null ? new java.sql.Date(item.getPublication_date().getTime()) : null,item.getVenue(),item.getPrice(),item.isPaused(),item.isBan(),item.getQuantity(),item.getImageURL(),item.getSeller_id(),item.getId());
//				PreparedStatement preparedStatement = connection
//				        .prepareStatement(sql);
//				preparedStatement.setString(1, item.getTitle());
//				preparedStatement.setString(2, item.getAuthors());
//				preparedStatement.setString(3, item.getType());
//				preparedStatement.setDate(4,item.getPublication_date() != null ? new java.sql.Date(item.getPublication_date().getTime()) : null);
//				preparedStatement.setString(5, item.getVenue());
//				preparedStatement.setDouble(6, item.getPrice());
//				preparedStatement.setBoolean(7, item.isPaused());
//				preparedStatement.setBoolean(8, item.isBan());
//				preparedStatement.setInt(9, item.getQuantity());
//				preparedStatement.setString(10, item.getImageURL());
//				preparedStatement.setInt(11, item.getSeller_id());
//				preparedStatement.setInt(12, item.getId());
//
//				preparedStatement.executeUpdate();
//				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@Override
	public Item getItemById(int id) {
//		Connection connection = getConnection();
//		String sql = "SELECT * FROM item WHERE id = ?";
//
//		try {
//			PreparedStatement preparedStatement = connection
//			        .prepareStatement(sql);
//			preparedStatement.setInt(1, id);
//
//			ResultSet rs = preparedStatement.executeQuery();
//			if (rs.next()) {
//				Item item = convertItem(rs);
//				connection.close();
//				return item;
//			}
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return null;
		  System.out.println("11111111111111");
		String sql = "SELECT * FROM item WHERE id = ?";
	try {   
		
		   Item item =JdbcUtil.getQueryRunner().query(sql, new BeanHandler<Item>(Item.class),id);
		   UserDao userDao = new UserDaoImpl();
		   item.setSeller(userDao.getUserById(item.getSeller_id()));
		   return item;

			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	@Override
	public List<Item> getAllItems() {
//		List<Item> items = new ArrayList<Item>();
//
//		Connection connection = getConnection();
//		String sql = "SELECT * FROM item";
//
//		try {
//			PreparedStatement preparedStatement = connection
//			        .prepareStatement(sql);
//			ResultSet rs = preparedStatement.executeQuery();
//
//			while (rs.next()) {
//				Item item = convertItem(rs);
//				items.add(item);
//			}
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return items;
		String sql = "SELECT * FROM item";
		try {
			
            List<Item>items=JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<Item>(Item.class));
            for(int i=1;i<items.size();i++){
            	Item item=items.get(i);
     		    UserDao userDao = new UserDaoImpl();
    		    item.setSeller(userDao.getUserById(item.getSeller_id()));
            	
            }
			return items;
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
	
		
	}

	@Override
	public List<Item> getRandomten() {
//		List<Item> items = new ArrayList<Item>();
//
//		Connection connection = getConnection();
//		String sql = "select * from item WHERE paused = 0 and ban = 0 order by rand() limit 10";
//
//		try {
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);
//			ResultSet rs = preparedStatement.executeQuery();
//
//			while (rs.next()) {
//				Item item = convertItem(rs);
//				items.add(item);
//			}
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return items;


		String sql = "select * from item WHERE paused = 0 and ban = 0 order by rand() limit 10";
		try {
            List<Item>items=JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<Item>(Item.class));
            for(int i=1;i<items.size();i++){
            	Item item=items.get(i);
     		    UserDao userDao = new UserDaoImpl();
    		    item.setSeller(userDao.getUserById(item.getSeller_id()));
            	
            }
			return items;
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
	
	}

	public List<Item> getAllItemsBySellerId(int seller_id) {
		List<Item> items = new ArrayList<Item>();

//		Connection connection = getConnection();
//		String sql = "SELECT * FROM item where seller_id = ?";
//
//		try {
//			PreparedStatement preparedStatement = connection
//			        .prepareStatement(sql);
//			preparedStatement.setInt(1, seller_id);
//
//			ResultSet rs = preparedStatement.executeQuery();
//
//			while (rs.next()) {
//				Item item = convertItem(rs);
//				items.add(item);
//			}
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return items;
		String sql = "SELECT * FROM item where seller_id = ?";
		try {
			
            List<Item>items1=JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<Item>(Item.class),seller_id);
            for(int i=1;i<items1.size();i++){
            	Item item=items1.get(i);
     		    UserDao userDao = new UserDaoImpl();
    		    item.setSeller(userDao.getUserById(item.getSeller_id()));
            	
            }
			return items1;
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
		
		
	}
	

	protected Item convertItem(ResultSet rs) throws SQLException {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setTitle(rs.getString("title"));
		item.setAuthors(rs.getString("authors"));
		item.setType(rs.getString("type"));
		item.setPublication_date(rs.getDate("publication_date"));
		item.setVenue(rs.getString("venue"));
		item.setPrice(rs.getDouble("price"));
		item.setBan(rs.getBoolean("ban"));
		item.setPaused(rs.getBoolean("paused"));
		item.setQuantity(rs.getInt("quantity"));
		item.setSeller_id(rs.getInt("seller_id"));
		item.setImageURL(rs.getString("imageURL"));
		UserDao userDao = new UserDaoImpl();
		item.setSeller(userDao.getUserById(rs.getInt("seller_id")));
		return item;
	}

}
