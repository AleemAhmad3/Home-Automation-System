package com.example.home;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    private Switch switchDarkMode;
    private TextView textViewContact,textview_dialogue,user_profile,support,feedback,help,about;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);


        user_profile = view.findViewById(R.id.user_profile);
        support = view.findViewById(R.id.textView_support);
        feedback  = view.findViewById(R.id.textView_send_feedback);
        help = view.findViewById(R.id.textView_help);
        about = view.findViewById(R.id.textView_about);
        textViewContact = view.findViewById(R.id.textView_contact);


        // Initialize the dark mode switch_____________
        switchDarkMode = view.findViewById(R.id.switch_dark_mode);

        // Set up the dark mode switch listener
        switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Enable dark mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    // Save the state in SharedPreferences
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("AppSettingsPrefs", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("IsNightMode", true);
                    editor.apply();
                } else {
                    // Disable dark mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    // Save the state in SharedPreferences
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("AppSettingsPrefs", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("IsNightMode", false);
                    editor.apply();
                }
            }
        });
//---------------------_______-----------
        Dialog myCustomDialog = new Dialog(getContext());
        if (myCustomDialog.getWindow() != null) {
            myCustomDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // Optional: makes the background of the dialog transparent
        }
        myCustomDialog.setContentView(R.layout.custom_dialogue_box); // Replace with your custom layout file
        textview_dialogue = (TextView) myCustomDialog.findViewById(R.id.tv_dialogue);// dialogbox's textview
// ________________-----------___________
        user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myCustomDialog.show();
                textview_dialogue.setText("03001234rge");

            }
        });

        textViewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCustomDialog.show();
               textview_dialogue.setText("03001234rg");
            }
        });


        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCustomDialog.show();
                textview_dialogue.setText("User_name");

            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCustomDialog.show();
                textview_dialogue.setText(" Tell us your Experience and Suggestions at 4021muhammadali@gmail.com");

            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCustomDialog.show();
                textview_dialogue.setText("for any kind of help you can contact 4021muhammadali@gmail.com");

            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCustomDialog.show();
                textview_dialogue.setText("This is an Android Application made for Home Automation by the Students of GCUF IT Department for final Year Project");

            }
        });


        return view;
    }
}
