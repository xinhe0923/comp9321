package dto;

import java.sql.Timestamp;


public class Cart {
	private int id;
	private int user_id;
	private int item_id;
	private int count;
	private Timestamp added;
	private Timestamp removed = null;
	private Item Item;

	public int getId() {
	    return id;
    }

	public void setId(int id) {
	    this.id = id;
    }

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Item getItem() {
		return Item;
	}

	public void setItem(Item item) {
		Item = item;
	}

	public Timestamp getAdded() {
		return added;
	}

	public void setAdded(Timestamp added) {
		this.added = added;
	}

	public Timestamp getRemoved() {
		return removed;
	}

	public void setRemoved(Timestamp removed) {
		this.removed = removed;
	}

	public boolean isRemoved() {
		return this.removed != null;
	}

}
