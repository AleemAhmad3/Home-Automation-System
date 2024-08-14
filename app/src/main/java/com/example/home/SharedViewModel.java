package com.example.home;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> text = new MutableLiveData<>();
    private final MutableLiveData<Uri> imageUri = new MutableLiveData<>();

    public LiveData<String> getText() {
        return text;
    }

    public void setText(String newText) {
        text.setValue(newText);
    }

    public LiveData<Uri> getImageUri() {
        return imageUri;
    }

    // Method to set the image URI in the ViewModel
    public void setImageUri(Uri newImageUri) {
        imageUri.setValue(newImageUri);
    }
}
