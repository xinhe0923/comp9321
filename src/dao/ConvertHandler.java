package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import service.ItemService;
import dto.DblpElement;
import dto.Item;

public class ConvertHandler {
	public static Item convert(DblpElement element) {
		Item item = new Item();
		item.setTitle(element.getTitle());
		item.setAuthors(element.getAuthors().toString());
		item.setType(element.getType());
		item.setVenue(element.getJournal());

		item.setSeller_id(2);
		int year = Integer.parseInt(element.getYear());
		Calendar cld = Calendar.getInstance();
		cld.set(Calendar.YEAR, year);
		Date result = cld.getTime();
		item.setPublication_date(result);

		return item;
	}

	public static void InsertItem() {
		ItemService itemService = new ItemService();
		Random ran = new Random();
		ArrayList<String> ranVenue = new ArrayList<String>(Arrays.asList("WWW",
		        "BPM", "VLDB"));
		for (DblpElement e : DblpDao.getAllElements()) {
			Item i = convert(e);
			// System.out.print(i.getTitle());
			
			// random price and quantity
			int x = ran.nextInt(101-10) + 10;
			int y = ran.nextInt(101-10) + 10;
			
			i.setPrice(x);
			i.setQuantity(y);
			
			// random venue
			int z = ran.nextInt(ranVenue.size());
			i.setVenue(ranVenue.get(z));
			itemService.saveOrUpdate(i);

		}
	}

	public static void main(String[] args) {
		System.out.print("running");
		ConvertHandler.InsertItem();
		System.out.print("finished");
	}
}
