package com.example.home;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {
    private TextView textView;
    private SharedViewModel sharedViewModel;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the ViewPager2 and TabLayout
        ViewPager2 viewPager = view.findViewById(R.id.vp1);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);

        // Initialize the adapter and set it to the ViewPager2
        MyFragmentStateAdapter adapter = new MyFragmentStateAdapter(this);
        viewPager.setAdapter(adapter);

        // Ensure the initial position is set to 0 (the first fragment)
        viewPager.setCurrentItem(0);

        // Set up the TabLayout with the ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Bedroom");
                        break;
                    case 1:
                        tab.setText("Livingroom");
                        break;
                    case 2:
                        tab.setText("Kitchen");
                        break;
                    case 3:
                        tab.setText("Other");
                        break;
                    case 4:
                        tab.setText("Tab 5");
                        break;
                }
            }
        }).attach();

        // Initialize the SharedViewModel and ImageView
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        imageView = view.findViewById(R.id.img_view);
        textView = view.findViewById(R.id.tv_name);

        // Observe the LiveData from the ViewModel
        sharedViewModel.getImageUri().observe(getViewLifecycleOwner(), new Observer<Uri>() {
            @Override
            public void onChanged(Uri newImageUri) {
                // Load the image into the ImageView
                Glide.with(requireContext())
                        .load(newImageUri)
                        .into(imageView);
            }
        });
        sharedViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newText) {
                textView.setText(newText);
            }
        });

        return view;
    }
}
