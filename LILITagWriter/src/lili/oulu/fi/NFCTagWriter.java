package lili.oulu.fi;

import java.io.IOException;
import java.nio.charset.Charset;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.TagLostException;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class NFCTagWriter extends Activity {
	private NfcAdapter nfcAdapter;
	private IntentFilter[] writeTagFilter;
	private PendingIntent nfcPendingIntent;
	private String Tag = NFCTagWriter.class.getName();
	
	TextView textViewStatus;
	TextView textViewData;
	String data=null;
	String type=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nfctag_writer);
		PackageManager pc = getPackageManager();
		// will get the message 
		 data= getIntent().getStringExtra("message");
		 type= getIntent().getStringExtra("type");
		 
		textViewStatus = (TextView)findViewById(R.id.textViewStatus);
		textViewData = (TextView)findViewById(R.id.textViewData);
		
		textViewData.setText(Html.fromHtml("<b>"+"Data: "+"</b>"+ data +"<br/><br/>" + "<b>" +" Type: "+"</b>"+type));
		
		if (pc.hasSystemFeature(PackageManager.FEATURE_NFC)
				&& NfcAdapter.getDefaultAdapter(this) != null) {
			Log.d(Tag, "Has Nfc sensor");
			initialize();
		} else {
			Log.d(Tag, "Has No Nfc sensor");
			Toast.makeText(this, "NFC Not Found", Toast.LENGTH_LONG).show();
			finish();
		}

	}

	protected void initialize() {
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		IntentFilter tagDetected = new IntentFilter(
				NfcAdapter.ACTION_TAG_DISCOVERED);
		IntentFilter ndefDetected = new IntentFilter(
				NfcAdapter.ACTION_NDEF_DISCOVERED);
		IntentFilter tecDetected = new IntentFilter(
				NfcAdapter.ACTION_TAG_DISCOVERED);
		// pending intent 
		nfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		
		// intent filter 
		writeTagFilter = new IntentFilter[] { tagDetected, ndefDetected,
				tecDetected };
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(nfcAdapter!=null){
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
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())){
			Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			boolean status=writeTag(detectedTag, data,type);
			if(status){
				textViewStatus.setText("Writen Successfully !!");
			}else {
				Toast.makeText(this, "Supported, Writable", Toast.LENGTH_SHORT).show();
			}
		}
		else if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction()) && data!=null) {
			Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			
				boolean status=writeTag(detectedTag, data,type);
				if(status){
					textViewStatus.setText("Writen Successfully !!");
				}else {
					Toast.makeText(this, "Supported, Writable", Toast.LENGTH_SHORT).show();
				}
				
			}
		}

	public static boolean writeTag(Tag tag, String data, String type) {
	    // Record with actual data we care about
		String appType="application/"+type;
	    NdefRecord mimeRecord = new NdefRecord(
	    	    NdefRecord.TNF_MIME_MEDIA ,
	    	    appType.getBytes(Charset.forName("US-ASCII")),
	    	    new byte[0], data.getBytes(Charset.forName("US-ASCII")));
	    
	    // Complete NDEF message with both records
	    NdefMessage message = new NdefMessage(new NdefRecord[] {mimeRecord});

	    try {
	        // If the tag is already formatted, just write the message to it
	        Ndef ndef = Ndef.get(tag);
	        if(ndef != null) {
	            ndef.connect();
	            
	            // Make sure the tag is writable
	            if(!ndef.isWritable()) {
	                return false;
	            }

	            // Check if there's enough space on the tag for the message
	            int size = message.toByteArray().length;
	            if(ndef.getMaxSize()< size) {
	                return false;
	            }

	            try {
	                // Write the data to the tag
	                ndef.writeNdefMessage(message);
	               // DialogUtils.displayInfoDialog(context, R.string.nfcWrittenTitle, R.string.nfcWritten);
	                return true;
	            } catch (TagLostException tle) {
	               // DialogUtils.displayErrorDialog(context, R.string.nfcTagLostErrorTitle, R.string.nfcTagLostError);
	                return false;
	            } catch (IOException ioe) {
	              //  DialogUtils.displayErrorDialog(context, R.string.nfcFormattingErrorTitle, R.string.nfcFormattingError);
	                return false;
	            } catch (FormatException fe) {
	              //  DialogUtils.displayErrorDialog(context, R.string.nfcFormattingErrorTitle, R.string.nfcFormattingError);
	                return false;
	            }
	        // If the tag is not formatted, format it with the message
	        } else {
	            NdefFormatable format = NdefFormatable.get(tag);
	            if(format != null) {
	                try {
	                    format.connect();
	                    format.format(message);

	                //    DialogUtils.displayInfoDialog(context, R.string.nfcWrittenTitle, R.string.nfcWritten);
	                    return true;
	                } catch (TagLostException tle) {
	                  //  DialogUtils.displayErrorDialog(context, R.string.nfcTagLostErrorTitle, R.string.nfcTagLostError);
	                    return false;
	                } catch (IOException ioe) {
	                   // DialogUtils.displayErrorDialog(context, R.string.nfcFormattingErrorTitle, R.string.nfcFormattingError);
	                    return false;
	                } catch (FormatException fe) {
	                   // DialogUtils.displayErrorDialog(context, R.string.nfcFormattingErrorTitle, R.string.nfcFormattingError);
	                    return false;
	                }
	            } else {
	             //   DialogUtils.displayErrorDialog(context, R.string.nfcNoNdefErrorTitle, R.string.nfcNoNdefError);
	                return false;
	            }
	        }
	    } catch(Exception e) {
	      //  DialogUtils.displayErrorDialog(context, R.string.nfcUnknownErrorTitle, R.string.nfcUnknownError);
	    }

	    return false;
	}
	
	
	public NdefMessage getNdefMessage(String payload) {
		
		String mimeType = "application/lili";
		//String payload = "This is a TNF_MIME_MEDIA";
		NdefRecord mimeRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA ,
		 mimeType.getBytes(), new byte[0], 
		 payload.getBytes(Charset.forName("US-ASCII")));
		NdefMessage newMessage= new NdefMessage(new NdefRecord[] { mimeRecord });
		
		return newMessage;
	}
	
}
