/*
 * WATER LEADERS ACADEMY AAPLICATION
 * 
 * FILE: SprinklersActivity.java
 * DESCRIPTION: Class responsible for the SprinklersActivity.
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
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SprinklersActivity extends Activity {
	
	// variables used with the seek bar
	private SeekBar sprinklerSeekBar;
	private TextView sprinklerTextView;

	// connector for the database
	DatabaseConnector databaseConnector = new DatabaseConnector(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// call superclass's version
		super.onCreate(savedInstanceState);
		
		//Opens app in portrait mode only
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		// inflate the screen GUI
		setContentView(R.layout.sprinklerslayout);
		
		initializeVariables();
		
		// seek bar listener
		sprinklerSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			int sprinklerMinsUsed = 0;
			
			@Override
			public void onProgressChanged(SeekBar sprinklerSeekBar, int mins, boolean arg2) {
				sprinklerMinsUsed = mins;
				sprinklerTextView.setText(""+sprinklerSeekBar.getProgress() + " minutes");
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
	
	//Method to add the sprinkler values to the database
	public void onClickAdd( View view)
	{
		//creating the alert dialog box
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
						String sSprinklers = crs.getString(crs.getColumnIndex("sprinklers"));
						Log.w("WaterLeadersAcademy", "Sprinklers previous value: " + sSprinklers);
						float fSprinklers = Float.parseFloat(sSprinklers);
						fSprinklers += sprinklerSeekBar.getProgress() * 22.7;
						databaseConnector.updateSprinklersRecord(lId, fSprinklers);
						databaseConnector.close();
						
						int i = sprinklerSeekBar.getProgress();
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
		sprinklerSeekBar = (SeekBar) findViewById(R.id.sprinklers_seekBar);
		sprinklerTextView = (TextView) findViewById(R.id.sprinklers_minutes_text);
	}
	
}
