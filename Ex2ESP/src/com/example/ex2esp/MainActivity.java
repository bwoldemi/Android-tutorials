package com.example.ex2esp;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView textViewTitle;
	private TextView textViewOption;
	private Button submit;
	private MyCustomDraw myCustomDraw;

	private boolean selected=false;
	private GestureDetectorCompat mDetector; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textViewTitle = (TextView) findViewById(R.id.title);
		
		textViewTitle.setText("Title");
		
		
		mDetector = new GestureDetectorCompat(this, new MyGesture());
		
		textViewOption = (TextView) findViewById(R.id.options);
		submit = (Button) findViewById(R.id.submit);

		myCustomDraw = new MyCustomDraw(this);
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.myCustomeDraw);
		linearLayout.addView(myCustomDraw);
		

		myCustomDraw.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mDetector.onTouchEvent(event);
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public class MyCustomDraw extends View {
		
		private Paint slectedPaint;
		private Paint unselectedPaint= new Paint(0);
		
		public MyCustomDraw(Context context) {
			super(context);
			slectedPaint= new Paint(0);
			slectedPaint.setColor(Color.GREEN);
		}
		

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			if(selected){
				canvas.drawText("Text", 10, 10, slectedPaint);
				canvas.drawCircle(40, 10, 10, slectedPaint);
			}else{
				canvas.drawText("Text", 10, 10, unselectedPaint);
				canvas.drawCircle(40, 10, 10, unselectedPaint);
			}
			
		}

	}
	

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		int action = MotionEventCompat.getActionMasked(event);
//		switch (action) {
//
//		case MotionEvent.ACTION_DOWN:
//			System.out.println("Down");
//			return true;
//		case MotionEvent.ACTION_MOVE:
//			System.out.print(" Move ");
//			return true;
//		case MotionEvent.ACTION_UP:
//			System.out.println("Action Up");
//			return true;
//		case MotionEvent.ACTION_OUTSIDE:
//			System.out.println("Out Side");
//			return true;
//		default:
//			return super.onTouchEvent(event);
//
//		}
//
//	}

	public class MyGesture extends GestureDetector.SimpleOnGestureListener {
		@Override
		public boolean onDown(MotionEvent e) {
			
			if(selected==true){
				selected=false;
			}else{
				selected=true;
			}
			System.out.println("ON Down  ******* ");
			myCustomDraw.invalidate();
			return true;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			System.out.println("ON Flip  ******* ");
			
			    
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}
}
