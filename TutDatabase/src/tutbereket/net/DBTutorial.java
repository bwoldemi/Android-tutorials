package tutbereket.net;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBTutorial extends SQLiteOpenHelper {
	
	private static final String DBNAME="PERSONDB";
	private static final String TABLENAME="PERSON";
	
	private static final String ID="ID";
	private static final String NAME="NAME";
	//private static final String[] COLUMNS= {ID, NAME};
	
	
	private static final String  dbCreate="CREATE TABLE"+TABLENAME+"( id INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME +" TEXT NOT NULL)";

	public DBTutorial(Context context) {
		super(context, DBNAME, null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	//	db.execSQL(dbCreate);
		String CREATE_BOOK_TABLE = "CREATE TABLE books ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "title TEXT, "+
                "author TEXT )";
 
        // create books table
        db.execSQL(CREATE_BOOK_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		 db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
//		 this.onCreate(db);
		 
		 db.execSQL("DROP TABLE IF EXISTS books");
		 
	        // create fresh books table
	        this.onCreate(db);
		 
		
	}
	public void addComment(String comment){
		Log.d("Adding Comment", "Comment");
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues values= new ContentValues();
		values.put(NAME	,comment);
		 db.insert(TABLENAME,  null, values);
		db.close();
		
	}
	
	public List<String> getAllBook(){
		List<String>result= new ArrayList<String>();
		String query= "SELECT * FROM "+TABLENAME;
		SQLiteDatabase db= this.getWritableDatabase();
		Cursor cursor= db.rawQuery(query, null);
		if(cursor.moveToFirst()){
			do{
			
				result.add(cursor.getString(0) +cursor.getString(1));
				
			}while(cursor.moveToNext());
			
		}
		return result;
	}

}
