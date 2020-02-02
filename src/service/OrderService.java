package service;

import java.sql.Timestamp;
import java.util.List;

import dao.*;
import dto.Cart;
import dto.Item;
import dto.Order;
import dto.OrderItem;

import javax.mail.MessagingException;

public class OrderService {

	private OrderDao orderDao = OrderDaoImpl.getInstance();
	private OrderItemDao orderItemDao = OrderItemDaoImpl.getInstance();
	private CartService cartService = new CartService();
	private ItemService itemService = new ItemService();

	/**
	 * create order
	 * 
	 * @param user_id
	 */
	public Order createOrder(int user_id) {
		// step 1, insert into order table
		Order order = new Order();
		order.setUser_id(user_id);
		orderDao.saveOrUpdate(order);

		// step 2, move all shopping cart into orderItem
		for (Cart c : cartService.getExistedCart(user_id)) {
			OrderItem oi = new OrderItem();
			oi.setOrder_id(order.getId());
			oi.setItem_id(c.getItem_id());
			oi.setCount(c.getCount());
			orderItemDao.save(oi);
		}

		// step 3, clear cart
		cartService.clearCartByUserId(user_id);

		// step 4, send email
		// TODO: send email
		
		return order;
	}

	public Order createOrder(List<Cart> cartList) throws MessagingException {
		// step 1, insert into order table
		Cart temp = cartList.get(0);
		Order order = new Order();
		order.setUser_id(temp.getUser_id());
		order.setCreated(new Timestamp(System.currentTimeMillis()));
		orderDao.saveOrUpdate(order);


		// step 2, move all shopping cart into orderItem and remove all cart in database
		for (Cart c : cartList) {
			OrderItem oi = new OrderItem();
			oi.setOrder_id(order.getId());
			oi.setItem_id(c.getItem_id());
			oi.setCount(c.getCount());
			cartService.deleteCart(c.getId());
			orderItemDao.save(oi);
		}

		//step3, change the quantity of items
		for (Cart c : cartList) {
			Item item = c.getItem();
			item.setQuantity(item.getQuantity()-c.getCount());
			itemService.saveOrUpdate(item);
		}


		// step 4, send email
		for (Cart c : cartList) {
			SendEmail.sendSellMail(c);
		}

		return order;
	}

	public List<Order> getAllOrderByUserId(int user_id) {
		return this.orderDao.getAllOrderByUserId(user_id);
	}

	public Order getOrderByOrderId(int id) {
		return this.orderDao.getOrderByOrderId(id);
	}
	
	public List<OrderItem> getAllOrderItemByOrderId(int id){
		return this.orderItemDao.getAllOrderItemByOrderId(id);
	}

}
