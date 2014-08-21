package com.kingsoft.main;

import java.io.File;

import com.kingsoft.entities.Sheet;
import com.kingsoft.entities.WorkBook;
import com.kingsoft.io.XlsxFile;
import com.kingsoft.xmlHandler.RelationShipHandler;
import com.kingsoft.xmlHandler.SAXXmlOperator;
import com.kingsoft.xmlHandler.SharedStringsHandler;
import com.kingsoft.xmlHandler.SheetHandler;
import com.kingsoft.xmlHandler.StylesHandler;
import com.kingsoft.xmlHandler.WorkBookHandler;

public class XlsxParser {
	// 总体解析器，按顺序调用各个handle解析xlsx文件
	private String filePath;
	private File zipFile;
	private XlsxFile xlsxFile;
	private WorkBook wBook;
	private SAXXmlOperator saxXmlOperator;

	public XlsxParser(String filePath, WorkBook wBook) {
		this.filePath = filePath;
		this.wBook = wBook;
	}

	public void init() {
		zipFile = new File(filePath);
		xlsxFile = new XlsxFile(zipFile);
		if (!xlsxFile.isAvailable()) {
			return;
		}
		parseWorkBookXml();
		parseRelationShipXml();
		parseSharedStringsXml();
		parseStylesXml();
		//parseAllSheetXml(wBook);
	}
	
	private void parseAllSheetXml(WorkBook wBook){//解析所有的sheet
		int sheetsSize = wBook.getSheetsSize();
		for (int i = 0; i < sheetsSize; i++) {
			parseSheetXml(wBook.getSheet(i));
		}
	}

	private void parseWorkBookXml() { // 解析workbook信息
		System.out.println("parse workbook.xml");
		WorkBookHandler wBookHandler = new WorkBookHandler(wBook);
		saxXmlOperator = new SAXXmlOperator(
				xlsxFile.getInputSteam("xl/workbook.xml"), wBookHandler);
		saxXmlOperator.parseXml();
	}

	private void parseRelationShipXml() {
		System.out.println("parse workbook.xml.rels");
		RelationShipHandler rShipHandler = new RelationShipHandler(wBook);
		saxXmlOperator = new SAXXmlOperator(
				xlsxFile.getInputSteam("xl/_rels/workbook.xml.rels"),
				rShipHandler);
		saxXmlOperator.parseXml();
	}

	private void parseSharedStringsXml() {
		System.out.println("parse sharedStrings.xml");
		SharedStringsHandler ssHandler = new SharedStringsHandler(wBook);
		saxXmlOperator = new SAXXmlOperator(
				xlsxFile.getInputSteam("xl/sharedStrings.xml"), ssHandler);
		saxXmlOperator.parseXml();
	}

	private void parseStylesXml() {
		System.out.println("parse styles.xml");
		StylesHandler stylesHandler = new StylesHandler();
		saxXmlOperator = new SAXXmlOperator(
				xlsxFile.getInputSteam("xl/styles.xml"), stylesHandler);
		saxXmlOperator.parseXml();
	}

	public void parseSheetXml(Sheet sheet) {
		System.out.println("parse sheet : " + sheet.getName());
		SheetHandler sHandler = new SheetHandler(sheet);
		String sPath = "xl/" + wBook.getRelationShips().get(sheet.getRId());
		System.out.println("sheet path : " + sPath);
		saxXmlOperator = new SAXXmlOperator(xlsxFile.getInputSteam(sPath),
				sHandler);
		saxXmlOperator.parseXml();
	}

	public void close() {
		if (xlsxFile != null) {
			xlsxFile.close();
		}

	}
}
