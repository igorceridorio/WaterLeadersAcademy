/*
 * WATER LEADERS ACADEMY APPLICATION
 * 
 * FILE: DatabaseConnector.java
 * DESCRIPTION: Class responsible to connect the application to a database and create
 * 				the tables that will be used in the application to store and retrieve 
 * 				data.
 * */

package com.ds.waterleadersacademy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DatabaseConnector {

	private static final String DATABASE_NAME = "WaterUsage"; // database name
	private SQLiteDatabase database; // database object
	private DatabaseOpenHelper databaseOpenHelper; // database helper

	// constructor
	public DatabaseConnector(Context context) {
		// create a new DatabaseOpenHelper
		databaseOpenHelper = new DatabaseOpenHelper(context, DATABASE_NAME,
				null, 1);
	}

	// open the database connection
	public void open() throws SQLException {
		// create or open a database for reading/writing
		database = databaseOpenHelper.getWritableDatabase();
		Log.w("WaterLeadersAcademy", "Successfully connected to the database");
	}

	// close the database connection
	public void close() {
		if (database != null) {
			database.close();
		}
	}

	// inserts a new record on the database
	public void insertRecord(float shower, float toilet, float bathroomSink,
			float kitchenSink, float dishwasher, float laundry, float outdoorHose,
			float sprinklers, float miscellaneous) {
		ContentValues newRecord = new ContentValues();
		newRecord.put("shower", shower);
		newRecord.put("toilet", toilet);
		newRecord.put("bathroomSink", bathroomSink);
		newRecord.put("kitchenSink", kitchenSink);
		newRecord.put("dishwasher", dishwasher);
		newRecord.put("laundry", laundry);
		newRecord.put("outdoorHose", outdoorHose);
		newRecord.put("sprinklers", sprinklers);
		newRecord.put("miscellaneous", miscellaneous);
		open();
		database.insert("usage", null, newRecord);
		Log.w("WaterLeadersAcademy", "Record succesfully added to the database");
		close();
	}

	// updates a full record from the database
	public void updateRecord(long id, float shower, float toilet, float bathroomSink,
			float kitchenSink, float dishwasher, float laundry, float outdoorHose,
			float sprinklers, float miscellaneous) {
		ContentValues editRecord = new ContentValues();
		editRecord.put("shower", shower);
		editRecord.put("toilet", toilet);
		editRecord.put("bathroomSink", bathroomSink);
		editRecord.put("kitchenSink", kitchenSink);
		editRecord.put("dishwasher", dishwasher);
		editRecord.put("laundry", laundry);
		editRecord.put("outdoorHose", outdoorHose);
		editRecord.put("sprinklers", sprinklers);
		editRecord.put("miscellaneous", miscellaneous);
		open();
		database.update("usage", editRecord, "_id=" + id, null);
		close();
	}
	
	// updates the shower record in the database
	public void updateShowerRecord(long id, float shower) {
		ContentValues editRecord = new ContentValues();
		editRecord.put("shower", shower);
		open();
		database.update("usage", editRecord, "_id=" + id, null);
	}
	
	// updates the toilet record in the database
	public void updateToiletRecord(long id, float toilet) {
		ContentValues editRecord = new ContentValues();
		editRecord.put("toilet", toilet);
		open();
		database.update("usage", editRecord, "_id=" + id, null);
	}
	
	// updates the bathroomSink record in the database
	public void updateBathroomSinkRecord(long id, float bathroomSink) {
		ContentValues editRecord = new ContentValues();
		editRecord.put("bathroomSink", bathroomSink);
		open();
		database.update("usage", editRecord, "_id=" + id, null);
	}
	
	// updates the kitchenSink record in the database
	public void updateKitchenSinkRecord(long id, float kitchenSink) {
		ContentValues editRecord = new ContentValues();
		editRecord.put("kitchenSink", kitchenSink);
		open();
		database.update("usage", editRecord, "_id=" + id, null);
	}
	
	// updates the dishwasher record in the database
	public void updateDishwasherRecord(long id, float dishwasher) {
		ContentValues editRecord = new ContentValues();
		editRecord.put("dishwasher", dishwasher);
		open();
		database.update("usage", editRecord, "_id=" + id, null);
	}
	
	// updates the laundry record in the database
	public void updateLaundryRecord(long id, float laundry) {
		ContentValues editRecord = new ContentValues();
		editRecord.put("laundry", laundry);
		open();
		database.update("usage", editRecord, "_id=" + id, null);
	}
	
	// updates the outdoorHose record in the database
	public void updateOutdoorHoseRecord(long id, float outdoorHose) {
		ContentValues editRecord = new ContentValues();
		editRecord.put("outdoorHose", outdoorHose);
		open();
		database.update("usage", editRecord, "_id=" + id, null);
	}
	
	// updates the sprinklers record in the database
	public void updateSprinklersRecord(long id, float sprinklers) {
		ContentValues editRecord = new ContentValues();
		editRecord.put("sprinklers", sprinklers);
		open();
		database.update("usage", editRecord, "_id=" + id, null);
	}
	
	// updates the miscellaneous record in the database
	public void updateMiscellaneousRecord(long id, float miscellaneous) {
		ContentValues editRecord = new ContentValues();
		editRecord.put("miscellaneous", miscellaneous);
		open();
		database.update("usage", editRecord, "_id=" + id, null);
	}

	// returns a Cursor with all information on the database
	public Cursor getAllRecords() {
		return database.query("usage", new String[] { "_id", "shower",
				"toilet", "bathroomSink", "kitchenSink", "dishwasher",
				"laundry", "outdoorHose", "sprinklers", "miscellaneous" },
				null, null, null, null, null);
	}

	// returns a Cursor with all information about one record
	public Cursor getOneRecord(long id) {
		return database.query("usage", null, "_id='" + id + "'", null, null,
				null, null);
	}

	// delete a record specified by a given id
	public void deleteRecord(long id) {
		open();
		database.delete("usage", "_id=" + id, null);
		close();
	}
	
	// returns the total amount of records present on the database
	public long getTotalRecords() {
		long n = DatabaseUtils.queryNumEntries(database, "usage");
		String s = String.valueOf(n);
		Log.w("WaterLeadersAcademy", "Total of records: " + s);
		return n;
	}

	private class DatabaseOpenHelper extends SQLiteOpenHelper {

		// constructor
		public DatabaseOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// query to create a new table named usage
			String createQuery = "CREATE TABLE usage"
							+ "(_id integer primary key autoincrement,"
							+ "shower REAL, toilet REAL, bathroomSink REAL,"
							+ "kitchenSink REAL, dishwasher REAL, laundry REAL,"
							+ "outdoorHose REAL, sprinklers REAL, miscellaneous REAL);";
			// execute the query
			db.execSQL(createQuery);
			Log.e("WaterLeadersAcademy", "Successfully create the table 'usage'");
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
		
	}

}
