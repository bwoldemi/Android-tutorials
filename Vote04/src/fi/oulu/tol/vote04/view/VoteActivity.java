package fi.oulu.tol.vote04.view;

import java.util.List;


import fi.oulu.tol.vote04.data.DataParser;
import fi.oulu.tol.vote04.model.Voting;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;

public class VoteActivity extends Activity{

	DataParser dataParser = new DataParser();
	List<Voting>	votingListData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		System.out.println("hdfjldsjf");
		votingListData = dataParser.getData();
		for(Voting v: votingListData){
			System.out.println(v.getId());
		}
	}
	
	
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
	}

	

	
	
}
