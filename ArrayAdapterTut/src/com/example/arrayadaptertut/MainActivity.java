package com.example.arrayadaptertut;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import android.app.ListActivity;
import android.view.Menu;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		List<Person> person = new ArrayList<Person>();
		for (int i=0;i<10;i++){
			Person p= new Person();
			p.setAge(31);
			p.setName("bereket");
			person.add(p);
			
		}
		
		setListAdapter(new ArrayAapterTut(this, person));
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
