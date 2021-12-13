package com.example.ncovi.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ncovi.Model.TinhTrangSucKhoe;
import com.example.ncovi.Model.user;
import com.example.ncovi.R;
import com.example.ncovi.View.Adaptor.TinhTrangSucKhoeAdaptor;
import com.example.ncovi.View.SharedPreference.DataManager;
import com.example.ncovi.ViewModel.Response.TinhTrangViewModel;

import java.util.List;

public class ShowAllSucKhoeActivity extends AppCompatActivity {
private TinhTrangSucKhoeAdaptor tinhTrangSucKhoeAdaptor;
private List<TinhTrangSucKhoe> mListSucKhoe;
private RecyclerView recyclerView;
private TinhTrangViewModel tinhTrangViewModel;
private String idMember;
private com.example.ncovi.Model.user user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_suc_khoe);
        user = DataManager.loadUser();
        idMember = user.getIdMember();
        recyclerView = findViewById(R.id.rcv_show_all);
        iniData();
    }

    private void iniData() {
        tinhTrangViewModel = new ViewModelProvider(this).get(TinhTrangViewModel.class);
        tinhTrangViewModel.getListTinhTrang().observe(this, new Observer<List<TinhTrangSucKhoe>>() {
            @Override
            public void onChanged(List<TinhTrangSucKhoe> tinhTrangSucKhoes) {
                mListSucKhoe = tinhTrangSucKhoes ;
                if(mListSucKhoe!=null)
                {
                    loadListSucKhoe(mListSucKhoe);
                }
            }
        });
        tinhTrangViewModel.iniSelectList(idMember);
    }

    private void loadListSucKhoe(List<TinhTrangSucKhoe> tinhTrangSucKhoes) {
        tinhTrangSucKhoeAdaptor = new TinhTrangSucKhoeAdaptor(this);
        tinhTrangSucKhoeAdaptor.setDataTinhTrang(tinhTrangSucKhoes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(tinhTrangSucKhoeAdaptor);
    }
}