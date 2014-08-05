package tut.bereket.tutasynck;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class FileDownloader extends AsyncTask<String, String, String>{
	public Context context;
	static String name="bereket";
	private ProgressDialog pDialog;
	@Override
	protected String doInBackground(String... params) {
		
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "finished **************************";
	}
	
	public FileDownloader(Context context){
		this.context= context;
		pDialog = new ProgressDialog(context);
	}
	
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if(pDialog.isShowing())
			pDialog.dismiss();
		Intent intent = new Intent( context,  OtherActivity.class);
		context.startActivity(intent);
	    //EditText editText = (EditText) findViewById(R.id.edit_message);
	   // String message = editText.getText().toString();
	    //intent.putExtra(EXTRA_MESSAGE, message);
	    context.startActivity(intent);
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		// start new activity 
		
		pDialog.setMessage("Downloading...");
		pDialog.setProgress(ProgressDialog.STYLE_SPINNER);
		pDialog.show();
		
		super.onPreExecute();
	}
	@Override
	protected void onProgressUpdate(String... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		
	}
	
	
	public String getDataD(){
		
		return "d";
	}
}
