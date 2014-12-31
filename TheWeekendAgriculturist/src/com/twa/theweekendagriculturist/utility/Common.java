package com.twa.theweekendagriculturist.utility;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

public class Common {

	
	public static boolean facebookAppInstalledOnDevice(Context context) {

		boolean isFacebookInstalled = false;
		try {
			context.getPackageManager().getApplicationInfo(
					Constants.FACEBOOK_PACKAGE_NAME, 0);
			isFacebookInstalled = true;
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}

		if (isFacebookInstalled) {
			// IF App is installed check if it is enabled or not
			int getAppEnabledInfo = context.getPackageManager()
					.getApplicationEnabledSetting(
							Constants.FACEBOOK_PACKAGE_NAME);
			if (getAppEnabledInfo == PackageManager.COMPONENT_ENABLED_STATE_ENABLED
					|| getAppEnabledInfo == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	
	public static void loadURLInBrowser(final Context context, String urlToLoad) {

		if (!urlToLoad.startsWith("http://")
				&& !urlToLoad.startsWith("https://")) {
			urlToLoad = "http://" + urlToLoad;
		}
		Intent browserIntent = new Intent(Intent.ACTION_VIEW,
				Uri.parse(urlToLoad));
		context.startActivity(browserIntent);
	}
}
