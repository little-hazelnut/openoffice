package com.kingsoft.sheetreader;

import java.util.Iterator;

import com.kingsoft.entities.Cell;
import com.kingsoft.entities.Row;
import com.kingsoft.entities.Sheet;
import com.kingsoft.entities.WorkBook;
import com.kingsoft.util.ViewTools;
import com.xiaozhen.styles.CellXf;
import com.xiaozhen.styles.Font;
import com.xiaozhen.styles.Styles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class SheetView extends View {
	private WorkBook wBook;
	private Sheet sheet;
	private Row row;
	private Cell cell;
	private CellXf cellXf;
	
	private Styles styles = Styles.getInstance();

	// 表格的行数和列数,初始为默认值
	private int rowSize = 30, colSize = 26;
	// sheet的实际行数、列数
	private int sheet_rowSize;
	// 定位表格的起点X,Y
	private final static int StartX = 5;
	private final static int StartY = 5;
	// 单元格高度和宽度
	private static float gridHeight = 24f;
	private static float gridWidth = 68f;

	// 行标宽度
	private static float indexGridWidth = 46f;

	// 列标高度
	private static float indexGridHeith = 20f;

	public SheetView(Context context, WorkBook wBook) {
		super(context);
		this.wBook = wBook;
	}

	public SheetView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setSheetId(int sheetId) {
		sheet = wBook.getSheet(sheetId);
		sheet_rowSize = sheet.getLastRowIndex();
		rowSize = rowSize + sheet_rowSize;
	}

	/*
	 * @Override protected void onSizeChanged(int w, int h, int oldw, int oldh)
	 * { gridWidth = (w - 10) / (this.col * 1.0f); if (this.row > this.col) {
	 * gridHeight = (h - 100) / (this.row * 1.0f); } else { gridHeight =
	 * gridWidth; } super.onSizeChanged(w, h, oldw, oldh); }
	 */

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 画表格列标
		Paint paintRect = new Paint();
		paintRect.setColor(Color.rgb(208, 208, 208));
		paintRect.setStrokeWidth(1);
		paintRect.setStyle(Style.STROKE);
		canvas.drawRect(StartX, StartY, StartX + gridWidth * this.colSize
				+ indexGridWidth, StartY + gridHeight * this.rowSize
				+ indexGridHeith, paintRect);
		// 画表格的行和列,先画行后画列
		canvas.drawLine(StartX, StartY + indexGridHeith, StartX + this.colSize
				* gridWidth + indexGridWidth, StartY + indexGridHeith,
				paintRect);
		for (int i = 0; i < this.rowSize - 1; i++) {
			canvas.drawLine(StartX, StartY + (i + 1) * gridHeight
					+ indexGridHeith, StartX + this.colSize * gridWidth
					+ indexGridWidth, StartY + (i + 1) * gridHeight
					+ indexGridHeith, paintRect);
		}

		canvas.drawLine(StartX + indexGridWidth, StartY, StartX
				+ indexGridWidth, StartY + this.rowSize * gridHeight
				+ indexGridHeith, paintRect);
		for (int j = 0; j < this.colSize - 1; j++) {
			canvas.drawLine(StartX + (j + 1) * gridWidth + indexGridWidth,
					StartY, StartX + (j + 1) * gridWidth + indexGridWidth,
					StartY + this.rowSize * gridHeight + indexGridHeith,
					paintRect);
		}

		// 在单元格填充内容
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextAlign(Align.CENTER);
		paint.setAntiAlias(true);

		Paint paintIndex = new Paint();
		paintIndex.setColor(Color.rgb(128, 128, 128));
		paintIndex.setTextAlign(Align.CENTER);
		paintIndex.setTextSize(9); // 字体大小
		paintIndex.setAntiAlias(true); // 优化锯齿

		FontMetrics fontMetrics = paintIndex.getFontMetrics();
		float fontHeight = fontMetrics.bottom - fontMetrics.top;
		// 先填充列标
		for (int i = 0; i < this.colSize; i++) {
			float mLeft = i * gridWidth + StartX + indexGridWidth;
			float mTop = StartY;
			float mRight = mLeft + gridWidth;
			float textBaseY = (int) (indexGridHeith + fontHeight) >> 1;
			canvas.drawText(String.valueOf(((char) ('A' + i))),
					(int) (mLeft + mRight) >> 1, textBaseY + mTop, paintIndex);
		}
		// 填单元格
		String cellContent;
		int CellCol;
		for (int i = 0; i < this.rowSize; i++) {
			// 填充行标
			float mLeft = 0;
			float mTop = i * gridHeight + StartY + indexGridHeith;
			float mRight = mLeft + indexGridWidth;
			float textBaseY = (int) (gridHeight + fontHeight) >> 1;
			canvas.drawText(String.valueOf(i + 1), (int) (mLeft + mRight) >> 1,
					textBaseY + mTop, paintIndex);
			if ((row = sheet.getRow(i + 1)) != null) {
				Iterator<Cell> it = row.getCellsIteartor();
				while (it.hasNext()) {
					cell = it.next();
					CellCol = cell.getId();
					short style = cell.getStyle();
					cellXf = styles.getCellXf(style);
					Font font = styles.getFontStyle(cellXf.getFontId());
					paint.setTextSize(font.getSize());
					if (font.isBold()) {
						paint.setTypeface(Typeface.DEFAULT_BOLD);
					}
					if (cell.getValue() != null) {// 填充内容
						cellContent = ViewTools.cellDisplay(cell, wBook);
						mLeft = (CellCol - 1) * gridWidth + StartX
								+ indexGridWidth;
						mTop = i * gridHeight + StartY + indexGridHeith;
						mRight = mLeft + gridWidth;
						textBaseY = (int) (gridHeight + fontHeight) >> 1;
						canvas.drawText(cellContent + "",
								(int) (mLeft + mRight) >> 1, textBaseY + mTop,
								paint);
					}
					resetPaint(paint);
				}
			}
		}

	}

	@Override
	public void scrollTo(int x, int y) {
		// TODO Auto-generated method stub
		super.scrollTo(x, y);
	}
	
	public void resetPaint(Paint paint) {
		paint.reset();
		paint.setColor(Color.BLACK);
		paint.setTextAlign(Align.CENTER);
		paint.setAntiAlias(true);
	}
}
