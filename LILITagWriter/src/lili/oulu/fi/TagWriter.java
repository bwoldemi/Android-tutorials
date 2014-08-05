package lili.oulu.fi;

import java.io.IOException;
import java.nio.charset.Charset;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;

public class TagWriter extends Activity {
	EditText editTextType;
	EditText editTextData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag_writer);
		
		editTextData= (EditText)findViewById(R.id.editTextMessage);
		editTextType= (EditText)findViewById(R.id.editTextType);
		
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
	public void writeOnTag(View view){
		String message=editTextData.getText().toString().trim();
		String type = editTextType.getText().toString().trim();
		
		if(type.equals("task")||type.equals("door")){
			if(message!=null && !message.equals("")){
				Intent intent = new Intent(this,NFCTagWriter.class);
				intent.putExtra("message", message);
				intent.putExtra("type",type);
				startActivity(intent);
			}else{
				Toast.makeText(this, "Please Enter data and type", Toast.LENGTH_SHORT).show();;
			}
		}
		
		
	}
	
//	public boolean writeOnTag(NdefMessage msg, Tag tag) throws IOException,
//			FormatException {
//		int size = msg.toByteArray().length;
//		Ndef ndef = Ndef.get(tag);
//		if (ndef != null) {
//			
//
////			if (ndef.getMaxSize() > size) {
////				Toast.makeText(this, "Text size outbound", Toast.LENGTH_SHORT).show();
////				return false;
////			}
//			ndef.writeNdefMessage(msg);
//			// Successful 
//			Toast.makeText(this, "Successfully Writen",
//					Toast.LENGTH_SHORT).show();
//			return true;
//		} 
//
//		return false;
//
//	}

	
//	public static boolean isSupportedTec(String[] techs) {
//		boolean ultralight = false;
//		boolean nfcA = false;
//		boolean ndef = false;
//		for (String tech : techs) {
//			if (tech.equals("android.nfc.tech.MifareUltralight")) {
//				ultralight = true;
//			} else if (tech.equals("android.nfc.tech.NfcA")) {
//				nfcA = true;
//			} else if (tech.equals("android.nfc.tech.Ndef")
//					|| tech.equals("android.nfc.tech.NdefFormatable")) {
//				ndef = true;
//			}
//		}
//		if (ultralight && nfcA && ndef) {
//			return true;
//		} else {
//			return false;
//		}
//	}

//	private boolean isWritableTag(Tag tag) {
//		try {
//			
//			Ndef ndef = Ndef.get(tag);
//			if (ndef != null) {
//				ndef.connect();
//				if (!ndef.isWritable()) {
//					Toast.makeText(this, "Tag is read-only.",
//							Toast.LENGTH_SHORT).show();
//					// Sounds.PlayFailed(this, silent);
//					ndef.close();
//					return false;
//				}
//				ndef.close();
//				return true;
//			}
//		} catch (Exception e) {
//			Toast.makeText(this, "Failed to read tag", Toast.LENGTH_SHORT)
//					.show();
//			// Sounds.PlayFailed(this, silent);
//		}
//		return false;
//	}
	
	
}
