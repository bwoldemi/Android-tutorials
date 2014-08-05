package com.example.ex;

import com.example.arrayadaptertut.R;

import android.content.Context;
import android.widget.ArrayAdapter;





public class TestT extends ArrayAdapter<String> {

	public TestT(Context context, int resource) {
		super(context, R.layout.activity_main);
		int x=R.id.name;
		// TODO Auto-generated constructor stub
	}
}
