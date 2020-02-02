package dao;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import dto.DblpElement;

public class DblpSAXHandler extends DefaultHandler {

	private DblpElement currentElement = null;
	private List<DblpElement> elements = new ArrayList<DblpElement>();

	// boolean for all attributes
	// author|editor|title|booktitle|pages|year|address|
	// journal|volume|number|month|url|ee|cdrom|cite|publisher|note
	// crossref|isbn|series|school|chapter">
	private boolean bTitle = false;
	private boolean bAuthor = false;
	private boolean bPage = false;
	private boolean bYear = false;
	private boolean bBookTitle = false;
	private boolean bEditor = false;
	private boolean bAddress = false;
	private boolean bJournal = false;
	private boolean bVolume = false;
	private boolean bNumber = false;
	private boolean bMonth = false;
	private boolean bUrl = false;
	private boolean bEe = false;
	private boolean bCdrom = false;
	private boolean bCite = false;
	private boolean bPublisher = false;
	private boolean bNote = false;
	private boolean bCrossref = false;
	private boolean bIsbn = false;
	private boolean bSeries = false;
	private boolean bSchool = false;
	private boolean bChapter = false;

	// PublicationTypes
	// private boolean bArticle = false;
	// private boolean bProceedings = false;
	// private boolean bBook = false;

	public List<DblpElement> getElements() {
		return this.elements;
	}
//this method is called every time the parser gets an open tag'<'
	//identifies which tag is being open at time by assigning an open flag
	@Override
	public void startElement(String uri, String localName, String qName,
	        Attributes attributes) throws SAXException {
		//

		// reading new node
		// possible types:
		// article|inproceedings|proceedings|book|incollection
		// phdthesis|mastersthesis|
		//如果当前节点=article，就把type设置为article
		if (qName.equalsIgnoreCase("article")) {
			currentElement = new DblpElement();
			currentElement.setType("article");
		} else if (qName.equalsIgnoreCase("proceedings")) {
			currentElement = new DblpElement();
			currentElement.setType("proceedings");
		} else if (qName.equalsIgnoreCase("book")) {
			currentElement = new DblpElement();
			currentElement.setType("book");
		} else if (qName.equalsIgnoreCase("inproceedings")) {
			currentElement = new DblpElement();
			currentElement.setType("inproceedings");
		} else if (qName.equalsIgnoreCase("incollection")) {
			currentElement = new DblpElement();
			currentElement.setType("incollection");
		} else if (qName.equalsIgnoreCase("phdthesis")) {
			currentElement = new DblpElement();
			currentElement.setType("phdthesis");
		} else if (qName.equalsIgnoreCase("mastersthesis")) {
			currentElement = new DblpElement();
			currentElement.setType("masterthesis");
		} else if (qName.equalsIgnoreCase("www")) {
			currentElement = new DblpElement();
			currentElement.setType("www");
		}

		// reading attribtues
		// possible attributes
		// author|editor|title|booktitle|pages|year|address
		// journal|volume|number|month|url|ee|cdrom|cite|publisher|note
		// crossref|isbn|series|school|chapter
		if (qName.equalsIgnoreCase("title")) {
			bTitle = true;
		} else if (qName.equalsIgnoreCase("author")) {
			bAuthor = true;
		} else if (qName.equalsIgnoreCase("editor")) {
			bEditor = true;
		} else if (qName.equalsIgnoreCase("page")) {
			bPage = true;
		} else if (qName.equalsIgnoreCase("year")) {
			bYear = true;
		} else if (qName.equalsIgnoreCase("booktitle")) {
			bBookTitle = true;
		} else if (qName.equalsIgnoreCase("address")) {
			bAddress = true;
		} else if (qName.equalsIgnoreCase("journal")) {
			bJournal = true;
		} else if (qName.equalsIgnoreCase("volume")) {
			bVolume = true;
		} else if (qName.equalsIgnoreCase("number")) {
			bNumber = true;
		} else if (qName.equalsIgnoreCase("month")) {
			bMonth = true;
		} else if (qName.equalsIgnoreCase("url")) {
			bUrl = true;
		} else if (qName.equalsIgnoreCase("ee")) {
			bEe = true;
		} else if (qName.equalsIgnoreCase("cdrom")) {
			bCdrom = true;
		} else if (qName.equalsIgnoreCase("cite")) {
			bCite = true;
		} else if (qName.equalsIgnoreCase("publisher")) {
			bPublisher = true;
		} else if (qName.equalsIgnoreCase("note")) {
			bNote = true;
		} else if (qName.equalsIgnoreCase("crossref")) {
			bCrossref = true;
		} else if (qName.equalsIgnoreCase("isbn")) {
			bIsbn = true;
		} else if (qName.equalsIgnoreCase("series")) {
			bSeries = true;
		} else if (qName.equalsIgnoreCase("school")) {
			bSchool = true;
		} else if (qName.equalsIgnoreCase("chapter")) {
			bChapter = true;
		}

	}
//this method is called every time the parser gets a closing tag'>'
	//分别将type插入�?列，各个小的类别再插入给自类别的�?
	@Override
	public void endElement(String uri, String localName, String qName)
	        throws SAXException {

		// possible types:
		// article|inproceedings|proceedings|book|incollection
		// phdthesis|mastersthesis|www
		//insert type of the input stored between tags
		if (qName.equalsIgnoreCase("article")) {
			this.elements.add(currentElement);
			this.currentElement = null;
		} else if (qName.equalsIgnoreCase("proceedings")) {
			this.elements.add(currentElement);
			this.currentElement = null;
		} else if (qName.equalsIgnoreCase("book")) {
			this.elements.add(currentElement);
			this.currentElement = null;
		} else if (qName.equalsIgnoreCase("inproceedings")) {
			this.elements.add(currentElement);
			this.currentElement = null;
		} else if (qName.equalsIgnoreCase("incollection")) {
			this.elements.add(currentElement);
			this.currentElement = null;
		} else if (qName.equalsIgnoreCase("phdthesis")) {
			this.elements.add(currentElement);
			this.currentElement = null;
		} else if (qName.equalsIgnoreCase("masterthesis")) {
			this.elements.add(currentElement);
			this.currentElement = null;
		} else if (qName.equalsIgnoreCase("www")) {
			this.elements.add(currentElement);
			this.currentElement = null;
		}

	}

