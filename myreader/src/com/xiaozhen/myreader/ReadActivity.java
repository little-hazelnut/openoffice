package com.xiaozhen.myreader;

import java.io.File;

import com.xiaozhen.control.GestureListener;
import com.xiaozhen.model.TxTio;
import com.xiaozhen.util.PreDispose;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ReadActivity extends ActionBarActivity {
	
	private TextView tv = null;
    private SeekBar seekbar;
    private boolean SeekBarStatus = false;	//seekbar的显示状态
	private String str = null;
	private TxTio txtio = null;
	private int curProgress = 1;	//curProgress的当前位置，跟当前页面同步

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        seekbar.setOnSeekBarChangeListener(seekBarChange);
        
		Bundle bundle = getIntent().getExtras();
		String filepath = bundle.getString("path");
		tv = (TextView) findViewById(R.id.show);
		File file = new File(filepath);
		String code = PreDispose.TxTcode(file);
		txtio = new TxTio(file, 300, code);
		txtio.init();
		str = txtio.getNext();
		if (str != null) {
			tv.setText(str);
		}

		// 需要监听左右滑动事件的view
		RelativeLayout view = (RelativeLayout) this
				.findViewById(R.id.rltlayout);
		// setLongClickable是必须的
		view.setLongClickable(true);
		view.setOnTouchListener(new MyGestureListener(this));
	}

	
	/**
	 * 继承GestureListener，重写left和right方法
	 */
	private class MyGestureListener extends GestureListener {
		public MyGestureListener(Context context) {
			super(context);
		}
		
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			if(SeekBarStatus){
				seekbar.setVisibility(View.GONE);
				SeekBarStatus = false;
			}else {//显示进度条，更新进度位置
				seekbar.setVisibility(View.VISIBLE);
				seekbar.setProgress(curProgress);
				SeekBarStatus = true;
			}
			return super.onSingleTapUp(e);
		}

		@Override
		public boolean left() {
			if(SeekBarStatus){//如果拖动条在，就让它消失吧
				seekbar.setVisibility(View.GONE);
				SeekBarStatus = false;
			}
			str = txtio.getNext();
			if (str != null) {
				curProgress = txtio.getPageIndex();
				tv.setText(str);
				return true;
			}
			return false;
		}

		@Override
		public boolean right() {
			if(SeekBarStatus){
				seekbar.setVisibility(View.GONE);
				SeekBarStatus = false;
			}
			str = txtio.getLast();
			if (str != null) {
				curProgress = txtio.getPageIndex();
				tv.setText(str);
				return true;
			}
			return false;
		}
	}
	
    private OnSeekBarChangeListener seekBarChange = new OnSeekBarChangeListener() {
    	
    	int temp_progress = 0;

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        	int sliderSize = curProgress-temp_progress;
        	if(sliderSize>0){//往左拖动
System.out.println("seekbar left: "+ (sliderSize));
        		curProgress = temp_progress;
        		for(int i = 0;i<sliderSize-1;i++)
        			txtio.getLast();
        		str = txtio.getLast();
        		tv.setText(str);
        	} else if(sliderSize<0){//往右拖动
System.out.println("seekbar right: "+ (sliderSize));
        		curProgress = temp_progress;
        		for(int i = 0;i<sliderSize*(-1)-1;i++)
        			txtio.getNext();
        		str = txtio.getNext();
        		tv.setText(str);
        	}
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { 
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                boolean fromUser) {
        	temp_progress = progress;
        }
    };

}
