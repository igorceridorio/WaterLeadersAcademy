/*
 * WATER LEADERS ACADEMY AAPLICATION
 * 
 * FILE: KitchenActivity.java
 * DESCRIPTION: Class responsible for the KitchenActivity.
 * */

package com.ds.waterleadersacademy;

import com.example.waterleadersacademy.R;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class KitchenActivity extends Activity {
	
	// variables used with the seek bar
	private SeekBar kitchenSinkSeekBar;
	private TextView kitchenSinkTextView;

	// connector for the database
	DatabaseConnector databaseConnector = new DatabaseConnector(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// call superclass's version
		super.onCreate(savedInstanceState);
		
		//Opens app in portrait mode only
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		// inflate the screen GUI
		setContentView(R.layout.kitchensinklayout);
		
		initializeVariables();
		
		kitchenSinkSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			int kitchenSinkMinsUsed = 0;
			
			@Override
			public void onProgressChanged(SeekBar kitchenSinkSeekBar, int mins, boolean arg2) {
				kitchenSinkMinsUsed = mins;
				kitchenSinkTextView.setText(""+kitchenSinkSeekBar.getProgress() + " minutes");
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
	
	//Method to add the kitchen sink value to the database
		public void onClickAdd(View view)
		{
			//Creating a dialog box
			new AlertDialog.Builder(this)
			.setTitle("Info")
			.setMessage("Input recorded successfully to the database.")
			.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					
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
							String sKitchenSink = crs.getString(crs.getColumnIndex("kitchenSink"));
							Log.w("WaterLeadersAcademy", "Kitchen sink previous value: " + sKitchenSink);
							float fKitchenSink = Float.parseFloat(sKitchenSink);
							fKitchenSink += kitchenSinkSeekBar.getProgress() * 11.3;
							databaseConnector.updateKitchenSinkRecord(lId, fKitchenSink);
							databaseConnector.close();
							
							int i = kitchenSinkSeekBar.getProgress();
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
		kitchenSinkSeekBar = (SeekBar) findViewById(R.id.kitchenSink_seekBar);
		kitchenSinkTextView = (TextView) findViewById(R.id.kitchenSink_minutes_text);
	}
	
}
