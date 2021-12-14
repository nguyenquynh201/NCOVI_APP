package com.example.ncovi.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ncovi.Model.login;
import com.example.ncovi.Model.phuongxa;
import com.example.ncovi.Model.quanhuyen;
import com.example.ncovi.Model.thanhpho;
import com.example.ncovi.Model.user;
import com.example.ncovi.R;
import com.example.ncovi.View.Adaptor.PhuongXaAdaptor;
import com.example.ncovi.View.Adaptor.QuanHuyenAdaptor;
import com.example.ncovi.View.Adaptor.ThanhPhoAdaptor;
import com.example.ncovi.View.Fragments.Edit_Thongtin_DialogFragments;
import com.example.ncovi.View.SharedPreference.DataManager;
import com.example.ncovi.ViewModel.Response.AddressViewModel;
import com.example.ncovi.ViewModel.Response.UserApplyViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThongTinCaNhanActivity extends AppCompatActivity {
    private ThanhPhoAdaptor thanhPhoAdaptor;
    private AddressViewModel addressViewModel;
    private QuanHuyenAdaptor quanHuyenAdaptor;
    private PhuongXaAdaptor phuongXaAdaptor;
    private List<com.example.ncovi.Model.thanhpho> mListTP;
    private List<com.example.ncovi.Model.quanhuyen> mListQH;
    private List<com.example.ncovi.Model.phuongxa> mListPX;
    private CheckBox checkBox;
    private TextView tv_sdt, tv_name, tv_date, tv_card, tv_address, tv_round, tv_spinner_TP, tv_spinner_QH, tv_spinner_PX , tv_mSdt;
    private TextView tv_tinh , tv_quanhuyen , tv_phuongxa , tv_edit;
    private EditText edt_date, edt_username, edt_address, edt_card, edt_email;
    private Spinner spinner_QH, spinner_PX, spinner_TP;
    private RadioButton rdo_all, rdo_man, rdo_woman;
    private RadioGroup rdoGroup;
    private UserApplyViewModel userApplyViewModel;
    user users;
    String phone_number;
    ProgressDialog progressDialog;
    private Button btn_check, btn_noCheck;
    String idmember, username, date, round, cmnd, thanhpho, quanhuyen, phuongxa, diachi, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);
        //load dữ liệu sdt từ sharedprefrence
        users = DataManager.loadUser();
        userApplyViewModel = new ViewModelProvider(this).get(UserApplyViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.setCanceledOnTouchOutside(false);
        iniUI();
        loadUser();

    }

    private void iniUI() {
        //ánh xạ checkbook
        checkBox = findViewById(R.id.cb_thongtin);

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
        tv_tinh  = findViewById(R.id.txt_tinh);
        tv_quanhuyen = findViewById(R.id.txt_huyen);
        tv_phuongxa = findViewById(R.id.txt_xaphuong);
        tv_edit = findViewById(R.id.tv_edit_address);
        tv_mSdt = findViewById(R.id.tv_sdt);
        //ánh xạ edittext
        edt_date = findViewById(R.id.txt_date);
        edt_username = findViewById(R.id.txt_name);
        edt_address = findViewById(R.id.txt_diachi);
        edt_card = findViewById(R.id.txt_cmnd);
        edt_email = findViewById(R.id.txt_email);
        //ánh xạ radiobutton
        rdoGroup = findViewById(R.id.rdo_group);
        rdo_man = findViewById(R.id.round_man);
        rdo_woman = findViewById(R.id.round_woman);
        // set cứng text
        String st_name = "Họ và tên *";
        String st_address = "Địa chỉ hiện tại *";
        String st_date = "Ngày tháng năm sinh *";
        String st_card = "Số CMT/CCCD/Hộ chiếu *";
        String st_round = "Giới tính *";
        String st_checkbox = "Tôi cam kết các thông tin khai là đúng sự thật và đồng ý với Điều khoản sử dụng";
        String ss_sdt = "Số điện thoại *";
        // tạo các spannable
        SpannableString ss_name = new SpannableString(st_name);
        SpannableString ss_address = new SpannableString(st_address);
        SpannableString ss_date = new SpannableString(st_date);
        SpannableString ss_card = new SpannableString(st_card);
        SpannableString ss_round = new SpannableString(st_round);
        SpannableString ss_checkbox = new SpannableString(st_checkbox);
        SpannableString ss_mSdt = new SpannableString(ss_sdt);
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
        ss_mSdt.setSpan(fcs_name, 14, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // truyền giá trị của spannable vào text
        tv_name.setText(ss_name);
        tv_address.setText(ss_address);
        tv_date.setText(ss_date);
        tv_card.setText(ss_card);
        tv_round.setText(ss_round);
        checkBox.setText(ss_checkbox);
        tv_mSdt.setText(ss_mSdt);

        // tạo định dạng cho date khi nhập giá  trị vào
        edt_date.addTextChangedListener(new TextWatcher() {

            public String current = "";
            public String ddmmyyyy = "DDMMYYYY";
            public Calendar cal = Calendar.getInstance();

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
        /*khi click vài checkbox thì btn cập  nhật hiện lên*/
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    int radioId = rdoGroup.getCheckedRadioButtonId();
                    rdo_all = findViewById(radioId);
                    round = (String) rdo_all.getText();
                    btn_check.setVisibility(View.VISIBLE);
                    btn_noCheck.setVisibility(View.GONE);
                    /* click update member */
                    btn_check.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // tạo biến kiểu String và gán giá trị
                            username = edt_username.getText().toString().trim();
                            date = edt_date.getText().toString().trim();
                            cmnd = edt_card.getText().toString().trim();
                            thanhpho = tv_tinh.getText().toString().trim();
                            quanhuyen = tv_quanhuyen.getText().toString().trim();
                            phuongxa = tv_phuongxa.getText().toString().trim();
                            diachi = edt_address.getText().toString().trim();
                            progressDialog.show();
                            if (username.isEmpty()
                                    || date.isEmpty()
                                    || rdo_all.getText().toString().trim().isEmpty()
                                    || thanhpho.isEmpty()
                                    || quanhuyen.isEmpty()
                                    || phuongxa.isEmpty()
                                    || diachi.isEmpty()
                                    || cmnd.isEmpty()) {
                                Toast.makeText(ThongTinCaNhanActivity.this, "Vui lòng nhập không bỏ trống thông tin cần thiết", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            } else {
                                userApplyViewModel.getUpDateMember().observe(ThongTinCaNhanActivity.this, new Observer<login>() {
                                    @Override
                                    public void onChanged(login check) {
                                        if (check.getSuccess().equals("200")) {
                                            progressDialog.dismiss();
                                            DataManager.saveUserName(check.getUser());
                                            Toast.makeText(ThongTinCaNhanActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                                            loadUser();
                                            checkBox.setChecked(false);
                                            btn_check.setVisibility(View.GONE);
                                        }
                                    }
                                });
                                Success();
                            }
                        }
                    });
                } else {
                    btn_check.setVisibility(View.GONE);
                    btn_noCheck.setVisibility(View.VISIBLE);
                }
            }
        });
        /*click sửa tỉnh quyện huyện thông qua textview*/
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = Edit_Thongtin_DialogFragments.newInstance();
                ((Edit_Thongtin_DialogFragments) dialogFragment).setCallBack(new Edit_Thongtin_DialogFragments.callBack() {
                    @Override
                    public void callback(String tinh, String quanhuyen, String xa) {
                        tv_tinh.setText(tinh);
                        tv_quanhuyen.setText(quanhuyen);
                        tv_phuongxa.setText(xa);
                    }
                });
                dialogFragment.show(getSupportFragmentManager() , "tag");
                dialogFragment.setCancelable(false);
            }
        });
    }

    // update dữ liệu lên service
    private void Success() {
        userApplyViewModel.iniDataUpdate(users.getIdMember(), edt_username.getText().toString().trim(),
                phone_number,
                rdo_all.getText().toString().trim(),
                edt_card.getText().toString().trim(),
                edt_date.getText().toString().trim(),
                tv_tinh.getText().toString().trim(),
                tv_quanhuyen.getText().toString().trim(),
                tv_phuongxa.getText().toString().trim(),
                edt_address.getText().toString().trim(),
                edt_email.getText().toString().trim());
    }

    private void loadUser() {
        //load dữ liệu sdt từ sharedprefrence
        users = DataManager.loadUser();
        // gán các biến string bằng dữ liệu user đã đươc lưu
        username = users.getName();
        date = users.getNgaysinh();
        round = users.getGioitinh();
        cmnd = users.getCmnd();
        thanhpho = users.getIdTinh();
        quanhuyen = users.getIdHuyen();
        phuongxa = users.getIdXa();
        diachi = users.getDiachi();
        phone_number = users.getSdt();
        email = users.getEmail();
        // truyền dữ liệu từ các biến string đổ ra edittext
        edt_username.setText(username);
        edt_date.setText(date);
        edt_address.setText(diachi);
        tv_tinh.setText(thanhpho);
        tv_quanhuyen.setText(quanhuyen);
        tv_phuongxa.setText(phuongxa);
        edt_card.setText(cmnd);
        tv_sdt.setText(phone_number);
        idmember = users.getIdMember();
        // check dữ liệu để đổ dữ liệu hiển thị lên checkbox
        if (round.equals("Name")) {
            rdo_man.setChecked(true);
            rdo_woman.setChecked(false);
        } else if (round.equals("Nữ")) {
            rdo_man.setChecked(false);
            rdo_woman.setChecked(true);
        }
        // check email nếu null thì gắn giá trị cho edittext
        if (users.getEmail().equals("")) {
            edt_email.setText("Nhập Email");
        }
        edt_email.setText(email);
    }
    public void check(View view) {
        int radioId = rdoGroup.getCheckedRadioButtonId();
        rdo_all = findViewById(radioId);
    }

    // tạo dialog thông báo cho người dùng
    public void Dialog_Notification(int gravity) {
        Dialog dialog_notification = new Dialog(this);
        dialog_notification.setCanceledOnTouchOutside(false);
        dialog_notification.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_notification.setContentView(R.layout.custom_dialog_thongtin);

        getDataTinh();
        // ánh xạ textview của dialog
        tv_spinner_TP = dialog_notification.findViewById(R.id.tv_spinnerTP);
        tv_spinner_QH = dialog_notification.findViewById(R.id.tv_spinnerQH);
        tv_spinner_PX = dialog_notification.findViewById(R.id.tv_spinnerPX);
        //ánh xạ spinner
        spinner_QH = dialog_notification.findViewById(R.id.spinner_QH);
        spinner_PX = dialog_notification.findViewById(R.id.spinner_PX);
        spinner_TP = dialog_notification.findViewById(R.id.spinner_TP);
        Button btn_dongy = dialog_notification.findViewById(R.id.btn_ok);
        Button btn_clear = dialog_notification.findViewById(R.id.btn_clear);
        Window window = dialog_notification.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = gravity;
        window.setAttributes(attributes);
        btn_dongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_notification.dismiss();

            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_notification.dismiss();
            }
        });
        dialog_notification.show();

    }
    // load danh sách tỉnh
    private void getDataTinh() {
        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        addressViewModel.getListThanhPho().observe(this, new Observer<List<thanhpho>>() {
            @Override
            public void onChanged(List<thanhpho> listTP) {
                mListTP = listTP;
                if (mListTP != null) {
                    thanhPhoAdaptor = new ThanhPhoAdaptor(ThongTinCaNhanActivity.this, R.layout.item_thanhpho, mListTP);
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

    // load danh sách quận huyện
    private void loadDataQH(String getId) {
        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        addressViewModel.getListQuanHuyen().observe(this, new Observer<List<quanhuyen>>() {
            @Override
            public void onChanged(List<quanhuyen> quanhuyens) {
                mListQH = quanhuyens;
                if (mListQH != null) {
                    quanHuyenAdaptor = new QuanHuyenAdaptor(ThongTinCaNhanActivity.this, R.layout.item_quanhuyen, mListQH);
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

    // load danh sách phường xã
    private void loadDataPX(String getIPPX) {
        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        addressViewModel.getListPhuongXa().observe(this, new Observer<List<phuongxa>>() {
            @Override
            public void onChanged(List<phuongxa> phuongxas) {
                mListPX = phuongxas;
                if (mListPX != null) {
                    phuongXaAdaptor = new PhuongXaAdaptor(ThongTinCaNhanActivity.this, R.layout.item_phuongxa, mListPX);
                    phuongXaAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_PX.setAdapter(phuongXaAdaptor);

                }
            }
        });
        addressViewModel.iniDataPhuongXa(getIPPX);
    }

}