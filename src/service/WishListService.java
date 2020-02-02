package service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.WishListDao;
import dao.WishListDaoImpl;
import dao.ItemDao;
import dao.ItemDaoImpl;
import dto.WishList;
import dto.Item;

public class WishListService {
	private WishListDao WishListDao = WishListDaoImpl.getInstance();

	// get all items in shopping WishList
	private List<WishList> getAllWishList(int user_id) {
		return WishListDao.getWishList(user_id);
	}

	/**
	 * helper
	 * 
	 * @param id
	 * @return
	 */
	public WishList getWishListById(String id) {
		return this.WishListDao.getWishListById(Integer.parseInt(id));
	}

	// get all removed items in shopping WishList
	public List<WishList> getRemovedWishList(int user_id) {
		List<WishList> list = new ArrayList<WishList>();
		for (WishList c : getAllWishList(user_id)) {
			if (c.isRemoved()) {
				list.add(c);
			}
		}
		return list;
	}

	// get all non removed items in shopping WishList
	public List<WishList> getExistedWishList(int user_id) {
		List<WishList> list = new ArrayList<WishList>();
		for (WishList c : getAllWishList(user_id)) {
			if (!c.isRemoved()) {
				list.add(c);
			}
		}
		return list;
	}

	public List<Item> getExistedItemInWishList(int user_id) {
		List<WishList> WishListList = getExistedWishList(user_id);
		List<Item> itemList = WishListList.stream().map(WishList::getItem)
		        .collect(Collectors.toList());
		return itemList;
	}

	/**
	 * clear shopping WishList
	 * 
	 * @param user_id
	 */
	public void clearWishListByUserId(int user_id) {
		WishListDao.clearWishListByUserId(user_id);
	}

	public void saveToWishList(int user_id, int item_id, int count) {
		WishList WishList = WishListDao.getWishListByUserIdAndItemId(user_id, item_id);
		if (WishList == null) {
			WishList = new WishList();
			WishList.setUser_id(user_id);
			WishList.setItem_id(item_id);
		}
		WishList.setCount(count);
		WishListDao.saveOrUpdate(WishList);
	}

	public void addToWishList(int user_id, int item_id) {
		WishList WishList = WishListDao.getWishListByUserIdAndItemId(user_id, item_id);
		if (WishList == null) {
			WishList = new WishList();
			WishList.setUser_id(user_id);
			WishList.setItem_id(item_id);
			WishList.setCount(1);
			WishList.setAdded(new Timestamp(System.currentTimeMillis()));
		} else {
			WishList.setCount(WishList.getCount() + 1);
		}
		WishListDao.saveOrUpdate(WishList);
	}

	public void removeWishList(int id) {
        WishList WishList = WishListDao.getWishListById(id);
        if (WishList != null) {
            WishList.setRemoved(new Timestamp(System.currentTimeMillis()));
            WishListDao.saveOrUpdate(WishList);
        }
    }


    public void deleteWishList(int id) {
            WishListDao.deletWishList(id);
    }
    public void removeWishList(int user_id, int item_id) {
        WishList WishList = WishListDao.getWishListByUserIdAndItemId(user_id, item_id);
        if (WishList != null) {
            WishList.setRemoved(new Timestamp(System.currentTimeMillis()));
            WishListDao.saveOrUpdate(WishList);
        }
    }

	public WishList getWishListByUserIdAndItemId(int user_id, int item_id) {
		return WishListDao.getWishListByUserIdAndItemId(user_id, item_id);
	}

    public WishList getWishListById(int id){
		WishList WishList = WishListDao.getWishListById(id);
		ItemDao itemDao = new ItemDaoImpl();
		Item item = itemDao.getItemById(WishList.getItem_id());
		WishList.setItem(item);

        return WishList;
    }
}
