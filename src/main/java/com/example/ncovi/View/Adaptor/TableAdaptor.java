package com.example.ncovi.View.Adaptor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ncovi.View.Fragments.Child1FeedBackFragment;
import com.example.ncovi.View.Fragments.Child2FeedBackFragment;

public class TableAdaptor extends FragmentStatePagerAdapter {

    public TableAdaptor(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Child1FeedBackFragment();
            case 1:
                return new Child2FeedBackFragment();
            default:
                return new Child1FeedBackFragment();
        }

    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position)
        {
            case 0 :
                 title = "Phản ánh";
                 break;
            case 1 :
                title = "Khai Báo tiếp xúc";
                break;
        }
        return title;
    }
}
