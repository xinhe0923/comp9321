package dao;

import java.util.List;

import dto.WishList;

public interface WishListDao {

	public List<WishList> getWishList(int user_id);

	public void saveOrUpdate(WishList WishList);

	public void clearWishListByUserId(int user_id);

	public WishList getWishListByUserIdAndItemId(int user_id, int item_id);

	public WishList getWishListById(int id);

	public void deletWishList(int id);
}
