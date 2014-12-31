package com.twa.theweekendagriculturist;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.twa.theweekendagriculturist.utility.Common;
import com.twa.theweekendagriculturist.utility.Constants;
import com.twa.theweekendagriculturist.utility.SlidingPaneInterface;

public class IntroFragment extends Fragment implements OnClickListener {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View viewFragment = inflater.inflate(R.layout.fragment_intro, null,
				true);

		ImageButton drawerBtn = (ImageButton) viewFragment
				.findViewById(R.id.menu_btn);
		drawerBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((SlidingPaneInterface) getActivity()).tappedDrawerIcon();
			}
		});
		
		
		 //Initialize buttons on the footer
		ImageButton blogBtn = (ImageButton) viewFragment
				.findViewById(R.id.intro_btn_blog);
		blogBtn.setOnClickListener(this);

		ImageButton emailBtn = (ImageButton) viewFragment
				.findViewById(R.id.intro_btn_email);
		emailBtn.setOnClickListener(this);

		ImageButton facebookButton = (ImageButton) viewFragment
				.findViewById(R.id.intro_btn_facebook);
		facebookButton.setOnClickListener(this);
		
		

		return viewFragment;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.intro_btn_email:
			sendEmail();
			break;

		case R.id.intro_btn_facebook:
			if (Common.facebookAppInstalledOnDevice(getActivity())) {
				Intent facebookIntent = new Intent(Intent.ACTION_VIEW,
						Uri.parse(Constants.FACEBOOK_TWA_PROFILE_URL));
				facebookIntent.setPackage(Constants.FACEBOOK_PACKAGE_NAME);
				startActivity(facebookIntent);
			} else {
				Common.loadURLInBrowser(getActivity(), Constants.URL_FACEBOOK);
			}
			break;
		case R.id.intro_btn_blog:
			Common.loadURLInBrowser(getActivity(), Constants.URL_BLOG);
			break;

		default:
			break;
		}

	}
	
	private void sendEmail() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("plain/text");
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "theweekendagriculturist@gmail.com" });
		startActivity(Intent.createChooser(intent, ""));
	}
}
