package com.kingsoft.xmlHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXXmlOperator {

	InputStream inputStream;
	DefaultHandler dhandler;

	public SAXXmlOperator() {
		super();
	}

	public SAXXmlOperator(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public SAXXmlOperator(InputStream inputStream, DefaultHandler dhandler) {
		this.inputStream = inputStream;
		this.dhandler = dhandler;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setHandler(DefaultHandler dhandler) {
		this.dhandler = dhandler;
	}

	public void parseXml() {
		if (inputStream == null || dhandler == null) {
			System.err
					.println("please check inputStream is existed or if defaultHandler is set");
			return;
		}

		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			saxParser.parse(inputStream, dhandler);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
