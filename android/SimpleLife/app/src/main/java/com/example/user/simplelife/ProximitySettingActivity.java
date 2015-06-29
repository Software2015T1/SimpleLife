package com.example.user.simplelife;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;


public class ProximitySettingActivity extends ActionBarActivity {

    ArrayList<String> distances;
    private Appliance appliance;
    private Light light;
    private AirConditioner air;
    public static final String GETPROXTEXT="Prox";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_setting);
       appliance = (Appliance)getIntent().getSerializableExtra("device");
        if(appliance.type.equals("Light")){
            light = (Light)getIntent().getSerializableExtra("device");
        }
        else{
            air = (AirConditioner)getIntent().getSerializableExtra("device");
        }
        TextView nameView = (TextView)findViewById(R.id.textName_proximity);
        nameView.setText(appliance.getName());
        ImageView icon = (ImageView)findViewById(R.id.image_icon_proximity);
        icon.setImageResource(appliance.getIcon());
        ImageButton backButton = (ImageButton)findViewById(R.id.ibtnBack_proximity);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getDistance();
        Spinner spinner = (Spinner)findViewById(R.id.spinner_distance);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,distances);
        spinner.setAdapter(adapter);

        Switch mySwitch = (Switch)findViewById(R.id.switchNotif_proximity);

        Button delButton = (Button)findViewById(R.id.btnDelete_proximity);
        delButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
            if(appliance.type.equals("Light")){
                light.setProximitySetting(null);
                appliance = (Appliance)light;
            }
            else{
                air.setProximitySetting(null);
                appliance = (Appliance)air;
            }
            MainController main = ObjectReader.loadMainController(appliance.getMainControllerID());
            ArrayList<Appliance> appliances = main.getAppliances();
            for(int i=0;i<appliances.size();i++){
                Appliance app = appliances.get(i);
                if(appliance.getDeviceID().equals(app.getDeviceID())){
                    appliances.set(i,appliance);
                    break;
                }
            }
            ObjectWriter.WriteAppliance(main, main.getMainControlerID());
            Intent intent = new Intent();
            String putText = "Proximity Setting";
            intent.putExtra(getString(R.string.Get_ListView_Text),putText);
            setResult(Activity.RESULT_OK, intent);
            finish();
                    }
            });

        Button saveButton = (Button)findViewById(R.id.btnSave_proximity);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Spinner spinner = (Spinner)findViewById(R.id.spinner_distance);
                String distanceM = spinner.getSelectedItem().toString();
                int d = Integer.parseInt(distanceM.substring(0,distanceM.indexOf('m')));
                Switch mySwitch = (Switch)findViewById(R.id.switchNotif_proximity);

                ProximitySetting ps = new ProximitySetting(d,true,mySwitch.isChecked());
                if(appliance.type.equals("Light")){
                    light.setProximitySetting(ps);
                    appliance = (Appliance)light;
                }
                else{
                    air.setProximitySetting(ps);
                    appliance = (Appliance)air;
                }
                MainController main = ObjectReader.loadMainController(appliance.getMainControllerID());
                ArrayList<Appliance> appliances = main.getAppliances();
                for(int i=0;i<appliances.size();i++){
                    Appliance app = appliances.get(i);
                    if(appliance.getDeviceID().equals(app.getDeviceID())){
                        appliances.set(i,appliance);
                        break;
                    }
                }
                ObjectWriter.WriteAppliance(main, appliance.getMainControllerID());
                Intent intent = new Intent();
                String putText = "Close to: "+distanceM;
                intent.putExtra(getString(R.string.Get_ListView_Text),putText);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_proximity_setting, menu);
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

    public void getDistance(){
        distances = new ArrayList<String>();
        distances.add(getString(R.string.d1));
        distances.add(getString(R.string.d2));
        distances.add(getString(R.string.d3));
        distances.add(getString(R.string.d4));
        distances.add(getString(R.string.d5));
        distances.add(getString(R.string.d6));
        distances.add(getString(R.string.d7));
        distances.add(getString(R.string.d8));
        distances.add(getString(R.string.d9));
    }
}
