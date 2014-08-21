package com.xiaozhen.control;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.xiaozhen.entities.WorkBook;

public class RelationShipHandler extends DefaultHandler {

	private final String TAG_Relationship = "Relationship";

	private final String QNAME_Id = "Id";
	private final String QNAME_Target = "Target";

	WorkBook workBook;
	Map<String, String> relationShips;

	public RelationShipHandler(WorkBook workBook) {
		super();
		this.workBook = workBook;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
	}

	@Override
	public void endDocument() throws SAXException {
		workBook.setRelationShips(relationShips);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
	}

	@Override
	public void startDocument() throws SAXException {
		relationShips = new HashMap<String, String>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals(TAG_Relationship)) {
			if (attributes.getLength()>0) {
				relationShips.put(attributes.getValue(QNAME_Id), attributes.getValue(QNAME_Target));	
			}	
		}
	}
}
