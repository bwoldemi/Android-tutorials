package com.bereket.tutfiledownloadwith;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

/**
 * This class will do two tasks 1.Download data Asyncrnous from the given web
 * site 2. Update or Display the content the download data at realtime
 * 
 * @author bereket & Samsi
 * 
 */

public class AsynckFileDownloder extends AsyncTask<String, Integer, String> {
	private ProgressDialog pDialog;
	private Context context;

	// we should get it from somewhere else
	private static final String votings_resources = "http://aani.opimobi.com:2014/1.0/votings";

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public AsynckFileDownloder(Context context) {
		this.context = context;
		pDialog = new ProgressDialog(context);
	}

	/**
	 * Display progress dialog
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog.setMessage("Please Wait ...");
		pDialog.setProgress(ProgressDialog.STYLE_SPINNER);
		pDialog.show();
	}

	/**
	 * File Download Asynchronously
	 */
	@Override
	protected String doInBackground(String... args) {

		// InputStream is = null;

		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(args[0]);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			InputStream inputStream = httpEntity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, "iso-8859-1"), 8);
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				stringBuffer.append(line);
				System.out.println(line);
			}
			inputStream.close(); // free memory

			System.out.println(stringBuffer.toString());
			
			//List<Voting> listVotingData=JsonDataParser.getData(stringBuffer.toString());
			
			//stringBuffer = null; // free memory
			
			
			return stringBuffer.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("AsynckFileDownloder", "Error downloading " + e.toString());
		}
		// return JSON String
		return null;

	}

	/**
	 * Data Downloading finished show the list activity
	 */
	@Override
	protected void onPostExecute(String listVoting) {
		if (pDialog.isShowing()) {
			pDialog.dismiss();
		}
		
		getDataFromUrl(listVoting+"*******************************");
		// MainActivity.no

		// Intent i= new Intent(context,MainActivity.class);

	}
	
	
	public String getDataFromUrl(String urlPath){
		  
		  //URL url = new URL(urlPath);
		  Vector<Voting> votings = null;
		  
		  try{
		JSONObject jsonObject = null;
		   
		//   System.out.print(jsonObject.toString());
		   
		   JSONArray array = new JSONArray(jsonObject.toString());  
		         for (int i = 0; i < array.length(); i++)  
		         {  
		             JSONObject jsonObj = (JSONObject) array.getJSONObject(i);  
		             
		             System.out.println(jsonObj.getString("creator") +"***********dkdslfkdslfjlsd");
		           /*  Voting voting = new Voting(jsonObj.getString("id"),
		               jsonObj.getString("creator"),
		               jsonObj.getString("Title"),
		               jsonObj.getString("startTime"),
		               jsonObj.getString("endime"),
		               jsonObj.getString("text"),
		               getOptions(jsonObj.getJSONArray("options"))
		               );*/
		  
		          //   votings.add(voting);  
		         } 

		         return null;

		   }catch (Exception e) {
		      // TODO: handle exception
		   }
		  return null;
		 }



}
