package edu.grinnell.kdic.browser;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.grinnell.kdic.R;

public class BrowserPagerAdapter extends FragmentStatePagerAdapter {

	ArrayList<Fragment> mFrags;
	
    public BrowserPagerAdapter(FragmentManager fm, ArrayList<Fragment> frags) {
		super(fm);
        mFrags = frags;
	}

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a DummySectionFragment (defined as a static inner class
        // below) with the page number as its lone argument.
        
    	Fragment fragment = mFrags.get(position);
        
        return fragment;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return getString(R.string.title_section1).toUpperCase(l);
            case 1:
                return getString(R.string.title_section2).toUpperCase(l);
            case 2:
                return getString(R.string.title_section3).toUpperCase(l);
        }
        return null;
    }

    /**
     * A dummy fragment representing a section of the app, but that simply
     * displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";
        
        private static ArrayList<DummySectionFragment>mFList;

        private DummySectionFragment() {
        	
        	
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
            TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
            dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
        
        public static DummySectionFragment getInstance(int numba) {
        	
        	if(mFList == null)
        		mFList = new ArrayList<DummySectionFragment>();
        		
        	
        	Fragment f = new DummySectionFragment();
        	Bundle b = new Bundle();
        	b.putInt(ARG_SECTION_NUMBER, numba);
        	f.setArguments(b);
        	
        	mFList.add(numba, new DummySectionFragment());
        	return mFList.get(numba);
        }
        
    }

}