	@Override
	public void characters(char ch[], int start, int length)
	        throws SAXException {

		String data = new String(ch, start, length);
//调用dblpelement，将当前节点数据插入相应对象的list，返回btitle为false
		if (bTitle) {
			currentElement.setTitle(data);
			bTitle = false;
		} else if (bAuthor) {
			//System.out.print(data + "\n");
			currentElement.getAuthors().add(data);
			bAuthor = false;
		} else if (bPage) {
			currentElement.setPage(data);
			bPage = false;
		} else if (bYear) {
			currentElement.setYear(data);
			bYear = false;
		} else if (bBookTitle) {
			currentElement.setBookTitle(data);
			bBookTitle = false;
		} else if (bAddress) {
			currentElement.setAddress(data);
			bAddress = false;
		} else if (bEditor) {
			currentElement.setEditor(data);
			bEditor = false;
		} else if (bJournal) {
			currentElement.setJournal(data);
			bJournal = false;
		} else if (bVolume) {
			currentElement.setVolume(data);
			bVolume = false;
		} else if (bNumber) {
			currentElement.setNumber(data);
			bNumber = false;
		} else if (bMonth) {
			currentElement.setMonth(data);
			bMonth = false;
		} else if (bUrl) {
			currentElement.setUrl(data);
			bUrl = false;
		} else if (bEe) {
			currentElement.setEe(data);
			bEe = false;
		} else if (bCdrom) {
			currentElement.setCdrom(data);
			bCdrom = false;
		} else if (bCite) {
			currentElement.setCite(data);
			bCite = false;
		} else if (bPublisher) {
			currentElement.setPublisher(data);
			bPublisher = false;
		} else if (bNote) {
			currentElement.setNote(data);
			bNote = false;
		} else if (bCrossref) {
			currentElement.setCrossref(data);
			bCrossref = false;
		} else if (bIsbn) {
			currentElement.setIsbn(data);
			bIsbn = false;
		} else if (bSeries) {
			currentElement.setSeries(data);
			bSeries = false;
		} else if (bSchool) {
			currentElement.setSchool(data);
			bSchool = false;
		} else if (bChapter) {
			currentElement.setChapter(data);
			bChapter = false;
		}

	}

}