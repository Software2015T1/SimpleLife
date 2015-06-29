package com.example.user.simplelife;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class TimeSettingActivity extends ActionBarActivity {

    private Appliance appliance;
    private Light light;
    private Other other;
    private AirConditioner air;
    private TimePickerDialog timePickerDialog;
    private TimePickerDialog timePickerDialog2;
    private Button doSetOnTime;
    private Button doSetOffTime;
    private Integer OnHour, OnMinute;
    private Integer OffHour, OffMinute;
    private Spinner onTime;
    private Spinner offTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_setting);
        appliance = (Appliance)getIntent().getSerializableExtra("device");
        if(appliance.getType().equals("AC"))
        {
            air = (AirConditioner)appliance;
        }
        else if(appliance.getType().equals("Light"))
        {
            light = (Light)appliance;
        }
        else if(appliance.getType().equals("Other"))
        {
            other = (Other)appliance;
        }
        TextView nameView = (TextView)findViewById(R.id.textName_time);
        nameView.setText(appliance.getName());
        ImageView icon = (ImageView)findViewById(R.id.image_icon_time);
        icon.setImageResource(appliance.getIcon());

        setSpinnerView();
        GregorianCalendar calendar = new GregorianCalendar();
        doSetOnTime = (Button) findViewById(R.id.btnOnTimeChoose);
        doSetOnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
        timePickerDialog =  new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                doSetOnTime.setText(hourOfDay + " : " + minute);
                OnHour = hourOfDay;
                OnMinute = minute;
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(calendar.MINUTE), false);

        doSetOffTime = (Button) findViewById(R.id.btnOffTimeChoose);
        doSetOffTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog2.show();
            }
        });
        timePickerDialog2 =  new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                doSetOffTime.setText(hourOfDay + " : " + minute);
                OffHour = hourOfDay;
                OffMinute = minute;
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(calendar.MINUTE), false);

        Button save = (Button) findViewById(R.id.btnSave_time);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtOnTime = onTime.getSelectedItem().toString();
                String txtOffTime = offTime.getSelectedItem().toString();
                Time on = new Time(OnMinute, OnHour, txtOnTime);
                Time off = new Time(OffMinute, OffHour, txtOffTime);
                Boolean state = true;
                Switch notify = (Switch) findViewById(R.id.switchNotif_time);
                TimeSetting ts = new TimeSetting(on, off, state, notify.isChecked());
                if(light!=null)
                {
                    light.setTimeSetting(ts);
                    appliance = light;
                }
                else if(other!=null)
                {
                    other.setTimeSetting(ts);
                    appliance = other;
                }
                else if(air!=null)
                {
                    air.setTimeSetting(ts);
                    appliance = air;
                }
                MainController mc = ObjectReader.loadMainController(appliance.getMainControllerID());
                mc.setAppliance(appliance);
                ObjectWriter.WriteAppliance(mc, appliance.getMainControllerID());
                Intent intent = new Intent();
                String text = "Turns on every "+ts.getStartTime().getDate() + " " +
                ts.getStartTime().getHour() + " : " + ts.getStartTime().getMinute() + " to "+
                        ts.getEndTime().getDate() + " " + ts.getEndTime().getHour() + " : " + ts.getEndTime().getMinute();
                setResult(Activity.RESULT_OK,intent);
                intent.putExtra(getString(R.string.Get_ListView_Text),text);

                finish();
            }
        });

        ImageButton backButton = (ImageButton)findViewById(R.id.ibtnBack_time);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnDelete = (Button)findViewById(R.id.btnDelete_time);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(light!=null)
                {
                    light.setTimeSetting(null);
                    appliance = light;
                }
                else if(other!=null)
                {
                    other.setTimeSetting(null);
                    appliance = other;
                }
                else if(air!=null)
                {
                    air.setTimeSetting(null);
                    appliance = air;
                }
                MainController mc = ObjectReader.loadMainController(appliance.getMainControllerID());
                mc.setAppliance(appliance);
                ObjectWriter.WriteAppliance(mc, appliance.getMainControllerID());
                Intent intent = new Intent();
                String text = "Time Setting";
                setResult(Activity.RESULT_OK, intent);
                intent.putExtra(getString(R.string.Get_ListView_Text), text);
                finish();
            }
        });
    }


    public void setSpinnerView(){
        ArrayList<String> onTimeList = new ArrayList<>();
        onTimeList.add("Monday");
        onTimeList.add("Tuesday");
        onTimeList.add("Wednesday");
        onTimeList.add("Thursday");
        onTimeList.add("Friday");
        onTimeList.add("Saturday");
        onTimeList.add("Sunday");
        ArrayList<String> offTimeList = new ArrayList<>();
        offTimeList.add("Monday");
        offTimeList.add("Tuesday");
        offTimeList.add("Wednesday");
        offTimeList.add("Thursday");
        offTimeList.add("Friday");
        offTimeList.add("Saturday");
        offTimeList.add("Sunday");

        onTime = (Spinner) findViewById(R.id.spinner_onTime);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,onTimeList);
        onTime.setAdapter(adapter);

        offTime = (Spinner) findViewById(R.id.spinner_offTime);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,offTimeList);
        offTime.setAdapter(adapter2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_time_setting, menu);
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
