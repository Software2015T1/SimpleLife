package com.example.user.simplelife;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class ApplianceActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance);

        FragmentTabHost tabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        //home tab
        tabHost.addTab(tabHost.newTabSpec("home").setIndicator("", ResourcesCompat.getDrawable(getResources(),R.drawable.tab_home, null)), ApplianceFragment.class, null);

        //favorite tab
        tabHost.addTab(tabHost.newTabSpec("Favorite").setIndicator("",ResourcesCompat.getDrawable(getResources(), R.drawable.tab_favorite, null)), FavoriteFragment.class, null);

        //chart tab
        tabHost.addTab(tabHost.newTabSpec("Chart").setIndicator("",ResourcesCompat.getDrawable(getResources(), R.drawable.tab_chart,null)), ChartFragment.class, null);

        //setting tab
        tabHost.addTab(tabHost.newTabSpec("Account").setIndicator("",ResourcesCompat.getDrawable(getResources(), R.drawable.tab_account, null)), AccountFragment.class, null);

        Bundle args =this.getIntent().getExtras();
        tabHost.setCurrentTab(args.getInt("type"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appliance, menu);
        return true;
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
}
