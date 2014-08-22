package com.xiaozhen.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import android.R.integer;

public class TxTio {

	private File file = null;
	private FileInputStream fis = null;
	private BufferedReader br = null;
	private String code = null; // 字符编码
	private int io_position = 0; // 字符缓存块在文本io位置，以bufferCapacity为一个单位
	private char[] buffer = null; // 字符缓存
	private boolean is_TxTend = false; // 文本是否已经读完
	private int read_size = 0; // 每次显示需要的字符
	private final int bufferCapacity = 5000; // 字符缓存容量
	private int buffer_size = 0; // 字符缓存当前存储元素大小
	private int buffer_position = 0; // 字符缓存读到的位置,下一个读取位置的开始
	private int pageIndex = 0; // 当前页数
	private int end_page_size = 0; // 最后一页的字符数

	public TxTio(File file, int read_size, String code) {
		if (file == null || code == null) {
			System.out.println("file or code is null!");
			return;
		}
		this.file = file;
		this.buffer = new char[bufferCapacity];
		this.read_size = read_size;
		this.code = code;
	}

	public void init() {
		try {
			if (file == null) {
				System.out.println("TxTio init fail: file no found!");
				return;
			}
			fis = new FileInputStream(file);
			try {
				br = new BufferedReader(new InputStreamReader(fis, code));
			} catch (UnsupportedEncodingException e) {
				System.out.println("UnsupportedEncoding!");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getNext() {
		System.out.println("getNext");
		// 已经在文件末尾，返回null
		if (is_TxTend && (buffer_size == buffer_position))
			return null;
		StringBuilder sBuilder = new StringBuilder();
		int temp_size = 0; // 已经读取的字符数量
		while (temp_size < read_size) {
			int read_remain = read_size - temp_size; // 剩余要读取的字符数量
			int buffer_remain = buffer_size - buffer_position; // 缓存剩余字符数量
			if ((buffer_remain) > read_remain) {// 从字符缓存标志位开始，剩余字符数量大于需要读取的字符数量
				sBuilder.append(buffer, buffer_position, read_remain);
				buffer_position = buffer_position + read_remain; // 更新字符在块中读到的位置
				temp_size = read_size;
			} else {// 从字符缓存标志位开始，剩余字符数量小于或者等于需要读取的字符数量
				sBuilder.append(buffer, buffer_position, buffer_remain);
				temp_size = temp_size + (buffer_remain); // 读取了多少个字符
				if (readfile_next()) {// 申请新的字符块,若还有下一个字符块，则buffer_position重置为0
					buffer_position = 0;
				} else {
					buffer_position = buffer_size;
					break;
				}
			}
		}
		System.out.println("buffer_position: " + buffer_position);
		System.out.println("buffer_size: " + buffer_size);
		if (temp_size > 0) {
			if (temp_size != read_size) {
				end_page_size = temp_size; // 如果最后一页的数量不等于需要读取的字数，保存这个数值
			}
			pageIndex++; // 若有读到字符，则页数自增
			System.out.println("pageIndex: " + pageIndex);
			return sBuilder.toString();
		} else
			return null;
	}

	public String getLast() {
		System.out.println("getLast");
		if (pageIndex == 1) // 在第一页，则不需读取
			return null;
		int temp_size = 0; // 需要返回的字符数
		if (is_TxTend && (buffer_size == buffer_position)) // 如果在文件末尾
			temp_size = read_size + end_page_size;
		else
			temp_size = read_size * 2; // 需要返回的字符数

		while (temp_size > 0) {
			if (buffer_position >= temp_size) {
				buffer_position = buffer_position - temp_size;
				temp_size = 0;
			} else {
				temp_size = temp_size - buffer_position;
				buffer_position = bufferCapacity;
				readfile_last();
			}
		}
		pageIndex = pageIndex - 2;
		return getNext();
	}

	// 获取当前页码
	public int getPageIndex() {
		return pageIndex;
	}
	/*
	// 往前跳页
	private String NextSkipPage(int size) {
		int skip_size = size; 	// 需要跳过的页数计数器,每跳一页减去一
		int temp_skip_charNum = 0; 	// 每跳一页实际跳过的字符数
		int skip_charNum = 0; 	//总共跳过的字符数
		if (is_TxTend && (buffer_size == buffer_position))
			return null;
		while (skip_size > 1) {
			try {
				br.mark(read_size);	//跳之前先标记
				temp_skip_charNum = (int) br.skip(read_size);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (temp_skip_charNum == read_size) {// 如果未到文件尾
				skip_size--;
			} else {// 已经到文件尾
				try {
					br.reset();
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(skip_size>1){//已经到文件末尾
			skip_charNum = (size - skip_size)*read_size;
		}else {
			skip_charNum = (size -1)*read_size;
		}
		//重新计算io_position和buffer_position
		io_position = io_position + skip_charNum/bufferCapacity;	
		int remain_size = (skip_charNum % bufferCapacity);
		if(bufferCapacity-buffer_position>remain_size)
			buffer_position = buffer_position + remain_size;
		else {
			io_position++;
			buffer_position = remain_size - (bufferCapacity-buffer_position);
		}
		return getNext();
	}
	*/

	/*
	// 往后跳页
	private String LastSkipPage(int size) {
		int to_pageIndex = pageIndex - size;
		if (to_pageIndex<=1) {//如果要跳的页数大于当前页数，直接跳到第一页
			this.close();
			this.init();
			io_position = 0;
			buffer_position = 0;
		}else {
			int temp_size = 0; // 需要返回的字符数
			if (is_TxTend && (buffer_size == buffer_position)) // 如果在文件末尾
				temp_size = read_size + end_page_size;
			else
				temp_size = read_size * 2; // 需要返回的字符数

			while (temp_size > 0) {
				if (buffer_position >= temp_size) {
					buffer_position = buffer_position - temp_size;
					temp_size = 0;
				} else {
					temp_size = temp_size - buffer_position;
					buffer_position = bufferCapacity;
					readfile_last();
				}
			}
			pageIndex = pageIndex - 2;
			return getNext();
			
			
		}
		return getNext();
	}
	*/

	// 读取上一个io块
	private boolean readfile_last() {
		if (io_position <= 1) {
			return false;
		}
		this.close();
		this.init();
		io_position = io_position - 2;
		try {
			br.skip(io_position * bufferCapacity);
			is_TxTend = false;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return readfile_next();
	}

	// 读取下一个io块
	private boolean readfile_next() {
		int temp = 0;
		if (is_TxTend)
			return !is_TxTend;
		if (br != null && file.exists()) {
			try {
				temp = br.read(buffer, 0, buffer.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (temp > 0) {// 如果能读到数据,则块位置自增,并更新buffer_size位置
			io_position++;
			buffer_size = temp;
		} else {// 如果没有数据了，文档结束标志is_TxTend设为true
			is_TxTend = true;
			this.close();
		}
		System.out.println("io_position: " + io_position);
		return !is_TxTend;
	}

	public void close() {
		try {
			if (br != null)
				br.close();
			if (fis != null)
				fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
