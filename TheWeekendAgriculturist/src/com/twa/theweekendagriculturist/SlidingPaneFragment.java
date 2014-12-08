package com.twa.theweekendagriculturist;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.twa.theweekendagriculturist.adapters.SlidingPaneListviewArrayAdapter;


public class SlidingPaneFragment extends Fragment implements
		OnItemClickListener {

	private ListView listviewSlidingPane;
	private static SlidingPaneListviewArrayAdapter slidingPaneArrayAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View viewFragment = inflater.inflate(R.layout.fragment_sliding_pane,
				container, true);

		listviewSlidingPane = (ListView) viewFragment
				.findViewById(R.id.listview_slidingpane);

		
		slidingPaneArrayAdapter = new SlidingPaneListviewArrayAdapter(
				getActivity(), getActivity().getResources().getStringArray(
						R.array.array_module_name));
		listviewSlidingPane.setAdapter(slidingPaneArrayAdapter);

		listviewSlidingPane.setDividerHeight(2);

		listviewSlidingPane.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		listviewSlidingPane.setOnItemClickListener(this);

		listviewSlidingPane.setItemChecked(0, true);
		
		return viewFragment;
	}

	public interface SlidingPanDelegate {
		public void didSelectListviewItemAtIndex(Integer index);

	}

	public static void refreshSlidingPaneListView(int position) {
		slidingPaneArrayAdapter.setSelectedIndex(position);
		slidingPaneArrayAdapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long id) {
		listviewSlidingPane.setItemChecked(position, true);
		refreshSlidingPaneListView(position);
		((SlidingPanDelegate) getActivity())
				.didSelectListviewItemAtIndex(position);
	}

}
