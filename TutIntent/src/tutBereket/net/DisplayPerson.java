package tutBereket.net;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;


public class DisplayPerson extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_display_person);
		Person person= (Person)getIntent().getParcelableExtra("Person");
		
		TextView te= (TextView)findViewById(R.id.textView);
		if(person!=null){
			te.append(person.getName() +person.getAdress());
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_person, menu);
		return true;
	}
	
	
}
