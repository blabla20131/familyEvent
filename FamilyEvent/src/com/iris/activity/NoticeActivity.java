package com.iris.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.iris.adapter.BaseExpandableAdapter;
import com.iris.familyevent.R;

public class NoticeActivity extends Activity {

    ExpandableListView mListView;
    private ArrayList<String> mGroupList = null;
    private ArrayList<ArrayList<String>> mChildList = null;
    private ArrayList<String> mChildListContent = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        mListView = (ExpandableListView) findViewById(R.id.expandable_list_view);

        mGroupList = new ArrayList<String>();
        mChildList = new ArrayList<ArrayList<String>>();
        mChildListContent = new ArrayList<String>();

        mGroupList.add("[목소리 특집]여러분의 목소리를 설문으로 들려주세요.");
        mGroupList.add("파일송수신이 가능해진 PC버전 안내");
        mGroupList.add("윈도우 PC버전 정식 오픈을 했습니다.");

        mChildListContent.add("안녕하세요. 카카오특 개발진 입니다." +
                "가을이 가고 겨울이 다가오고 있습니다. 환정기에 건강은 안녕하신지요?" +
                "카카오톡 개발진은 오늘도 고민하며 유저 여러분께 새로운 재미를" +
                "선사래 드리기 위해 노력하고 있습니다." +
                "유저 여러분과 함께 호흡하고 맥박을 함께 공유하며 발전한다고 생각합니다." +
                "그런의미에서 함께 만들어가는 카카오 톡 만들기 위해 유저 여러분의 소중한 목소리를" +
                "듣고자 합니다.");

        mChildList.add(mChildListContent);
        mChildList.add(mChildListContent);
        mChildList.add(mChildListContent);

        mListView.setAdapter(new BaseExpandableAdapter(this, mGroupList, mChildList));

        // 그룹 클릭 했을 경우 이벤트
        mListView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                    int groupPosition, long id) {
                return false;
            }
        });

        // 차일드 클릭 했을 경우 이벤트
        mListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                return false;
            }
        });

        // 그룹이 닫힐 경우 이벤트
        mListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });

        // 그룹이 열릴 경우 이벤트
        mListView.setOnGroupExpandListener(new OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });
    }

}