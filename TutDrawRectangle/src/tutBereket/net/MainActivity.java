package tutBereket.net;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends Activity {
	MyView myView;
	Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		 myView = new MyView(this);
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.optionCustom);
		linearLayout.addView(myView);
		handler = new Handler(){
		    public void handleMessage(android.os.Message msg) {
		    	myView.invalidate();
		        System.out.println("redraw");
		    };
		};
		
		

	}
	
	@Override
	protected void onStart() {
		super.onStart();
		myView.run();
		 
	}
	
	class MyView extends View implements Runnable{
		int radius=50;
		Paint paint= new Paint();
		public MyView(Context context) {
			super(context);
			
			
			// TODO Auto-generated constructor stub
		}
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			
			//canvas.drawCircle(0, 0, 200, paint);
			canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius,
					paint);
			if (radius < getWidth() / 2) {
				radius = radius + 20;
			} else {
				radius = 0;
			}

		}
		
		

		@Override
		public void run() {
			System.out.println("Runnable");
			while (true) {
		        try {
		            Thread.sleep(1000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		        handler.sendEmptyMessage(0);
		    }
		}
		
	}
}
