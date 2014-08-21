package com.kingsoft.util;

import com.kingsoft.entities.Cell;
import com.kingsoft.entities.WorkBook;
import com.kingsoft.value.BoolValue;
import com.kingsoft.value.CellType.ValueType;
import com.kingsoft.value.ErrorValue;
import com.kingsoft.value.NumericValue;
import com.kingsoft.value.StringValue;
import com.kingsoft.value.Value;

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
				Double dbnObject = Double.valueOf(num);
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
