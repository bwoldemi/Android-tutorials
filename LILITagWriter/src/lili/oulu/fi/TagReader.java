package lili.oulu.fi;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.NetworkInfo.DetailedState;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class TagReader extends Activity {

	public static final int STATE_OFF = 1;
	public static final int STATE_TURNING_ON = 2;
	public static final int STATE_ON = 3;
	public static final int STATE_TURNING_OFF = 4;

	public static final String Tag = TagWriter.class.getName();

	private NfcAdapter nfcAdapter;
	private IntentFilter[] writeTagFilter;
	private PendingIntent nfcPendingIntent;

	private TextView textViewType;
	private TextView textViewSize;
	private TextView textViewContent;
	private TextView textViewOther;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag_reader);
		textViewType =(TextView)findViewById(R.id.textViewType);
		textViewSize =(TextView)findViewById(R.id.textViewSize);
		textViewContent =(TextView)findViewById(R.id.textViewContent);
		textViewOther =(TextView)findViewById(R.id.textViewOther);
		
		PackageManager pc = getPackageManager();

		if (pc.hasSystemFeature(PackageManager.FEATURE_NFC)
				&& NfcAdapter.getDefaultAdapter(this) != null) {
			Log.d(Tag, "Has Nfc sensor");
			initialize();

		} else {
			Log.d(Tag, "Has No Nfc sensor");
			Toast.makeText(this, "NFC not Found", Toast.LENGTH_SHORT).show();
		}

	}

	/**
	 * initialize 
	 */
	public void initialize() {
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
		IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
		IntentFilter tecDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);

		nfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

		writeTagFilter = new IntentFilter[] { tagDetected,ndefDetected,tecDetected};

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (nfcAdapter != null) {
			nfcAdapter.enableForegroundDispatch(this, nfcPendingIntent, writeTagFilter, null);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (nfcAdapter != null) {
			nfcAdapter.disableForegroundDispatch(this);
		}
	}
	
	
	/**
	 * call back function when NFC tag approach to the device 
	 */
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())){
			textViewType.setText(" Ndef Discovered");
			 Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		        if (rawMsgs != null) {
		        	NdefMessage[]  msgs = new NdefMessage[rawMsgs.length];
		            for (int i = 0; i < rawMsgs.length; i++) {
		                msgs[i] = (NdefMessage) rawMsgs[i];
		            }
		        }
		    
			
		}else if(NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())){
			Log.d("ACTION_TYPE", "ACTION_TECH_DISCOVERED");
			textViewType.setText(" Tech Discovered");
		}
		else if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
			Log.d("ACTION_TYPE", "ACTION_TAG_DISCOVERED");
			textViewType.setText("Tag Discovered");
			String type= intent.getType();
//			if(type!=null){
//				textViewContent.setText(type);
//			}else {
//				textViewContent.setText("Null");
//			}
			
//			Parcelable[] rawData = intent.getParcelableArrayExtra(NfcAdapter.ACTION_TAG_DISCOVERED);
//			NdefMessage nMsg = (NdefMessage)rawData[0];
//			textViewContent.setText(new String(nMsg.getRecords()[0].getPayload()));
//			if (parcelable == null) {
//				Log.d("Parcable", "null");
//
//			} else {
//				for (Parcelable p : parcelable) {
//					Log.d("id Parcable", p.toString());
//				}
//			}
			
			Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			NfcTagInfo tagInfo;
			try {
				tagInfo = getTagInfo(tag);
				textViewSize.setText("Size byte: "+Integer.toString(tagInfo.size));
				textViewOther.setText("Type:  "+ tagInfo.getStatus());
				new NdefReaderTask().execute(tag);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			Log.d("Tag Id", tag.getId().toString());
			Log.d("Tag Class Name", tag.getId().toString());
			Log.d("Tag To String", tag.toString());
		}
		
	}
	
	/**
	 * get Tag info 
	 * @param tag
	 * @return
	 * @throws FormatException 
	 * @throws IOException 
	 */
	public NfcTagInfo getTagInfo(Tag tag) throws IOException, FormatException {
		Ndef ndef = Ndef.get(tag);
		int size= ndef.getMaxSize();
		//String data=ndef.getType();
		//String data =ndefMessage.toString();
		try {
			
			if (ndef.isWritable()) {
				ndef.close();
				return new NfcTagInfo("Writable", size);
			} else{
				ndef.close();
				return new NfcTagInfo("Read Only", size);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			return new NfcTagInfo("UnKnown", size);
		}

	}

	
	class NfcTagInfo{
		private String status;
		private int size;
	
		
		/**
		 * Tag Info 
		 * @param status
		 * @param size
		 */
		public NfcTagInfo(String status, int size) {
			super();
			this.status = status;
			this.size = size;
			
		}

		public String getStatus() {
			return status;
		}
	
		public int getSize() {
			return size;
		}
		
		
		
	}
	
	
	private class NdefReaderTask extends AsyncTask<Tag, Void, String> {
		 
	    @Override
	    protected String doInBackground(Tag... params) {
	        Tag tag = params[0];
	         
	        Ndef ndef = Ndef.get(tag);
	        if (ndef == null) {
	            // NDEF is not supported by this Tag. 
	            return null;
	        }
	 
	        NdefMessage ndefMessage = ndef.getCachedNdefMessage();
	 
	        NdefRecord[] records = ndefMessage.getRecords();
	        for (NdefRecord ndefRecord : records) {
	           // if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
	                try {
	                    return readText(ndefRecord);
	                } catch (UnsupportedEncodingException e) {
	                    Log.e("DO_IN_BACKGROUDND", "Unsupported Encoding", e);
	                }
	           // }
	        }
	 
	        return null;
	    }
	     
	    private String readText(NdefRecord record) throws UnsupportedEncodingException {
	        
	        byte[] payload = record.getPayload();
	        
	        // Get the Text Encoding
	        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
	 
	        // Get the Language Code
	      //  int languageCodeLength = payload[0] & 0063;
	         
	        // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");
	        // e.g. "en"
	         
	        // Get the Text
	        return new String(payload);
	    }
	     
	    @Override
	    protected void onPostExecute(String result) {
	        if (result != null) {
	        	textViewContent.setText("Read content: " + result);
	        }
	    }
	}
}
