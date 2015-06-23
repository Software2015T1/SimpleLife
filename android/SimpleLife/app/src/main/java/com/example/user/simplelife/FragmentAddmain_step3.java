package com.example.user.simplelife;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAddmain_step3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAddmain_step3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddmain_step3 extends FragmentAdd_step {

    public static FragmentAddmain_step3 newInstance() {
        FragmentAddmain_step3 fragment = new FragmentAddmain_step3();
        Bundle args = new Bundle();

        return fragment;
    }

    public FragmentAddmain_step3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_addmain_step3, container, false);

        ImageButton nextButton = (ImageButton) view.findViewById(R.id.ibtnNext_addmain3);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               EditText et = (EditText)getActivity().findViewById(R.id.editTextID_addmain);
                final String MCID = et.getText().toString();
                new Thread() {
                    public void run() {
                        Socket s = UserProfile.Socket2Server;
                        String email = UserProfile.email;
                        String password =UserProfile.password;

                        try {
                            DataOutputStream outs = new DataOutputStream(s.getOutputStream());
                            DataInputStream inputs = new DataInputStream(s.getInputStream());
                            outs.writeUTF("/AddMC "+email+" "+password+" " +MCID+" "+email);
                            String returnCode = inputs.readUTF();
                            if(returnCode.equals("R005"))
                            {
                                MainController main = (MainController)((Add_MainControllerActivity)getActivity()).getMainController();
                                EditText mainID = (EditText)view.findViewById(R.id.editTextID_addmain);
                                main.setMainControlerID(mainID.getText().toString());

                                mListener.onFragmentInteraction("next");
                            }
                            else  if(returnCode.equals("R006"))
                            {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        new AlertDialog.Builder(getActivity()).setMessage("Main Controller ID not exist or had not  connected to server").show();
                                    }
                                });
                            }
                        } catch (IOException e) {

                            e.printStackTrace();
                        }


                    }
                }.start();
            }
        });
        return view;
    }
}
