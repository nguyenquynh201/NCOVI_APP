package com.example.ncovi.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ncovi.R;
import com.example.ncovi.View.Adaptor.TableAdaptor;
import com.google.android.material.tabs.TabLayout;


public class FeedBackFragment extends Fragment {
private TabLayout tableLayout;
private ViewPager viewPager;
private TableAdaptor tableAdaptor;
    View mView;
    public FeedBackFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_feedback, container, false);
        initUI();
        return mView;
    }

    private void initUI() {
        tableLayout = mView.findViewById(R.id.table_feedback);
        viewPager = mView.findViewById(R.id.viewpager_feedback);
        tableAdaptor = new TableAdaptor(getChildFragmentManager() , FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT );
        viewPager.setAdapter(tableAdaptor);
        tableLayout.setupWithViewPager(viewPager);

    }
}