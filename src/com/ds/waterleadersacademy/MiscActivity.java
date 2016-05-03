/*
 * WATER LEADERS ACADEMY AAPLICATION
 * 
 * FILE: MisActivity.java
 * DESCRIPTION: Class responsible for the MiscActivity.
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

public class MiscActivity extends Activity {

	// variables used with the seek bar
	private SeekBar miscGalsSeekBar;
	private SeekBar miscLitersSeekBar;
	private TextView miscGalsTextView;
	private TextView miscLitersTextView;
	
	// connector for the database
	DatabaseConnector databaseConnector = new DatabaseConnector(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// call superclass's version
		super.onCreate(savedInstanceState);
		
		//Opens app in portrait mode only
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// inflate the screen GUI
		setContentView(R.layout.misclayout);
		
		initializeVariables();

		// seek bar listener
		miscGalsSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			int galsUsed = 0;

			@Override
			public void onProgressChanged(SeekBar miscGalsSeekBar, int gals, boolean arg2) {
				galsUsed = gals;
				miscGalsTextView.setText(""+miscGalsSeekBar.getProgress() + " gallons");
				miscLitersSeekBar.setProgress(0);
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

		// seek bar listener
		miscLitersSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			int litersUsed = 0;

			@Override
			public void onProgressChanged(SeekBar miscLitersSeekBar, int ltrs, boolean arg2) {
				litersUsed = ltrs;
				miscLitersTextView.setText(""+miscLitersSeekBar.getProgress() + " liters");
				miscGalsSeekBar.setProgress(0);
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

	//Method to add the miscellaneous values to the report
	public void onClickAdd(View view)
	{
		
		// verifies which of the seek bars is being used and retrieve the data
		final double seekValue;
		if(miscGalsSeekBar.getProgress() != 0)
			seekValue = miscGalsSeekBar.getProgress() * 3.78541;
		else if(miscLitersSeekBar.getProgress() != 0)
			seekValue = miscLitersSeekBar.getProgress();
		else
			seekValue = 0;
		
		
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
						
						// getting the previous value of miscellaneous, incrementing and updating it
						String sMiscellaneous = crs.getString(crs.getColumnIndex("miscellaneous"));
						Log.w("WaterLeadersAcademy", "Miscellaneous previous value: " + sMiscellaneous);
						float fMiscellaneous = Float.parseFloat(sMiscellaneous);
						
						// TODO 
						
						
						fMiscellaneous += seekValue;
						databaseConnector.updateMiscellaneousRecord(lId, fMiscellaneous);
						databaseConnector.close();
						
						double i = seekValue;
						Log.w("WaterLeadersAcademy", "Seek bar value: " + Double.toString(i));

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
		miscGalsSeekBar = (SeekBar) findViewById(R.id.misc_gallons_seekBar);
		miscGalsTextView = (TextView) findViewById(R.id.misc_gallons_text);
		miscLitersSeekBar = (SeekBar) findViewById(R.id.misc_liters_seekBar);
		miscLitersTextView = (TextView) findViewById(R.id.misc_liters_text);
	}

}
