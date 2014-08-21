package com.xiaozhen.cellValue;

public class ErrorValue implements Value {
	String value;
	String formula;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
}
