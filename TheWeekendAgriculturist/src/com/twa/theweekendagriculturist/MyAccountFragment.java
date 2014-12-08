package com.twa.theweekendagriculturist;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.twa.theweekendagriculturist.utility.SlidingPaneInterface;

public class MyAccountFragment extends Fragment{

	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {

	        View viewFragment = inflater.inflate(R.layout.fragment_myaccount,
	                null, true);
	        
	   
			ImageButton drawerBtn = (ImageButton) viewFragment.findViewById(R.id.menu_btn);
			drawerBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					((SlidingPaneInterface) getActivity()).tappedDrawerIcon();
				}
			});
			
			
	        return viewFragment;
	  }
}
