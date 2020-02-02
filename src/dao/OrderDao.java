package dao;

import java.util.List;

import dto.Order;

public interface OrderDao {

	public void saveOrUpdate(Order order);

	public List<Order> getAllOrderByUserId(int user_id);

	public Order getOrderByOrderId(int id);

}
