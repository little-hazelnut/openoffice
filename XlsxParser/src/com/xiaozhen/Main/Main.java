package com.xiaozhen.main;

import java.util.Iterator;

import com.xiaozhen.entities.Cell;
import com.xiaozhen.entities.Row;
import com.xiaozhen.entities.Sheet;
import com.xiaozhen.entities.WorkBook;
import com.xiaozhen.styles.CellXf;
import com.xiaozhen.styles.Styles;
import com.xiaozhen.util.ViewTools;

public class Main {
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		String filePath = "C:/Users/Administrator/Desktop/testxlsx/testxlsx(2M).xlsx";
		WorkBook wBook = new WorkBook();
		XlsxParser xParser = new XlsxParser(filePath, wBook);
		xParser.init();
		xParser.close();
		// Cell cell = getCell(wBook, 2, 1, 2);
		// ViewTools.cellDisplay(cell, wBook);
		Styles styles = Styles.getInstance();
		System.out.println(styles.toString());
		Iterator<CellXf> CellxfIt = styles.getCellxfsIterator();
		CellXf cellXf = null;
		int i = 0;
		while (CellxfIt.hasNext()) {
			cellXf = CellxfIt.next();
			System.out.println("-----------------------------------------");
			System.out.println("cellXfId: " + i);
			System.out.println(cellXf.toString());
			System.out.println(styles.getBorderStyle(cellXf.getBorderId()));
			System.out.println(styles.getFillStyle(cellXf.getFillId()));
			System.out.println(styles.getFontStyle(cellXf.getFontId()));
			System.out.println("NumFmt "
					+ styles.getNumFmtStyle(cellXf.getNumFmtId()));
			i++;
		}
		printSheet(wBook, 1);
		System.out.println("time spend : "
				+ (float) (System.currentTimeMillis() - time) / 1000 + " s ");
	}

	public static Cell getCell(WorkBook wBook, int sheetNum, int rowNum,
			int colNum) {

		Sheet sheet;
		Row row;
		Cell cell = null;

		sheet = wBook.getSheet(sheetNum);
		if (sheet != null) {
			row = sheet.getRow(rowNum);
			if (row != null) {
				cell = row.getCell(colNum);
			}
		}
		return cell;
	}

	public static void printSheet(WorkBook wBook, int sheetNum) {
		Sheet sheet = wBook.getSheet(sheetNum);
		if (sheet == null) {
			System.err.println("sheet no existed");
			return;
		}
		int lastLowIndex = sheet.getLastRowIndex();
		Row row;
		Cell cell;
		for (int i = 1; i < lastLowIndex + 1; i++) {
			row = sheet.getRow(i);
			System.out.print("row " + i + " : \t");
			if (row != null) {
				int lastCellIndex = row.getLastCellIndex();
				for (int j = 1; j < lastCellIndex + 1; j++) {
					cell = row.getCell(j);
					if (cell != null) {
						System.out.print(ViewTools.cellDisplay(cell, wBook)
								+ "\t");
					} else {
						System.out.print("\t");
					}
				}
				System.out.println();
			} else {
				System.out.println();
			}
		}
	}
}
