package fi.oulu.tol.vote04.view;

import java.util.List;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class VotingArrayAdapter extends ArrayAdapter<String> {
	
	
	private final Context context;
	private final String[] valuesOne;
	private final String[] valueTwo;

	public VotingArrayAdapter(Context context, String str[],String str2[]) {
		super(context, R.layout.fragment_vote);
		this.context=context;
		this.valuesOne=str;
		this.valueTwo=str2;
		// TODO Auto-generated constructor stub
	}



	

	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view=inflater.inflate(R.layout.fragment_vote, parent);
		
		TextView textView= (TextView)view.findViewById(R.id.above);
		
		TextView textViewTwo= view.findViewById(R.id.t);
		
		return view;
	}
}
