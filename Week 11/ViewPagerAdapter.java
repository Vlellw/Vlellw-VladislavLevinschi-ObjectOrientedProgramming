package com.example.soccerteammanager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new TeamsFragment();
            case 1: return new PlayersFragment();
            case 2: return new MatchesFragment();
            default: return new TeamsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}