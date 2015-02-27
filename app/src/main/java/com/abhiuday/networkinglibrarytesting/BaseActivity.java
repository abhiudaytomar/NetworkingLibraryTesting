package com.abhiuday.networkinglibrarytesting;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public abstract class BaseActivity extends ActionBarActivity {

    private static final int[] NAVDRAWER_TITLE_RES_ID = new int[] {
            R.string.retrofit_networking_framework,
            R.string.okHTTP_networking_framework,
            R.string.volley_networking_framework
    };
    private CharSequence mTitle;
    private DrawerLayout mDrawerLayout;
    private Toolbar mActionBarToolbar;
    private Handler mHandler;
    private int mThemedStatusBarColor;
    private int mNormalStatusBarColor;
    private ListView mNavDrawerList;
    private ArrayList<String> mNavDrawerItems = new ArrayList<String>();
    
    protected static final int NAVDRAWER_RETROFIT = 0;
    protected static final int NAVDRAWER_OKHTTP = 1;
    protected static final int NAVDRAWER_VOLLEY = 2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        mTitle = getTitle();
        mThemedStatusBarColor = getResources().getColor(R.color.theme_primary_dark);
        mNormalStatusBarColor = mThemedStatusBarColor;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setupNavDrawer();
    }

    private void setupNavDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(mDrawerLayout == null) {
            return;
        }
        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.theme_primary_dark));

        if (mActionBarToolbar != null) {
            mActionBarToolbar.setNavigationIcon(R.drawable.ic_drawer);
            mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDrawerLayout.openDrawer(Gravity.START);
                }
            });
        }
        
        populateNavDrawer();
    }

    private void populateNavDrawer() {
        mNavDrawerItems.clear();
        mNavDrawerItems.add(getString(R.string.retrofit_networking_framework));
        mNavDrawerItems.add(getString(R.string.okHTTP_networking_framework));
        mNavDrawerItems.add(getString(R.string.volley_networking_framework));
        createNavDrawer();
    }

    private void createNavDrawer() {
        mNavDrawerList = (ListView)findViewById(R.id.nav_drawer);
        mNavDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mNavDrawerItems));
        mNavDrawerList.setOnItemClickListener(new NavDrawerItemClickListener());
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getActionBarToolbar();
    }

    private Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
            if (mActionBarToolbar != null) {
                setSupportActionBar(mActionBarToolbar);
            }
        }
        return mActionBarToolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class NavDrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String message = "This " + mNavDrawerItems.get(position) + "has been clicked";
            Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
}
