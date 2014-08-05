package com.example.tutasyncktask;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ArrayAapterTut extends ArrayAdapter<List<String>>{
	List<String> data;
	public ArrayAapterTut(Context context,List<String> data) {
		super(context, R.layout.activity_main);
		this.data=data;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return super.getView(position, convertView, parent);
	}
	
	

}
