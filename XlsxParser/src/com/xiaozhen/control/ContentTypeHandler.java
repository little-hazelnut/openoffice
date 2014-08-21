package com.xiaozhen.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.xiaozhen.model.XFileDir;

public class ContentTypeHandler extends DefaultHandler {

	private final String TAG_Override = "Override";
	
	private final String QName_CT = "ContentType";
	private final String QName_PN = "PartName";
	
	private final static String CY_worksheet = "worksheet+xml";
	private final static String CY_sharedStrings = "sharedStrings+xml";
	//private final static String CY_relationships = "relationships+xml";
	//private final static String CY_main = "sheet.main+xml";
	//private final static String CY_theme = "theme+xml";
	//private final static String CY_styles = "styles+xml";	
	//private final static String CY_calcChain = "calcChain+xml";
	//private final static String CY_core = "core-properties+xml";
	//private final static String CY_app = "extended-properties+xml";

	Map<String, Object> dirMap; // 文件目录map
	List<String> sheetFlieNames; // sheet路径map
	XFileDir xFileDir;

	public ContentTypeHandler(XFileDir xFileDir) {
		super();
		this.xFileDir = xFileDir;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
	}

	@Override
	public void endDocument() throws SAXException {
		xFileDir.setFileDir(dirMap);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
	}

	@Override
	public void startDocument() throws SAXException {
		dirMap = new HashMap<String, Object>();
		sheetFlieNames = new ArrayList<String>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals(TAG_Override)) {
			if (attributes.getLength() == 2) {
				String contentTypeStr = attributes.getValue(QName_CT);
				String partNameStr = attributes.getValue(QName_PN);
				if (partNameStr.charAt(0) == '/') {
					partNameStr = partNameStr.substring(1);
				}
				if (contentTypeStr.endsWith(CY_sharedStrings)) {// sharedStrings.xml
					dirMap.put(XFileDir.DirKEY_sharedStrings, partNameStr);
				} else if (contentTypeStr.endsWith(CY_worksheet)) {
					sheetFlieNames.add(partNameStr);
					if (!dirMap.containsKey(XFileDir.DirKEY_worksheet))
						dirMap.put(XFileDir.DirKEY_worksheet, sheetFlieNames);
				}
			}
		}
	}

}
