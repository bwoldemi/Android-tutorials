package com.example.tutservice;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends ActionBarActivity {
	private TextView textView;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		//Button startButton= (Button)findViewById(R.id.start);
		
		//Button stopButton = (Button)findViewById(R.id.stop);
		
		TextView textView= (TextView)findViewById(R.id.tv);
		intent= new Intent(this, TutService.class);
		
	}
	
	public void startTut(View v){
		startService(intent);
		//bindService(intent, );
		//Log.d("Tag", "start clicked");
	}
	public void stopTut(View v){
		stopService(intent);
		//Log.d("Tag", "stoped clicked");
	} 
	public void setText(String str){
		textView.setText("dfjls");
	}
}
