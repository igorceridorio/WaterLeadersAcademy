/*
 * WATER LEADERS ACADEMY AAPLICATION
 * 
 * FILE: OutdoorHoseActivity.java
 * DESCRIPTION: Class responsible for the OutdoorHoseActivity.
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

public class OutdoorHoseActivity extends Activity {

	// variables used with the seek bar
	private SeekBar outdoorHoseSeekBar;
	private TextView outdoorHoseTextView;

	// connector for the database
	DatabaseConnector databaseConnector = new DatabaseConnector(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// call superclass's version
		super.onCreate(savedInstanceState);
		
		//Opens app in portrait mode only
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// inflate the screen GUI
		setContentView(R.layout.outdoorhoselayout);
		
		initializeVariables();

		// seek bar listener
		outdoorHoseSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			int outdoorHoseMinsUsed = 0;

			@Override
			public void onProgressChanged(SeekBar outdoorHoseSeekBar, int mins, boolean arg2) {
				outdoorHoseMinsUsed = mins;
				outdoorHoseTextView.setText(""+outdoorHoseSeekBar.getProgress() + " minutes");
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

	//Method to add the outdoor hose value to the report
	public void onClickAdd(View view)
	{
		//Creating and alert Dialog box
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
						String sOutdoorHose = crs.getString(crs.getColumnIndex("outdoorHose"));
						Log.w("WaterLeadersAcademy", "Outdoor hose previous value: " + sOutdoorHose);
						float fOutdoorHose = Float.parseFloat(sOutdoorHose);
						fOutdoorHose += outdoorHoseSeekBar.getProgress() * 22.7;
						databaseConnector.updateOutdoorHoseRecord(lId, fOutdoorHose);
						
						int i = outdoorHoseSeekBar.getProgress();
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

	private void initializeVariables() {
		outdoorHoseSeekBar = (SeekBar) findViewById(R.id.outdoorHose_seekBar);
		outdoorHoseTextView = (TextView) findViewById(R.id.outdoorHose_minutes_text);
	}

}
