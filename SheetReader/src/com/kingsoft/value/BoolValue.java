package com.kingsoft.value;


public class BoolValue implements Value {
	boolean value;

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	@Override
	public void setFormula(String str) {
	}

}
