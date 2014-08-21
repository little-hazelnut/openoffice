package com.kingsoft.sheetreader;

import com.kingsoft.control.GestureListener;
import com.kingsoft.control.LoadSheetBundle;
import com.kingsoft.control.ShowSheetListener;
import com.kingsoft.entities.WorkBook;
import com.kingsoft.thread.LoadSheet;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class SheetActivity extends ActionBarActivity implements LoadSheetBundle {

	private WorkBook wBook;
	private boolean[] LoadSheetFlag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sheetreader);
		Bundle bundle = getIntent().getExtras();
		String filepath = bundle.getString("path");
		wBook = new WorkBook();
		new Thread(new LoadSheet(this, filepath, wBook)).start();
		
		LinearLayout mainLayout = (LinearLayout) this
				.findViewById(R.id.linearlayout);
		mainLayout.setLongClickable(true);
		mainLayout.setOnTouchListener(new MyGestureListener(this));

		LinearLayout bLayout = new LinearLayout(this);
		bLayout.setOrientation(LinearLayout.HORIZONTAL);
		mainLayout.addView(bLayout);

		while (true) {
			if (LoadSheetFlag != null && getSheetFlag(wBook.getActiveTab())) {// 如果workbook和活动sheet内容已经解析完成
				SheetView sheetView = new SheetView(this, wBook);
				sheetView.setSheetId(wBook.getActiveTab()); // 获取活动页面
				sheetView.setLayoutParams(new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.MATCH_PARENT));
				mainLayout.addView(sheetView);
				int wBookSize = wBook.getSheetsSize();
				for (int i = 0; i < wBookSize; i++) {// 添加button
					Button button = new Button(this);
					button.setTextSize(9);
					button.setText("Sheet" + (i + 1));
					button.setLayoutParams(new ViewGroup.LayoutParams(
							ViewGroup.LayoutParams.WRAP_CONTENT,
							ViewGroup.LayoutParams.WRAP_CONTENT));
					button.setOnClickListener(new ShowSheetListener(this,
							sheetView, i));
					bLayout.addView(button);
				}
				break;
			}
		}
	}

	@Override
	public void setLoadSheetFlag(int sheetIndex) {
		if (sheetIndex < 0 || sheetIndex > LoadSheetFlag.length - 1) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			LoadSheetFlag[sheetIndex] = true;
		}
	}

	@Override
	public void initLoadSheetFlag(int sheetSize) {
		this.LoadSheetFlag = new boolean[sheetSize];
	}

	@Override
	public boolean getSheetFlag(int sheetIndex) {
		return this.LoadSheetFlag[sheetIndex];
	}

	private class MyGestureListener extends GestureListener {
		public MyGestureListener(Context context) {
			super(context);
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return super.onSingleTapUp(e);
		}

		@Override
		public boolean left() {
			return super.left();
		}

		@Override
		public boolean right() {
			return super.right();
		}
	}
}
