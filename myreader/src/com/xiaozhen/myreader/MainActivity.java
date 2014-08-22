package com.xiaozhen.myreader;

import java.util.HashMap;
import java.util.Map;

import com.xiaozhen.control.CallbackBundle;
import com.xiaozhen.myreader.OpenFileDialog;

import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends ActionBarActivity {

	static private int openfileDialogId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ���õ�����ťʱ���ļ��Ի���
		findViewById(R.id.button_openfile).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						showDialog(openfileDialogId);
					}
				});
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == openfileDialogId) {
			Map<String, Integer> images = new HashMap<String, Integer>();
			// ���漸�����ø��ļ����͵�ͼ�꣬ ��Ҫ���Ȱ�ͼ����ӵ���Դ�ļ���
			images.put(OpenFileDialog.sRoot, R.drawable.filedialog_root); // ��Ŀ¼ͼ��
			images.put(OpenFileDialog.sParent, R.drawable.filedialog_folder_up); // ������һ���ͼ��
			images.put(OpenFileDialog.sFolder, R.drawable.filedialog_folder); // �ļ���ͼ��
			images.put("txt", R.drawable.filedialog_text); // txt�ļ�ͼ��
			images.put(OpenFileDialog.sEmpty, R.drawable.filedialog_root);
			Dialog dialog = OpenFileDialog.createDialog(id, this, "���ļ�",
					new CallbackBundle() {
						@Override
						public void callback(Bundle bundle) {
							Intent intent = new Intent(getApplicationContext(),
									ReadActivity.class);
							intent.putExtras(bundle);
							startActivity(intent);
						}
					}, ".txt;", images);
			return dialog;
		}
		return null;
	}
}
