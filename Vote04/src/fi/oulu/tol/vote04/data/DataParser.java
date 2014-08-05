package fi.oulu.tol.vote04.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Date;

import android.util.Log;

import fi.oulu.tol.vote04.model.Voting;

public class DataParser {
	List<Voting> voting;
	// Context c;
	private static final String votings_resources = "http://aani.opimobi.com:2014/1.0/votings";

	JSONParser jsonParser = new JSONParser();

	JSONArray jsonArray = null;

	public List<Voting> getData() {

		voting = new ArrayList<Voting>();
		// temp data voting
		Voting tempVoting;
		// joson file
		jsonArray = jsonParser.makeHttpRequest(votings_resources, "GET");

		if (jsonArray != null) 
			Log.d("votings data", jsonArray.toString());

			try {

				for (int i = 0; i < jsonArray.length(); i++) {
					/*tempVoting = new Voting();
					JSONObject jo = jsonArray.getJSONObject(i);
					tempVoting.setId(Integer.parseInt(jo.getString("id")));
					tempVoting.setCreator(jo.getString("creator"));
					tempVoting.setTitle(jo.getString("title"));
					//tempVoting.setStartTime(this.toDate(jo.getString("start-time")));
					//tempVoting.setStartTime(this.toDate(jo.getString("end-time")));
					
					tempVoting.setText(jo.getString("text"));
					voting.add(tempVoting);*/
					System.out.print("djfl");

				}

			//} catch (JSONException e) {
			//	Log.e("onPostExecute", e.toString());
			} catch (NullPointerException npe) {
				Log.e("onPostExecute", npe.toString());
			}

		
		return voting;
	}

	public Date toDate(String date) {

		return null;
	}
}
