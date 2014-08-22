package com.xiaozhen.util;

import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class PreDispose {

	/**
	 * 判断文件的编码方式，如果不是文件则返回null
	 * 
	 * @param file
	 *            :file
	 * @return 文件编码类型
	 */
	public static String TxTcode(File file) {
		if (file == null)
			return null;
		if (!file.isFile())
			return null;
		String filename = file.getName().trim().toLowerCase();
		if (filename.endsWith(".txt") || filename.endsWith(".log")) {
			String code = codeString(file);
			System.out.println(code);
			return code;
		} else
			return null;
	}

	/**
	 * 判断文件的编码格式
	 * 
	 * @param file
	 *            :file
	 * @return 文件编码格式
	 * @throws Exception
	 */
	private static String codeString(File file) {
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			System.out.println("找不到文件");
		}
		int p = 0;
		try {
			p = (bis.read() << 8) + bis.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String code = null;

		switch (p) {
		case 0xefbb:
			code = "UTF-8";
			break;
		case 0xfffe:
			code = "Unicode";
			break;
		case 0xfeff:
			code = "UTF-16BE";
			break;
		default:{
				code = detectTxtCode(file);
				//code = "GBK";
			}
		}
		try {
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return code;
	}

	/**
	 * 判断文件的编码格式,使用detector开源库
	 * 
	 * @param file
	 *            :file
	 * @return 文件编码格式
	 * @throws Exception
	 */
	private static String detectTxtCode(File file) {
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		//detector.add(new ParsingDetector(false));
		detector.add(JChardetFacade.getInstance());
		// ASCIIDetector用于ASCII编码测定
		//detector.add(ASCIIDetector.getInstance());
		// UnicodeDetector用于Unicode家族编码的测定
		//detector.add(UnicodeDetector.getInstance());
		java.nio.charset.Charset charset = null;
		try {
			charset = detector.detectCodepage(file.toURI().toURL());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (charset != null) {
			System.out.println(file.getName() + "编码是：" + charset.name());
			return charset.name();
		} else {
			System.out.println(file.getName() + "未知");
			return "GBK";
		}
	}

}
