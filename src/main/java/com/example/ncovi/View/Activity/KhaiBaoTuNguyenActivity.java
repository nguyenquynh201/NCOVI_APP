package com.example.ncovi.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ncovi.Model.user;
import com.example.ncovi.R;
import com.example.ncovi.View.SharedPreference.DataManager;
import com.example.ncovi.ViewModel.Response.KhaiBaoViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class KhaiBaoTuNguyenActivity extends AppCompatActivity {
    private CheckBox cb_check_khaibao;
    private CheckBox cb_2_khaibao_1, cb_2_khaibao_2, cb_2_khaibao_3, cb_2_khaibao_4, cb_2_khaibao_5, cb_2_khaibao_6;
    private CheckBox cb_khaibao_1, cb_khaibao_2, cb_khaibao_3, cb_khaibao_4, cb_khaibao_5, cb_khaibao_6;
    private RadioGroup rdo_group_khai_bao_1, rdo_group_khai_bao_2, rdo_group_khai_bao_3;
    private RadioButton radio_khai_bao_1, radio_khai_bao_2, radio_khai_bao_3;
    private EditText edt_khai_bao_thong_tin;
    String idMember, th1, th2, th3, th4 , thoigian;
    Date date;
    SimpleDateFormat dateFormat;
    user mUser;
    private Button btn_guithongtin, no_btn_guithongtin;
    private KhaiBaoViewModel khaiBaoViewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khai_bao_tu_nguyen);
        mUser = DataManager.loadUser();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Vui L??ng ?????i....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        khaiBaoViewModel = new ViewModelProvider(this).get(KhaiBaoViewModel.class);
        iniUI();
    }

    private void iniUI() {
        //??nh x??? radiobutton n???i dung 1
        rdo_group_khai_bao_1 = findViewById(R.id.rdo_group_khai_bao_1);
        rdo_group_khai_bao_2 = findViewById(R.id.rdo_group_khai_bao_2);
        rdo_group_khai_bao_3 = findViewById(R.id.rdo_group_khai_bao_3);
        //??nh x??? edittext n???i dung 2
        edt_khai_bao_thong_tin = findViewById(R.id.edt_khai_bao_thong_tin);
        // ??nh x??? checkbox n???i dung 3
        cb_khaibao_1 = findViewById(R.id.cb_khaibao_1);
        cb_khaibao_2 = findViewById(R.id.cb_khaibao_2);
        cb_khaibao_3 = findViewById(R.id.cb_khaibao_3);
        cb_khaibao_4 = findViewById(R.id.cb_khaibao_4);
        cb_khaibao_5 = findViewById(R.id.cb_khaibao_5);
        cb_khaibao_6 = findViewById(R.id.cb_khaibao_6);
        // ??nh x??? checkbox n???i dung 4
        cb_2_khaibao_1 = findViewById(R.id.cb_2_khaibao_1);
        cb_2_khaibao_2 = findViewById(R.id.cb_2_khaibao_2);
        cb_2_khaibao_3 = findViewById(R.id.cb_2_khaibao_3);
        cb_2_khaibao_4 = findViewById(R.id.cb_2_khaibao_4);
        cb_2_khaibao_5 = findViewById(R.id.cb_2_khaibao_5);
        cb_2_khaibao_6 = findViewById(R.id.cb_2_khaibao_6);
        // ??nh x??? checkbox click
        cb_check_khaibao = findViewById(R.id.cb_check_khaibao);
        //??nh x??? button
        btn_guithongtin = findViewById(R.id.btn_guithongtin);
        no_btn_guithongtin = findViewById(R.id.no_btn_guithongtin);
        cb_check_khaibao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    closeKeyBoard();
                    btn_guithongtin.setVisibility(View.VISIBLE);
                    no_btn_guithongtin.setVisibility(View.GONE);
                    btn_guithongtin.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("SimpleDateFormat")
                        @Override
                        public void onClick(View v) {
                            progressDialog.show();
                            date = Calendar.getInstance().getTime();
                            dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
                            thoigian = dateFormat.format(date.getTime());
                            idMember = mUser.getIdMember();
                            int radioId_1 = rdo_group_khai_bao_1.getCheckedRadioButtonId();
                            radio_khai_bao_1 = findViewById(radioId_1);
                            int radioId_2 = rdo_group_khai_bao_2.getCheckedRadioButtonId();
                            radio_khai_bao_2 = findViewById(radioId_2);
                            int radioId_3 = rdo_group_khai_bao_3.getCheckedRadioButtonId();
                            radio_khai_bao_3 = findViewById(radioId_3);

                            if (edt_khai_bao_thong_tin.getText().toString().trim().isEmpty()) {
                                Toast.makeText(KhaiBaoTuNguyenActivity.this, "Vui l??ng kh??ng b??? tr???ng n???i dung th??? 2", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            } else {
                                th1 = "Tr?????ng H???p 1 : " + radio_khai_bao_1.getText() + "," + radio_khai_bao_2.getText() + "," + radio_khai_bao_3.getText();
                                th2 = "Tr?????ng H???p 2 : " + edt_khai_bao_thong_tin.getText().toString().trim();
                                // check tr?????ng h???p checkbox1
                                if (cb_2_khaibao_1.isChecked() && cb_2_khaibao_2.isChecked() && cb_2_khaibao_3.isChecked() && cb_2_khaibao_4.isChecked() && cb_2_khaibao_5.isChecked() && cb_2_khaibao_6.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_1.getText().toString().trim() + "," + cb_2_khaibao_2.getText().toString().trim() + "," + cb_2_khaibao_3.getText().toString().trim() + "," + cb_2_khaibao_4.getText().toString().trim() + "," + cb_2_khaibao_5.getText().toString().trim() + "," + cb_2_khaibao_6.getText().toString().trim();
                                } else if (cb_2_khaibao_1.isChecked() && cb_2_khaibao_2.isChecked() && cb_2_khaibao_3.isChecked() && cb_2_khaibao_4.isChecked() && cb_2_khaibao_5.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_1.getText().toString().trim() + "," + cb_2_khaibao_2.getText().toString().trim() + "," + cb_2_khaibao_3.getText().toString().trim() + "," + cb_2_khaibao_4.getText().toString().trim() + "," + cb_2_khaibao_5.getText().toString().trim();
                                } else if (cb_2_khaibao_1.isChecked() && cb_2_khaibao_2.isChecked() && cb_2_khaibao_3.isChecked() && cb_2_khaibao_4.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_1.getText().toString().trim() + "," + cb_2_khaibao_2.getText().toString().trim() + "," + cb_2_khaibao_3.getText().toString().trim() + "," + cb_2_khaibao_4.getText().toString().trim();
                                } else if (cb_2_khaibao_1.isChecked() && cb_2_khaibao_2.isChecked() && cb_2_khaibao_3.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_1.getText().toString().trim() + "," + cb_2_khaibao_2.getText().toString().trim() + "," + cb_2_khaibao_3.getText().toString().trim();
                                } else if (cb_2_khaibao_1.isChecked() && cb_2_khaibao_2.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_1.getText().toString().trim() + "," + cb_2_khaibao_2.getText().toString().trim();
                                } else if (cb_2_khaibao_1.isChecked() && cb_2_khaibao_3.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_1.getText().toString().trim() + "," + cb_2_khaibao_3.getText().toString().trim();
                                } else if (cb_2_khaibao_1.isChecked() && cb_2_khaibao_4.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_1.getText().toString().trim() + "," + cb_2_khaibao_4.getText().toString().trim();
                                } else if (cb_2_khaibao_1.isChecked() && cb_2_khaibao_5.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_1.getText().toString().trim() + "," + cb_2_khaibao_5.getText().toString().trim();
                                } else if (cb_2_khaibao_1.isChecked() && cb_2_khaibao_6.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_1.getText().toString().trim() + "," + cb_2_khaibao_6.getText().toString().trim();
                                } else if (cb_2_khaibao_1.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_1.getText().toString().trim();
                                }//check tr?????ng h???p checkbox 2
                                else if (cb_2_khaibao_2.isChecked() && cb_2_khaibao_3.isChecked() && cb_2_khaibao_4.isChecked() && cb_2_khaibao_5.isChecked() && cb_2_khaibao_6.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_2.getText().toString().trim() + "," + cb_2_khaibao_3.getText().toString().trim() + "," + cb_2_khaibao_4.getText().toString().trim() + "," + cb_2_khaibao_5.getText().toString().trim() + "," + cb_2_khaibao_6.getText().toString().trim();
                                } else if (cb_2_khaibao_2.isChecked() && cb_2_khaibao_3.isChecked() && cb_2_khaibao_4.isChecked() && cb_2_khaibao_5.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_2.getText().toString().trim() + "," + cb_2_khaibao_3.getText().toString().trim() + "," + cb_2_khaibao_4.getText().toString().trim() + "," + cb_2_khaibao_5.getText().toString().trim();
                                } else if (cb_2_khaibao_2.isChecked() && cb_2_khaibao_3.isChecked() && cb_2_khaibao_4.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_2.getText().toString().trim() + "," + cb_2_khaibao_3.getText().toString().trim() + "," + cb_2_khaibao_4.getText().toString().trim();
                                } else if (cb_2_khaibao_2.isChecked() && cb_2_khaibao_3.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_2.getText().toString().trim() + "," + cb_2_khaibao_3.getText().toString().trim();
                                } else if (cb_2_khaibao_4.isChecked() && cb_2_khaibao_2.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_4.getText().toString().trim() + "," + cb_2_khaibao_2.getText().toString().trim();
                                } else if (cb_2_khaibao_5.isChecked() && cb_2_khaibao_2.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_5.getText().toString().trim() + "," + cb_2_khaibao_2.getText().toString().trim();
                                } else if (cb_2_khaibao_6.isChecked() && cb_2_khaibao_2.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_6.getText().toString().trim() + "," + cb_2_khaibao_2.getText().toString().trim();
                                } else if (cb_2_khaibao_2.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_2.getText().toString().trim();
                                }// check tr?????ng h???p checkbox3
                                else if (cb_2_khaibao_3.isChecked() && cb_2_khaibao_4.isChecked() && cb_2_khaibao_5.isChecked() && cb_2_khaibao_6.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_3.getText().toString().trim() + "," + cb_2_khaibao_4.getText().toString().trim() + "," + cb_2_khaibao_5.getText().toString().trim() + "," + cb_2_khaibao_6.getText().toString().trim();
                                } else if (cb_2_khaibao_3.isChecked() && cb_2_khaibao_4.isChecked() && cb_2_khaibao_5.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_3.getText().toString().trim() + "," + cb_2_khaibao_4.getText().toString().trim() + "," + cb_2_khaibao_5.getText().toString().trim();
                                } else if (cb_2_khaibao_3.isChecked() && cb_2_khaibao_4.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_3.getText().toString().trim() + "," + cb_2_khaibao_4.getText().toString().trim();
                                } else if (cb_2_khaibao_3.isChecked() && cb_2_khaibao_5.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_3.getText().toString().trim() + "," + cb_2_khaibao_5.getText().toString().trim();
                                } else if (cb_2_khaibao_3.isChecked() && cb_2_khaibao_6.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_3.getText().toString().trim() + "," + cb_2_khaibao_6.getText().toString().trim();
                                } else if (cb_2_khaibao_3.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_3.getText().toString().trim();
                                }
                                //check tr?????ng h???p checkbox4
                                else if (cb_2_khaibao_4.isChecked() && cb_2_khaibao_5.isChecked() && cb_2_khaibao_6.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_4.getText().toString().trim() + "," + cb_2_khaibao_5.getText().toString().trim() + "," + cb_2_khaibao_6.getText().toString().trim();
                                } else if (cb_2_khaibao_4.isChecked() && cb_2_khaibao_5.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_4.getText().toString().trim() + "," + cb_2_khaibao_5.getText().toString().trim();
                                } else if (cb_2_khaibao_4.isChecked() && cb_2_khaibao_6.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_4.getText().toString().trim() + "," + cb_2_khaibao_6.getText().toString().trim();
                                } else if (cb_2_khaibao_4.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_4.getText().toString().trim();
                                }
                                //check tr?????ng h???p checkbox5
                                else if (cb_2_khaibao_5.isChecked() && cb_2_khaibao_6.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_5.getText().toString().trim() + "," + cb_2_khaibao_6.getText().toString().trim();
                                } else if (cb_2_khaibao_5.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_5.getText().toString().trim();
                                }
                                //check tr?????ng h???p checkbox6
                                else if (cb_2_khaibao_6.isChecked()) {
                                    th4 = "Tr?????ng H???p 4 :" + cb_2_khaibao_6.getText().toString().trim();
                                }
                                //check tr?????ng h???p kh??ng click v??o checkbox n??o
                                else if (!cb_2_khaibao_1.isChecked() && !cb_2_khaibao_2.isChecked() && !cb_2_khaibao_3.isChecked() && !cb_2_khaibao_4.isChecked() && !cb_2_khaibao_5.isChecked() && !cb_2_khaibao_6.isChecked()) {
                                    th4 = "B??nh Th?????ng";
                                }
                                //check tr?????ng h???p checkbox1
                                if (cb_khaibao_1.isChecked() && cb_khaibao_2.isChecked() && cb_khaibao_3.isChecked() && cb_khaibao_4.isChecked() && cb_khaibao_5.isChecked() && cb_khaibao_6.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_1.getText().toString().trim() + "," + cb_khaibao_2.getText().toString().trim() + "," + cb_khaibao_3.getText().toString().trim() + "," + cb_khaibao_4.getText().toString().trim() + "," + cb_khaibao_5.getText().toString().trim() + "," + cb_khaibao_6.getText().toString().trim();
                                } else if (cb_khaibao_1.isChecked() && cb_khaibao_2.isChecked() && cb_khaibao_3.isChecked() && cb_khaibao_4.isChecked() && cb_khaibao_5.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_1.getText().toString().trim() + "," + cb_khaibao_2.getText().toString().trim() + "," + cb_khaibao_3.getText().toString().trim() + "," + cb_khaibao_4.getText().toString().trim() + "," + cb_khaibao_5.getText().toString().trim();
                                } else if (cb_khaibao_1.isChecked() && cb_khaibao_2.isChecked() && cb_khaibao_3.isChecked() && cb_khaibao_4.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_1.getText().toString().trim() + "," + cb_khaibao_2.getText().toString().trim() + "," + cb_khaibao_3.getText().toString().trim() + "," + cb_khaibao_4.getText().toString().trim();
                                } else if (cb_khaibao_1.isChecked() && cb_khaibao_2.isChecked() && cb_khaibao_3.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_1.getText().toString().trim() + "," + cb_khaibao_2.getText().toString().trim() + "," + cb_khaibao_3.getText().toString().trim();
                                } else if (cb_khaibao_1.isChecked() && cb_khaibao_2.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_1.getText().toString().trim() + "," + cb_khaibao_2.getText().toString().trim();
                                } else if (cb_khaibao_1.isChecked() && cb_khaibao_3.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_1.getText().toString().trim() + "," + cb_khaibao_3.getText().toString().trim();
                                } else if (cb_khaibao_1.isChecked() && cb_khaibao_4.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_1.getText().toString().trim() + "," + cb_khaibao_4.getText().toString().trim();
                                } else if (cb_khaibao_1.isChecked() && cb_khaibao_5.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_1.getText().toString().trim() + "," + cb_khaibao_5.getText().toString().trim();
                                } else if (cb_khaibao_1.isChecked() && cb_khaibao_6.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_1.getText().toString().trim() + "," + cb_khaibao_6.getText().toString().trim();
                                } else if (cb_khaibao_1.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_1.getText().toString().trim();
                                }
                                //check tr?????ng h???p checkbox2
                                else if (cb_khaibao_2.isChecked() && cb_khaibao_3.isChecked() && cb_khaibao_4.isChecked() && cb_khaibao_5.isChecked() && cb_khaibao_6.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_2.getText().toString().trim() + "," + cb_khaibao_3.getText().toString().trim() + "," + cb_khaibao_4.getText().toString().trim() + "," + cb_khaibao_5.getText().toString().trim() + "," + cb_khaibao_6.getText().toString().trim();
                                } else if (cb_khaibao_2.isChecked() && cb_khaibao_3.isChecked() && cb_khaibao_4.isChecked() && cb_khaibao_5.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_2.getText().toString().trim() + "," + cb_khaibao_3.getText().toString().trim() + "," + cb_khaibao_4.getText().toString().trim() + "," + cb_khaibao_5.getText().toString().trim();
                                } else if (cb_khaibao_2.isChecked() && cb_khaibao_3.isChecked() && cb_khaibao_4.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_2.getText().toString().trim() + "," + cb_khaibao_3.getText().toString().trim() + "," + cb_khaibao_4.getText().toString().trim();
                                } else if (cb_khaibao_2.isChecked() && cb_khaibao_3.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_2.getText().toString().trim() + "," + cb_khaibao_3.getText().toString().trim();
                                } else if (cb_khaibao_2.isChecked() && cb_khaibao_4.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_2.getText().toString().trim() + "," + cb_khaibao_4.getText().toString().trim();
                                } else if (cb_khaibao_2.isChecked() && cb_khaibao_5.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_2.getText().toString().trim() + "," + cb_khaibao_5.getText().toString().trim();
                                } else if (cb_khaibao_2.isChecked() && cb_khaibao_6.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_2.getText().toString().trim() + "," + cb_khaibao_6.getText().toString().trim();
                                } else if (cb_khaibao_2.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_2.getText().toString().trim();
                                }
                                //check tr?????ng h???p checkbox3
                                else if (cb_khaibao_3.isChecked() && cb_khaibao_4.isChecked() && cb_khaibao_5.isChecked() && cb_khaibao_6.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_3.getText().toString().trim() + "," + cb_khaibao_4.getText().toString().trim() + "," + cb_khaibao_5.getText().toString().trim() + "," + cb_khaibao_6.getText().toString().trim();
                                } else if (cb_khaibao_3.isChecked() && cb_khaibao_4.isChecked() && cb_khaibao_5.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_3.getText().toString().trim() + "," + cb_khaibao_4.getText().toString().trim() + "," + cb_khaibao_5.getText().toString().trim();
                                } else if (cb_khaibao_3.isChecked() && cb_khaibao_4.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_3.getText().toString().trim() + "," + cb_khaibao_4.getText().toString().trim();
                                } else if (cb_khaibao_3.isChecked() && cb_khaibao_5.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_3.getText().toString().trim() + "," + cb_khaibao_5.getText().toString().trim();
                                } else if (cb_khaibao_3.isChecked() && cb_khaibao_6.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_3.getText().toString().trim() + "," + cb_khaibao_6.getText().toString().trim();
                                } else if (cb_khaibao_3.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_3.getText().toString().trim();
                                }
                                //check tr?????ng h???p checkbox4
                                else if (cb_khaibao_4.isChecked() && cb_khaibao_5.isChecked() && cb_khaibao_6.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_4.getText().toString().trim() + "," + cb_khaibao_5.getText().toString().trim() + "," + cb_khaibao_6.getText().toString().trim();
                                } else if (cb_khaibao_4.isChecked() && cb_khaibao_5.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_4.getText().toString().trim() + "," + cb_khaibao_5.getText().toString().trim();
                                } else if (cb_khaibao_4.isChecked() && cb_khaibao_6.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_4.getText().toString().trim() + "," + cb_khaibao_6.getText().toString().trim();
                                } else if (cb_khaibao_4.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_4.getText().toString().trim();
                                }
                                //check tr?????ng h???p checkbox5
                                else if (cb_khaibao_5.isChecked() && cb_khaibao_6.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_5.getText().toString().trim() + "," + cb_khaibao_6.getText().toString().trim();
                                } else if (cb_khaibao_5.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_5.getText().toString().trim();
                                }
                                //check tr?????ng h???p checkbox6
                                else if (cb_khaibao_6.isChecked()) {
                                    th3 = "Tr?????ng H???p 3 :" + cb_khaibao_6.getText().toString().trim();
                                }
                                //check tr?????ng h???p kh??ng click v??o checkbox n??o
                                else if (!cb_khaibao_1.isChecked() && !cb_khaibao_2.isChecked() && !cb_khaibao_3.isChecked() && !cb_khaibao_4.isChecked() && !cb_khaibao_5.isChecked() && !cb_khaibao_6.isChecked()) {
                                    th3 = "B??nh Th?????ng";
                                }
                                khaiBaoViewModel.getInsertKhaiBao().observe(KhaiBaoTuNguyenActivity.this, new Observer<String>() {
                                    @Override
                                    public void onChanged(String s) {

                                        if (s.equals("success")) {
                                            Toast.makeText(KhaiBaoTuNguyenActivity.this, "Khai B??o Th??nh C??ng", Toast.LENGTH_SHORT).show();
                                            reset();
                                            progressDialog.dismiss();

                                        } else {
                                            progressDialog.dismiss();
                                            reset();
                                            Toast.makeText(KhaiBaoTuNguyenActivity.this, "Khai B??o Kh??ng Th??nh C??ng", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                khaiBaoViewModel.iniInsertData(idMember, th1, th2, th3, th4 , thoigian);
                            }

                        }
                    });
                } else {
                    btn_guithongtin.setVisibility(View.GONE);
                    no_btn_guithongtin.setVisibility(View.VISIBLE);

                }
            }
        });
    }

    private void reset() {
        radio_khai_bao_2.clearFocus();
        radio_khai_bao_1.clearFocus();
        radio_khai_bao_3.clearFocus();
        edt_khai_bao_thong_tin.setText("");
        cb_khaibao_1.setChecked(false);
        cb_khaibao_2.setChecked(false);
        cb_khaibao_3.setChecked(false);
        cb_khaibao_4.setChecked(false);
        cb_khaibao_5.setChecked(false);
        cb_khaibao_6.setChecked(false);
        cb_2_khaibao_1.setChecked(false);
        cb_2_khaibao_2.setChecked(false);
        cb_2_khaibao_3.setChecked(false);
        cb_2_khaibao_4.setChecked(false);
        cb_2_khaibao_5.setChecked(false);
        cb_2_khaibao_6.setChecked(false);
        btn_guithongtin.setVisibility(View.GONE);
        no_btn_guithongtin.setVisibility(View.VISIBLE);
        cb_check_khaibao.setChecked(false);
    }


    private void closeKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onStart() {
        closeKeyBoard();
        super.onStart();
    }

    public void checkClick(View view) {
        int radioId_1 = rdo_group_khai_bao_1.getCheckedRadioButtonId();
        radio_khai_bao_1 = findViewById(radioId_1);
    }

    public void checkClick_1(View view) {
        int radioId_2 = rdo_group_khai_bao_2.getCheckedRadioButtonId();
        radio_khai_bao_2 = findViewById(radioId_2);
    }

    public void checkClick_2(View view) {
        int radioId_3 = rdo_group_khai_bao_3.getCheckedRadioButtonId();
        radio_khai_bao_3 = findViewById(radioId_3);
    }
}