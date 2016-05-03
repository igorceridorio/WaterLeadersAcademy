/*
 * WATER LEADERS ACADEMY APPLICATION
 * 
 * FILE: ReportActivity.java
 * DESCRIPTION: Class responsible for the ReportActivity.
 * */

package com.ds.waterleadersacademy;

import java.util.Locale;

import com.example.waterleadersacademy.R;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ReportActivity extends Activity {

	// variables used to update the data values
	private TextView bathroomSinkGalTextView;
	private TextView bathroomSinkLitTextView;
	private TextView dishwasherGalTextView;
	private TextView dishwasherLitTextView;
	private TextView kitchenSinkGalTextView;
	private TextView kitchenSinkLitTextView;
	private TextView laundryGalTextView;
	private TextView laundryLitTextView;
	private TextView outdoorHoseGalTextView;
	private TextView outdoorHoseLitTextView;
	private TextView showerGalTextView;
	private TextView showerLitTextView;
	private TextView sprinklersGalTextView;
	private TextView sprinklersLitTextView;
	private TextView toiletGalTextView;
	private TextView toiletLitTextView;
	private TextView miscellaneousGalTextView;
	private TextView miscellaneousLitTextView;
	private TextView totalGalTextView;
	private TextView totalLitTextView;
	
	// connector for the database
	DatabaseConnector databaseConnector = new DatabaseConnector(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// call superclass's version
		super.onCreate(savedInstanceState);
		
		//Opens app in portrait mode only
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		// inflate the screen GUI
		setContentView(R.layout.reportlayout);
		
		// open the connection with the database
		databaseConnector.open();
		
		initializeVariables();
		
		// call the method that loads the values saved in the database
		loadData();
		
	}
	
	// close this activity and go back to the main menu 
	public void onClickMainMenu(View view) {
		finish();
	}
	
	// method responsible for loading the database info
	public void loadData() {
		Cursor crs = databaseConnector.getAllRecords();
		while(crs.moveToNext()) {
			
			// getting the _id of the record
			String sId = crs.getString(crs.getColumnIndex("_id"));
			Log.w("WaterLeadersAcademy", "Id of the record: " + sId);
			
			// showing the value stored in each column of the database
			String sShower = crs.getString(crs.getColumnIndex("shower"));
			Log.w("WaterLeadersAcademy", "Shower current value: " + sShower);
			showerLitTextView.setText(sShower);
			if(Double.parseDouble(sShower) != 0)
				showerGalTextView.setText(Double.toString(Double.valueOf(String.format(Locale.US, "%.2f", Float.parseFloat(sShower) / 3.78541))));

			String sToilet = crs.getString(crs.getColumnIndex("toilet"));
			Log.w("WaterLeadersAcademy", "Toilet current value: " + sToilet);
			toiletLitTextView.setText(sToilet);
			if(Double.parseDouble(sToilet) != 0)
				toiletGalTextView.setText(Double.toString(Double.valueOf(String.format(Locale.US, "%.2f", Float.parseFloat(sToilet) / 3.78541))));
			
			String sBathroomSink = crs.getString(crs.getColumnIndex("bathroomSink"));
			Log.w("WaterLeadersAcademy", "Bathroom sink current value: " + sBathroomSink);
			bathroomSinkLitTextView.setText(sBathroomSink);
			if(Double.parseDouble(sBathroomSink) != 0)
				bathroomSinkGalTextView.setText(Double.toString(Double.valueOf(String.format(Locale.US, "%.2f", Float.parseFloat(sBathroomSink) / 3.78541))));
			
			String sKitchenSink = crs.getString(crs.getColumnIndex("kitchenSink"));
			Log.w("WaterLeadersAcademy", "Kitchen sink current value: " + sKitchenSink);
			kitchenSinkLitTextView.setText(sKitchenSink);
			if(Double.parseDouble(sKitchenSink) != 0)
				kitchenSinkGalTextView.setText(Double.toString(Double.valueOf(String.format(Locale.US, "%.2f", Float.parseFloat(sKitchenSink) / 3.78541))));
			
			String sDishwasher = crs.getString(crs.getColumnIndex("dishwasher"));
			Log.w("WaterLeadersAcademy", "Dishwasher current value: " + sDishwasher);
			dishwasherLitTextView.setText(sDishwasher);
			if(Double.parseDouble(sDishwasher) != 0)
				dishwasherGalTextView.setText(Double.toString(Double.valueOf(String.format(Locale.US, "%.2f", Float.parseFloat(sDishwasher) / 3.78541))));
			
			String sLaundry = crs.getString(crs.getColumnIndex("laundry"));
			Log.w("WaterLeadersAcademy", "Laundry current value: " + sLaundry);
			laundryLitTextView.setText(sLaundry);
			if(Double.parseDouble(sLaundry) != 0)
				laundryGalTextView.setText(Double.toString(Double.valueOf(String.format(Locale.US, "%.2f", Float.parseFloat(sLaundry) / 3.78541))));
			
			String sSprinklers = crs.getString(crs.getColumnIndex("sprinklers"));
			Log.w("WaterLeadersAcademy", "Sprinklers current value: " + sSprinklers);
			sprinklersLitTextView.setText(sSprinklers);
			if(Double.parseDouble(sSprinklers) != 0)
				sprinklersGalTextView.setText(Double.toString(Double.valueOf(String.format(Locale.US, "%.2f", Float.parseFloat(sSprinklers) / 3.78541))));
			
			String sMiscellaneous = crs.getString(crs.getColumnIndex("miscellaneous"));
			Log.w("WaterLeadersAcademy", "Miscellaneous current value: " + sMiscellaneous);
			miscellaneousLitTextView.setText(sMiscellaneous);
			if(Double.parseDouble(sMiscellaneous) != 0)
				miscellaneousGalTextView.setText(Double.toString(Double.valueOf(String.format(Locale.US, "%.2f", Float.parseFloat(sMiscellaneous) / 3.78541))));
			
			String sOutdoorHose = crs.getString(crs.getColumnIndex("outdoorHose"));
			Log.w("WaterLeadersAcademy", "Outdoor hose current value: " + sOutdoorHose);
			outdoorHoseLitTextView.setText(sOutdoorHose);
			if(Double.parseDouble(sOutdoorHose) != 0)
				outdoorHoseGalTextView.setText(Double.toString(Double.valueOf(String.format(Locale.US, "%.2f", Float.parseFloat(sOutdoorHose) / 3.78541))));
			
			//converting to float to calculate the total
			float fShower = Float.parseFloat(sShower);
			float fToilet = Float.parseFloat(sToilet);
			float fBathroomSink = Float.parseFloat(sBathroomSink);
			float fKitchenSink = Float.parseFloat(sKitchenSink);
			float fDishwasher = Float.parseFloat(sDishwasher);
			float fLaundry = Float.parseFloat(sLaundry);
			float fSprinklers = Float.parseFloat(sSprinklers);
			float fMiscellaneous = Float.parseFloat(sMiscellaneous);
			float fOutdoorHose = Float.parseFloat(sOutdoorHose);
			
			float total = fShower + fToilet + fBathroomSink + fKitchenSink+ fDishwasher + fLaundry + fSprinklers + fMiscellaneous + fOutdoorHose;
			Log.w("WaterLeadersAcademy", "Total: " + Float.toString(total));
			if(total != 0) {
				totalLitTextView.setText(Double.toString(Double.valueOf(String.format(Locale.US, "%.2f", total))));
				totalGalTextView.setText(Double.toString(Double.valueOf(String.format(Locale.US, "%.2f", total / 3.78541))));
			}
			
			// close the connection with the database
			databaseConnector.close();
			
		}
	}
	
	// method to clear the data from the report
	public void onClickClear(View view)
	{
		//Creating the alert dialog box
		new AlertDialog.Builder(this)
		.setTitle("Delete")
		.setMessage("Are you sure you want to clear all the data?")
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
						
						// reset all values to 0 again
						databaseConnector.updateRecord(lId, 0, 0, 0, 0, 0, 0, 0, 0, 0);
					}
				}
				
				// close the connection with the database
				databaseConnector.close();
				
				// close the activity and return to menu
				finish();
				
			}
		})
		.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// do nothing when clicked

			}
		})
		.setIcon(android.R.drawable.ic_delete)
		.show();
	}
	
	// method used to initialize the data variables
	private void initializeVariables() {
		bathroomSinkGalTextView = (TextView) findViewById(R.id.bathroomSink_total_gal);
		bathroomSinkLitTextView = (TextView) findViewById(R.id.bathroomSink_total_lit);;
		dishwasherGalTextView = (TextView) findViewById(R.id.dishwasher_total_gal);
		dishwasherLitTextView = (TextView) findViewById(R.id.dishwasher_total_lit);
		kitchenSinkGalTextView = (TextView) findViewById(R.id.kitchenSink_total_gal);
		kitchenSinkLitTextView = (TextView) findViewById(R.id.kitchenSink_total_lit);
		laundryGalTextView = (TextView) findViewById(R.id.laundry_total_gal);
		laundryLitTextView = (TextView) findViewById(R.id.laundry_total_lit);
		outdoorHoseGalTextView = (TextView) findViewById(R.id.outdoorHose_total_gal);
		outdoorHoseLitTextView = (TextView) findViewById(R.id.outdoorHose_total_lit);
		showerGalTextView = (TextView) findViewById(R.id.shower_total_gal);
		showerLitTextView = (TextView) findViewById(R.id.shower_total_lit);
		sprinklersGalTextView = (TextView) findViewById(R.id.sprinklers_total_gal);
		sprinklersLitTextView = (TextView) findViewById(R.id.sprinklers_total_lit);
		toiletGalTextView = (TextView) findViewById(R.id.toilet_total_gal);
		toiletLitTextView = (TextView) findViewById(R.id.toilet_total_lit);
		miscellaneousGalTextView = (TextView) findViewById(R.id.misc_total_gal);
		miscellaneousLitTextView = (TextView) findViewById(R.id.misc_total_lit);
		totalGalTextView = (TextView) findViewById(R.id.finalTotal_total_gal);
		totalLitTextView = (TextView) findViewById(R.id.finalTotal_total_lit);
	}
	
	// Extras activity button on click event
	public void onClickExtras(View view) {
		// create a new Intent object, responsible for launching the extras activity
		Intent showerIntent = new Intent(ReportActivity.this, ExtrasActivity.class);
		startActivity(showerIntent);
	}
}
