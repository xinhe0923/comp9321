package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;
import dao.ItemDao;
import dao.ItemDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import dto.Item;
import dto.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ItemService {

	private ItemDao itemDao = ItemDaoImpl.getInstance();
	private UserDao userDao = UserDaoImpl.getInstance();

	/**
	 * helper function, load seller object for item, by default, item object
	 * only contians seller_id
	 * 
	 * @param item
	 */
	public void loadItemSeller(Item item) {
		User seller = userDao.getUserById(item.getSeller_id());
		item.setSeller(seller);
	}

	// list all avaiable items
	private List<Item> getAllItems() {
		return itemDao.getAllItems();
	}

	/**
	 * For home page
	 */
	public List<Item> getAllAvailibleItems() {
		List<Item> resultList = new ArrayList<Item>();
		for (Item i : getAllItems()) {
			if ( (i.getTitle() == null|| i.getTitle().equals(""))
					|| (i.getAuthors() == null||i.getAuthors().equals(""))) {
				continue;
			}
			if (!i.isPaused() && !i.isBan()) {
				resultList.add(i);
			}
		}
		return resultList;
	}

	/**
	 * for advance search
	 */
	public List<Item> searchItemsByParam(Item example) {
		List<Item> resultList = new ArrayList<Item>();

		for (Item i : getAllAvailibleItems()) {
			// compare title
			if (example.getTitle() != null && i.getTitle() != null) {
				if (!i.getTitle().equalsIgnoreCase(example.getTitle())) {
					continue;
				}
			}
			// compare type
			if (example.getType() != null && i.getType() != null) {
				if (!i.getType().equalsIgnoreCase(example.getType())) {
					continue;
				}
			}

			// compare authors
			if (example.getAuthors() != null && i.getAuthors() != null) {
				if (!i.getAuthors().toLowerCase()
				        .contains(example.getAuthors().toLowerCase())) {
					continue;
				}
			}

			// compare Venue
			if (example.getVenue() != null && i.getVenue() != null) {
				if (!i.getVenue().toLowerCase()
				        .contains(example.getVenue().toLowerCase())) {
					continue;
				}
			}

			resultList.add(i);
		}

		return resultList;
	}

	/**
	 * For item detail page
	 * 
	 * @param id
	 */
	public void pauseItem(String id) {
		Item item = itemDao.getItemById(Integer.parseInt(id));
		item.setPaused(true);
		itemDao.saveOrUpdate(item);
	}
	/**
	 * For item detail page
	 * 
	 * @param id
	 */
	public void unPauseItem(String id) {
		Item item = itemDao.getItemById(Integer.parseInt(id));
		item.setPaused(false);
		itemDao.saveOrUpdate(item);
	}
	/**
	 * for user uploaded items pages
	 * 
	 * @param seller_id
	 * @return
	 */
	public List<Item> getAllOwnedItems(int seller_id) {
		return itemDao.getAllItemsBySellerId(seller_id);
	}

	/**
	 * for add/edit item pages
	 * 
	 * @param item
	 */
	public void saveOrUpdate(Item item) {
		itemDao.saveOrUpdate(item);
	}
	
	/**
	 * For admin ban page
	 * 
	 * @param id
	 */
	public void banItem(String id) {
		Item item = itemDao.getItemById(Integer.parseInt(id));
		item.setBan(true);
		itemDao.saveOrUpdate(item);
	}

	/**
	 * For admin page
	 * 
	 * @param id
	 */
	public void unBanItem(String id) {
		Item item = itemDao.getItemById(Integer.parseInt(id));
		item.setBan(false);
		itemDao.saveOrUpdate(item);
	}

	/**
	 * For home page
	 *
	 * @param
	 */
	public List<Item> getRandom10(){
		return itemDao.getRandomten();
	}
	/**
	 * For store page
	 *
	 * @param userId
	 */
	public List<Item> getAllItemByseller(int userId){
		return itemDao.getAllItemsBySellerId(userId);
	}

	public Item getItemById(String id){
		return  itemDao.getItemById(Integer.parseInt(id));
	}

	public List<Item> basicSearch (String keywords){
		List<Item> wholeList = getAllAvailibleItems();
		List<Item> resultList = new ArrayList<>();
		for (Item element : wholeList){
			if(keywords == null || keywords.equals("")){
				continue;
			}
			if(element.getTitle().toLowerCase().indexOf(keywords) != -1){
				resultList.add(element);
				continue;
			}
			if(element.getAuthors().toLowerCase().indexOf(keywords) != -1){
				resultList.add(element);
				continue;
			}
		}
		return resultList;
	}

	public List<Item> advSearch (HttpServletRequest request) throws ParseException {
		List<Item> wholeList = getAllAvailibleItems();
		List<Item> resultList = new ArrayList<>();
		boolean typeSame;
		boolean titleSame;
		boolean authorSame;
		boolean venusSame;
		boolean checkPrice;
		boolean checkdate;
		for (Item temp : wholeList) {
			typeSame = false;
			titleSame = false;
			authorSame = false;
			venusSame = false;
			checkPrice = false;
			checkdate = false;
			
			if ( request.getParameter("type") == null||request.getParameter("type").equals("all")
					|| request.getParameter("type").equals("")
					|| request.getParameter("type").toLowerCase().equals(temp.getType().toLowerCase())) {
				typeSame = true;
			}
			if ( request.getParameter("title") == null|| request.getParameter("title").equals("")) {
				titleSame =true;
			}
			else {
				if (temp.getTitle().toLowerCase().indexOf(request.getParameter("title").toLowerCase())!= -1){
					titleSame = true;
				}
			}
			if ( request.getParameter("author") == null||request.getParameter("author").equals("") ) {
				authorSame =true;
			}
			else {
				if (temp.getAuthors().toLowerCase().indexOf(request.getParameter("author").toLowerCase())!= -1) {
					authorSame = true;
				}

			}
			if (  request.getParameter("venues") == null||request.getParameter("venues").equals("")||request.getParameter("venues").equals("all")) {
				venusSame =true;
			}
			else {
				if (temp.getVenue().toLowerCase().indexOf(request.getParameter("venues").toLowerCase())!= -1){
					venusSame = true;
				}

			}
			if ((request.getParameter("price1") == null||request.getParameter("price1").equals(""))
					&&(request.getParameter("price2") == null||request.getParameter("price2").equals(""))) {
				checkPrice =true;
			}
			else if ((request.getParameter("price1") == null||request.getParameter("price1").equals(""))){
                if ( temp.getPrice() <= Double.parseDouble(request.getParameter("price2"))) {
                    checkPrice = true;
                }
			}
			else if ((request.getParameter("price2") == null||request.getParameter("price2").equals(""))){
                if (temp.getPrice() >= Double.parseDouble(request.getParameter("price1"))) {
                    checkPrice = true;
                }
			}
			else if (temp.getPrice() >= Double.parseDouble(request.getParameter("price1"))
					&& temp.getPrice() <= Double.parseDouble(request.getParameter("price2"))){
				checkPrice =true;
			}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
			if ((request.getParameter("publicationdate1") == null||request.getParameter("publicationdate1").equals(""))
					&&(request.getParameter("publicationdate2") == null||request.getParameter("publicationdate2").equals(""))) {
				checkdate =true;
			}
			else if ((request.getParameter("publicationdate1") == null||request.getParameter("publicationdate1").equals(""))){
                if (temp.getPublication_date().before(sdf.parse(request.getParameter("publicationdate2")))){
                    checkdate =true;
                }
			}
			else if ((request.getParameter("publicationdate2") == null||request.getParameter("publicationdate2").equals(""))){
                if (temp.getPublication_date().after(sdf.parse(request.getParameter("publicationdate1")))){
                    checkdate =true;
                }
			}
			else if (temp.getPublication_date().after(sdf.parse(request.getParameter("publicationdate1")))
					&& temp.getPublication_date().before(sdf.parse(request.getParameter("publicationdate2")))){
				checkdate =true;
			}
			if ( (request.getParameter("type") == null||request.getParameter("type").equals("all") || request.getParameter("type").equals(""))
					&& (request.getParameter("title") == null|| request.getParameter("title").equals(""))
					&& (request.getParameter("author") == null||request.getParameter("author").equals(""))
					&&(request.getParameter("venues") == null||request.getParameter("venues").equals("")||request.getParameter("venues").equals("all"))
					&& ((request.getParameter("price1") == null||request.getParameter("price1").equals("")) && (request.getParameter("price2") == null||request.getParameter("price2").equals("")))
					&&((request.getParameter("publicationdate1") == null||request.getParameter("publicationdate1").equals("")) && (request.getParameter("publicationdate2") == null||request.getParameter("publicationdate2").equals("")))){
				continue;
			}
			if (typeSame && titleSame && authorSame && venusSame && checkdate && checkPrice){
				resultList.add(temp);
			}
		}
		return resultList;
	}

	public List<Item> adminAdvSearch (HttpServletRequest request) throws ParseException {
		List<Item> wholeList = getAllAvailibleItems();
		List<Item> resultList = new ArrayList<>();
		boolean typeSame;
		boolean titleSame;
		boolean authorSame;
		boolean venusSame;
		boolean checkPrice;
		boolean checkdate;
		for (Item temp : wholeList) {
			typeSame = false;
			titleSame = false;
			authorSame = false;
			venusSame = false;
			checkPrice = false;
			checkdate = false;
			
			if ( request.getParameter("type") == null||request.getParameter("type").equals("all")
					|| request.getParameter("type").equals("")
					|| request.getParameter("type").toLowerCase().equals(temp.getType().toLowerCase())) {
				typeSame = true;
			}
			if ( request.getParameter("title") == null|| request.getParameter("title").equals("")) {
				titleSame =true;
			}
			else {
				if (temp.getTitle().toLowerCase().indexOf(request.getParameter("title").toLowerCase())!= -1){
					titleSame = true;
				}
			}
			if ( request.getParameter("author") == null||request.getParameter("author").equals("") ) {
				authorSame =true;
			}
			else {
				if (temp.getAuthors().toLowerCase().indexOf(request.getParameter("author").toLowerCase())!= -1) {
					authorSame = true;
				}

			}
			if (  request.getParameter("venues") == null||request.getParameter("venues").equals("")||request.getParameter("venues").equals("all")) {
				venusSame =true;
			}
			else {
				if (temp.getVenue().toLowerCase().indexOf(request.getParameter("venues").toLowerCase())!= -1){
					venusSame = true;
				}

			}
			if ((request.getParameter("price1") == null||request.getParameter("price1").equals(""))
					&&(request.getParameter("price2") == null||request.getParameter("price2").equals(""))) {
				checkPrice =true;
			}
			else if ((request.getParameter("price1") == null||request.getParameter("price1").equals(""))){
                if ( temp.getPrice() <= Double.parseDouble(request.getParameter("price2"))) {
                    checkPrice = true;
                }
			}
			else if ((request.getParameter("price2") == null||request.getParameter("price2").equals(""))){
                if (temp.getPrice() >= Double.parseDouble(request.getParameter("price1"))) {
                    checkPrice = true;
                }
			}
			else if (temp.getPrice() >= Double.parseDouble(request.getParameter("price1"))
					&& temp.getPrice() <= Double.parseDouble(request.getParameter("price2"))){
				checkPrice =true;
			}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
			if ((request.getParameter("publicationdate1") == null||request.getParameter("publicationdate1").equals(""))
					&&(request.getParameter("publicationdate2") == null||request.getParameter("publicationdate2").equals(""))) {
				checkdate =true;
			}
			else if ((request.getParameter("publicationdate1") == null||request.getParameter("publicationdate1").equals(""))){
                if (temp.getPublication_date().before(sdf.parse(request.getParameter("publicationdate2")))){
                    checkdate =true;
                }
			}
			else if ((request.getParameter("publicationdate2") == null||request.getParameter("publicationdate2").equals(""))){
                if (temp.getPublication_date().after(sdf.parse(request.getParameter("publicationdate1")))){
                    checkdate =true;
                }
			}
			else if (temp.getPublication_date().after(sdf.parse(request.getParameter("publicationdate1")))
					&& temp.getPublication_date().before(sdf.parse(request.getParameter("publicationdate2")))){
				checkdate =true;
			}
			if ( (request.getParameter("type") == null||request.getParameter("type").equals("all") || request.getParameter("type").equals(""))
					&& (request.getParameter("title") == null|| request.getParameter("title").equals(""))
					&& (request.getParameter("author") == null||request.getParameter("author").equals(""))
					&&(request.getParameter("venues") == null||request.getParameter("venues").equals("")||request.getParameter("venues").equals("all"))
					&& ((request.getParameter("price1") == null||request.getParameter("price1").equals("")) && (request.getParameter("price2") == null||request.getParameter("price2").equals("")))
					&&((request.getParameter("publicationdate1") == null||request.getParameter("publicationdate1").equals("")) && (request.getParameter("publicationdate2") == null||request.getParameter("publicationdate2").equals("")))){
				continue;
			}
			if (typeSame && titleSame && authorSame && venusSame && checkdate && checkPrice){
				resultList.add(temp);
			}
		}
		return resultList;
	}

	public Item makeItemByRequest (HttpServletRequest request) throws ParseException {
		Item item = new Item();
		item.setId(0);
		if (request.getParameter("title") != null && !request.getParameter("title").equals("")) {
			item.setTitle(request.getParameter("title"));
		}
		if (request.getParameter("author") != null && !request.getParameter("author").equals("")) {
			item.setAuthors(request.getParameter("author"));
		}
		if (request.getParameter("publicationdate") != null && !request.getParameter("publicationdate").equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			item.setPublication_date(sdf.parse(request.getParameter("publicationdate")));
		}
		if (request.getParameter("venues") != null && !request.getParameter("venues").equals("")) {
			item.setVenue(request.getParameter("venues"));
		}
		if (request.getParameter("price") != null && !request.getParameter("price").equals("")) {
			item.setPrice(Double.parseDouble(request.getParameter("price")));
		}
		if (request.getParameter("quantity") != null && !request.getParameter("quantity").equals("")) {
			item.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		}
		if (request.getParameter("image") != null && !request.getParameter("image").equals("")) {
			item.setImageURL(request.getParameter("image"));
		}
		if (request.getParameter("typesearch") != null && !request.getParameter("typesearch").equals("")) {
			item.setType(request.getParameter("typesearch"));
		}
		item.setPaused(false);
		item.setBan(false);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		item.setSeller_id(user.getId());
		return item;
	}
}
