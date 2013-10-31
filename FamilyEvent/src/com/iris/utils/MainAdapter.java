package com.iris.utils;

import java.util.ArrayList;

import com.iris.familyevent.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends ArrayAdapter<MainItem>{

	private final Context context;
	private final ArrayList<MainItem> items;
	private final LayoutInflater vi;

	public MainAdapter(Context context,ArrayList<MainItem> items) {
		super(context,0,items);
		this.context = context;
		this.items = items;
//		vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		vi = LayoutInflater.from(this.context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;

		final MainItem item = items.get(position);
		if(item != null){
			row = vi.inflate(R.layout.main_list_row,null);

			final TextView titleText = (TextView) row.findViewById(R.id.listTitle);
			final TextView dateText = (TextView) row.findViewById(R.id.listDate);
			final TextView areaText = (TextView) row.findViewById(R.id.listArea);
			final TextView categoryText = (TextView) row.findViewById(R.id.listCategory);
			final TextView phoneNumberText = (TextView) row.findViewById(R.id.listPhoneNumber);
			final TextView amountText = (TextView) row.findViewById(R.id.listAmount);

				titleText.setText(item.title);
				dateText.setText(item.date);
				areaText.setText(item.area);
				categoryText.setText(item.category);
				phoneNumberText.setText(item.phoneNumber);
				amountText.setText(String.valueOf(item.amount));
		}
		return row;
	}
}
