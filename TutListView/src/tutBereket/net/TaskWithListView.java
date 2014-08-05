package tutBereket.net;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.os.Build;

public class TaskWithListView extends Activity {
	private static final String [] values={"One","Two","Three"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_task_with_list_view);
		
		ListView listView= (ListView)findViewById(R.id.listView);
		listView.setAdapter(new TaskAdapter(this, values));
//		ArrayList<String>arrayList= new ArrayList<String>();
//		for(String str: values){
//			arrayList.add(str);
//		}
		
	
	}
	
}
