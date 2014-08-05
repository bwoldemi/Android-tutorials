package tutBereket.net;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {
	private static final String values[]={"one","two","three","four"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//setListAdapter(new TaskAdapter(this,values));
		
	}

	 public void startOtherListView(View view){
		Intent i = new Intent(this, TaskWithListView.class);
		startActivity(i);
	}
}
