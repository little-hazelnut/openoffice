package com.kingsoft.control;

import com.kingsoft.sheetreader.SheetView;

import android.view.View;
import android.view.View.OnClickListener;

public class ShowSheetListener implements OnClickListener {
	private int SheetId;
	private View view;
	LoadSheetBundle lsBundle;

	public ShowSheetListener(LoadSheetBundle lsBundle, View view, int SheetId) {
		this.lsBundle = lsBundle;
		this.view = view;
		this.SheetId = SheetId;
	}

	@Override
	public void onClick(View v) {
		showSheet(SheetId);
	}

	private void showSheet(int sheetId) {
		while (true) {
			if (lsBundle.getSheetFlag(sheetId)) {
				((SheetView) view).setSheetId(sheetId);
				view.invalidate();
				break;
			}
		}

	}
}
