package tutbereket.net;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.os.Build;

public class MainActivity extends Activity {
	GestureDetectorCompat gestureListner;
	//String vertical[]= {"horizontal_one", "hotizonta_two"};
	String vertical[]= {"vertical_one", "vertical_two", "vertical_three"};
	int count= 0;
	int maxCount=2;
	MyCustomeText myCustomText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LinearLayout layout= (LinearLayout)findViewById(R.id.myCustomView);
		 myCustomText= new MyCustomeText(this);
		layout.addView(myCustomText);
		gestureListner = new GestureDetectorCompat(this, new MyGestureListener());

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		this.gestureListner.onTouchEvent(event);
		//Log.d("ONTOUCH", "clicked");
		return super.onTouchEvent(event);
	}

	class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures"; 
        
        @Override
        public boolean onDown(MotionEvent event) { 
           // Log.d(DEBUG_TAG,"onDown: " + event.toString()); 
            return true;
        }

        @Override
    	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
	
			//calculation for forward and backward swipe
			//user will move forward through votings on fling left
			boolean forward = false;
			//user will move backward through votings on fling right
			boolean backward = false;

			//calculate the change in X position within the fling gesture
			float horizontalDiff = Math.abs(e2.getX() - e1.getX());
			//calculate the change in Y position within the fling gesture
			float verticalDiff = Math.abs(e2.getY() - e1.getY());
			
			//float absVelocityX = Math.abs(velocityX);
			Log.d("Diffrence", Float.toString(horizontalDiff -verticalDiff));
			if(horizontalDiff -verticalDiff >0){
				System.out.println(" Horizontal");
			}else {
				myCustomText.invalidate();
				System.out.println(" Vertical");
				if(count<maxCount){
					count++;
				}else{
					count=0;
				}
			}
					
			return true;
		}
    }
	class MyCustomeText extends View{
		
		Paint paint;
		public MyCustomeText(Context context) {
			super(context);
			paint= new Paint();
			paint.setColor(Color.RED);
		
			
		}
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			int counter=0;
			for (int i = 0; i < vertical.length; i++) {
				if(i==count){
					canvas.drawCircle(20, counter+45, 5, paint);
				}
				canvas.drawText(vertical[i], 50, counter+50, paint);
				counter= counter+50;
			}
				
			
		}
		
	}
}
