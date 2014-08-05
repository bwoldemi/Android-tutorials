package com.example.arrayadaptertut;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ArrayAapterTut extends ArrayAdapter<Person>{
	List<Person> personList;
	// names[];
	Context context;
	public ArrayAapterTut(Context context, List<Person> personList) {
		super(context, R.layout.activity_main, personList);
		this.context= context;
		this.personList=personList;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflator= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view= inflator.inflate(R.layout.activity_main, parent,false);
		TextView tv1= (TextView)view.findViewById(R.id.name);
		//TextView tv2= (TextView)view.findViewById(R.id.name);
		tv1.setText(personList.get(position).toString());
		
		return view;
		
	}
	
	
	
	
}
