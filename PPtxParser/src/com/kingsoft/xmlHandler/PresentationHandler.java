package com.kingsoft.xmlHandler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.kingsoft.entities.Presentation;
import com.kingsoft.entities.Slide;
import com.kingsoft.entities.SlideMaster;
import com.kingsoft.util.Tools;

public class PresentationHandler extends DefaultHandler {

	private Presentation presentation;
	private List<SlideMaster> sldMasterList;
	private List<Slide> sldList;

	// share var
	String strVar;
	SlideMaster slideMaster;
	Slide slide;
	// TAG
	private final String P_SldMasterId = "p:sldMasterId";
	private final String P_SldId = "p:sldId";
	// Qname
	private final String R_Id = "r:id";
	private final String ID = "id";

	public PresentationHandler(Presentation presentation) {
		this.presentation = presentation;
	}

	@Override
	public void startDocument() throws SAXException {
		sldMasterList = new ArrayList<SlideMaster>();
		sldList = new ArrayList<Slide>();
		presentation.setSldMasterList(sldMasterList);
		presentation.setSldList(sldList);
	}

	@Override
	public void endDocument() throws SAXException {
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals(P_SldMasterId)) {
			slideMaster = new SlideMaster();
			if ((strVar = attributes.getValue(R_Id)) != null) {// r:id
				slideMaster.setRelationId(Tools.rIdStrToShort(strVar));
			}
			if ((strVar = attributes.getValue(ID)) != null) {// id
				slideMaster.setId(Long.parseLong(strVar));
			}
			sldMasterList.add(slideMaster);
			slideMaster = null;
		} else if (qName.equals(P_SldId)) {
			slide = new Slide();
			if ((strVar = attributes.getValue(R_Id)) != null) {// r:id
				slide.setRelationId(Tools.rIdStrToShort(strVar));
			}
			if ((strVar = attributes.getValue(ID)) != null) {// id
				slide.setId(Integer.parseInt(strVar));
			}
			sldList.add(slide);
			slide = null;
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
		if (presentation != null)
			presentation = null;
		if (sldMasterList != null)
			sldMasterList = null;
		if (sldList != null)
			sldList = null;
	}

}
