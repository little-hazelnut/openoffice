package com.xiaozhen.control;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.xiaozhen.entities.WorkBook;

public class SharedStringsHandler extends DefaultHandler {

	// private final String TAG_sst = "sst";
	// private final String TAG_si = "si";
	private final String TAG_t = "t";

	private WorkBook wBook;
	private StringBuilder sb = new StringBuilder();
	private List<String> sharedStringList;
	
	public SharedStringsHandler(WorkBook wBook){
		this.wBook = wBook;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		sb.append(ch, start, length);
	}

	@Override
	public void startDocument() throws SAXException {
		// System.out.println("startDocument...");
		sharedStringList = new ArrayList<String>();
	}

	@Override
	public void endDocument() throws SAXException {
		wBook.setSharedStringList(sharedStringList);
		// System.out.println("endDocument...");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// System.out.println("startElement...");
		sb.setLength(0);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// System.out.println("endElement...");
		String value = sb.toString();
		if (TAG_t.equals(qName)) {
			sharedStringList.add(value);
		}
	}

}
