package com.example.user.simplelife;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class TVActivity extends ActionBarActivity {

    private TV appliance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);

        appliance = (TV)getIntent().getSerializableExtra("device");
        TextView nameText = (TextView)findViewById(R.id.textName_tv);
        nameText.setText(appliance.getName());
        TextView contentText = (TextView)findViewById(R.id.textContent_tv);
        contentText.setText("Connected to \n" + appliance.getMainControllerName());

        ImageButton backButton = (ImageButton)findViewById(R.id.ibtnBack_tv);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton btnOn = (ImageButton) findViewById(R.id.ibtnCircle_tv);
        if(appliance.getState()){
            btnOn.setImageResource(R.drawable.circle_tv_yellow);
        }
        else{
            btnOn.setImageResource(R.drawable.circle_tv);
        }
        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appliance.getState()) {
                    AddCommand("onoff", "off");
                    appliance.setState(false);
                    ImageButton btnOn = (ImageButton) findViewById(R.id.ibtnCircle_tv);
                    btnOn.setImageResource(R.drawable.circle_tv);
                } else {
                    AddCommand("onoff", "on");
                    appliance.setState(true);
                    ImageButton btnOn = (ImageButton) findViewById(R.id.ibtnCircle_tv);
                    btnOn.setImageResource(R.drawable.circle_tv_yellow);
                }
            }
        });

        ImageButton btnMenuUp = (ImageButton) findViewById(R.id.ibtnUp_menu_tv);
        btnMenuUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("menu", "up");
            }
        });

        ImageButton btnMenuDown = (ImageButton) findViewById(R.id.ibtnDown_menu_tv);
        btnMenuDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("menu", "down");
            }
        });

        ImageButton btnMenuLeft = (ImageButton) findViewById(R.id.ibtnLeft_menu_tv);
        btnMenuLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("menu", "left");
            }
        });

        ImageButton btnMenuRight = (ImageButton) findViewById(R.id.ibtnRight_menu_tv);
        btnMenuRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("menu", "right");
            }
        });

        Button btnMenuOK = (Button) findViewById(R.id.btnOK_tv);
        btnMenuOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("menu", "ok");
            }
        });

        ImageButton btnMenu = (ImageButton) findViewById(R.id.ibtnMenu_tv);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AddCommand("menu", "menu");
            }
        });

        ImageButton btnVolumeUp = (ImageButton) findViewById(R.id.ibtnVolumeUp);
        btnVolumeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("volume", "up");
            }
        });

        ImageButton btnVolumeDown = (ImageButton) findViewById(R.id.ibtnVolumeDown);
        btnVolumeDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("volume", "down");
            }
        });

        ImageButton btnMute = (ImageButton) findViewById(R.id.ibtnMute);
        btnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("volume", "mute");
            }
        });

        ImageButton btnChannelUp = (ImageButton) findViewById(R.id.ibtnUp_tv);
        btnChannelUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("channel", "up");
            }
        });

        ImageButton btnChannelDown = (ImageButton) findViewById(R.id.ibtnDown_tv);
        btnChannelDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("channel", "down");
            }
        });

        ImageButton btnReturn = (ImageButton) findViewById(R.id.ibtnReturn_tv);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("channel", "return");
            }
        });

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("channel", "1");
            }
        });

        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("channel", "2");
            }
        });

        Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("channel", "3");
            }
        });

        Button btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("channel", "4");
            }
        });

        Button btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("channel", "5");
            }
        });

        Button btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("channel", "6");
            }
        });

        Button btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("channel", "7");
            }
        });

        Button btn8 = (Button) findViewById(R.id.btn8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("channel", "8");
            }
        });

        Button btn9 = (Button) findViewById(R.id.btn9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("channel", "9");
            }
        });

        Button btn0 = (Button) findViewById(R.id.btn0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommand("channel", "0");
            }
        });
    }

    public void AddCommand(String cmd1, String cmd2) {
        CommandCreator cc = new CommandCreator();
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("/ControlAppliance");
        strings.add(UserProfile.email);
        strings.add(UserProfile.password);
        strings.add(appliance.getMainControllerID());
        strings.add(appliance.getType());
        strings.add(appliance.getDeviceID());
        strings.add(cmd1);
        strings.add(cmd2);
        cc.createCommand(strings);
        cc.sendToServer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tv, menu);
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
