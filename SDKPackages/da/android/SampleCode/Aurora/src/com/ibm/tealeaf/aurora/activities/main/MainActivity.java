/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.ibm.tealeaf.aurora.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.ibm.tealeaf.aurora.AuroraHelper;
import com.ibm.tealeaf.aurora.R;
import com.ibm.tealeaf.aurora.activities.main.fragments.CartFragment;
import com.ibm.tealeaf.aurora.activities.main.fragments.CategoryListFragment;
import com.ibm.tealeaf.aurora.activities.main.fragments.OrdersFragment;
//import com.tl.uic.Tealeaf;
//import com.tl.uic.model.ScreenviewType;
import com.ibm.tealeaf.aurora.tagging.TagPageView;

public class MainActivity extends ActionBarActivity {
    private String logicalPageName;

    // This array holds a unique resource id for all root fragments (SHOP, CART, ORDERS)
    private static final int[] FRAGMENT_TAB_ID = new int[] { R.id.fragment_tab_root_one, R.id.fragment_tab_root_two,
            R.id.fragment_tab_root_three };
    private static final int FRAGMENT_TAB_COUNT = FRAGMENT_TAB_ID.length;

    public ViewPager mPager;
    private MainPagerAdapter mPagerAdapter;
    private ActionBar mActionBar;
    private int currentTabIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        
        // this will get layout of page after it being created.
        //Tealeaf.logScreenview(this, getLogicalPageName(), ScreenviewType.LOAD);
        //Tealeaf.logScreenLayoutOnCreate(this, "MainActivity");

        mActionBar = this.getSupportActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mPagerAdapter = new MainPagerAdapter(this.getSupportFragmentManager());

        mPager = (ViewPager) this.findViewById(R.id.activity_main_ViewPager);
        mPager.setAdapter(mPagerAdapter);
        mPager.setOffscreenPageLimit(FRAGMENT_TAB_COUNT);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentTabIndex = position;
                TabContainerFragment tabContainerFragment = (TabContainerFragment) mPagerAdapter.instantiateItem(
                        mPager, position);
                if (tabContainerFragment != null) {
                    tabContainerFragment.mContentFragment.fragmentIsVisible();
                }
                mActionBar.setSelectedNavigationItem(position);
            }
        });

        configureNavigationTabs();
        Intent intent = getIntent();
        if (intent != null) {
            String extra = intent.getStringExtra("type");
            if (extra != null && extra.equals("submit")) {
                mPager.setCurrentItem(2);
            }
        }
        
        //TAGGING: PageView Tag
        final TagPageView tag = new TagPageView(this, false); //false, no need to start a new session
        tag.executeTag();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_logout:
                AuroraHelper.logOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    // This method stops accidental app closers
    @Override
    public void onBackPressed() {
        int stackCount = this.getSupportFragmentManager().getBackStackEntryCount();

        if (currentTabIndex != 0 || stackCount == 0) { // TODO this needs to be done in a better way
            AuroraHelper.logOut(this);
        } else {
            super.onBackPressed();
        }
    }

    private void configureNavigationTabs() {
        Tab tab;
        TabListener tabListener = new TabListener() {
            @Override
            public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            }

            @Override
            public void onTabSelected(Tab tab, FragmentTransaction ft) {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
            }
        };

        // Tab 1
        tab = mActionBar.newTab().setText(R.string.tab_one_title).setTabListener(tabListener);
        mActionBar.addTab(tab);

        // Tab 2
        tab = mActionBar.newTab().setText(R.string.tab_two_title).setTabListener(tabListener);
        mActionBar.addTab(tab);

        // Tab 3
        tab = mActionBar.newTab().setText(R.string.tab_three_title).setTabListener(tabListener);
        mActionBar.addTab(tab);
    }

    protected void onPause() {
        //Tealeaf.onPause(this, getLogicalPageName());
        super.onPause();
    }

    protected void onResume() {
       //Tealeaf.onResume(this, getLogicalPageName());
        super.onResume();
    }

    protected void onDestroy() {
        //Tealeaf.onDestroy(this, getLogicalPageName());
        super.onDestroy();
    }

    /**
     * View pager adapter responsible for managing fragments
     * 
     * @author cyang
     * 
     */
    public class MainPagerAdapter extends FragmentStatePagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {

                case 0:
                    return new TabContainerFragment(FRAGMENT_TAB_ID[position], new CategoryListFragment());

                case 1:
                    return new TabContainerFragment(FRAGMENT_TAB_ID[position], new CartFragment());

                case 2:
                    return new TabContainerFragment(FRAGMENT_TAB_ID[position], new OrdersFragment());

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return FRAGMENT_TAB_COUNT;
        }
    }

    /**
     * Logical page name of the Activity.
     * 
     * @return Logical page name of the Activity.
     */
    public final String getLogicalPageName() {
        if ((this.logicalPageName == null) || (this.logicalPageName.equals(""))) {
            this.logicalPageName = this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1);
        }
        return this.logicalPageName;
    }

    /**
     * Logical page name of the Activity.
     * 
     * @param logicalPageName Logical page name of the Activity.
     */
    public final void setLogicalPageName(final String logicalPageName) {
        this.logicalPageName = logicalPageName;
    }
}
