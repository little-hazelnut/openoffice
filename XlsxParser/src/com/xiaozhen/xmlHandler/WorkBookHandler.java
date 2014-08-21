package com.xiaozhen.xmlHandler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.xiaozhen.entities.Sheet;
import com.xiaozhen.entities.WorkBook;

public class WorkBookHandler extends DefaultHandler {

	private final String TAG_sheet = "sheet";
	private final String TAG_workbookView = "workbookView";

	private final String QNAME_name = "name";
	private final String QNAME_sheetId = "sheetId";
	private final String QNAME_rId = "r:id";

	private final String wBookView_activeTab = "activeTab";
	private final String wBookView_windowHeight = "windowHeight";
	private final String wBookView_windowWidth = "windowWidth";
	private final String wBookView_yWindow = "yWindow";
	private final String wBookView_xWindow = "xWindow";

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
		String attriVal;
		if (qName.equals(TAG_sheet)) {// 解析sheet标签
			Sheet sheet = new Sheet();
			if (attributes.getLength() > 0) {
				sheet.setName(attributes.getValue(QNAME_name));
				sheet.setSheetId(attributes.getValue(QNAME_sheetId));
				sheet.setRId(attributes.getValue(QNAME_rId));
			}
			sheets.add(sheet);
			sheet = null;
		} else if (qName.equals(TAG_workbookView)) {// 解析workbookView标签
			if (attributes.getLength() > 0) {
				if ((attriVal = attributes.getValue(wBookView_activeTab))!=null) {//activeTab
					wBook.setActiveTab(Byte.parseByte(attriVal));
				}
				if ((attriVal = attributes.getValue(wBookView_windowHeight))!=null) {//windowHeight
					wBook.setWindowHeight(Integer.parseInt(attriVal));
				}
				if ((attriVal = attributes.getValue(wBookView_windowWidth))!=null) {//windowWidth
					wBook.setWindowWidth(Integer.parseInt(attriVal));
				}
				if ((attriVal = attributes.getValue(wBookView_yWindow))!=null) {//yWindow
					wBook.setyWindow(Integer.parseInt(attriVal));
				}
				if ((attriVal = attributes.getValue(wBookView_xWindow))!=null) {//xWindow
					wBook.setyWindow(Integer.parseInt(attriVal));
				}
			}
		}

	}

}
