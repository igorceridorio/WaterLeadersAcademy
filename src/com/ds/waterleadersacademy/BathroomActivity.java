/*
 * WATER LEADERS ACADEMY AAPLICATION
 * 
 * FILE: BathroomActivity.java
 * DESCRIPTION: Class responsible for the BathroomActivity.
 * */

package com.ds.waterleadersacademy;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.waterleadersacademy.R;

public class BathroomActivity extends Activity {

	// variables used with the seek bar
	private SeekBar bathroomSinkSeekBar;
	private TextView bathroomSinkTextView;
	
	// connector for the database
	DatabaseConnector databaseConnector = new DatabaseConnector(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// call superclass's version
		super.onCreate(savedInstanceState);
		
		//Opens app in portrait mode only
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// inflate the screen GUI
		setContentView(R.layout.bathroomsinklayout);
		
		initializeVariables();

		// seek bar listener
		bathroomSinkSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			int bathroomSinkMinsUsed = 0;

			@Override
			public void onProgressChanged(SeekBar bathroomSinkSeekBar, int mins, boolean arg2) {
				bathroomSinkMinsUsed = mins;
				bathroomSinkTextView.setText(""+bathroomSinkSeekBar.getProgress() + " minutes");
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
	//Method to add the bathroom sink value to the report
	public void onClickAdd(View view)
	{
		//Creating the dialog box
		new AlertDialog.Builder(this)
		.setTitle("Info")
		.setMessage("Input recorded successfully to the database")
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
						
						// getting the previous value of bathroom sink, incrementing and updating it
						String sBathroomSink = crs.getString(crs.getColumnIndex("bathroomSink"));
						Log.w("WaterLeadersAcademy", "Bathroom sink previous value: " + sBathroomSink);
						float fBathroomSink = Float.parseFloat(sBathroomSink);
						fBathroomSink += bathroomSinkSeekBar.getProgress() * 11.3;
						databaseConnector.updateBathroomSinkRecord(lId, fBathroomSink);
						databaseConnector.close();
						
						int i = bathroomSinkSeekBar.getProgress();
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
		bathroomSinkSeekBar = (SeekBar) findViewById(R.id.bathroomSink_seekBar);
		bathroomSinkTextView = (TextView) findViewById(R.id.bathroomSink_minutes_text);
	}

}
