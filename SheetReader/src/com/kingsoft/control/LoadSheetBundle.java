package com.kingsoft.control;

//载入sheet回调接口
public interface LoadSheetBundle {
	public void setLoadSheetFlag(int sheetIndex);

	public void initLoadSheetFlag(int sheetSize);

	public boolean getSheetFlag(int sheetIndex);

}
