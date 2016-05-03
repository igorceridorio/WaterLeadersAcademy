/*
 * WATER LEADERS ACADEMY AAPLICATION
 * 
 * FILE: LaundryActivity.java
 * DESCRIPTION: Class responsible for the LaundryActivity.
 * */

package com.ds.waterleadersacademy;

import com.example.waterleadersacademy.R;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

public class LaundryActivity extends Activity {

	// connector for the database
	DatabaseConnector databaseConnector = new DatabaseConnector(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// call superclass's version
		super.onCreate(savedInstanceState);
		
		//Opens app in portrait mode only
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		// inflate the screen GUI
		setContentView(R.layout.laundrylayout);
	}
	
	// close this activity and go back to the main menu 
	public void onClickMainMenu(View view) {
		finish();
	}
	
	// method used to add Laundry value to the database
		public void onClickLaundry(View view) {
			//create a alert dialog box
			new AlertDialog.Builder(this)
				.setTitle("Info")
				.setMessage("Input recorded successfully to the database.")
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						// open the connection with the database
						databaseConnector.open();
						
						// add the value to the database
						long n = databaseConnector.getTotalRecords();
						if(n != 0) {
							Cursor crs = databaseConnector.getAllRecords();
							while(crs.moveToNext()) {
							
								// getting the _id of the record
								String sId = crs.getString(crs.getColumnIndex("_id"));
								Log.w("WaterLeadersAcademy", "Id of the record: " + sId);
								long lId = Long.parseLong(sId);
								
								// getting the previous value of laundry, incrementing and updating it
								String sLaundry = crs.getString(crs.getColumnIndex("laundry"));
								Log.w("WaterLeadersAcademy", "Laundry previous value: " + sLaundry);
								float fLaundry = Float.parseFloat(sLaundry);
								fLaundry += 113.6;
								databaseConnector.updateLaundryRecord(lId, fLaundry);

							}
						}
						
						// close the connection with the database
						databaseConnector.close();
						
					}
				})
				.setIcon(android.R.drawable.ic_dialog_info)
				.show();
		}
}
