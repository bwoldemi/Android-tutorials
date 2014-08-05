package com.example.tutservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BinderTut extends Service{
	LocalBinder mBinder= new LocalBinder();
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}
	public class LocalBinder extends Binder{
		public BinderTut getBinderTut(){
			return BinderTut.this;
		}
		
		
		
	}
	
	
}
