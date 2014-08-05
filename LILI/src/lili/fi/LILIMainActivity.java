package lili.fi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.TaskContainer;
import model.TaskForOneCustomer;
import model.Tasks;
import server.fi.Server;
import storeage.SQliteHelper;
import android.app.Activity;

import android.app.AlertDialog;

import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.text.format.DateFormat;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class LILIMainActivity extends Activity implements TaskObsorver, OnSharedPreferenceChangeListener {

	private TextView textViewCustmerName;
	private TextView textViewCustomerAdress;
	private TextView textViewCareGiver;
	private TextView textviewTaskName;
	private TextView textViewTaskDetail;
	private TextView textViewTaskReview;

	boolean taskListed = false;
	boolean taskStarted = false;

	private String currentTaskTagId = null;

	public List<Tasks> tasks = null;

	TaskForOneCustomer customerTask;

	// NFC access
	private NfcAdapter mNfcAdapter;
	private PendingIntent nfcPendingIntent;

	// for downloading data from webserver
	private Server server;
	private TaskContainer taskContainer;

	// database
	SQliteHelper db;
	
	// NFC TAG data
	private String tagId = null;
	SharedPreferences preferences;

	private static final String TAG = LILIMainActivity.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lilimain);

		//customerTask = new TaskForOneCustomer();

		textViewCustmerName = (TextView) findViewById(R.id.textViewCustomerName);
		textViewCustomerAdress = (TextView) findViewById(R.id.textViewCustomerAddress);
		textViewCareGiver = (TextView) findViewById(R.id.textViewCareGiver);

		textviewTaskName = (TextView) findViewById(R.id.textViewTaskName);
		textViewTaskDetail = (TextView) findViewById(R.id.textViewTaskDetail);
		textViewTaskReview = (TextView) findViewById(R.id.textViewTaskReview);

		// getting NFC service
		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
		nfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

		if (mNfcAdapter == null) {
			Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG)
					.show();
			finish();
			return;
		}

		if (mNfcAdapter.isEnabled() == false) {
			Toast.makeText(this, "Please Enable NFC Mode", Toast.LENGTH_LONG)
					.show();
			finish();
		}

		// Initializing
		taskContainer = new TaskContainer();
		server = new Server(this);
		db = new SQliteHelper(this);
		server.setmObsorver((TaskObsorver) this);
		if (checkNetwork()) {
			server.reteriveData();
		} else {
			this.error("No Internet Connection");
		}
		
		

	}

	/**
	 * Check network connection, return true if connected
	 * 
	 * @return
	 */
	public boolean checkNetwork() {
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		Log.d("CheckNetwork", "No network, cannot initiate retrieval!");
		return false;
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mNfcAdapter != null) {
			enableForeGround();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mNfcAdapter != null) {
			// mNfcAdapter.disableForegroundDispatch(this);
		}

	}

	// TODO
	@Override
	protected void onNewIntent(Intent intent) {
		if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
			String tagUssage = null;
			NdefMessage[] messages = getNdefMessage(intent);
			// for TNF Media type
			if (messages != null) {
				StringBuilder type = new StringBuilder();
				StringBuilder data = new StringBuilder();
				for (int i = 0; i < messages.length; i++) {
					for (int j = 0; j < messages[0].getRecords().length; j++) {
						NdefRecord record = messages[i].getRecords()[j];
						type.append(new String(record.getType()));
						data.append(new String(record.getPayload()));
					}
				}

				tagUssage = type.toString();
				tagId = data.toString();
				if (tagUssage.equals("application/door") && taskListed == false
						&& taskStarted == false) {
					int customerId = Integer.parseInt(tagId.toString());

					customerTask = taskContainer.getCustomerTask(customerId);
					tasks = customerTask.getTasksList();
					customerTask.setStartTimeCustomer(new Date());
					listTask();
					taskListed = true;
				} else if (tagUssage.equals("application/door")
						&& taskListed == true) {
					Toast.makeText(this,
							"Please tap Task-Tags, to see list of Tasks",
							Toast.LENGTH_LONG).show();
					;

				} else if (tagUssage.equals("application/task")
						&& taskStarted == false && taskListed == true) {

					displayTaskDetail();
					// display Task

				} else if (tagUssage.equals("application/task")
						&& taskStarted == true && taskListed == false) {
					// check if it is same tag, if same taskid display dialog
					// else ask to diplay
					if (currentTaskTagId.equals(tagId)) {
						AlertDialog.Builder dialog = new AlertDialog.Builder(
								this);
						dialog.setTitle("Task status");
						dialog.setMessage("Click YES to complete the task!")
								.setCancelable(false)
								.setPositiveButton("Yes",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {

												Tasks task = null;
												for (Tasks t : tasks) {
													if (t.getTaskId().equals(
															tagId)) {
														task = t;
													}
												}
												task.setTaskEndTime(new Date());
												listTask();

											}
										})
								.setNegativeButton("No",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {
												dialog.cancel();
											}
										});
						AlertDialog alertDialog = dialog.create();
						alertDialog.show();
					} else {
						Toast.makeText(this, "Please Complete Current Task",
								Toast.LENGTH_SHORT).show();
					}
				}

			}
		}// last

	}

	NdefMessage[] getNdefMessage(Intent intent) {
		NdefMessage[] message = null;
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
			Parcelable[] rawMessage = intent
					.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
			if (rawMessage != null) {
				message = new NdefMessage[rawMessage.length];
				for (int i = 0; i < rawMessage.length; i++) {
					message[i] = (NdefMessage) rawMessage[i];
				}
			} else {
				byte[] empty = new byte[] {};
				NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN,
						empty, empty, empty);
				NdefMessage msg = new NdefMessage(new NdefRecord[] { record });
				message = new NdefMessage[] { msg };
			}
		} else if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
			Parcelable[] rawMessage = intent
					.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
			if (rawMessage != null) {
				message = new NdefMessage[rawMessage.length];
				for (int i = 0; i < rawMessage.length; i++) {
					message[i] = (NdefMessage) rawMessage[i];
				}
			} else {
				byte[] empty = new byte[] {};
				NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN,
						empty, empty, empty);
				NdefMessage msg = new NdefMessage(new NdefRecord[] { record });
				message = new NdefMessage[] { msg };
			}
		}

		return message;

	}

	public void displayTaskDetail() {

		Tasks task = null;
		for (Tasks t : this.tasks) {
			if (t.getTaskId().equals(tagId)) {
				task = t;
			}
		}
		if (task.getTaskStartTime() != null) {

			Toast.makeText(this, "Task already Done", Toast.LENGTH_SHORT)
					.show();
			

		} else {
			currentTaskTagId = tagId;
			this.taskStarted = true;
			this.taskListed = false;

			task.setTaskStartTime(new Date());
			if (task != null) {
				textviewTaskName.setText(Html.fromHtml("<b> Task Name</b>"
						+ ": " + task.getTaskName()));
				textViewTaskDetail.setText(Html
						.fromHtml("<b>Task Detail</b><br/>" + "  *"
								+ task.getTaskDetail()));
			}
		}

	}

	public void listTask() {
		reset();
		this.taskStarted = false;
		this.taskListed = true;
		if (customerTask == null) {
			return;
		}
		

		textViewCustmerName.setText(Html.fromHtml("<b>" + "Customer Name: "
				+ "</b>" + customerTask.getCustomerName()));
		textViewCustomerAdress.setText(Html.fromHtml("<b>"
				+ "Customer Adress : " + "</b>"
				+ customerTask.getCustomerAddress()));
		if (customerTask.getStartTimeCustomer() != null) {
			textViewCareGiver.setText(Html.fromHtml("<b>" + "Care Giver Name:"
					+ "</b>" + customerTask.getCareGiver()
					+ "<br/><b>Task Started: </b>"
					+ customerTask.getStartTimeCustomer().toLocaleString()));
		} else {
			textViewCareGiver.setText(Html.fromHtml("<b>" + "Care Giver Name:"
					+ "</b>" + customerTask.getCareGiver()));
		}

		List<Tasks> tasks = customerTask.getTasksList();
		if (tasks != null) {

			for (int i = 0; i < tasks.size(); i++) {
				if (tasks.get(i).getTaskStartTime() == null) {
					textViewTaskDetail.append(Html.fromHtml(Integer
							.toString(i + 1)
							+ "."
							+ tasks.get(i).getTaskName()
							+ "    "
							+ "<font color='red'>Waiting</font><br/><br/>"));
				} else if (tasks.get(i).getTaskStartTime() != null
						&& tasks.get(i).getTaskEndTime() == null) {
					textViewTaskDetail.append(Html.fromHtml(Integer
							.toString(i + 1)
							+ "."
							+ tasks.get(i).getTaskName()
							+ "    "
							+ "<font color='green'>Started</font><br/><br/>"));
				} else {
					SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
					String startTime = sdf.format(tasks.get(i)
							.getTaskStartTime());
					String endTime = sdf.format(tasks.get(i).getTaskEndTime());
					textViewTaskDetail
							.append(Html.fromHtml(Integer.toString(i + 1) + "."
									+ tasks.get(i).getTaskName() + "    "
									+ " <br/><font color='yellow'>Completed :"
									+ startTime + " -" + endTime
									+ "</font><br/><br/>"));
				}
			}
		}

		if (isAllTasksCompleted()) {
			customerTask.setEndTimeCusotmer(new Date());
			String startTime = DateFormat.format("h:mm a",
					customerTask.getStartTimeCustomer()).toString();
			String endTime = DateFormat.format("h:mm a",
					customerTask.getEndTimeCusotmer()).toString();

			textViewTaskReview.setText(Html.fromHtml("Task Completed" + "<br/>"
					+ "<font style='yellow'>" + startTime + " - " + endTime
					+ "</font>"));

			this.taskCompleted();

		}
	}

	public void enableForeGround() {
		IntentFilter ndefIntentFilter = new IntentFilter(
				NfcAdapter.ACTION_NDEF_DISCOVERED);
		IntentFilter tagIntentFilter = new IntentFilter(
				NfcAdapter.ACTION_TAG_DISCOVERED);
		IntentFilter tecIntentFilter = new IntentFilter(
				NfcAdapter.ACTION_TECH_DISCOVERED);
		IntentFilter[] writeTagFilters = new IntentFilter[] { ndefIntentFilter,
				tagIntentFilter, tecIntentFilter };
		mNfcAdapter.enableForegroundDispatch(this, nfcPendingIntent,
				writeTagFilters, null);

	}

	// initialize
	public void initialize() {

		customerTask.setStartTimeCustomer(null);
		customerTask.setEndTimeCusotmer(null);

		for (Tasks t : tasks) {
			t.setTaskEndTime(null);
			t.setTaskStartTime(null);
		}
		taskListed = false;
		taskStarted = false;

	}

	public void reset() {
		textViewCustmerName.setText("");
		textViewCustomerAdress.setText("");
		textViewCareGiver.setText("");
		textViewTaskDetail.setText("");
		textviewTaskName.setText("");
		textViewTaskReview.setText("");

	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub
	}

	@Override
	public void taskCompleted() {
		db.add(customerTask);
		initialize();
	}

	@Override
	public void taskStarted() {
	}

	@Override
	public void downloadCompleted() {
		taskContainer.addTasks(server.getTasks());
		Toast.makeText(this, "Download Completed! ", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void error(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	public boolean isAllTasksCompleted() {
		if (tasks == null) {
			return false;
		}
		for (Tasks t : tasks) {
			if (t.getTaskEndTime() == null) {
				return false;
			}
		}
		return true;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.lilimain, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			return true;
		}else if(id ==R.id.history){
			Intent intent = new Intent(this, History.class);
			String history =db.retriveAll();
			intent.putExtra("history", history);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	 
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		
	}

}
