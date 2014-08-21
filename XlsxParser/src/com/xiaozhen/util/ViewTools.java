package com.xiaozhen.util;

import com.xiaozhen.cellValue.BoolValue;
import com.xiaozhen.cellValue.CellType.ValueType;
import com.xiaozhen.cellValue.ErrorValue;
import com.xiaozhen.cellValue.NumericValue;
import com.xiaozhen.cellValue.StringValue;
import com.xiaozhen.cellValue.Value;
import com.xiaozhen.entities.Cell;
import com.xiaozhen.entities.WorkBook;

public class ViewTools {

	private final static String TRUE_STRING = "TRUE";
	private final static String False_STRING = "FALSE";

	/**
	 * 根据workbook里的映射表查找cell的显示字符串 `
	 * 
	 * @param node
	 * 
	 * @return String
	 */
	public static String cellDisplay(Cell cell, WorkBook wBook) {
		if (cell == null || wBook == null) {
			System.err
					.println("ViewTools.cellDisplay() : cell or wBook no existed...");
			return null;
		}
		String display = null;
		ValueType c_type = cell.getType();
		Value value = cell.getValue();
		if (value == null) {// cell没有值
			return "";
		}
		if (c_type != null) {
			if (c_type.equals(ValueType.NUMERIC)) {
				double num = ((NumericValue) value).getValue();
				Double dbnObject = new Double(num);
				int int_num = dbnObject.intValue();
				if (int_num == num) {// 判断double num 是否是个整数
					display = String.valueOf(int_num);
				} else
					display = String.valueOf(num);
			} else if (c_type.equals(ValueType.STRING)) {
				display = wBook.getSharedStringList().get(
						((StringValue) value).getValue());
			} else if (c_type.equals(ValueType.BOOLEAN)) {
				if (((BoolValue) value).getValue())
					display = TRUE_STRING;
				else
					display = False_STRING;
			} else if (c_type.equals(ValueType.ERROR)) {
				display = ((ErrorValue) value).getValue();
			}
		}
		return display;
	}
}
