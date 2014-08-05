package com.example.myexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class PieChart extends View {
	Paint paint;
	public PieChart(Context context) {
		super(context);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint= new Paint();
		
		paint.setColor(Color.BLACK);
		canvas.drawCircle(10, 20, 20, paint);
		
	}

}
