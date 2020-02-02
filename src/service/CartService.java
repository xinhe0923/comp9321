package service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.CartDao;
import dao.CartDaoImpl;
import dao.ItemDao;
import dao.ItemDaoImpl;
import dto.Cart;
import dto.Item;

public class CartService {
	private CartDao cartDao = CartDaoImpl.getInstance();

	// get all items in shopping cart
	private List<Cart> getAllCart(int user_id) {
		return cartDao.getCart(user_id);
	}

	/**
	 * helper
	 * 
	 * @param id
	 * @return
	 */
	public Cart getCartById(String id) {
		return this.cartDao.getCartById(Integer.parseInt(id));
	}

	// get all removed items in shopping cart
	public List<Cart> getRemovedCart(int user_id) {
		List<Cart> list = new ArrayList<Cart>();
		for (Cart c : getAllCart(user_id)) {
			if (c.isRemoved()) {
				list.add(c);
			}
		}
		return list;
	}

	// get all non removed items in shopping cart
	public List<Cart> getExistedCart(int user_id) {
		List<Cart> list = new ArrayList<Cart>();
		for (Cart c : getAllCart(user_id)) {
			if (!c.isRemoved()) {
				list.add(c);
			}
		}
		return list;
	}

	public List<Item> getExistedItemInCart(int user_id) {
		List<Cart> cartList = getExistedCart(user_id);
		List<Item> itemList = cartList.stream().map(Cart::getItem)
		        .collect(Collectors.toList());
		return itemList;
	}

	/**
	 * clear shopping cart
	 * 
	 * @param user_id
	 */
	public void clearCartByUserId(int user_id) {
		cartDao.clearCartByUserId(user_id);
	}

	public void saveToCart(int user_id, int item_id, int count) {
		Cart cart = cartDao.getCartByUserIdAndItemId(user_id, item_id);
		if (cart == null) {
			cart = new Cart();
			cart.setUser_id(user_id);
			cart.setItem_id(item_id);
		}
		cart.setCount(count);
		cartDao.saveOrUpdate(cart);
	}

	public void addToCart(int user_id, int item_id) {
		Cart cart = cartDao.getCartByUserIdAndItemId(user_id, item_id);
		if (cart == null) {
			cart = new Cart();
			cart.setUser_id(user_id);
			cart.setItem_id(item_id);
			cart.setCount(1);
			cart.setAdded(new Timestamp(System.currentTimeMillis()));
		} else {
			cart.setCount(cart.getCount() + 1);
		}
		cartDao.saveOrUpdate(cart);
	}

	public void removeCart(int id) {
        Cart cart = cartDao.getCartById(id);
        if (cart != null) {
            cart.setRemoved(new Timestamp(System.currentTimeMillis()));
            cartDao.saveOrUpdate(cart);
        }
    }


    public void deleteCart(int id) {
            cartDao.deletCart(id);
    }
    public void removeCart(int user_id, int item_id) {
        Cart cart = cartDao.getCartByUserIdAndItemId(user_id, item_id);
        if (cart != null) {
            cart.setRemoved(new Timestamp(System.currentTimeMillis()));
            cartDao.saveOrUpdate(cart);
        }
    }

	public Cart getCartByUserIdAndItemId(int user_id, int item_id) {
		return cartDao.getCartByUserIdAndItemId(user_id, item_id);
	}

    public Cart getCartById(int id){
		Cart cart = cartDao.getCartById(id);
		ItemDao itemDao = new ItemDaoImpl();
		Item item = itemDao.getItemById(cart.getItem_id());
		cart.setItem(item);

        return cart;
    }
}
