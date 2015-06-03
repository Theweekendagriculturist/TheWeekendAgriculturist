package com.twa.theweekendagriculturist;

import com.twa.theweekendagriculturist.Model.Event;
import com.twa.theweekendagriculturist.utility.Fonts;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.TextView;

public class EventDetailsActivity extends Activity {
	
	private Event event;
	private TextView eventTitleTextView;
	private TextView eventDescriptionTextView;
	private TextView eventDetailsTextView;
	private TextView eventDateTextView;
	private TextView eventTimeTextView;
	private TextView eventPlaceTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_details);
		
		event = getIntent().getExtras().getParcelable(
				"event");
		
		initializeView();
		
		populateEventInView(event);
		
	}
	
	private void initializeView() {

		eventTitleTextView = (TextView)findViewById(R.id.eventDetails_eventName_textView);
		eventTitleTextView.setTypeface(Typeface.createFromAsset(getAssets(),Fonts.FontKarlaBold));
		eventDescriptionTextView = (TextView)findViewById(R.id.eventDetails_eventDescription_value_textView);
		eventDetailsTextView = (TextView)findViewById(R.id.eventDetails_eventDetailInfo_value_textView);
		eventDateTextView = (TextView)findViewById(R.id.eventDetails_eventDate_value_textView);
		eventTimeTextView = (TextView)findViewById(R.id.eventDetails_eventTime_value_textView);
		eventPlaceTextView = (TextView)findViewById(R.id.eventDetails_eventPlace_value_textView);
	}

	private void populateEventInView(Event event) {
		eventTitleTextView.setText(event.getTitle());
		eventDescriptionTextView.setText(event.getDescription());
		eventDetailsTextView.setText(event.getDetails());
		eventDateTextView.setText(event.getTitle());
		eventTimeTextView.setText(event.getTitle());
		eventPlaceTextView.setText(event.getPlace());

	}

	
}
