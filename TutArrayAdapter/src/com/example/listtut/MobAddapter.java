package com.example.listtut; 
import java.util.List;


import android.content.Context;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;
 
public class MobAddapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
	private final String [] valueTwo;
 
	public MobAddapter(Context context, String[] values, String[] valueTwo) {
		super(context, R.layout.list_layout, values);
		this.context = context;
		this.values = values;
		this.valueTwo= valueTwo;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.list_layout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		//T imageView = (ImageView) rowView.findViewById(R.id.logo);
		TextView textViewTwo= (TextView)rowView.findViewById(R.id.above);
		
		textView.setText(Integer.toString(position));
		textViewTwo.setText(valueTwo[position]);
		
		
		
		
		
		
 
		return rowView;
	}
}