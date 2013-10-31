package com.iris.utils;


public class EntryItem implements Item{

	public final String title;
	public final  int img1;

	public EntryItem(String title, int img1) {
		this.title = title;
		this.img1 = img1;
	}
	
	@Override
	public boolean isSection() {
		return false;
	}

}
