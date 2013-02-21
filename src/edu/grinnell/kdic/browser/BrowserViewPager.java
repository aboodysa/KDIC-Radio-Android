package edu.grinnell.kdic.browser;

import android.content.Context;
import android.support.v4.view.ViewPager;

public class BrowserViewPager extends ViewPager {

	BrowserPagerAdapter mAdapter = new BrowserPagerAdapter(null, null);
	
	public BrowserViewPager(Context context) {
		super(context);
		this.setAdapter(mAdapter);
	}

	
	
}
