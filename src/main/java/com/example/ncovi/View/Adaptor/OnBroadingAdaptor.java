package com.example.ncovi.View.Adaptor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ncovi.R;
import com.example.ncovi.View.Fragments.OnBroading1Fragment;
import com.example.ncovi.View.Fragments.OnBroading2Fragment;
import com.example.ncovi.View.Fragments.OnBroading3Fragment;

public class OnBroadingAdaptor extends FragmentStatePagerAdapter {

    public OnBroadingAdaptor(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0 :
                return new OnBroading1Fragment();
            case 1 :
                return new OnBroading2Fragment();
            case 2 :
                return new OnBroading3Fragment();
            default:
                return new OnBroading1Fragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
