package com.example.tutepereference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Calc extends Activity {
	public static final String  PREF_NAME="MPereferenceFragment";
	
	@Override 
	protected void onCreate(Bundle state){
		super.onCreate(state);
		
	
		 SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
		 boolean silent= settings.getBoolean("first_key", false);
		 System.out.println(silent);
	}

	
}
