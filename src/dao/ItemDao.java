package dao;

import java.util.List;

import dto.Item;

public interface ItemDao {

	public void saveOrUpdate(Item item);
	
	public Item getItemById(int id);

	public List<Item> getAllItems();

	public List<Item> getRandomten();

	public List<Item> getAllItemsBySellerId(int seller_id);

}
