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
	private String code = null; // �ַ�����
	private int io_position = 0; // �ַ���������ı�ioλ�ã���bufferCapacityΪһ����λ
	private char[] buffer = null; // �ַ�����
	private boolean is_TxTend = false; // �ı��Ƿ��Ѿ�����
	private int read_size = 0; // ÿ����ʾ��Ҫ���ַ�
	private final int bufferCapacity = 5000; // �ַ���������
	private int buffer_size = 0; // �ַ����浱ǰ�洢Ԫ�ش�С
	private int buffer_position = 0; // �ַ����������λ��,��һ����ȡλ�õĿ�ʼ
	private int pageIndex = 0; // ��ǰҳ��
	private int end_page_size = 0; // ���һҳ���ַ���

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
		// �Ѿ����ļ�ĩβ������null
		if (is_TxTend && (buffer_size == buffer_position))
			return null;
		StringBuilder sBuilder = new StringBuilder();
		int temp_size = 0; // �Ѿ���ȡ���ַ�����
		while (temp_size < read_size) {
			int read_remain = read_size - temp_size; // ʣ��Ҫ��ȡ���ַ�����
			int buffer_remain = buffer_size - buffer_position; // ����ʣ���ַ�����
			if ((buffer_remain) > read_remain) {// ���ַ������־λ��ʼ��ʣ���ַ�����������Ҫ��ȡ���ַ�����
				sBuilder.append(buffer, buffer_position, read_remain);
				buffer_position = buffer_position + read_remain; // �����ַ��ڿ��ж�����λ��
				temp_size = read_size;
			} else {// ���ַ������־λ��ʼ��ʣ���ַ�����С�ڻ��ߵ�����Ҫ��ȡ���ַ�����
				sBuilder.append(buffer, buffer_position, buffer_remain);
				temp_size = temp_size + (buffer_remain); // ��ȡ�˶��ٸ��ַ�
				if (readfile_next()) {// �����µ��ַ���,��������һ���ַ��飬��buffer_position����Ϊ0
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
				end_page_size = temp_size; // ������һҳ��������������Ҫ��ȡ�����������������ֵ
			}
			pageIndex++; // ���ж����ַ�����ҳ������
			System.out.println("pageIndex: " + pageIndex);
			return sBuilder.toString();
		} else
			return null;
	}

	public String getLast() {
		System.out.println("getLast");
		if (pageIndex == 1) // �ڵ�һҳ�������ȡ
			return null;
		int temp_size = 0; // ��Ҫ���ص��ַ���
		if (is_TxTend && (buffer_size == buffer_position)) // ������ļ�ĩβ
			temp_size = read_size + end_page_size;
		else
			temp_size = read_size * 2; // ��Ҫ���ص��ַ���

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

	// ��ȡ��ǰҳ��
	public int getPageIndex() {
		return pageIndex;
	}
	/*
	// ��ǰ��ҳ
	private String NextSkipPage(int size) {
		int skip_size = size; 	// ��Ҫ������ҳ��������,ÿ��һҳ��ȥһ
		int temp_skip_charNum = 0; 	// ÿ��һҳʵ���������ַ���
		int skip_charNum = 0; 	//�ܹ��������ַ���
		if (is_TxTend && (buffer_size == buffer_position))
			return null;
		while (skip_size > 1) {
			try {
				br.mark(read_size);	//��֮ǰ�ȱ��
				temp_skip_charNum = (int) br.skip(read_size);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (temp_skip_charNum == read_size) {// ���δ���ļ�β
				skip_size--;
			} else {// �Ѿ����ļ�β
				try {
					br.reset();
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(skip_size>1){//�Ѿ����ļ�ĩβ
			skip_charNum = (size - skip_size)*read_size;
		}else {
			skip_charNum = (size -1)*read_size;
		}
		//���¼���io_position��buffer_position
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
	// ������ҳ
	private String LastSkipPage(int size) {
		int to_pageIndex = pageIndex - size;
		if (to_pageIndex<=1) {//���Ҫ����ҳ�����ڵ�ǰҳ����ֱ��������һҳ
			this.close();
			this.init();
			io_position = 0;
			buffer_position = 0;
		}else {
			int temp_size = 0; // ��Ҫ���ص��ַ���
			if (is_TxTend && (buffer_size == buffer_position)) // ������ļ�ĩβ
				temp_size = read_size + end_page_size;
			else
				temp_size = read_size * 2; // ��Ҫ���ص��ַ���

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

	// ��ȡ��һ��io��
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

	// ��ȡ��һ��io��
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
		if (temp > 0) {// ����ܶ�������,���λ������,������buffer_sizeλ��
			io_position++;
			buffer_size = temp;
		} else {// ���û�������ˣ��ĵ�������־is_TxTend��Ϊtrue
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
