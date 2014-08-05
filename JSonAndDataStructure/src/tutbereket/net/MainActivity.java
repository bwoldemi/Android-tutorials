package tutbereket.net;

import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.annotation.SuppressLint;
import android.os.Bundle;


@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements  ActionBar.TabListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final android.app.ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		actionBar.addTab(actionBar.newTab().setText("Tab One"));
		actionBar.addTab(actionBar.newTab().setText("Tab Two"));
		actionBar.addTab(actionBar.newTab().setText("Tab Three"));
		}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	

	
}
