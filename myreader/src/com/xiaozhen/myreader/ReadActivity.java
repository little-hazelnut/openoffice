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
    private boolean SeekBarStatus = false;	//seekbar����ʾ״̬
	private String str = null;
	private TxTio txtio = null;
	private int curProgress = 1;	//curProgress�ĵ�ǰλ�ã�����ǰҳ��ͬ��

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

		// ��Ҫ�������һ����¼���view
		RelativeLayout view = (RelativeLayout) this
				.findViewById(R.id.rltlayout);
		// setLongClickable�Ǳ����
		view.setLongClickable(true);
		view.setOnTouchListener(new MyGestureListener(this));
	}

	
	/**
	 * �̳�GestureListener����дleft��right����
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
			}else {//��ʾ�����������½���λ��
				seekbar.setVisibility(View.VISIBLE);
				seekbar.setProgress(curProgress);
				SeekBarStatus = true;
			}
			return super.onSingleTapUp(e);
		}

		@Override
		public boolean left() {
			if(SeekBarStatus){//����϶����ڣ���������ʧ��
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
        	if(sliderSize>0){//�����϶�
System.out.println("seekbar left: "+ (sliderSize));
        		curProgress = temp_progress;
        		for(int i = 0;i<sliderSize-1;i++)
        			txtio.getLast();
        		str = txtio.getLast();
        		tv.setText(str);
        	} else if(sliderSize<0){//�����϶�
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
