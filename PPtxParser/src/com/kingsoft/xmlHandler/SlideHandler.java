package com.kingsoft.xmlHandler;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.kingsoft.entities.NvSpPr;
import com.kingsoft.entities.NvSpPr.PhType;
import com.kingsoft.entities.Picture;
import com.kingsoft.entities.Shape;
import com.kingsoft.entities.Slide;
import com.kingsoft.entities.SpTree;

public class SlideHandler extends DefaultHandler {

	Slide slide;
	SpTree spTree;
	List<Shape> spList;
	List<Picture> picList;

	// share var
	String strVal;
	Shape shape;
	NvSpPr nvSpPr;

	// Tag
	private final String P_sp = "p:sp";
	private final String P_nvSpPr = "p:nvSpPr";
	private final String P_cNvPr = "p:cNvPr";
	private final String A_spLocks = "a:spLocks";
	private final String P_ph = "p:ph";

	private final String P_Pic = "p:pic";

	// Qname
	private final String NAME = "name";
	private final String ID = "id";
	private final String NOGRP = "noGrp";
	private final String TYPE = "type";
	
	//phType
	private final String BODY = "body";
	private final String CHART = "chart";
	private final String CLIPART = "clipArt";
	private final String CTRTITLE = "ctrTitle";
	private final String DGM = "dgm";
	private final String DT = "dt";
	private final String FTR = "ftr";
	private final String HDR = "hdr";
	private final String MEDIA = "media";
	private final String OBJ = "obj";
	private final String PIC = "pic";
	private final String SLDIMG ="sldImg";
	private final String SLDNUM = "sldNum";
	private final String SUBTITLE = "subTitle";
	private final String TBL = "tbl";
	private final String TITLE = "title";
	

	public SlideHandler(Slide slide) {
		this.slide = slide;
	}

	@Override
	public void startDocument() throws SAXException {
		spTree = new SpTree();
		slide.setSpTree(spTree);
	}

	@Override
	public void endDocument() throws SAXException {
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals(P_sp)) {
			if (spList == null) {
				spList = new ArrayList<Shape>();
				spTree.setSpList(spList);
			}
			shape = new Shape();
			spList.add(shape);
		} else if (qName.equals(P_nvSpPr)) {
			nvSpPr = new NvSpPr();
			shape.setNvSpPr(nvSpPr);
		} else if (qName.equals(P_cNvPr)) {
			if ((strVal = attributes.getValue(NAME)) != null) {
				nvSpPr.setcNvPr_name(strVal);
			}
			if ((strVal = attributes.getValue(ID)) != null) {
				nvSpPr.setcNvPr_id(Integer.parseInt(strVal));
			}
		}else if(qName.equals(A_spLocks)){
			if ((strVal = attributes.getValue(NOGRP)) != null) {
				nvSpPr.setSpLocks_noGrp(Short.parseShort(strVal));
			}
		}else if(qName.equals(P_ph)){
			if ((strVal = attributes.getValue(TYPE)) != null){
				nvSpPr.setNvPr_ph(getPhType(strVal));
			}
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
	
	private PhType getPhType(String phType){
		if (phType.equals(BODY)) {
			return PhType.body;
		}else if (phType.equals(CHART)) {
			return PhType.chart;
		}else if (phType.equals(CLIPART)) {
			return PhType.clipArt;
		}else if (phType.equals(CTRTITLE)) {
			return PhType.ctrTitle;
		}else if (phType.equals(DGM)) {
			return PhType.dgm;
		}else if (phType.equals(DT)) {
			return PhType.dt;
		}else if (phType.equals(FTR)) {
			return PhType.ftr;
		}else if (phType.equals(HDR)) {
			return PhType.hdr;
		}else if (phType.equals(MEDIA)) {
			return PhType.media;
		}else if (phType.equals(OBJ)) {
			return PhType.obj;
		}else if (phType.equals(PIC)) {
			return PhType.pic;
		}else if (phType.equals(SLDIMG)) {
			return PhType.sldImg;
		}else if (phType.equals(SLDNUM)) {
			return PhType.sldNum;
		}else if (phType.equals(SUBTITLE)) {
			return PhType.subTitle;
		}else if (phType.equals(TBL)) {
			return PhType.tbl;
		}else if (phType.equals(TITLE)) {
			return PhType.title;
		}else {
			return null;
		}
	}

}
