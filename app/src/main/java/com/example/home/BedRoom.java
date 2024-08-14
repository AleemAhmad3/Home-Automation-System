package com.example.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BedRoom extends Fragment {

    private static final String TAG = "BedRoom";
    private Switch mySwitch;
    private DatabaseReference FBR; // Firebase reference

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bedroom, container, false); // Ensure this layout file exists and contains your Switch

        // Initialize Firebase Realtime Database reference
        FBR = FirebaseDatabase.getInstance().getReference();
        Log.d(TAG, "Firebase Database Reference initialized");

        // Find the Switch by its ID
        mySwitch = view.findViewById(R.id.s1); // Ensure this ID matches the one in your layout file
        Log.d(TAG, "Switch initialized");

        // Set custom text for the switch states

        // Set an OnCheckedChangeListener to update the database when the switch is toggled
        mySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int switchState = isChecked ? 1 : 0;
            Log.d(TAG, "Switch state changed to: " + switchState);
            FBR.child("Fan").setValue(switchState).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Database updated successfully");
                } else {
                    Log.e(TAG, "Database update failed", task.getException());
                }
            });
        });

        return view;
    }
}
