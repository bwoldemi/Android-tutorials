package com.example.ex7;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements DeviceOrientationHandler{
	 DeviceOrientationHandler handler;
	 private boolean isUsingSensors;
	 
	 float gravity[] = new float[3];
	 float linear_acceleration[] = new float[3];
	  
	 private long  timeStamp;    // prevous coordinate timestamp
	 private final long INITIAL_RECOGNITION_DELAY = 200 * 1000 * 1000; // gesture recognition delay in ns.
	 private final long SUBSEQUENT_RECOGNITION_DELAY = 500 * 1000 * 1000; // gesture recognition delay in ns.
	 private final long DELAY_TO_SWITCH_BACK_TO_INITIAL_DELAY = 1000 * 1000 * 1000;
	 private long currentDelay = INITIAL_RECOGNITION_DELAY;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SensorManager sensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		
	//	sensorManager.registerListener(sensorEventListner, Sensor.TYPE_ACCELEROMETER, sensorManager.SENSOR_DELAY_NORMAL, 2);
		//sensorManager.registerListener(sensorEventListner, sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	}

	
//	public DeviceOrientationRecognizer(DeviceOrientationHandler h) {
//	    handler = h;
//	    
//	    return null; 	
//	}


	@Override
	public void tiltedLeft() {
		// TODO Auto-generated method stub
		System.out.println("Left");
	}


	@Override
	public void tiltedRight() {
		// TODO Auto-generated method stub
		System.out.println("Right");
	}


	@Override
	public void tiltedAway() {
		// TODO Auto-generated method stub
		System.out.println("away");
	}


	@Override
	public void tiltedTowards() {
		// TODO Auto-generated method stub
		System.out.println("towards");
	}
	
	SensorEventListener sensorEventListner= new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-ge	nerated method stub
			System.out.println();
			System.out.println(event.values[0]);
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	};
	
}
