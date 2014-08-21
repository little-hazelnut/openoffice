package com.xiaozhen.control;

import java.util.Map;
import java.util.TreeMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.xiaozhen.cellValue.BoolValue;
import com.xiaozhen.cellValue.CellType.ValueType;
import com.xiaozhen.cellValue.ErrorValue;
import com.xiaozhen.cellValue.NumericValue;
import com.xiaozhen.cellValue.StringValue;
import com.xiaozhen.cellValue.Value;
import com.xiaozhen.cellValue.ValueFactory;
import com.xiaozhen.entities.Cell;
import com.xiaozhen.entities.Row;
import com.xiaozhen.entities.Sheet;
import com.xiaozhen.util.SheetTools;

public class SheetHandler extends DefaultHandler {

	private Cell cell;
	private Value value;
	private Sheet sheet;
	private Map<Integer, Row> rowMap;
	private Row row;
	private String rowId;
	private Map<Integer, Cell> cellMap;

	private final String RQNAME_r = "r";
	private final String RQNAME_x14ac_dyDescent = "x14ac:dyDescent";
	private final String RQNAME_spans = "spans";

	public SheetHandler(Sheet sheet) {
		super();
		this.sheet = sheet;
	}

	private final String TAG_row = "row";
	private final String TAG_c = "c";
	private final String TAG_v = "v";
	private final String TAG_f = "f";

	private final String CTAG_r = "r";
	private final String CTAG_s = "s";
	private final String CTAG_t = "t";

	private final String CELLTYPE_s = "s";
	private final String CELLTYPE_e = "e";
	private final String CELLTYPE_b = "b";

	private StringBuilder sb = new StringBuilder();

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		sb.append(ch, start, length);
	}

	@Override
	public void startDocument() throws SAXException {
		rowMap = new TreeMap<Integer, Row>();
	}

	@Override
	public void endDocument() throws SAXException {
		sheet.setRows(rowMap);
		rowMap = null;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		sb.setLength(0);
		if (qName.equals(TAG_row)) {// 新的一行
			row = new Row();
			if (attributes.getLength() > 0) {
				rowId = attributes.getValue(RQNAME_r);
				row.setRowId(Integer.parseInt(rowId));
				row.setX14ac_dyDescent(attributes
						.getValue(RQNAME_x14ac_dyDescent));
				row.setSpans(attributes.getValue(RQNAME_spans));
			}
			cellMap = new TreeMap<Integer, Cell>();
		}
		if (qName.equals(TAG_c)) {
			cell = new Cell();
			if (attributes.getLength() > 0) {
				String c_aValue;
				if ((c_aValue = attributes.getValue(CTAG_r)) != null) {// 解析c标签的r属性，即c位置
					String cellColstr = c_aValue.substring(0,
							c_aValue.indexOf(rowId));// 解析列标志字母
					cell.setId(SheetTools.ColStrToInt(cellColstr));
				}

				if ((c_aValue = attributes.getValue(CTAG_t)) == null) {// 解析类型属性t,默认为数值型
					cell.setType(ValueType.NUMERIC);
				} else {
					if (c_aValue.equals(CELLTYPE_s)) {
						cell.setType(ValueType.STRING);
					} else if (c_aValue.equals(CELLTYPE_b)) {
						cell.setType(ValueType.BOOLEAN);
					} else if (c_aValue.equals(CELLTYPE_e)) {
						cell.setType(ValueType.ERROR);
					}
				}

				if ((c_aValue = attributes.getValue(CTAG_s)) != null) {// 解析样式属性s
					cell.setStyle(Short.parseShort(c_aValue));
				}
			}

		}

		if (qName.equals(TAG_v) || qName.equals(TAG_f)) {
			if (value == null) {
				value = ValueFactory.getInstance().creatValue(cell.getType()); // 根据属性生产value
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		String valueStr = sb.toString();
		if (cell != null) {
			if (qName.equals(TAG_f)) // function
				value.setFormula(valueStr);

			if (qName.equals(TAG_v)) // value 设置value内容
				if (cell.getType() == ValueType.NUMERIC) {
					((NumericValue) value).setValue(Double
							.parseDouble(valueStr));
				} else if (cell.getType() == ValueType.STRING) {
					((StringValue) value).setValue(Integer.parseInt(valueStr));
				} else if (cell.getType() == ValueType.BOOLEAN) {
					if (valueStr.equals("1"))
						((BoolValue) value).setValue(true);
					else {
						((BoolValue) value).setValue(false);
					}
				} else if (cell.getType() == ValueType.ERROR) {
					((ErrorValue) value).setValue(valueStr);
				}

			if (qName.equals(TAG_c)) { // c标签结束
				cell.setValue(value);
				cellMap.put(cell.getId(), cell);
				cell = null;
				value = null;
			}
		} else if (qName.equals(TAG_row)) {// 行结束
			row.setCells(cellMap);
			rowMap.put(row.getRowId(), row);
			cellMap = null;
			row = null;
		}

	}

}
