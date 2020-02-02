package dao;
import java.util.List;

import dto.Entity;

public interface EntityDao 
{
	Object searchEntityIdByLabel(String name);
	Object searchEntityObjectById(String id);
	List<Entity> findAllFullLabels(String subLabel);
}
