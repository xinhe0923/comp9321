package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import dto.Admin;
import dto.Item;
import dto.User;
import jdbcdemo.Jdbc_Utils;
import util.JdbcUtil;

public class AdminDaoImpl extends JdbcDao implements AdminDao {

	// singleton
	public static AdminDaoImpl instance;

	public static AdminDaoImpl getInstance() {
		if (instance == null) {
			instance = new AdminDaoImpl();
		}
		return instance;
	}

	@Override
	public void saveOrUpdate(Admin admin) {
		Connection connection = getConnection();

		if (admin.getId() > 0) {
//			// update
//
//			String sql = "UPDATE `admin` SET `username` = ?, `password` = ? WHERE `id`=?";
//			PreparedStatement preparedStatement;
//			try {
//				preparedStatement = connection.prepareStatement(sql);
//				preparedStatement.setString(1, admin.getUsername());
//				preparedStatement.setString(2, admin.getPassword());
//				preparedStatement.setInt(3, admin.getId());
//				preparedStatement.executeUpdate();
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			String sql = "UPDATE `admin` SET `username` = ?, `password` = ? WHERE `id`=?";
			try {
				JdbcUtil.getQueryRunner().update(sql,admin.getUsername(),admin.getPassword(),admin.getId());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		} else {
//			// save
//			String sql = "INSERT INTO `admin` (`username`, `password`) VALUES (?, ?)";
//			PreparedStatement preparedStatement;
//			try {
//				preparedStatement = connection.prepareStatement(sql);
//				preparedStatement.setString(1, admin.getUsername());
//				preparedStatement.setString(2, admin.getPassword());
//				preparedStatement.executeUpdate();
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			String sql = "INSERT INTO `admin` (`username`, `password`) VALUES (?, ?)";
			 try {
				JdbcUtil.getQueryRunner().update(sql,admin.getUsername(),admin.getPassword());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			

		}
	}

	@Override
	public Admin getAdaminByUsernameAndPassword(String username, String password) {
//		Connection connection = getConnection();
//		String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
//
//		try {
//			PreparedStatement preparedStatement = connection
//			        .prepareStatement(sql);
//			preparedStatement.setString(1, username);
//			preparedStatement.setString(2, password);
//
//			ResultSet rs = preparedStatement.executeQuery();
//			if (rs.next()) {
//				Admin rsAdmin = convertAdmin(rs);
//				connection.close();
//				return rsAdmin;
//			}
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
		String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
		 try {
			Admin rsAdmin=JdbcUtil.getQueryRunner().query(sql, new BeanHandler<Admin>(Admin.class),username,password);
			return rsAdmin;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Admin getAdminByUsername(String username){
//		Connection connection = getConnection();
//		String sql = "SELECT * FROM admin WHERE username = ?";
//
//		try {
//			PreparedStatement preparedStatement = connection
//			        .prepareStatement(sql);
//			preparedStatement.setString(1, username);
//
//			ResultSet rs = preparedStatement.executeQuery();
//			if (rs.next()) {
//				Admin rsAdmin = convertAdmin(rs);
//				connection.close();
//				return rsAdmin;
//			}
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return null;
		String sql = "SELECT * FROM admin WHERE username = ?";
		 try {
			Admin rsAdmin=JdbcUtil.getQueryRunner().query(sql, new BeanHandler<Admin>(Admin.class),username);
			return rsAdmin;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
//	private Admin convertAdmin(ResultSet rs) throws SQLException {
//		Admin admin = new Admin();
//		admin.setId(rs.getInt("id"));
//		admin.setUsername(rs.getString("username"));
//		admin.setPassword(rs.getString("password"));
//
//		return admin;
//	}
}
