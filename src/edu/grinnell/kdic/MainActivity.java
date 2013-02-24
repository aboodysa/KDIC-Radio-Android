package edu.grinnell.kdic;

import java.util.ArrayList;

import edu.grinnell.kdic.browser.BrowserPagerAdapter;
import edu.grinnell.kdic.streambanner.StreamBannerFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.net.Uri;
//import android.widget.ImageView;
//import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

	static ArrayList<Fragment> mFrags;
    private ViewPager 			mBrowserPager;
    private BrowserPagerAdapter	mBrowserPagerAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        createBlankFragArray();
        	
        getSupportFragmentManager().beginTransaction()
        	.replace(R.id.radio_banner_container, new StreamBannerFragment())
        	.commit();
        
        mBrowserPager = (ViewPager) findViewById(R.id.browser_pager);
        mBrowserPagerAdapter = new BrowserPagerAdapter(null, mFrags);
        mBrowserPager.setAdapter(mBrowserPagerAdapter);
        
    }
    
	public static void createBlankFragArray(){
    	for(int i=0; i<3; i++)
    		mFrags.add(new Fragment());
    }
    
}
