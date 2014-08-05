package tutBereket.net;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class TaskAdapter extends ArrayAdapter<String>{
	private final String[] valuess;
	private final Context context;
	
	public TaskAdapter(Context context, String [] values) {
		super(context,R.layout.adapter, values);
		this.valuess=values;
		this.context=context;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflator= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView= inflator.inflate(R.layout.adapter, parent,false);
		TextView tv= 	(TextView)rowView.findViewById(R.id.textViewAdapter);
		tv.setText(valuess[position]);
		
		return rowView;
	}

}
