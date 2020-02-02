package jdbcdemo;

import java.sql.*;



public class Jdbc_Utils {
	public static  String database = "jdbc:mysql://localhost:3306/thisfinaltest";
	public static  String username = "root";
	public static  String password = "";
    
	public static Connection getConnection() throws ClassNotFoundException {
		Connection connection = null;

		try {
			//1.get a connect to database
			connection =  DriverManager.getConnection(database,username,password);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
}
	

}
