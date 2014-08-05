package com.example.tutservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TutService extends Service{
	TestPublicClass tr=null;
	static final String TAG="SERVICE TUT";
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		if(tr==null)
		 tr= new TestPublicClass();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		Log.d(TAG,"TESTING SERVICE.....");
		System.out.println("Flags"+flags);
		System.out.println("StartId"+startId);
		
		tr.start();
		return START_STICKY;
		//return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if(tr!=null)
			tr=null;
		
	}
	
	
}
