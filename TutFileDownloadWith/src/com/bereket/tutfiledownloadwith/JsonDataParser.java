package com.bereket.tutfiledownloadwith;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class JsonDataParser {

	static List<Voting> votingListData = new ArrayList<Voting>();
	/**
	 *  Convers Json Array format
	 * @param data
	 * @return
	 * @throws JSONException
	 */
	
	static  List<Voting> getData(String data) throws JSONException {
		JSONArray jsonArrayData= new JSONArray(data);
		JSONObject jsonObect= null;
		Voting tempVotingData= new Voting();
		for (int i = 0; i < jsonArrayData.length(); i++) {
			// parsing json data from json arrray 
			jsonObect = jsonArrayData.getJSONObject(i);
			
			jsonObect.getString("id");
			
			
			votingListData.add(tempVotingData);
		}
		
		
		jsonArrayData= null;
		jsonObect= null;
		
		return votingListData;
	}

}
