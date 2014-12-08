package com.twa.theweekendagriculturist.adapters;

import com.twa.theweekendagriculturist.R;
import com.twa.theweekendagriculturist.utility.Fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class SlidingPaneListviewArrayAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] values;
	private Integer selectedIndex;
	
	public Integer getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(Integer selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public SlidingPaneListviewArrayAdapter(Context context, String[] values) {
		super(context, R.layout.listview_slidingpane_row, values);
		this.context = context;
		this.values = values;
		this.selectedIndex = 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listview_slidingpane_row,
					parent, false);

		}

		TextView textView = (TextView) convertView
				.findViewById(R.id.sliding_pane_textview_modulename);
		textView.setTypeface(Typeface.createFromAsset(context.getAssets(),Fonts.FontForumRegular));
		 if(position == selectedIndex){
         	textView.setTextColor(context.getResources().getColor(R.color.white));
         	convertView.setBackgroundColor(context.getResources().getColor(R.color.green));
         }
         else{
        		textView.setTextColor(context.getResources().getColor(R.color.blackColor));
        		convertView.setBackgroundColor(context.getResources().getColor(R.color.white));
         }
		textView.setText(values[position]);

		return convertView;
	}

}
