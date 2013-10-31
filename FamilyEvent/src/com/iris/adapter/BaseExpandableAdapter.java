package com.iris.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iris.familyevent.R;

public class BaseExpandableAdapter extends BaseExpandableListAdapter {

    private ArrayList<String> groupList = null;
    private ArrayList<ArrayList<String>> childList = null;
    private LayoutInflater inflater = null;
    private ViewHolder viewHolder = null;

    public BaseExpandableAdapter(Context c, ArrayList<String> groupList,
            ArrayList<ArrayList<String>> childList) {
        super();
        this.inflater = LayoutInflater.from(c);
        this.groupList = groupList;
        this.childList = childList;
    }

    // 그룹 포지션을 반환한다.
    @Override
    public String getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    // 그룹 사이즈를 반환한다.
    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    // 그룹 ID를 반환한다.
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    // 그룹뷰 각각의 ROW 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_expandable, parent, false);
            viewHolder.expandable_item_group_text_view = (TextView) convertView.findViewById(R.id.expandable_item_group_text_view);
            viewHolder.iv_image = (ImageView) convertView.findViewById(R.id.expandable_item_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 그룹을 펼칠때와 닫을때 아이콘을 변경해 준다.
        if (isExpanded) {
            viewHolder.iv_image.setBackgroundResource(R.drawable.expandable_list_view_down_btn);
        } else {
            viewHolder.iv_image.setBackgroundResource(R.drawable.expandable_list_view_right_btn);
        }

        viewHolder.expandable_item_group_text_view.setText(getGroup(groupPosition));

        return convertView;
    }

    // 차일드뷰를 반환한다.
    @Override
    public String getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    // 차일드뷰 사이즈를 반환한다.
    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    // 차일드뷰 ID를 반환한다.
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    // 차일드뷰 각각의 ROW
    @Override
    public View getChildView(int groupPosition, int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            viewHolder = new ViewHolder();
            v = inflater.inflate(R.layout.list_item_expandable, null);
            viewHolder.expandable_item_child_text_view = (TextView) v.findViewById(R.id.expandable_item_child_text_view);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.expandable_item_child_text_view.setText(getChild(groupPosition, childPosition));

        return v;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolder {
        public ImageView iv_image;
        public TextView expandable_item_group_text_view;
        public TextView expandable_item_child_text_view;
    }

}
