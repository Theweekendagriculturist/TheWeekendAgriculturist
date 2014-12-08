package com.twa.theweekendagriculturist;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.Menu;
import android.view.View;
import android.view.Window;

import com.twa.theweekendagriculturist.SlidingPaneFragment.SlidingPanDelegate;
import com.twa.theweekendagriculturist.utility.SlidingPaneInterface;

public class BaseActivity extends Activity implements SlidingPanDelegate, SlidingPaneInterface{

	private static Integer selectedModuleIndex;
	private SlidingPaneLayout slidingPan;
	private Handler handler;
	private Fragment fragmentToLoad = null;
	private boolean shouldSelectListViewItem = true;
	private final static String SHOULD_SELECT_LIST_VIEW_ITEM = "shouldSelectListViewItem";
	private final static String SELECTED_MODULE = "selectedModuleIndex";
	
	@Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SHOULD_SELECT_LIST_VIEW_ITEM, false);
        outState.putInt(SELECTED_MODULE, selectedModuleIndex);
    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		selectedModuleIndex = -1;

		if (savedInstanceState != null) {
			shouldSelectListViewItem = savedInstanceState
					.getBoolean(SHOULD_SELECT_LIST_VIEW_ITEM);
			selectedModuleIndex = savedInstanceState.getInt(SELECTED_MODULE);
		}

		setContentView(R.layout.activity_base);

		this.slidingPan = (SlidingPaneLayout) findViewById(R.id.slidingPane);
		this.slidingPan.setPanelSlideListener(new PaneListener());
		
		
		//Load the first fragment (breaking news) for the first time
		if (shouldSelectListViewItem == true) {
			this.didSelectListviewItemAtIndex(0);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.base, menu);
		return true;
	}

	@Override
	public void didSelectListviewItemAtIndex(Integer index) {
		// To avoid loading same fragment again
		if (selectedModuleIndex == index) {
			slidingPan.closePane();
			return;
		}
		
		if (!slidingPan.isOpen())
			return;
		selectedModuleIndex = index;

		// Replace the MainFragment with the Fragment that needs to be loaded
		// after it is ready
		switch (index) {
		case 0:
			fragmentToLoad = new IntroFragment();
			break;
		case 1:
			fragmentToLoad = new EventsFragment();
			break;
		case 2:
			fragmentToLoad = new GalleryFragment();
			break;
		case 3:
			fragmentToLoad = new ExperiencesFragment();
			break;
		case 4:
			fragmentToLoad = new ContactUsFragment();
			break;
		case 5:
			fragmentToLoad = new MyAccountFragment();
			break;
		case 6:
			fragmentToLoad = new SuggestionsFragment();
			break;
		default:
			break;
		}
		// setTitle(moduleTitles[index]);

		handler = new Handler();
		loadFragment(BaseActivity.this, fragmentToLoad);
		handler.post(new Runnable() {

			@Override
			public void run() {
				slidingPan.closePane();
			}
		});

		
	}
	
	private static void loadFragment(Context context, Fragment fragment) {

		FragmentManager fragmentManager = ((Activity) context)
				.getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		if (fragment != null) {

			transaction.replace(R.id.main_layout_container, fragment);
		}
		transaction.commit();
		fragmentManager.executePendingTransactions();
	}

	@Override
	public void tappedDrawerIcon() {
		slidingPan.openPane();
	}
	
	
	// SlidingPaneLayout Listener
		private class PaneListener implements SlidingPaneLayout.PanelSlideListener {

			@Override
			public void onPanelClosed(View view) {

			}

			@Override
			public void onPanelOpened(View view) {

			}

			@Override
			public void onPanelSlide(View view, float arg1) {
			}
		}
		
}
