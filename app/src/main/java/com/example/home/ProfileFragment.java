package com.example.home;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class ProfileFragment extends Fragment {
    private SharedViewModel sharedViewModel;
    private EditText editText;
    private static final int REQUEST_IMAGE_PICKER = 100;
    private static final int PERMISSION_REQUEST_CODE = 101;

    private ImageView profileImageView;
    private AppCompatButton profileBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        profileImageView = rootView.findViewById(R.id.profile_picture);
        profileBtn = rootView.findViewById(R.id.change_profile_picture);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


        loadProfileImage();

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndRequestPermissions();
            }
        });

        // Initialize the ViewModel
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Find the EditText and Button in the layout
        editText = rootView.findViewById(R.id.et_name);
        Button updateButton = rootView.findViewById(R.id.update_btn);

        // Set an OnClickListener on the Button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditText and update the ViewModel
                String text = editText.getText().toString();
                sharedViewModel.setText(text);
                Toast.makeText(requireContext(), "User Name Updated", Toast.LENGTH_SHORT).show();

                    // or Toast.makeText(getContext(), "Remove Device is Clicked", Toast.LENGTH_SHORT).show();
                }


        });

        return rootView;
    }

    private void checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            openImagePicker();
        }
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_PICKER);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker();
            } else {
                Toast.makeText(requireContext(), "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICKER && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            Glide.with(this)
                    .load(selectedImageUri)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Log.e("Glide", "Failed to load image: " + e);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            Log.d("Glide", "Image loaded successfully");
                            // Set the image URI in the shared ViewModel
                            sharedViewModel.setImageUri(selectedImageUri);
                            return false;
                        }
                    })
                    .into(profileImageView);
        }
    }

    private void loadProfileImage() {
        // Load profile image if it's already selected
        Uri imageUri = sharedViewModel.getImageUri().getValue();
        if (imageUri != null) {
            Glide.with(this)
                    .load(imageUri)
                    .into(profileImageView);
            Toast.makeText(requireContext(), "Profile Picture Updated", Toast.LENGTH_SHORT).show();
            // or Toast.makeText(getContext(), "Remove Device is Clicked", Toast.LENGTH_SHORT).show();
        }

    }
}
