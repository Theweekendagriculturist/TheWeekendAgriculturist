package com.twa.theweekendagriculturist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class SplashScreenActivity extends Activity {
	
	public static final int SPLASH_SCREEN_TIME_OUT = 3000;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		getActionBar().hide();

        
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent baseIntent = new Intent(SplashScreenActivity.this, BaseActivity.class);
				startActivity(baseIntent);
				finish();
			}
		}, SPLASH_SCREEN_TIME_OUT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

}
