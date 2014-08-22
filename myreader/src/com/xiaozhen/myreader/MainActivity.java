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

		// 设置单击按钮时打开文件对话框
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
			// 下面几句设置各文件类型的图标， 需要你先把图标添加到资源文件夹
			images.put(OpenFileDialog.sRoot, R.drawable.filedialog_root); // 根目录图标
			images.put(OpenFileDialog.sParent, R.drawable.filedialog_folder_up); // 返回上一层的图标
			images.put(OpenFileDialog.sFolder, R.drawable.filedialog_folder); // 文件夹图标
			images.put("txt", R.drawable.filedialog_text); // txt文件图标
			images.put(OpenFileDialog.sEmpty, R.drawable.filedialog_root);
			Dialog dialog = OpenFileDialog.createDialog(id, this, "打开文件",
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
