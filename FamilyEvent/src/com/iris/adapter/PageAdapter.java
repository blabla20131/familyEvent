package com.iris.adapter;

import java.util.ArrayList;

import com.iris.activity.ExpenseActivity;
import com.iris.activity.IncomeActivity;
import com.iris.activity.MainListActivity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;

public class PageAdapter extends FragmentStatePagerAdapter implements ActionBar.TabListener, OnPageChangeListener{
	Context context;
	ActionBar mTabBar;
	ViewPager mViewPager;
	private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
	
	static final class TabInfo {
		private final Class<?> clss;
		private final Bundle args;
		

		TabInfo(Class<?> _class, Bundle _args) {
			clss = _class;
			args = _args;
		}

	}

	public PageAdapter(FragmentManager fragmentmanager ,Activity activity, ViewPager viewager)
	{

		super(fragmentmanager);
		mTabBar = activity.getActionBar();
		mViewPager = viewager;
		mViewPager.setAdapter(this);
		mViewPager.setOnPageChangeListener(this);
	}

	public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args) {
		TabInfo info = new TabInfo(clss, args);
		tab.setTag(info);
		tab.setTabListener(this);
		mTabs.add(info);
		mTabBar.addTab(tab);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	//	@Override
	//	public void destroyItem(View container, int position, Object object) {
	//		// TODO Auto-generated method stub
	//		((ViewPager)container).removeView(((View)object));
	//	}
	//	@Override
	//	public boolean isViewFromObject(View arg0, Object arg1) {
	//		// TODO Auto-generated method stub
	//		return arg0 == arg1;
	//	}


	@Override
	public Fragment getItem(int positions) {

		switch (positions) {
		case 0:
			return new MainListActivity();
		case 1:
			return new IncomeActivity();
		case 2:
			return new ExpenseActivity();
		}
		return null;

	}


	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
//		mViewPager.setCurrentItem(tab.getPosition());
		mViewPager.setCurrentItem(tab.getPosition());
		Object tag = tab.getTag();
		for (int i=0; i<mTabs.size(); i++) {
			if (mTabs.get(i) == tag) {
				mViewPager.setCurrentItem(i);
			}
		}

	}


	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		mTabBar.setSelectedNavigationItem(position);
	}
}
