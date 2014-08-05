package com.example.tutepereference;

import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class MainActivity extends Activity  implements OnSharedPreferenceChangeListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//shared peference change 
		getFragmentManager().beginTransaction().replace(android.R.id.content, new MPereferenceFragment()).commit();
		// 
		SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
		preferences.registerOnSharedPreferenceChangeListener(this);
	}

	

	
	public static class MPereferenceFragment extends PreferenceFragment{

		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.pereferences);
		}
		
	}
	@Override
	protected void onResume() {
		super.onResume();
	}
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		//Toast.makeText(MainActivity.this, "jhkjhkjh", Toast.LENGTH_LONG).show();
		if(key.equals("text")){
			Toast.makeText(MainActivity.this, sharedPreferences.getString(key, "bereket"), Toast.LENGTH_LONG).show();;
			System.out.println(key+ "has chaged dddd");
		}else if(key.equals("second_key")){
			System.out.println("Coffee changed");
		}else {
			System.out.println(key);
			
		}
	}

}
