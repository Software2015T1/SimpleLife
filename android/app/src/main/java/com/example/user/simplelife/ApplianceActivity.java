package com.example.user.simplelife;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
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
        tabHost.addTab(tabHost.newTabSpec("home").setIndicator("",getResources().getDrawable(R.drawable.tab_home)), ApplianceFragment.class, null);

        //favorite tab
        tabHost.addTab(tabHost.newTabSpec("Favorite").setIndicator("",getResources().getDrawable(R.drawable.tab_favorite)), FavoriteFragment.class, null);

        //chart tab
        tabHost.addTab(tabHost.newTabSpec("Chart").setIndicator("",getResources().getDrawable(R.drawable.tab_chart)), ChartFragment.class, null);

        //setting tab
        tabHost.addTab(tabHost.newTabSpec("Account").setIndicator("",getResources().getDrawable(R.drawable.tab_account)), AccountFragment.class, null);

    }



    public String getHomeData()
    {
        return "123";
    }

    public String getFavoriteData()
    {
        return "456";
    }

    public String getChartData()
    {
        return "789";
    }

    public String getSettingData()
    {
        return "000";
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
