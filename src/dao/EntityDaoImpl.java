package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dto.EntityObject;
import util.JdbcUtil;
import dto.Entity;

public class EntityDaoImpl implements EntityDao {

	public Object searchEntityIdByLabel(String name) {
		String sql = "select * from Entity where Predicate=\"label\" and Object=?";
		
		try {
			Entity e = (Entity) JdbcUtil.getQueryRunner().query(sql, new BeanHandler<Entity>(Entity.class), name);
			return e;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}

	@Override
	public Object searchEntityObjectById(String id) {
		String sql = "select * from Entity where Subject=?";
		
		try {
			EntityObject eo = new EntityObject();
			ArrayList<Entity> entities = (ArrayList<Entity>) JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<Entity>(Entity.class), id);
			for (Entity e : entities)
			{
				if (e.getPredicate().equals("Type"))
				{
					eo.setType(e.getObject());
				}
				
				if (e.getPredicate().equals("Class"))
				{
					eo.seteClass(e.getObject());
				}
				
				if (e.getPredicate().equals("Label"))
				{
					eo.setLabel(e.getObject());
				}
			}
			eo.setId(id);
			return eo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	@Override
	public List<Entity> findAllFullLabels(String subLabel) {
		
		String sql = "select * from Entity where Predicate = \"label\" and Object like ?";
		try {
			return JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<Entity>(Entity.class), "%" + subLabel + "%");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	

	}
	
}
