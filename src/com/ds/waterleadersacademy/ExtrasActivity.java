package com.ds.waterleadersacademy;

import java.util.Locale;

import com.example.waterleadersacademy.R;

import android.app.Activity;
import android.database.Cursor;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ExtrasActivity extends Activity {

	// variables used to update the data values
	private TextView memorialMyselfTime;
	private TextView memorialCommunityTime;
	private TextView olympicMyselfTime;
	private TextView olympicCommunityTime;
	private EditText communityEditText;

	// connector for the database
	DatabaseConnector databaseConnector = new DatabaseConnector(this);
	
	// total variable
	float classTotal = 0;
	float classTotalAux;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// call superclass's version
		super.onCreate(savedInstanceState);
		
		//Opens app in portrait mode only
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		// inflate the screen GUI
		setContentView(R.layout.extraslayout);

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
		while (crs.moveToNext()) {

			// getting the _id of the record
			String sId = crs.getString(crs.getColumnIndex("_id"));
			Log.w("WaterLeadersAcademy", "Id of the record: " + sId);

			// showing the value stored in each column of the database
			String sShower = crs.getString(crs.getColumnIndex("shower"));
			Log.w("WaterLeadersAcademy", "Shower current value: " + sShower);
			String sToilet = crs.getString(crs.getColumnIndex("toilet"));
			Log.w("WaterLeadersAcademy", "Toilet current value: " + sToilet);
			String sBathroomSink = crs.getString(crs
					.getColumnIndex("bathroomSink"));
			Log.w("WaterLeadersAcademy", "Bathroom sink current value: "
					+ sBathroomSink);
			String sKitchenSink = crs.getString(crs
					.getColumnIndex("kitchenSink"));
			Log.w("WaterLeadersAcademy", "Kitchen sink current value: "
					+ sKitchenSink);
			String sDishwasher = crs
					.getString(crs.getColumnIndex("dishwasher"));
			Log.w("WaterLeadersAcademy", "Dishwasher current value: "
					+ sDishwasher);
			String sLaundry = crs.getString(crs.getColumnIndex("laundry"));
			Log.w("WaterLeadersAcademy", "Laundry current value: " + sLaundry);
			String sSprinklers = crs
					.getString(crs.getColumnIndex("sprinklers"));
			Log.w("WaterLeadersAcademy", "Sprinklers current value: "
					+ sSprinklers);
			String sMiscellaneous = crs.getString(crs
					.getColumnIndex("miscellaneous"));
			Log.w("WaterLeadersAcademy", "Miscellaneous current value: "
					+ sMiscellaneous);
			String sOutdoorHose = crs.getString(crs
					.getColumnIndex("outdoorHose"));
			Log.w("WaterLeadersAcademy", "Outdoor hose current value: "
					+ sOutdoorHose);

			// converting to float to calculate the total
			float fShower = Float.parseFloat(sShower);
			float fToilet = Float.parseFloat(sToilet);
			float fBathroomSink = Float.parseFloat(sBathroomSink);
			float fKitchenSink = Float.parseFloat(sKitchenSink);
			float fDishwasher = Float.parseFloat(sDishwasher);
			float fLaundry = Float.parseFloat(sLaundry);
			float fSprinklers = Float.parseFloat(sSprinklers);
			float fMiscellaneous = Float.parseFloat(sMiscellaneous);
			float fOutdoorHose = Float.parseFloat(sOutdoorHose);

			float total = fShower + fToilet + fBathroomSink + fKitchenSink
					+ fDishwasher + fLaundry + fSprinklers + fMiscellaneous
					+ fOutdoorHose;
			Log.w("WaterLeadersAcademy", "Total: " + Float.toString(total));
			if (total != 0) {
				
				classTotal = total;
				classTotalAux = total;
				
				// calculate the total for myself and community related to olympic size pool
				int olympicMyselfDays = (int) (2500000 / classTotal);
				int olympicMyselfHours = (int) (((2500000 / classTotal)-olympicMyselfDays) * 24);
				int olympicMyselfMinutes = (int) (((((2500000 / classTotal)-olympicMyselfDays) * 24)-olympicMyselfHours)*60);
					

				Log.w("WaterLeadersAcademy", "Olympic days: " + olympicMyselfDays);
				Log.w("WaterLeadersAcademy", "Olympic hours: " + olympicMyselfHours);
				Log.w("WaterLeadersAcademy", "Olympic minutes: " + olympicMyselfMinutes);

				olympicMyselfTime.setText(" " + Integer.toString(olympicMyselfDays)
						+ " days, " + Integer.toString(olympicMyselfHours)
						+ " hours, " + Integer.toString(olympicMyselfMinutes)
						+ " min");

				// calculate the total for myself and community related to memorial stadium size
				int stadiumMyselfDays = (int) (1090743693 / classTotal);
				int stadiumMyselfHours = (int) (((1090743693 / classTotal)-stadiumMyselfDays) * 24);
				int stadiumMyselfMinutes = (int) (((((1090743693 / classTotal)-stadiumMyselfDays) * 24)-stadiumMyselfHours)*60);

				Log.w("WaterLeadersAcademy", "Memorial days: " + stadiumMyselfDays);
				Log.w("WaterLeadersAcademy", "Memorial hours: " + stadiumMyselfHours);
				Log.w("WaterLeadersAcademy", "Memorial minutes: " + stadiumMyselfMinutes);

				memorialMyselfTime.setText(" " + Integer.toString(stadiumMyselfDays)
						+ " days, " + Integer.toString(stadiumMyselfHours)
						+ " hours, " + Integer.toString(stadiumMyselfMinutes)
						+ " min");

			}

			// close the connection with the database
			databaseConnector.close();

		}
	}

	// method used to initialize the data variables
	private void initializeVariables() {
		memorialMyselfTime = (TextView) findViewById(R.id.memorial_myself_time);
		memorialCommunityTime = (TextView) findViewById(R.id.memorial_community_time);
		olympicMyselfTime = (TextView) findViewById(R.id.olympic_myself_time);
		olympicCommunityTime = (TextView) findViewById(R.id.olympic_community_time);
		communityEditText = (EditText) findViewById(R.id.community_size_number);
	}
	
	// method used to update the community usage
	public void onClickUpdate(View view) {
		
		classTotal = classTotalAux;
		
		if(!communityEditText.getText().toString().isEmpty()) {
			
			if(communityEditText.getText().toString().equals("-"))
				classTotal = 0;
			else			
				classTotal = classTotal * Float.parseFloat(communityEditText.getText().toString());	
			
			if(classTotal < 0)
				classTotal *= (-1);
			
			if(classTotal > 0) {
			
				Log.w("WaterLeadersAcademy", "Community total: " + classTotal);
				
				// calculate the total for myself and community related to olympic size pool
				int olympicCommunityDays = (int) (2500000 / classTotal);
				int olympicCommunityHours = (int) (((2500000 / classTotal)-olympicCommunityDays) * 24);
				int olympicCommunityMinutes = (int) (((((2500000 / classTotal)-olympicCommunityDays) * 24)-olympicCommunityHours)*60);
		
				Log.w("WaterLeadersAcademy", "Olympic days: " + olympicCommunityDays);
				Log.w("WaterLeadersAcademy", "Olympic hours: " + olympicCommunityHours);
				Log.w("WaterLeadersAcademy", "Olympic minutes: " + olympicCommunityMinutes);
		
				olympicCommunityTime.setText(" " + Integer.toString(olympicCommunityDays)
						+ " days, " + Integer.toString(olympicCommunityHours)
						+ " hours, " + Integer.toString(olympicCommunityMinutes)
						+ " min");
		
				// calculate the total for myself and community related to memorial stadium size
				int stadiumCommunityDays = (int) (1090743693 / classTotal);
				int stadiumCommunityHours = (int) (((1090743693 / classTotal)-stadiumCommunityDays) * 24);
				int stadiumCommunityMinutes = (int) (((((1090743693 / classTotal)-stadiumCommunityDays) * 24)-stadiumCommunityHours)*60);
		
				Log.w("WaterLeadersAcademy", "Memorial days: " + stadiumCommunityDays);
				Log.w("WaterLeadersAcademy", "Memorial hours: " + stadiumCommunityHours);
				Log.w("WaterLeadersAcademy", "Memorial minutes: " + stadiumCommunityMinutes);
		
				memorialCommunityTime.setText(" " + Integer.toString(stadiumCommunityDays)
						+ " days, " + Integer.toString(stadiumCommunityHours)
						+ " hours, " + Integer.toString(stadiumCommunityMinutes)
						+ " min");
				
			}
		
		
		} else {
			//create a alert dialog box
			new AlertDialog.Builder(this)
				.setTitle("Info")
				.setMessage("You should type a value.")
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				})
				.setIcon(android.R.drawable.ic_dialog_info)
				.show();			
		}
		
	}

}
