package lili.oulu.fi;

import java.nio.charset.Charset;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.Tag;
import android.nfc.tech.Ndef;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}
	
	public void tagWriter(View view){
		Intent intent = new Intent(this, TagWriter.class);
		startActivity(intent);
	}
	public void tagReader(View view){
		Intent intent = new Intent(this, TagReader.class);
		startActivity(intent);
	}
	
}
