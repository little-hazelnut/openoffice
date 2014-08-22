package com.kingsoft.xmlHandler;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.kingsoft.entities.Presentation;
import com.kingsoft.util.Tools;

public class PresentationRSHandler extends DefaultHandler {

	private Presentation presentation;
	private Map<Short, String> relationMap;
	// Tag
	private final String RELATIONSHIP = "Relationship";
	// Qname
	private final String TARGET = "Target";
	private final String ID = "Id";

	public PresentationRSHandler(Presentation presentation) {
		this.presentation = presentation;
	}

	@Override
	public void startDocument() throws SAXException {
		relationMap = new HashMap<Short, String>();
	}

	@Override
	public void endDocument() throws SAXException {
		presentation.setRelationMap(relationMap);
		try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals(RELATIONSHIP)) {
			String target = attributes.getValue(TARGET);
			short rId = Tools.rIdStrToShort(attributes.getValue(ID));
			relationMap.put(rId, target);
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

	}

	@Override
	protected void finalize() throws Throwable {
		relationMap = null;
		presentation = null;
	}
}
