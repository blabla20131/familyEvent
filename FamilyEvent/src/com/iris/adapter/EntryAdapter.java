package com.iris.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iris.familyevent.R;
import com.iris.utils.EntryItem;
import com.iris.utils.Item;
import com.iris.utils.SectionItem;

public class EntryAdapter extends ArrayAdapter<Item> {

    private final Context context;
    private final ArrayList<Item> items;
    private final LayoutInflater vi;

    public EntryAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final Item i = items.get(position);
        if (i != null) {
            if (i.isSection()) {
                SectionItem si = (SectionItem) i;
                v = vi.inflate(R.layout.list_item_section, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                final TextView sectionView = (TextView) v.findViewById(R.id.list_item_section_text);
                sectionView.setTextColor(Color.WHITE);
                sectionView.setText(si.getTitle());
            } else {
                EntryItem ei = (EntryItem) i;
                v = vi.inflate(R.layout.icon_list_item, null);
                final TextView title = (TextView) v.findViewById(R.id.text1);

                final ImageView img1 = (ImageView) v.findViewById(R.id.icon);

                if (title != null)
                    title.setText(ei.title);
                if (img1 != null)
                    img1.setImageResource(ei.img1);
            }
        }
        return v;
    }

}
