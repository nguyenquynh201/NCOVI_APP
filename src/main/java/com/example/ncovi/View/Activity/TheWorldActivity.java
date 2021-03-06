package com.example.ncovi.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.ncovi.Class.NetworkConnected.NetworkConnect;
import com.example.ncovi.Model.ModelCovid.covid;
import com.example.ncovi.R;
import com.example.ncovi.View.Adaptor.TheWorldAdaptor;
import com.example.ncovi.ViewModel.Response.CovidViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TheWorldActivity extends AppCompatActivity {
    private EditText edt_search;
    private RecyclerView recyclerView;
    private TheWorldAdaptor theWorldAdaptor;
    private ArrayList<covid> mListCovid;
    private CovidViewModel viewModel;
    private ProgressDialog dialog;
    private SwipeRefreshLayout refreshLayout;
    private NetworkConnect networkConnect = new NetworkConnect();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_world);
        iniUI();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.setCancelable(false);
        dialog.show();
        viewModel = new ViewModelProvider(this).get(CovidViewModel.class);
        viewModel.getSelectCovid().observe(this, new Observer<ArrayList<covid>>() {
            @Override
            public void onChanged(ArrayList<covid> covidList) {
                mListCovid = covidList;
                if (mListCovid != null) {
                    dialog.dismiss();
                    loadDataTheWorld(mListCovid);

                }
            }
        });
        viewModel.iniSelectDate();

    }

    @SuppressLint("ResourceAsColor")
    private void iniUI() {
        //??nh x??? refresh_layout
        refreshLayout = findViewById(R.id.refresh_layout);
        refreshLayout.setColorSchemeColors(R.color.color_main);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        loadDataTheWorld(mListCovid);
                        refreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        recyclerView = findViewById(R.id.rcv_show_world);
        edt_search = findViewById(R.id.edt_search_world);
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchCity(s.toString());
            }
        });

    }

    private void searchCity(String name) {
        ArrayList<covid> covids = new ArrayList<>();
        for (covid covid : mListCovid) {
            if (covid.getCountry().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))) {
                covids.add(covid);
                covid covid1;

            }
            theWorldAdaptor.search(covids);
        }
    }

    private void loadDataTheWorld(ArrayList<covid> covidList) {
        theWorldAdaptor = new TheWorldAdaptor(covidList, TheWorldActivity.this);
        theWorldAdaptor.setData(covidList);
        recyclerView.setLayoutManager(new LinearLayoutManager(TheWorldActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(TheWorldActivity.this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(theWorldAdaptor);

    }

    //Check connect
    @Override
    protected void onStart() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkConnect, intentFilter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkConnect);
        super.onStop();

    }
}