package tutBereket.net;

import tutBereket.net.MServiceBinder.LocalBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;



public class MainActivity extends Activity {
	MServiceBinder mServiceBinder;
	boolean mBound = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
	}


	@Override
	protected void onStart() {
		super.onStart();

		if(mBound){
			Intent intent= new Intent(this, MServiceBinder.class);
			bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
			
		}
		
		
	}
	
	 public void selfDestruct(View v) {
	        if (mBound) {
	            // Call a method from the LocalService.
	            // However, if this call were something that might hang, then this request should
	            // occur in a separate thread to avoid slowing down the activity performance.
	            int num = mServiceBinder.getRandom();
	            Toast.makeText(this, "number: " + num, Toast.LENGTH_SHORT).show();
	            System.out.println("djfldsfldjfl");
	        }
	    }

	
	 private ServiceConnection mConnection = new ServiceConnection() {

	        @Override
	        public void onServiceConnected(ComponentName className,
	                IBinder service) {
	            // We've bound to LocalService, cast the IBinder and get LocalService instance
	            LocalBinder binder = (LocalBinder) service;
	            mServiceBinder  = binder.getService();
	            mBound = true;
	        }

	        @Override
	        public void onServiceDisconnected(ComponentName arg0) {
	            mBound = false;
	        }
	    };
}
