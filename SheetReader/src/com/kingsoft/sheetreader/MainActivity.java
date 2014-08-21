package com.kingsoft.sheetreader;

import java.util.HashMap;
import java.util.Map;

import com.kingsoft.control.CallbackBundle;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {

	static private int openfileDialogId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
			images.put(OpenFileDialog.sRoot, R.drawable.filedialog_root);
			images.put(OpenFileDialog.sParent, R.drawable.filedialog_folder_up);
			images.put(OpenFileDialog.sFolder, R.drawable.filedialog_folder);
			images.put("xlsx", R.drawable.filedialog_sheet);
			images.put(OpenFileDialog.sEmpty, R.drawable.filedialog_root);
			Dialog dialog = OpenFileDialog.createDialog(id, this, "打开文件",
					new CallbackBundle() {
						@Override
						public void callback(Bundle bundle) {
							Intent intent = new Intent(getApplicationContext(),
									SheetActivity.class);
							intent.putExtras(bundle);
							startActivity(intent);
						}
					}, ".xlsx;", images);
			return dialog;
		}
		return null;
	}
}
