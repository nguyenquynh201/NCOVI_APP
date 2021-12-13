package com.example.ncovi.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ncovi.Model.phuongxa;
import com.example.ncovi.Model.quanhuyen;
import com.example.ncovi.Model.thanhpho;
import com.example.ncovi.Model.user;
import com.example.ncovi.R;
import com.example.ncovi.View.Adaptor.PhuongXaAdaptor;
import com.example.ncovi.View.Adaptor.QuanHuyenAdaptor;
import com.example.ncovi.View.Adaptor.ThanhPhoAdaptor;
import com.example.ncovi.View.SharedPreference.DataManager;
import com.example.ncovi.ViewModel.Response.AddressViewModel;
import com.example.ncovi.ViewModel.Response.UserApplyViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InformationActivity extends AppCompatActivity {

    private ThanhPhoAdaptor thanhPhoAdaptor;
    private AddressViewModel addressViewModel;
    private QuanHuyenAdaptor quanHuyenAdaptor;
    private PhuongXaAdaptor phuongXaAdaptor;
    private List<thanhpho> mListTP;
    private List<quanhuyen> mListQH;
    private List<phuongxa> mListPX;
    private CheckBox checkBox;
    private TextView tv_sdt, tv_name, tv_date, tv_card, tv_address, tv_round, tv_spinner_TP, tv_spinner_QH, tv_spinner_PX;
    private EditText edt_date, edt_username, edt_address, edt_card;
    private Spinner spinner_QH, spinner_PX, spinner_TP;
    private RadioButton rdo_all;
    private RadioGroup rdoGroup;
    private UserApplyViewModel userApplyViewModel;
    user users;
    String phone_number;
    ProgressDialog progressDialog;
    private Button btn_check, btn_noCheck;
    String username, date, round, cmnd, thanhpho, quanhuyen, phuongxa, diachi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        phone_number = DataManager.loadSDT();
        iniUI();
        getDataTinh();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void iniDataUser() {

    }

    private void getDataTinh() {
        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        addressViewModel.getListThanhPho().observe(this, new Observer<List<thanhpho>>() {
            @Override
            public void onChanged(List<thanhpho> listTP) {
                mListTP = listTP;
                if (mListTP != null) {
                    thanhPhoAdaptor = new ThanhPhoAdaptor(InformationActivity.this, R.layout.item_thanhpho, mListTP);
                    thanhPhoAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_TP.setAdapter(thanhPhoAdaptor);

                    spinner_TP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String getId = mListTP.get(position).getMatp();
                            String name = mListTP.get(position).getName();
                            tv_spinner_TP.setText(name);
                            loadDataQH(getId);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

            }
        });
        addressViewModel.iniData();
    }

    private void iniUI() {
        //ánh xạ checkbook
        checkBox = findViewById(R.id.cb_thongtin);
        //ánh xạ spinner
        spinner_QH = findViewById(R.id.spinner_QH);
        spinner_PX = findViewById(R.id.spinner_PX);
        spinner_TP = findViewById(R.id.spinner_TP);
        //ánh xạ button
        btn_check = findViewById(R.id.btn_thongtin);
        btn_noCheck = findViewById(R.id.no_btn_thongtin);
        // ánh xạ textview
        tv_sdt = findViewById(R.id.txt_sdt);
        tv_address = findViewById(R.id.tv_address);
        tv_card = findViewById(R.id.tv_card);
        tv_date = findViewById(R.id.tv_date);
        tv_name = findViewById(R.id.tv_name);
        tv_round = findViewById(R.id.tv_round);
        tv_spinner_TP = findViewById(R.id.tv_spinnerTP);
        tv_spinner_QH = findViewById(R.id.tv_spinnerQH);
        tv_spinner_PX = findViewById(R.id.tv_spinnerPX);
        //load dữ liệu sdt từ sharedprefrence
        tv_sdt.setText(phone_number);
        //ánh xạ edittext
        edt_date = findViewById(R.id.txt_date);
        edt_username = findViewById(R.id.txt_name);
        edt_address = findViewById(R.id.txt_diachi);
        edt_card = findViewById(R.id.txt_cmnd);
        //ánh xạ radiobutton
        rdoGroup = findViewById(R.id.rdo_group);

        // set cứng text
        String st_name = "Họ và tên *";
        String st_address = "Địa chỉ hiện tại *";
        String st_date = "Ngày tháng năm sinh *";
        String st_card = "Số CMT/CCCD/Hộ chiếu *";
        String st_round = "Giới tính *";
        String st_checkbox = "Tôi cam kết các thông tin khai là đúng sự thật và đồng ý với Điều khoản sử dụng";
        // tạo các spannable
        SpannableString ss_name = new SpannableString(st_name);
        SpannableString ss_address = new SpannableString(st_address);
        SpannableString ss_date = new SpannableString(st_date);
        SpannableString ss_card = new SpannableString(st_card);
        SpannableString ss_round = new SpannableString(st_round);
        SpannableString ss_checkbox = new SpannableString(st_checkbox);
        // tạo màu
        ForegroundColorSpan fcs_name = new ForegroundColorSpan(Color.RED);
        UnderlineSpan line = new UnderlineSpan(); // tạo kiểu chữ
        // set thời điểm bắt đầu và kết thúc
        ss_name.setSpan(fcs_name, 10, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_address.setSpan(fcs_name, 17, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_date.setSpan(fcs_name, 20, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_card.setSpan(fcs_name, 21, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_round.setSpan(fcs_name, 10, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_checkbox.setSpan(fcs_name, 61, 79, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_checkbox.setSpan(line, 61, 79, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // truyền giá trị của spannable vào text
        tv_name.setText(ss_name);
        tv_address.setText(ss_address);
        tv_date.setText(ss_date);
        tv_card.setText(ss_card);
        tv_round.setText(ss_round);
        checkBox.setText(ss_checkbox);


        // tạo định dạng cho date khi nhập giá  trị vào
        edt_date.addTextChangedListener(new TextWatcher() {

            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

            @SuppressLint("DefaultLocale")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8) {
                        clean = clean + ddmmyyyy.substring(clean.length());
                    } else {
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day = Integer.parseInt(clean.substring(0, 2));
                        int mon = Integer.parseInt(clean.substring(2, 4));
                        int year = Integer.parseInt(clean.substring(4, 8));

                        if (mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon - 1);

                        year = (year < 1900) ? 1900 : (year > 2100) ? 2100 : year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE)) ? cal.getActualMaximum(Calendar.DATE) : day;
                        clean = String.format("%02d%02d%02d", day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    edt_date.setText(current);
                    edt_date.setSelection(sel < current.length() ? sel : current.length());

                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    int radioId = rdoGroup.getCheckedRadioButtonId();
                    rdo_all = findViewById(radioId);
                    round = (String) rdo_all.getText();
                    // tạo biến kiểu String và gán giá trị
                    username = edt_username.getText().toString().trim();
                    date = edt_date.getText().toString().trim();
                    cmnd = edt_card.getText().toString().trim();
                    thanhpho = tv_spinner_TP.getText().toString().trim();
                    quanhuyen = tv_spinner_QH.getText().toString().trim();
                    phuongxa = tv_spinner_PX.getText().toString().trim();
                    diachi = edt_address.getText().toString().trim();
                    btn_check.setVisibility(View.VISIBLE);
                    btn_noCheck.setVisibility(View.GONE);
                    btn_check.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressDialog.show();
                            userApplyViewModel = new ViewModelProvider(InformationActivity.this).get(UserApplyViewModel.class);
                            userApplyViewModel.getListUser().observe(InformationActivity.this, new Observer<com.example.ncovi.Model.user>() {
                                @Override
                                public void onChanged(com.example.ncovi.Model.user user) {
                                    users = user;
                                    if(user!=null)
                                    {
                                        if (edt_username.getText().toString().trim().isEmpty()
                                                || edt_date.getText().toString().trim().isEmpty()
                                                || round.isEmpty()
                                                || rdo_all.getText().toString().trim().isEmpty()
                                                || tv_spinner_TP.getText().toString().trim().isEmpty()
                                                || tv_spinner_QH.getText().toString().trim().isEmpty()
                                                || tv_spinner_PX.getText().toString().trim().isEmpty()
                                                || edt_address.getText().toString().trim().isEmpty()) {
                                            progressDialog.dismiss();
                                            Toast.makeText(InformationActivity.this, "Vui lòng nhập không bỏ trống thông tin", Toast.LENGTH_SHORT).show();
                                        } else  {
                                            progressDialog.dismiss();
                                            DataManager.saveUserName(users);
                                            Toast.makeText(InformationActivity.this, "Đăng Ký thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(InformationActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            Log.e("AAAA", String.valueOf(user));
                                        }
                                    }

                                }
                            });
                            userApplyViewModel.iniData(edt_username.getText().toString().trim(),
                                    phone_number,
                                    rdo_all.getText().toString().trim(),
                                    edt_card.getText().toString().trim(),
                                    edt_date.getText().toString().trim(),
                                    tv_spinner_TP.getText().toString().trim(),
                                    tv_spinner_QH.getText().toString().trim(),
                                    tv_spinner_PX.getText().toString().trim(),
                                    edt_address.getText().toString().trim());


                        }
                    });
                } else {
                    btn_check.setVisibility(View.GONE);
                    btn_noCheck.setVisibility(View.VISIBLE);

                }
            }
        });

    }

    private void loadDataQH(String getId) {
        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        addressViewModel.getListQuanHuyen().observe(this, new Observer<List<quanhuyen>>() {
            @Override
            public void onChanged(List<quanhuyen> quanhuyens) {
                mListQH = quanhuyens;
                if (mListQH != null) {
                    quanHuyenAdaptor = new QuanHuyenAdaptor(InformationActivity.this, R.layout.item_quanhuyen, mListQH);
                    quanHuyenAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_QH.setAdapter(quanHuyenAdaptor);
                    spinner_QH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String getId = mListQH.get(position).getMaqh();
                            String name = mListQH.get(position).getName();
                            tv_spinner_QH.setText(name);
                            loadDataPX(getId);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
        });
        addressViewModel.iniDataQuanHuyen(getId);
    }

    private void loadDataPX(String getIPPX) {
        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        addressViewModel.getListPhuongXa().observe(this, new Observer<List<phuongxa>>() {
            @Override
            public void onChanged(List<phuongxa> phuongxas) {
                mListPX = phuongxas;
                if (mListPX != null) {
                    phuongXaAdaptor = new PhuongXaAdaptor(InformationActivity.this, R.layout.item_phuongxa, mListPX);
                    phuongXaAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_PX.setAdapter(phuongXaAdaptor);
                    spinner_PX.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String name = mListPX.get(position).getName();
                            tv_spinner_PX.setText(name);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
        });
        addressViewModel.iniDataPhuongXa(getIPPX);
    }

    //click checkbox
    public void check(View view) {
        int radioId = rdoGroup.getCheckedRadioButtonId();
        rdo_all = findViewById(radioId);
    }
}
