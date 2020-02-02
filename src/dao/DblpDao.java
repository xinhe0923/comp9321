package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import dto.DblpElement;

public class DblpDao {

	// singleton
	public static List<DblpElement> allElements;

	// /Users/NewFolder/Desktop/sample.xml
	public static String xmlFilePath = "/Users/yangxinhe/Downloads/dblp.xml";

	

	public static List<DblpElement> getAllElements() {

		if (allElements == null) {

			try {
				File inputFile = new File(xmlFilePath);
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser saxParser = factory.newSAXParser();
				DblpSAXHandler userhandler = new DblpSAXHandler();
				saxParser.parse(inputFile, userhandler);

				allElements = userhandler.getElements();
				// System.out.print(allElements.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return allElements;
	}

	public static DblpElement getDblpElementById(int id) {
		for (DblpElement element : getAllElements()) {
			if (element.getId() == id) {
				return element;
			}
		}
		return null;
	}

	public static List<DblpElement> advanceSearchByParams(String title,
	        String author, String year, String url, String publicationType) {

		title = title.toLowerCase().trim();
		author = author.toLowerCase().trim();
		year = year.toLowerCase().trim();
		url = url.toLowerCase().trim();
		publicationType = publicationType.toLowerCase().trim();

		List<DblpElement> resultList = new ArrayList<DblpElement>();

		for (DblpElement element : getAllElements()) {

			if (!publicationType.isEmpty() && element.getType() != null) {
				if (!publicationType.equalsIgnoreCase(element.getType())) {
					continue;
				}
			}

			// search title
			if (!title.isEmpty() && element.getTitle() != null) {
				if (!element.getTitle().toLowerCase().contains(title)) {
					continue;
				}
			}

			// search year
			if (!year.isEmpty() && element.getYear() != null) {
				if (!element.getYear().toLowerCase().contains(year)) {
					continue;
				}
			}

			// search url
			if (!url.isEmpty() && element.getUrl() != null) {
				if (!element.getUrl().toLowerCase().contains(url)) {
					continue;
				}
			}
			// search author
			if (!author.isEmpty()) {
				boolean foundAuthor = false;
				for (String au : element.getAuthors()) {
					if (au.toLowerCase().contains(author)) {
						foundAuthor = true;
						break;
					}
				}

				if (!foundAuthor) {
					continue;
				}
			}

			resultList.add(element);

			

		}
		return resultList;
	}

	public static List<DblpElement> searchByParams(String keywords,
	        String publicationType) {
		keywords = keywords.toLowerCase();

		List<DblpElement> resultList = new ArrayList<DblpElement>();

		for (DblpElement element : getAllElements()) {

			if (!publicationType.equalsIgnoreCase(element.getType())) {
				continue;
			}

			// add search rules

			// search title
			if (element.getTitle() != null
			        && element.getTitle().toLowerCase().contains(keywords)) {
				resultList.add(element);
				continue;
			}

			// search author
			boolean foundAuthor = false;
			for (String author : element.getAuthors()) {
				if (author.toLowerCase().contains(keywords)) {
					resultList.add(element);
					foundAuthor = true;
					break;
				}
			}

			if (foundAuthor) {
				continue;
			}

			// search book title
			if (element.getBookTitle() != null
			        && element.getBookTitle().toLowerCase().contains(keywords)) {
				resultList.add(element);
				continue;
			}
			
			// search year
			if (element.getYear() != null
			        && element.getYear().toLowerCase().contains(keywords)) {
				resultList.add(element);
				continue;
			}

		}

		return resultList;
	}

	public static List<DblpElement> getRandomTen() {
		List<DblpElement> list = getAllElements();
		//System.out.print("total entries" + list.size() + "\n");
		Collections.shuffle(list);
		if (list.size() > 10) {
			return list.subList(0, 10);
		} else {
			return list;
		}
	}
}
