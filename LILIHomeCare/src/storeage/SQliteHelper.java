package storeage;

import model.TaskForOneCustomer;
import model.Tasks;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQliteHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "LILIDB";
	private static final String TABLE_NAME = "LILI_TABLE";
	private static final int DATABASE_VERSION = 1;
	private static final String KEY_ID = "id";
	private static final String CAREGIVER = "caregiver";
	private static final String CLIENT_NAME = "client";
	private static final String START_TIME = "startTime";
	private static final String END_TIME = "endTime";
	private static final String TASK = "task";

	

	public SQliteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_BOOK_TABLE = "CREATE TABLE "+ TABLE_NAME +"( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                CLIENT_NAME+" TEXT, "+
                START_TIME+" TEXT, "+
                END_TIME+" TEXT, "+
                CAREGIVER+" TEXT, "+
                TASK+" TEXT )";
        db.execSQL(CREATE_BOOK_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		this.onCreate(db);

	}

	public void add(TaskForOneCustomer clientTask) {
		StringBuilder tasks= new StringBuilder();
		for (Tasks t : clientTask.getTasksList()) {
			tasks.append(t.toString());
		}

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(CLIENT_NAME, clientTask.getCustomerName());
		values.put(CAREGIVER, clientTask.getCareGiver());
		
		values.put(START_TIME, clientTask.getStartTimeCustomer().toGMTString());
		values.put(END_TIME, clientTask.getEndTimeCusotmer().toGMTString());
		values.put(TASK, tasks.toString());
		db.insert(TABLE_NAME, null, values);

	}

	public String retriveAll() {
		String query ="SELECT * FROM "+TABLE_NAME;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor=db.rawQuery(query, null);
		StringBuilder sb=null;
		if(cursor.moveToFirst()){
			sb= new StringBuilder();
			do{
				sb.append(cursor.getString(0)+". "+ "Client: "+cursor.getString(1)+"<br/>"
						+"<br/> Caregiver: " + cursor.getString(4)+"<br/>"
						+"<br/> Time: " + cursor.getString(2)+" - " +cursor.getString(3)+"<br/>"
						+ cursor.getString(5)+"<br/><br/>");
			}while(cursor.moveToNext());
		}
		
		return sb.toString();
	}

}
