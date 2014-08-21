package com.kingsoft.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.kingsoft.entities.Sheet;
import com.kingsoft.entities.WorkBook;

public class WorkBookHandler extends DefaultHandler {

	private final String TAG_sheet = "sheet";

	private final String QNAME_name = "name";
	private final String QNAME_sheetId = "sheetId";
	private final String QNAME_rId = "r:id";

	WorkBook wBook;
	List<Sheet> sheets;

	public WorkBookHandler(WorkBook wBook) {
		super();
		this.wBook = wBook;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

	}

	@Override
	public void endDocument() throws SAXException {
		wBook.setSheets(sheets);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

	}

	@Override
	public void startDocument() throws SAXException {
		sheets = new ArrayList<Sheet>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals(TAG_sheet)) {
			Sheet sheet = new Sheet();
			if (attributes.getLength() > 0) {
				sheet.setName(attributes.getValue(QNAME_name));
				sheet.setSheetId(attributes.getValue(QNAME_sheetId));
				sheet.setRId(attributes.getValue(QNAME_rId));
			}
			sheets.add(sheet);
			sheet = null;
		}

	}

}
