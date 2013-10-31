package com.iris.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.iris.adapter.FamilyDbAdapter;
import com.iris.adapter.MainAdapter;
import com.iris.constants.Constants;
import com.iris.familyevent.R;
import com.iris.utils.MainItem;

public class ExpenseActivity extends Fragment implements OnScrollListener {
    ListView mMainList;
    ArrayList<MainItem> mainItems = new ArrayList<MainItem>();
    MainAdapter mainAdapter;
    FamilyDbAdapter familyDbAdapter;
    Spinner mSpinner;
    ArrayList<String> categoryItems = new ArrayList<String>();
    private String selectSortText;
    ArrayAdapter<String> adapterSpinner;
    private String mCategory = "";
    TextView emptyTextView;

    public static ExpenseActivity newInstance(int position) {
        ExpenseActivity frag = new ExpenseActivity();
        Bundle args = new Bundle();

        args.putInt("position", position);
        frag.setArguments(args);

        return (frag);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void mainListMake(String selectSortText)
    {
        familyDbAdapter = new FamilyDbAdapter(getActivity());
        familyDbAdapter.open();
        mainItems.clear();

        String queryString = "";
        if (mCategory.equals("전체"))
            queryString = "select * from familyevent where section = 2 order by " + selectSortText;
        else
            queryString = "select * from familyevent where category = '" + mCategory + "' and " +
                    "section = 2 order by " + selectSortText;

        Cursor result = familyDbAdapter.selectFamilyEvent(queryString);

        if (result.getCount() == 0)
        {
            mMainList.setEmptyView(emptyTextView);
            mMainList.setVisibility(View.GONE);
            emptyTextView.setVisibility(View.VISIBLE);
        }
        else
        {
            mMainList.setVisibility(View.VISIBLE);
            emptyTextView.setVisibility(View.GONE);
        }
        result.moveToFirst();
        MainItem mainItem;

        while (!result.isAfterLast())
        {
            String title = result.getString(1);
            String date = result.getString(2);
            String area = result.getString(3);
            String category = result.getString(4);
            String phoneNumber = result.getString(5);
            int amount = result.getInt(6);
            mainItem = new MainItem(title, date, area, category, phoneNumber, amount);
            mainItems.add(mainItem);
            result.moveToNext();
        }
        result.close();
        mainAdapter = new MainAdapter(getActivity(), mainItems);
        mMainList.setAdapter(mainAdapter);
        mainAdapter.notifyDataSetChanged();
        mMainList.setOnScrollListener(this);
    }

    private final OnItemClickListener mMainOnItemClickListener = new OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
            String extraString = mainItems.get(position).title;
            Intent detaileActivityIntent = new Intent(getActivity(), DetaileActivity.class);
            detaileActivityIntent.putExtra("value", extraString);
            startActivity(detaileActivityIntent);
        }
    };

    @Override
    public View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container,
            Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.activity_expense, container, false);
        mMainList = (ListView) rootView.findViewById(R.id.expenseList);
        mMainList.setOnItemClickListener(mMainOnItemClickListener);
        selectSortText = Constants.SORT_SELECT_DATE;

        emptyTextView = (TextView) rootView.findViewById(R.id.emptyText);

        mCategory = "전체";
        mainListMake(selectSortText);

        rootView.findViewById(R.id.sortAmountBtn).setOnClickListener(mClickListener);
        rootView.findViewById(R.id.sortNameBtn).setOnClickListener(mClickListener);
        rootView.findViewById(R.id.sortDateBtn).setOnClickListener(mClickListener);

        mSpinner = (Spinner) rootView.findViewById(R.id.categorySpinner);
        mSpinner.setOnItemSelectedListener(mOnItemSelectedListener);
        spinnerMake();

        return rootView;

    };

    private void spinnerMake()
    {
        categoryItems.add("전체");
        categoryItems.add("결혼식");
        categoryItems.add("장례식");
        categoryItems.add("생일");
        categoryItems.add("돌잔치");
        categoryItems.add("카테고리 추가");

        adapterSpinner = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, categoryItems);
        //		adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mSpinner.setAdapter(adapterSpinner);
        mSpinner.setSelection(0);
    }

    private final OnItemSelectedListener mOnItemSelectedListener = new OnItemSelectedListener()
    {
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
            // TODO Auto-generated method stub
            String selectItem = adapterSpinner.getItem(arg2);
            if (selectItem.equals("카테고리 추가"))
            {
                //                Intent createCategoryIntent = new Intent(getActivity(), CreateCategoryActivity.class);
                //                startActivity(createCategoryIntent);
            }
            else
            {
                mCategory = selectItem;
                mainListMake(selectSortText);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    };

    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.sortAmountBtn:
                selectSortText = Constants.SORT_SELECT_AMOUNT;
                mainListMake(selectSortText);
                break;
            case R.id.sortDateBtn:
                selectSortText = Constants.SORT_SELECT_DATE;
                mainListMake(selectSortText);
                break;
            case R.id.sortNameBtn:
                selectSortText = Constants.SORT_SELECT_NAME;
                mainListMake(selectSortText);
                break;
            }

        }
    };

    @Override
    public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onScrollStateChanged(AbsListView arg0, int arg1) {
        // TODO Auto-generated method stub

    }

}
