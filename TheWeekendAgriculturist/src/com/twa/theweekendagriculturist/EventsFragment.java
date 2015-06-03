package com.twa.theweekendagriculturist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.twa.theweekendagriculturist.Model.Event;
import com.twa.theweekendagriculturist.adapters.EventListAdapter;
import com.twa.theweekendagriculturist.utility.Constants;
import com.twa.theweekendagriculturist.utility.SlidingPaneInterface;

public class EventsFragment extends Fragment implements
 OnTabChangeListener{
	
	private ListView eventListView;
	private List<String> tabNamesList = new ArrayList<String>();
	private ArrayList<String> tabTitles = new ArrayList<String>();
	private TextView tabTitleTextView;
	private TabHost host;

	private EventListAdapter eventListAdapter;
	
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {

	        View viewFragment = inflater.inflate(R.layout.fragment_events,
	                null, true);
	        
	   
			ImageButton drawerBtn = (ImageButton) viewFragment.findViewById(R.id.menu_btn);
			drawerBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					((SlidingPaneInterface) getActivity()).tappedDrawerIcon();
				}
			});
			
			
			// INITIALIZE LIST AND SET ADAPTER
			eventListView = (ListView) viewFragment
					.findViewById(R.id.event_list);
			eventListAdapter = new EventListAdapter(getActivity(), false);
			eventListView.setAdapter(eventListAdapter);
			setOnclickListenerForListView();
			
			
			
			// SET UP TABS
			setupTabs(viewFragment);
			
	        return viewFragment;
	  }
	 
	 
	 private void setOnclickListenerForListView() {
		 eventListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {

					

					Event event = (Event) eventListAdapter.getItem(arg2);

					Intent eventDetailsIntent = new Intent(getActivity(),
							EventDetailsActivity.class);
					Bundle bundle=new Bundle();
			   		bundle.putParcelable("event", (Parcelable) event);
			   		eventDetailsIntent.putExtras(bundle);
					startActivity(eventDetailsIntent);
				}
			});

		
 
	 }
	 private void setupTabs(View viewFragment) {
			tabNamesList.add(getActivity().getResources().getString(
					R.string.event_tab_upcoming));
			tabNamesList.add(getActivity().getResources().getString(
					R.string.event_tab_all));
		

			tabTitles.add(tabNamesList.get(0));
			tabTitles.add(tabNamesList.get(1));

			host = (TabHost) viewFragment.findViewById(R.id.tab_host);
			host.setup();

			for (int i = 0; i < tabTitles.size(); i++) {
				String tabTitle = tabTitles.get(i);
				View titleView;
				if (i == 0) {
					titleView = getTitleView(i, true);
				} else {
					titleView = getTitleView(i, false);
				}

				TabSpec tabSpec = host.newTabSpec(tabTitle);
				tabSpec.setIndicator(titleView);
				tabSpec.setContent(eventListView.getId());
				host.addTab(tabSpec);

				host.setCurrentTab(1);
				host.setCurrentTab(0);
			}

			host.setOnTabChangedListener(this);
		}
	 
	 private View getTitleView(int index, boolean selected) {

			LayoutInflater titleInflator = (LayoutInflater) getActivity()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View titleView = titleInflator.inflate(R.layout.event_tab_title,
					null, false);
			Typeface fontForumRegular = Typeface.createFromAsset(getActivity()
					.getAssets(), Constants.FontForumRegular);
			tabTitleTextView = (TextView) titleView.findViewById(R.id.title);
			tabTitleTextView.setText(tabTitles.get(index));
			tabTitleTextView.setTypeface(fontForumRegular);

			if (selected) {
				tabTitleTextView.setTextColor(Color.WHITE);
				tabTitleTextView
						.setBackgroundResource(R.drawable.day_highlight_bg);
			} else {
				tabTitleTextView.setTextColor(getActivity().getResources()
						.getColor(R.color.green_light));
				tabTitleTextView.setBackgroundResource(0);
			}

			return titleView;
		}

	@Override
	public void onTabChanged(String tabId) {
		int selectedIndex = tabNamesList.indexOf(tabId);

		switch (selectedIndex) {
		case 0:
			//updateFilteredScheduleArray(Constants.FRIDAY);
			break;
		case 1:
			//updateFilteredScheduleArray(Constants.SATURDAY);

			break;
		case 2:
			//updateFilteredScheduleArray(Constants.SUNDAY);

			break;
		}

		for (int i = 0; i < tabTitles.size(); i++) {
			View titleView = host.getTabWidget().getChildTabViewAt(i);
			TextView titleTextView = (TextView) titleView
					.findViewById(R.id.title);
			if (selectedIndex == i) {
				titleTextView.setTextColor(Color.WHITE);
				titleTextView
						.setBackgroundResource(R.drawable.day_highlight_bg);
			} else {
				titleTextView.setTextColor(getActivity().getResources()
						.getColor(R.color.green_light));
				titleTextView.setBackgroundResource(0);
			}

		}
		
	}

	 
}

