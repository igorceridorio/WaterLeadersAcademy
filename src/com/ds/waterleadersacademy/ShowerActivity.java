/*
 * WATER LEADERS ACADEMY APPLICATION
 * 
 * FILE: ShowerActivity.java
 * DESCRIPTION: Class responsible for the ShowerActivity.
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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ShowerActivity extends Activity {
	
	// variables used with the seek bar
	private SeekBar showerSeekBar;
	private TextView showerTextView;
	
	// connector for the database
	DatabaseConnector databaseConnector = new DatabaseConnector(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// call superclass's version
		super.onCreate(savedInstanceState);
		
		//Opens app in portrait mode only
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		// inflate the screen GUI
		setContentView(R.layout.showerlayout);	
		
		initializeVariables();
		
		// seek bar listener
		showerSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			int showerMinsUsed = 0;
			
			@Override
			public void onProgressChanged(SeekBar showerSeekBar, int mins, boolean arg2) {
				showerMinsUsed = mins;
				showerTextView.setText(""+showerSeekBar.getProgress() + " minutes");
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	// close this activity and go back to the main menu 
	public void onClickMainMenu(View view) {
		finish();
	}
	
	// method used to add a average shower value to the database
	public void onClickAverage(View view) {
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
							
							// getting the previous value of shower, incrementing and updating it
							String sShower = crs.getString(crs.getColumnIndex("shower"));
							Log.w("WaterLeadersAcademy", "Shower previous value: " + sShower);
							float fShower = Float.parseFloat(sShower);
							fShower += 151;
							databaseConnector.updateShowerRecord(lId, fShower);

						}
					}
					
					// close the connection with the database
					databaseConnector.close();
					
				}
			})
			.setIcon(android.R.drawable.ic_dialog_info)
			.show();
	}
	
	// method used to add a custom shower value to the database
	public void onClickAdd(View view) {
		//create a alert dialog box
		new AlertDialog.Builder(this)
			.setTitle("Info")
			.setMessage("Input recorded successfully in database.")
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
							
							// getting the previous value of shower, incrementing and updating it
							String sShower = crs.getString(crs.getColumnIndex("shower"));
							Log.w("WaterLeadersAcademy", "Shower previous value: " + sShower);
							float fShower = Float.parseFloat(sShower);
							fShower += showerSeekBar.getProgress() * 15.1;
							databaseConnector.updateShowerRecord(lId, fShower);
							
							int i = showerSeekBar.getProgress();
							Log.w("WaterLeadersAcademy", "Seek bar value: " + Integer.toString(i));

						}
					}
					
					// close the connection with the database
					databaseConnector.close();
					
				}
			})
			.setIcon(android.R.drawable.ic_dialog_info)
			.show();
	}
	
	// method used to initialize the seek bar variables
	private void initializeVariables() {
		showerSeekBar = (SeekBar) findViewById(R.id.shower_seekBar);
		showerTextView = (TextView) findViewById(R.id.shower_minutes_text);
	}
	
}
