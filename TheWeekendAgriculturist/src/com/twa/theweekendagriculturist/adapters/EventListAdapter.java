package com.twa.theweekendagriculturist.adapters;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.twa.theweekendagriculturist.R;
import com.twa.theweekendagriculturist.utility.Constants;
import com.twa.theweekendagriculturist.utility.Fonts;

public class EventListAdapter extends ParseQueryAdapter<ParseObject>{

	private Context context;
	public EventListAdapter(final Context context , final boolean isEventOver) {
		super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
			public ParseQuery<ParseObject> create() {

				ParseQuery<ParseObject> query = ParseQuery
						.getQuery("Event");				
				query.whereEqualTo("isEventOver", isEventOver);
				query.orderByAscending("eventDate");
				
				
				try {
				List<ParseObject> parseObjects =	query.find();
				System.out.println(parseObjects);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				return query;

			}
		});
		this.context = context;

		}
	
	
	@Override
	public void setPaginationEnabled(boolean paginationEnabled) {
		super.setPaginationEnabled(false);
	}
	
	@Override
	public View getItemView(ParseObject object, View convertView,
			ViewGroup parent) {

		TextView eventTitleTextView;
		TextView eventDateTextView;
		TextView eventDescriptionTextView;
		ParseImageView eventImageView;

		if (convertView == null) {
			convertView = View.inflate(getContext(), R.layout.event_row, null);
		}

		super.getItemView(object, convertView, parent);
		
		eventTitleTextView = (TextView) convertView
				.findViewById(R.id.eventTitleTextView);
		eventImageView = (ParseImageView) convertView
				.findViewById(R.id.eventImageView);
		eventDateTextView = (TextView) convertView
				.findViewById(R.id.eventDateTextView);
		eventDescriptionTextView = (TextView) convertView
				.findViewById(R.id.eventDescriptionTextView);

		
		
		//Set fonts
		eventTitleTextView.setTypeface(Typeface.createFromAsset(context.getAssets(),Fonts.FontKarlaBold));
		eventDateTextView.setTypeface(Typeface.createFromAsset(context.getAssets(),Fonts.FontKarlaRegular));
		eventDescriptionTextView.setTypeface(Typeface.createFromAsset(context.getAssets(),Fonts.FontKarlaRegular));
		
		// reset the imageview
		eventImageView.setParseFile(null);


		// Set values to each of the components in the row
		eventTitleTextView.setText(object.getString("title"));
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				Constants.dateFormat, java.util.Locale.getDefault());
		String stringDate = dateFormatter.format(object.getDate("eventDate"));
		eventDateTextView.setText(stringDate);
		eventDescriptionTextView.setText(object.getString("description"));
		
		ParseFile photoFile = object.getParseFile("picture");

		if (photoFile != null) {
			eventImageView.setParseFile(photoFile);
			eventImageView.loadInBackground();
			photoFile = null;
		}

		

		return convertView;
	}


}
