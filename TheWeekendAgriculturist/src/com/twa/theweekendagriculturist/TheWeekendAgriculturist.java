package com.twa.theweekendagriculturist;

import com.parse.Parse;
import com.parse.ParseObject;
import com.twa.theweekendagriculturist.Model.Event;
import com.twa.theweekendagriculturist.utility.Constants;

import android.app.Application;

public class TheWeekendAgriculturist extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Parse.initialize(this,Constants.Parse_App_Id , Constants.Parse_Client_key);
		
		//Register model classes
		ParseObject.registerSubclass(Event.class);
		
	

	}

}
