package com.example.user.simplelife;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;


public class AddProductActivity extends ActionBarActivity {

    private ListView list;
    private AddProduct_ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        list = (ListView) findViewById(R.id.listView_addproduct);
        adapter = new AddProduct_ListAdapter(this);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent();
                    intent.setClass(AddProductActivity.this, Add_MainControllerActivity.class);
                    startActivity(intent);
                }
                else if(position == 1){
                    Intent intent = new Intent();
                    intent.setClass(AddProductActivity.this, Add_LightActivity.class);
                    startActivity(intent);
                }
                else if(position == 2){
                    Intent intent = new Intent();
                    intent.setClass(AddProductActivity.this, Add_AirActivity.class);
                    startActivity(intent);
                }
                else if(position == 3){
                    Intent intent = new Intent();
                    intent.setClass(AddProductActivity.this, Add_TVActivity.class);
                    startActivity(intent);
                }
                else if(position == 4){
                    Intent intent = new Intent();
                    intent.setClass(AddProductActivity.this, Add_OtherActivity.class);
                    startActivity(intent);
                }
            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.ibtnBack_addproduct);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_product, menu);
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
