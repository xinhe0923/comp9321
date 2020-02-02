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
import dto.User;
import util.JdbcUtil;

public class UserDaoImpl extends JdbcDao implements UserDao {

	// singleton
	public static UserDaoImpl instance;

	public static UserDaoImpl getInstance() {
		if (instance == null) {
			instance = new UserDaoImpl();
		}
		return instance;
	}

	@Override
	public void saveOrUpdate(User user) {
		Connection connection = getConnection();

		// if it's new user, the do insert
		if (user.getId() == 0) {
			

//				String sql = "INSERT INTO user (username, password, nickname, firstname, lastname, email, birthyear, address, creditcard, ban, verified, uuid) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
//				PreparedStatement preparedStatement = connection
//				        .prepareStatement(sql);
//
//				preparedStatement.setString(1, user.getUsername());
//				preparedStatement.setString(2, user.getPassword());
//				preparedStatement.setString(3, user.getNickname());
//				preparedStatement.setString(4, user.getFirstname());
//				preparedStatement.setString(5, user.getLastname());
//				preparedStatement.setString(6, user.getEmail());
//				preparedStatement.setInt(7, user.getBirthyear());
//				preparedStatement.setString(8, user.getAddress());
//				preparedStatement.setString(9, user.getCreditcard());
//				preparedStatement.setBoolean(10, user.isBan());
//				preparedStatement.setBoolean(11, user.isVerified());
//				preparedStatement.setString(12, user.getUuid());
//
//				preparedStatement.executeUpdate();
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
				String sql = "INSERT INTO user (username, password, nickname, firstname, lastname, email, birthyear, address, creditcard, ban, verified, uuid) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				try {
					JdbcUtil.getQueryRunner().update(sql,user.getUsername(), user.getPassword(),user.getNickname(), user.getFirstname(),user.getLastname(), user.getEmail(),user.getBirthyear(), user.getAddress(),user.getCreditcard(), user.isBan(), user.isVerified(), user.getUuid());
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			
		} else {
			try {
//				String sql = "UPDATE user SET username=?, password=?, nickname=?, firstname=?, lastname=?, email=?, birthyear=?, address=?, creditcard=?, ban=?, verified=?, uuid=? WHERE id=?";
//
//				PreparedStatement preparedStatement = connection
//				        .prepareStatement(sql);
//				preparedStatement.setString(1, user.getUsername());
//				preparedStatement.setString(2, user.getPassword());
//				preparedStatement.setString(3, user.getNickname());
//				preparedStatement.setString(4, user.getFirstname());
//				preparedStatement.setString(5, user.getLastname());
//				preparedStatement.setString(6, user.getEmail());
//				preparedStatement.setInt(7, user.getBirthyear());
//				preparedStatement.setString(8, user.getAddress());
//				preparedStatement.setString(9, user.getCreditcard());
//				preparedStatement.setBoolean(10, user.isBan());
//				preparedStatement.setBoolean(11, user.isVerified());
//				preparedStatement.setString(12, user.getUuid());
//				preparedStatement.setInt(13, user.getId());
//
//				preparedStatement.executeUpdate();
//				connection.close();
				String sql = "UPDATE user SET username=?, password=?, nickname=?, firstname=?, lastname=?, email=?, birthyear=?, address=?, creditcard=?, ban=?, verified=?, uuid=? WHERE id=?";
				JdbcUtil.getQueryRunner().update(sql,user.getUsername(),user.getPassword(),user.getNickname(),user.getFirstname(), user.getLastname(),user.getEmail(),user.getBirthyear(), user.getAddress(),user.getCreditcard(),user.isBan(),user.isVerified(), user.getUuid(), user.getId());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public User getLoginUser(String username, String password) {
//		Connection connection = getConnection();
//		String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
//
//		try {
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, username);
//			preparedStatement.setString(2, password);
//
//			ResultSet rs = preparedStatement.executeQuery();
//			if (rs.next()) {
//                User rsUser = convertUser(rs);
//				connection.close();
//				return rsUser;
//			}
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return null;
		String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
		try {
			User rsUser=JdbcUtil.getQueryRunner().query(sql, new BeanHandler<User>(User.class),username,password);
			return rsUser;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User getUserByUuid(String uuid) {
//		Connection connection = getConnection();
//		String sql = "SELECT * FROM user WHERE uuid = ?";
//
//		try {
//			PreparedStatement preparedStatement = connection
//			        .prepareStatement(sql);
//			preparedStatement.setString(1, uuid);
//
//			ResultSet rs = preparedStatement.executeQuery();
//			if (rs.next()) {
//                User rsUser = convertUser(rs);
//				connection.close();
//				return rsUser;
//			}
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return null;
		String sql = "SELECT * FROM user WHERE uuid = ?";
		try {
			User rsUser=JdbcUtil.getQueryRunner().query(sql, new BeanHandler<User>(User.class),uuid);
			return rsUser;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public User getUserByUsername(String username) {
//		Connection connection = getConnection();
//		String sql = "SELECT * FROM user WHERE username = ?";
//
//		try {
//			PreparedStatement preparedStatement = connection
//			        .prepareStatement(sql);
//			preparedStatement.setString(1, username);
//
//			ResultSet rs = preparedStatement.executeQuery();
//			if (rs.next()) {
//                User rsUser = convertUser(rs);
//				connection.close();
//				return rsUser;
//			}
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return null;
		String sql = "SELECT * FROM user WHERE username = ?";
		try {
			User rsUser=JdbcUtil.getQueryRunner().query(sql, new BeanHandler<User>(User.class),username);
			return rsUser;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public User getUserById(int id) {
//		Connection connection = getConnection();
//		String sql = "SELECT * FROM user WHERE id = ?";
//
//		try {
//			PreparedStatement preparedStatement = connection
//			        .prepareStatement(sql);
//			preparedStatement.setInt(1, id);
//
//			ResultSet rs = preparedStatement.executeQuery();
//			if (rs.next()) {
//                User rsUser = convertUser(rs);
//				connection.close();
//				return rsUser;
//			}
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return null;
		String sql = "SELECT * FROM user WHERE id = ?";
		try {
			User rsUser=JdbcUtil.getQueryRunner().query(sql, new BeanHandler<User>(User.class),id);
			return rsUser;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<User> getAllUsers() {
//		List<User> users = new ArrayList<User>();
//
//		Connection connection = getConnection();
//		String sql = "SELECT * FROM user";
//
//		try {
//			PreparedStatement preparedStatement = connection
//			        .prepareStatement(sql);
//			ResultSet rs = preparedStatement.executeQuery();
//
//			while (rs.next()) {
//				User user = convertUser(rs);
//				users.add(user);
//			}
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return users;
		String sql = "SELECT * FROM user";
		try {
			List<User>users=JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<User>(User.class));
			return users;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	private User convertUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setFirstname(rs.getString("firstname"));
		user.setLastname(rs.getString("lastname"));
		user.setEmail(rs.getString("email"));
		user.setBirthyear(rs.getInt("birthyear"));
		user.setAddress(rs.getString("address"));
		user.setCreditcard(rs.getString("creditcard"));
		user.setBan(rs.getBoolean("ban"));
		user.setVerified(rs.getBoolean("verified"));
		user.setUuid(rs.getString("uuid"));
		user.setNickname(rs.getString("nickname"));
		return user;
	}

}
