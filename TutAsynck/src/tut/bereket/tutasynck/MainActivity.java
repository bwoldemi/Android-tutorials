package tut.bereket.tutasynck;

import android.view.View;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;



public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		new FileDownloader(this).execute();
		
		
		
		
		/*b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			    //EditText editText = (EditText) findViewById(R.id.edit_message);
			   // String message = editText.getText().toString();
			    //intent.putExtra(EXTRA_MESSAGE, message);
			  
				System.out.println("ddkjfldsjflksdjlf");
				
			}
		});
		*/
		
		 //new FileDownloader(this).execute();
		//super.onPostExecute(result);
		//System.out.println(result);
		
		//Intent intent = new Intent(this, ListActivity.class);
	    //EditText editText = (EditText) findViewById(R.id.edit_message);
	   // String message = editText.getText().toString();
	    //intent.putExtra(EXTRA_MESSAGE, message);
	  // startActivity(intent);
		
		
	}

	public  void startListActivity(View view){
		Intent intent = new Intent( this,  OtherActivity.class);
		startActivity(intent);
		
		System.out.println("djflkdsjfl");
		
	}
	

}
