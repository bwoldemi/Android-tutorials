package tutBereket.net;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {
	Person person;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		person= new Person();
		person.setName("bereket");
		person.setAdress("Tellerovontie");
		person.setAge(31);
	}
	public void startActivity(View view){
		Intent i= new Intent(this,DisplayPerson.class);
		Bundle bundle= new Bundle();
		bundle.putParcelable("Person", person);
		i.putExtras(bundle);
		startActivity(i);
		
	}
}
