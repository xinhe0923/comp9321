package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.handlers.BeanListHandler;


import dto.Graph;
import util.JdbcUtil;

public class GraphDaoImpl implements GraphDao {
	// singleton
	@Override
	public ArrayList<Graph> searchGraph(String id) 
	{
		String sql = "select * from graph where Subject=? or Object=?";
		
		try {
			return (ArrayList<Graph>) JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<Graph>(Graph.class), id, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	}
