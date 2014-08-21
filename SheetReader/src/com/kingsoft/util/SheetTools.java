package com.kingsoft.util;

public class SheetTools {

	public static int ColStrToInt(String str) {// 由字母得到列号
		int strLen = str.length();
		int colnum = 0;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			colnum += pow(26, strLen - 1) * (ch - 64);
			strLen--;
		}
		return colnum;
	}

	public static int pow(int x, int y) { // x的y次方
		int result = 1;
		for (int i = 0; i < y; i++) {
			result *= x;
		}
		return result;
	}

	public static int rgbStringToInt(String rgb) {// 将RGB字符串转换为int
		String temp = rgb.substring(2, rgb.length());
		return Integer.parseInt(temp, 16);
	}

	public static String intToRgbString(int rgb) {// 将int类型RGB换为字符串转
		return "FF" + Integer.toString(rgb, 16).toUpperCase();
	}
}
