package com.example.user.simplelife;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ProfileActivity extends ActionBarActivity {

    Button btnLogout ;
    Button btnChangePassword;
    EditText etextNewPassword;
    EditText etexCPassword;
    EditText etexCurrPassword;
    Button btnSaveNewPassword;
    ImageButton backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        backButton = (ImageButton)findViewById(R.id.ibtnBack_profile);
        btnLogout = (Button)findViewById(R.id.btnLogOut);
        btnChangePassword=(Button)findViewById(R.id.btnChangePassword);
        etexCPassword = (EditText)findViewById(R.id.editText_confirmPassword);
        etextNewPassword =(EditText)findViewById(R.id.editText_NewPassword);
        etexCurrPassword =(EditText)findViewById(R.id.editText_currentPassword);
        btnSaveNewPassword = (Button)findViewById(R.id.btnSaveNewPassword);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoutEvent();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ApplianceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("type", 3);
                intent.putExtras(bundle);
                startActivity(intent);;
            }
        });
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etextNewPassword.setVisibility(View.VISIBLE);
                etexCPassword.setVisibility(View.VISIBLE);
                etexCurrPassword.setVisibility(View.VISIBLE);
                btnSaveNewPassword.setVisibility(View.VISIBLE);
            }
        });
        btnSaveNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String currPassword = etexCurrPassword.getText().toString();
                final String confirmPassword = etexCPassword.getText().toString();
                final String changePassowrd =etextNewPassword.getText().toString();
                boolean isValid = true;
                etexCurrPassword.setError(null);
                if(!isPasswordValid(currPassword))
                {
                    etexCurrPassword.setError("Password is invalid");
                    isValid = false;
                }
                etexCPassword.setError(null);
                if(!isPasswordValid(confirmPassword))
                {
                    etexCPassword.setError("Password is invalid");
                    isValid = false;
                }
                if(!isPasswordValid(changePassowrd))
                {
                    etextNewPassword.setError("Password is invalid");
                    isValid =false;
                }
                if(isValid)
                {
                    new Thread() {
                        public void run()
                        {
                            Socket s = UserProfile.Socket2Server;
                            String currPasswordMd5 = Md5.md5(currPassword);
                            String newPasswordMd5 = Md5.md5(changePassowrd);
                            try
                            {
                                DataOutputStream outs = new DataOutputStream(s.getOutputStream());
                                DataInputStream inputs = new DataInputStream(s.getInputStream());
                                outs.writeUTF("/ChangePassword "+UserProfile.email+" "+currPasswordMd5+" "+newPasswordMd5);
                                String returnCode = inputs.readUTF();
                                if(returnCode.equals("R012"))
                                {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            new AlertDialog.Builder(ProfileActivity.this).setMessage("Password Changed!").show();
                                        }
                                    });
                                }
                                else if(returnCode.equals("R013"))
                                {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            new AlertDialog.Builder(ProfileActivity.this).setMessage("Password incorrect").show();
                                        }
                                    });
                                }


                            } catch(IOException e)
                            {

                            }
                        }
                    }.start();


                }
            }
        });
    }
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        boolean r =true;
        if(password.length()<8 || password.length()>20) r =false;
        boolean hasDigit = false;
        boolean hasLetter =false;
        for(int i=0;i<password.length();i++)
        {
            char c = password.charAt(i);
            if(Character.isDigit(c))hasDigit=true;
            if(Character.isLetter(c))hasLetter=true;
        }
        if(!hasDigit || !hasLetter) r =false;
        return r;
    }
    private void LogoutEvent()
    {
        UserProfile.Dispose();
        startActivity(new Intent(ProfileActivity.this,MainActivity.class));

    }

}
