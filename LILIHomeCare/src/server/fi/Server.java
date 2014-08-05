package server.fi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import lilihomecare.fi.TaskObsorver;
import model.TaskForOneCustomer;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Upload and Download data from server
 * 
 * @author bereket
 * 
 */
public class Server implements TaskDataDownloder {
	Vector<TaskForOneCustomer> tasks=null;
	Context context=null;
	String url = "http://www.tutbereket.net/liliTest/liliSample.json";
	TaskObsorver mObsorver;
	
	public Server (Context context){
		context = this.context;
		
	}
	
	

	public void setmObsorver(TaskObsorver mObsorver) {
		this.mObsorver = mObsorver;
	}
	public Vector<TaskForOneCustomer> getTasks (){
		return this.tasks;
	}

	@Override
	public void reteriveData() {
		new DownLoader().execute(url);
		
	}

	@Override
	public void uploadData() {
		// TODO Auto-generated method stub

	}

	public boolean checkNetwork() {
		ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		Log.d("CheckNetwork", "No network, cannot initiate retrieval!");
		return false;
	}

	protected String readInputStream(InputStream stream) throws IOException,
			UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		String content = new String();
		char[] buffer = new char[1024];
		int read;
		do {
			read = reader.read(buffer);
			if (read > 0) {
				content += String.valueOf(buffer, 0, read);
			}
		} while (read > 0);
		return content;
	}

	class DownLoader extends AsyncTask<String, String, String> {
		@Override
		protected String doInBackground(String... params) {
			String content = null;
			try {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				System.out.println(params[0]);
				//get the first argument passed to the execute method
				HttpGet httpGet = new HttpGet(params[0]);
				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				InputStream inputStream = httpEntity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						inputStream, "iso-8859-1"), 8);
				StringBuffer stringBuffer = new StringBuffer();
				String line = null;
				while ((line = reader.readLine()) != null) {
					stringBuffer.append(line);
				}
				inputStream.close(); // free memory

				content=stringBuffer.toString();
				//System.out.println(content);
				return content;
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
		
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if(result ==null){
				mObsorver.error("Error Downloading");
				return;
			}else {
				try {
					tasks=Parser.parse(result);
					mObsorver.downloadCompleted();
				} catch (JSONException e) {
					mObsorver.error("Error Parsing Data");
					e.printStackTrace();
				}
				Log.d("RESULT", result);
			}
			
		}
	}
}
