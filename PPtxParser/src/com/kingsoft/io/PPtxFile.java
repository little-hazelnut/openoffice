package com.kingsoft.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class PPtxFile {

	// xlsx文件类，提供zip文件读取功能

	private File file;
	private ZipFile zipFile;
	private List<ZipEntry> ZipEntryList;

	public PPtxFile(File file) {
		this.file = file;
		init();
	}

	public boolean isAvailable() {// 文件是否有效
		if (zipFile != null) {
			return true;
		} else
			return false;
	}

	private void init() {
		if (file == null || !file.exists() || !file.isFile()) {
			System.out.println("init fail...");
			return;
		}
		try {
			zipFile = new ZipFile(file);
		} catch (ZipException e) {
			System.err.println("open file fail...");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<ZipEntry> getAllEntry() {
		ZipEntryList = new ArrayList<ZipEntry>();
		@SuppressWarnings("unchecked")
		Enumeration<ZipEntry> zipEntries = (Enumeration<ZipEntry>) zipFile
				.entries();
		while (zipEntries.hasMoreElements()) {
			ZipEntry zipEntry = (ZipEntry) zipEntries.nextElement();
			ZipEntryList.add(zipEntry);
		}
		return ZipEntryList;
	}

	public ZipEntry getZipEntry(String fileName) {
		return zipFile.getEntry(fileName);
	}

	// 由zipEntry对象获得文件输入流
	public InputStream getInputSteam(ZipEntry zipEntry) {
		InputStream iStream = null;
		try {
			iStream = zipFile.getInputStream(zipEntry);
		} catch (IOException e) {
			System.err.println("getInputSteam fail...");
			e.printStackTrace();
		}
		return iStream;
	}

	// 由文件名获得文件输入流
	public InputStream getInputSteam(String fileName) {
		InputStream iStream = null;
		try {
			iStream = zipFile.getInputStream(getZipEntry(fileName));
		} catch (IOException e) {
			System.err.println("getInputSteam fail...");
			e.printStackTrace();
		}
		return iStream;
	}

	// 关闭XlsxFile
	public void close() {
		if (zipFile != null) {
			try {
				zipFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
