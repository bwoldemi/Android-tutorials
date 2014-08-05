package com.example.mygesture;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		LinearLayout l = (LinearLayout) findViewById(R.id.graphics_holder);
		l.addView(new PlayAreaView(this));
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.e("ON TOUCH", "Clicked");
		return super.onTouchEvent(event);
		
	}

	private class PlayAreaView extends View {
		Paint paint;

		public PlayAreaView(Context context) {
			super(context);
			paint = new Paint();
			paint.setColor(Color.RED);
		}

		protected void onDraw(Canvas canvas) {
			canvas.drawCircle(getWidth(), getWidth(), getWidth(), paint);
		}
	}
}
