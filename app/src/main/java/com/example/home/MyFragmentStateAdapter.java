package com.example.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyFragmentStateAdapter extends FragmentStateAdapter {

    public MyFragmentStateAdapter(@NonNull HomeFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BedRoom();
            case 1:
                return new LivingRoom();
            case 2:
                return new Kitchen();
            case 3:
                return new Other();
            default:
                return new BedRoom(); // Default case
        }
    }

    @Override
    public int getItemCount() {
        return 4; // Number of fragments
    }
}
