package com.kingsoft.value;

import com.kingsoft.value.BoolValue;
import com.kingsoft.value.CellType.ValueType;

public class ValueFactory {
	private static ValueFactory vFactory = new ValueFactory();

	private ValueFactory() {
	}

	public static ValueFactory getInstance() {
		return vFactory;
	}

	public Value creatValue(ValueType vType) {
		if (vType == ValueType.NUMERIC) {
			return new NumericValue();
		} else if (vType == ValueType.STRING) {
			return new StringValue();
		} else if (vType == ValueType.BOOLEAN) {
			return new BoolValue();
		} else if (vType == ValueType.ERROR) {
			return new ErrorValue();
		}else
			return null;

	}
}
