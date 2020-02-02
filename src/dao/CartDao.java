package dao;

import java.util.List;

import dto.Cart;

public interface CartDao {

	public List<Cart> getCart(int user_id);

	public void saveOrUpdate(Cart cart);

	public void clearCartByUserId(int user_id);

	public Cart getCartByUserIdAndItemId(int user_id, int item_id);

	public Cart getCartById(int id);

	public void deletCart(int id);

}
