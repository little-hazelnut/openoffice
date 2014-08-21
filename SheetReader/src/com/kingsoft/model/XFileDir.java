package com.kingsoft.model;

import java.util.Map;

public class XFileDir {
	
	public final static String DirKEY_sharedStrings = "sharedStrings";
	public final static String DirKEY_worksheet = "worksheet";
	
	//xlsx文件目录，通过解析[Content_Types].xml得到
	private Map<String, Object> fileDir;

	public Map<String, Object> getFileDir() {
		return fileDir;
	}
	
	public void setFileDir(Map<String, Object> fileDir) {
		this.fileDir = fileDir;
	}

}
