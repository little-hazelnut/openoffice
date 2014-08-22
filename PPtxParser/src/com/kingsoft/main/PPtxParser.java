package com.kingsoft.main;

import java.io.File;

import com.kingsoft.entities.Presentation;
import com.kingsoft.io.PPtxFile;
import com.kingsoft.xmlHandler.PresentationHandler;
import com.kingsoft.xmlHandler.PresentationRSHandler;
import com.kingsoft.xmlHandler.SAXXmlOperator;

public class PPtxParser {
	// 总体解析器，按顺序调用各个handle解析xlsx文件
	private String filePath;
	private File zipFile;
	private PPtxFile pptxFile;
	private Presentation presentation;
	private SAXXmlOperator saxXmlOperator;

	public PPtxParser(String filePath, Presentation presentation) {
		this.filePath = filePath;
		this.presentation = presentation;
	}

	public void init() {
		zipFile = new File(filePath);
		pptxFile = new PPtxFile(zipFile);
		if (!pptxFile.isAvailable()) {
			return;
		}
		parsePresentationXml();
		parsePresentationRSXml();
		
	}

	private void parsePresentationXml() { // 解析presentation信息
		System.out.println("parse presentation.xml");
		PresentationHandler presentationHandler = new PresentationHandler(presentation);
		saxXmlOperator = new SAXXmlOperator(
				pptxFile.getInputSteam("ppt/presentation.xml"),
				presentationHandler);
		saxXmlOperator.parseXml();
	}

	private void parsePresentationRSXml() {
		System.out.println("parse presentation.xml.rels");
		PresentationRSHandler pRsHandler = new PresentationRSHandler(presentation);
		saxXmlOperator = new SAXXmlOperator(
				pptxFile.getInputSteam("ppt/_rels/presentation.xml.rels"),
				pRsHandler);
		saxXmlOperator.parseXml();
	}

	public void close() {
		if (pptxFile != null) {
			pptxFile.close();
		}

	}
}
