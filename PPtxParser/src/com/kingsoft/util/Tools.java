package com.kingsoft.util;

public class Tools {
	public static short rIdStrToShort(String rId){
		String id = rId.substring(3);
		return Short.parseShort(id);
	}
}
