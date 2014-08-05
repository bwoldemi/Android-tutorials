package com.example.testqu;


import java.io.InputStream;

import java.util.Vector;

import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	private TextView tv;

	private String urlPath = "http://aani.opimobi.com:2014/1.0/votings";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.tv);
		new UsingAsyncTask().execute(urlPath);
		StringBuffer stb= new StringBuffer();
		System.out.println("ldkjfldsjfldsjlfjsdlfkjsd");
		for(Voting v: getDataFromUrl()){
			System.out.println(stb.append(v.getText()));
		}
		tv.append(stb.toString());
		

	}

	public Vector<Voting> getDataFromUrl() {

		// URL url = new URL(urlPath);
		Vector<Voting> votings = new Vector<Voting>();
		JSONObject jsonObj =null;
		try {
			JSONObject jsonObject = doGet(urlPath);

			JSONArray array = new JSONArray(jsonObject.toString());
			for (int i = 0; i < array.length(); i++) {
				 jsonObj = array.getJSONObject(i);
				Voting voting = new Voting(jsonObj.getString("id"),
						jsonObj.getString("creator"),
						jsonObj.getString("Title"),
						jsonObj.getString("start-time"),
						jsonObj.getString("end-time"),
						jsonObj.getString("text"),
						jsonObj.getString("options"));

				votings.add(voting);
			}

			return votings;

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	public static JSONObject doGet(String url) {
		
		try {
			String result = null;
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet request = new HttpGet(url);
			HttpResponse response = httpClient.execute(request);

			result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
			JSONObject object = new JSONObject(result);

			return object;
		} catch (Exception e) {
			return null;

			// TODO: handle exception
		}
		
	}

	// Plese do this one your self

	/*
	 * public Vector<Option> getOptions(JSONArray jsonArray){ Vector<Option>
	 * options = null;
	 * 
	 * try { for(int i = 0; i < jsonArray.length(); i++){
	 * 
	 * JSONObject jsonObj = (JSONObject) jsonArray.getJSONObject(i);
	 * options.add(new Option (jsonObj.toString(),0)); }
	 * 
	 * return options; } catch (Exception e) { // TODO: handle exception }
	 * return null; }
	 */

	private class UsingAsyncTask extends
			AsyncTask<String, String, Vector<Voting>> {

		@Override
		protected Vector<Voting> doInBackground(String... params) {
			try {
				String result = null;
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet request = new HttpGet(params[0]);
				HttpResponse response = httpClient.execute(request);

				result = EntityUtils.toString(response.getEntity());
				System.out.println( result);
				
				
				JSONObject jsonObj=null;
				Vector<Voting> votings = new Vector<Voting>();
				JSONArray array = new JSONArray(result);
				for (int i = 0; i < array.length(); i++) {
					
					 jsonObj =  array.getJSONObject(i);
					 
					Voting voting = new Voting(jsonObj.getString("id"),
							jsonObj.getString("creator"),
							jsonObj.getString("title"),
							jsonObj.getString("start-time"),
							jsonObj.getString("end-time"),
							jsonObj.getString("text"),
							jsonObj.getString("options"));

					votings.add(voting);
					
				}
				return votings;
			} catch (Exception e) {
				return null;// TODO: handle exception
			}

		}

		@Override
		protected void onPostExecute(Vector<Voting> result) {
			super.onPostExecute(result);
			StringBuffer stb= new StringBuffer();
			System.out.println("ldkjfldsjfldsjlfjsdlfkjsd");
			for(Voting v: result){
				System.out.println(stb.append(v.getText()));
			}
			tv.append(stb.toString());
			
		}
	}
}
