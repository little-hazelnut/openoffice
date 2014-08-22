package com.kingsoft.main;

import com.kingsoft.entities.Presentation;

public class Main {
	public static void main(String[] args) {
		String filePath = "C:/Users/Administrator/Desktop/testpptx/ppt2.pptx";
		Presentation presentation = new Presentation();
		PPtxParser PPTXparser = new PPtxParser(filePath, presentation);
		PPTXparser.init();
		PPTXparser.close();	
	}
}
