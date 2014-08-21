package com.xiaozhen.main;

import com.xiaozhen.entities.WorkBook;

public class LoadSheet implements Runnable {

	XlsxParser xParser;

	public LoadSheet(String filePath, WorkBook wBook) {
		xParser = new XlsxParser(filePath, wBook);
	}

	@Override
	public void run() {
		xParser.init();
		xParser.close();
	}
}
