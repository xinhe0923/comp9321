package dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDao {

	public static final String database = "jdbc:mysql://localhost:3306/thisfinaltest";
	public static final String username = "root";
	public static final String password = "";

	public static Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(database, username,
			        password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}