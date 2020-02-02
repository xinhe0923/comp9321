package util;


import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {

	private static DataSource dataSource;
	
	static{
		
		dataSource = new ComboPooledDataSource();
	}
	
	
	public static QueryRunner getQueryRunner(){
	 return new QueryRunner(dataSource);

}
	
}
