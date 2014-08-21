package com.kingsoft.thread;

import com.kingsoft.control.LoadSheetBundle;
import com.kingsoft.entities.WorkBook;
import com.kingsoft.main.XlsxParser;

public class LoadSheet implements Runnable {
	LoadSheetBundle lsbundle;
	XlsxParser xlsxParser;
	WorkBook wBook;

	public LoadSheet(LoadSheetBundle lsbundle, String filePath, WorkBook wBook) {
		this.lsbundle = lsbundle;
		this.wBook = wBook;
		xlsxParser = new XlsxParser(filePath, wBook);
	}

	@Override
	public void run() {
		xlsxParser.init();
		notifyInitComplete(wBook.getSheetsSize());
		byte activeTab = wBook.getActiveTab();
		xlsxParser.parseSheetXml(wBook.getSheet(activeTab));
		notifyLoadSheetComplete(activeTab);
		for (int i = 0; i < wBook.getSheetsSize(); i++) {
			if (i != activeTab) {
				xlsxParser.parseSheetXml(wBook.getSheet(i));
				notifyLoadSheetComplete(i);
			}
		}
		xlsxParser.close();
	}

	public void notifyLoadSheetComplete(int sheetIndex) {
		lsbundle.setLoadSheetFlag(sheetIndex);
	}

	public void notifyInitComplete(int sheetSize) {
		lsbundle.initLoadSheetFlag(sheetSize);
	}
}
