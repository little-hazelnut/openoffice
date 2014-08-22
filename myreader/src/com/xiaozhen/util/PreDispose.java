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
	 * �ж��ļ��ı��뷽ʽ����������ļ��򷵻�null
	 * 
	 * @param file
	 *            :file
	 * @return �ļ���������
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
	 * �ж��ļ��ı����ʽ
	 * 
	 * @param file
	 *            :file
	 * @return �ļ������ʽ
	 * @throws Exception
	 */
	private static String codeString(File file) {
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			System.out.println("�Ҳ����ļ�");
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
	 * �ж��ļ��ı����ʽ,ʹ��detector��Դ��
	 * 
	 * @param file
	 *            :file
	 * @return �ļ������ʽ
	 * @throws Exception
	 */
	private static String detectTxtCode(File file) {
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		//detector.add(new ParsingDetector(false));
		detector.add(JChardetFacade.getInstance());
		// ASCIIDetector����ASCII����ⶨ
		//detector.add(ASCIIDetector.getInstance());
		// UnicodeDetector����Unicode�������Ĳⶨ
		//detector.add(UnicodeDetector.getInstance());
		java.nio.charset.Charset charset = null;
		try {
			charset = detector.detectCodepage(file.toURI().toURL());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (charset != null) {
			System.out.println(file.getName() + "�����ǣ�" + charset.name());
			return charset.name();
		} else {
			System.out.println(file.getName() + "δ֪");
			return "GBK";
		}
	}

}
