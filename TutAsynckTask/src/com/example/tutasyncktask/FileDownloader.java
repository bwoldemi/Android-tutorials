package com.example.tutasyncktask;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class FileDownloader extends AsyncTask<String, String, List<String>>{
	public Context context;
	static String name="bereket";
	private ProgressDialog pDialog;
	List <String> listData;
	@Override
	protected List<String> doInBackground(String... params) {
		
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String>lists=new ArrayList<String>();
		lists.add("dkjfl");
		lists.add("dkjfl");
		lists.add("dkjfl");
		lists.add("dkjfl");
		
		return lists;
	}
	
	public FileDownloader(Context context){
		this.context= context;
		pDialog = new ProgressDialog(context);
	}
	
	
	@Override
	protected void onPostExecute(List<String> result) {
		super.onPostExecute(result);
		if(pDialog.isShowing())
			pDialog.dismiss();
		
		Object jsonObject = null;
		JSONArray array = null;
		try {
			array = new JSONArray(jsonObject.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        for (int i = 0; i < array.length(); i++)  
        {  
            try {
				JSONObject jsonObj = (JSONObject) array.getJSONObject(i);
				 System.out.println(jsonObj.getString("id")+" *******************************");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
           
          /*  Voting voting = new Voting(jsonObj.getString("id"),
              jsonObj.getString("creator"),
              jsonObj.getString("Title"),
              jsonObj.getString("startTime"),
              jsonObj.getString("endime"),
              jsonObj.getString("text"),
              getOptions(jsonObj.getJSONArray("options"))
              );*/
 
           // votings.add(voting);  
        } 
		
		
		
		
		
		
		
		//Intent intent = new Intent( context,  OtherActivity.class);
		//context.startActivity(intent);
	    //EditText editText = (EditText) findViewById(R.id.edit_message);
	   // String message = editText.getText().toString();
	    //intent.putExtra(EXTRA_MESSAGE, message);
	   // context.startActivity(intent);
		listData=result;
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		// start new activity 
		super.onPreExecute();
		
		pDialog.setMessage("Downloading...");
		pDialog.setProgress(ProgressDialog.STYLE_SPINNER);
		pDialog.show();
		
		
		
	}
	@Override
	protected void onProgressUpdate(String... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		
	}
	
	
	public List<String> getDataD(){
		
		return listData;
	}
}
