package com.iris.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FamilyDbAdapter {
	public static final String KEY_TITLE = "title";    
	public static final String KEY_DATE = "date";    
	public static final String KEY_AREA = "area";
	public static final String KEY_CATEGORY = "category";   
	public static final String KEY_PHONENUMBER = "phoneNumber";   
	public static final String KEY_AMOUNT = "amount";   
	public static final String KEY_SECTION = "section"; // 1 = 지출 , 2 = 수입
	
	private static final String TAG = "FamilyDbAdapter";    
	
	private DatabaseHelper mDbHelper;    
	private SQLiteDatabase mDb;  
	
	private static final String DATABASE_CREATE = "create table familyevent (no integer primary key autoincrement, "     
			+ "title text not null, date text not null, area text not null, category text not null,"
			+ "phoneNumber text not null, amount int not null, section int not null);";    
	
	private static final String DATABASE_NAME = "IRIS";   
	private static final String DATABASE_TABLE = "familyevent";   
	private static final int DATABASE_VERSION = 2;    
	private final Context mCtx;    
	private static class DatabaseHelper extends SQLiteOpenHelper 
	{        
		DatabaseHelper(Context context) 
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);       
		}
		@Override        
		public void onCreate(SQLiteDatabase db) 
		{ 
			db.execSQL(DATABASE_CREATE); 
			db.execSQL("insert into familyevent values(null,'a결혼식','20130326','전북 송파구','결혼식','010-1234-5678',50000,1)");
			db.execSQL("insert into familyevent values(null,'b결혼식','20140326','서울 강남구','생일','010-3333-5678', 150000,2)");
			db.execSQL("insert into familyevent values(null,'c결혼식','20150326','서울 강동구','장례식','010-5555-5678',30000,1)");
		}
		@Override       
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{              
			db.execSQL("DROP TABLE IF EXISTS familyevent");         
			onCreate(db);      
		}
	}
	public FamilyDbAdapter(Context ctx) 
	{
		this.mCtx = ctx;  
	}
	public FamilyDbAdapter open() throws SQLException
	{
		mDbHelper = new DatabaseHelper(mCtx);      
		mDb = mDbHelper.getWritableDatabase();     
		

		return this;  
	}
	public void close()
	{
		mDbHelper.close(); 
	}
	public long createFamilyEvent(String title, String date, String area, String category, String phoneNumber, int amount) 
	{
		ContentValues initialValues = new ContentValues();  
		initialValues.put(KEY_TITLE, title);    
		initialValues.put(KEY_DATE, date);  
		initialValues.put(KEY_AREA, area);
		initialValues.put(KEY_AMOUNT, amount);
		initialValues.put(KEY_CATEGORY, category);
		initialValues.put(KEY_PHONENUMBER, phoneNumber);
		return mDb.insert(DATABASE_TABLE, null, initialValues);  
	}
//	public boolean deleteNote(long rowId) 
//	{     
//		return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;   
//	}
	
	public Cursor selectFamilyEvent(String sqlQuery) 
	{
		return mDb.rawQuery(sqlQuery, null);  
	}     
	
//	public Cursor fetchNote(long rowId) throws SQLException 
//	{
//		Cursor mCursor = mDb.query(true, DATABASE_TABLE, new String[]
//				{KEY_ROWID, KEY_TITLE, KEY_BODY }, KEY_ROWID             
//				+ "=" + rowId, null, null, null, null, null);       
//		if (mCursor != null) 
//		{          
//			mCursor.moveToFirst();       
//		}
//		return mCursor;   
//	}
	
//	public boolean updateNote(long rowId, String title, String body)
//	{
//		ContentValues args = new ContentValues();   
//		args.put(KEY_TITLE, title);   
//		args.put(KEY_BODY, body);   
//		return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;   
//	}
}

