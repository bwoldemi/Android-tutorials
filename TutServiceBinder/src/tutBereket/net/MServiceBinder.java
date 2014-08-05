package tutBereket.net;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MServiceBinder extends Service{
	private LocalBinder binder= new LocalBinder();
	private final Random mGenerator= new Random();
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return binder;
	}
	
	public class LocalBinder extends Binder{
		MServiceBinder getService(){
			return MServiceBinder.this;
		}
	}
	
	public int getRandom(){
		
		System.out.print(mGenerator.nextInt(100));
		return mGenerator.nextInt(100);
	}

}
