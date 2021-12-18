package com.example.ncovi.View.Fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ncovi.Model.phananh;
import com.example.ncovi.Model.phuongxa;
import com.example.ncovi.Model.quanhuyen;
import com.example.ncovi.Model.thanhpho;
import com.example.ncovi.Model.user;
import com.example.ncovi.R;
import com.example.ncovi.View.Activity.InformationActivity;
import com.example.ncovi.View.Adaptor.PhuongXaAdaptor;
import com.example.ncovi.View.Adaptor.QuanHuyenAdaptor;
import com.example.ncovi.View.Adaptor.ThanhPhoAdaptor;
import com.example.ncovi.View.SharedPreference.DataManager;
import com.example.ncovi.ViewModel.Response.AddressViewModel;
import com.example.ncovi.ViewModel.Response.FeedbackViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Child1FeedBackFragment extends Fragment {
    private CheckBox cb_feedback_1, cb_feedback_2, cb_feedback_3, cb_feedback_ok;
    private Spinner spinner_TP, spinner_QH, spinner_PX;
    private TextView tv_spinner_TP, tv_spinner_QH, tv_spinner_PX, tv_datetime, tv_address_fb, tv_time_fb, tv_content_fb, tv_truonghop_fb;
    private Button btn_ok, btn_no;
    private EditText edt_thongtin, edt_address;
    private ImageView img_date;
    private FeedbackViewModel feedbackViewModel;
    View view;
    private ThanhPhoAdaptor thanhPhoAdaptor;
    private AddressViewModel addressViewModel;
    private QuanHuyenAdaptor quanHuyenAdaptor;
    private PhuongXaAdaptor phuongXaAdaptor;
    private List<thanhpho> mListTP;
    private List<quanhuyen> mListQH;
    private List<phuongxa> mListPX;
    private String idMember, th1, th2, th3, noidung, thoigian, tinh, huyen, xa, diachi;
    private user users;
    private ProgressDialog progressDialog;

    public Child1FeedBackFragment() {
        // Required empty public constructor
    }

    public static Child1FeedBackFragment newInstance(String param1, String param2) {
        Child1FeedBackFragment fragment = new Child1FeedBackFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_child1_feed_back, container, false);
        iniUI();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        feedbackViewModel = new ViewModelProvider(this).get(FeedbackViewModel.class);
        iniUI();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("load....");
        progressDialog.setCancelable(false);
        users = DataManager.loadUser();
        getDataTinh();

    }

    @SuppressLint("SetTextI18n")
    private void iniUI() {
        // ánh xạ textview
        tv_datetime = view.findViewById(R.id.tv_time_feedback);
        tv_spinner_PX = view.findViewById(R.id.tv_spinnerPX_feedback);
        tv_spinner_QH = view.findViewById(R.id.tv_spinnerQH_feedback);
        tv_spinner_TP = view.findViewById(R.id.tv_spinnerTP_feedback);
        tv_address_fb = view.findViewById(R.id.tv_address_feedback);
        tv_time_fb = view.findViewById(R.id.tv_time_fb);
        tv_content_fb = view.findViewById(R.id.content_fb);
        tv_truonghop_fb = view.findViewById(R.id.tv_truonghop_fb);
        //ánh xạ editext
        edt_thongtin = view.findViewById(R.id.edt_feedback);
        edt_address = view.findViewById(R.id.edt_diachi);
        // ánh xạ Spinner
        spinner_TP = view.findViewById(R.id.spinner_TP_feedback);
        spinner_QH = view.findViewById(R.id.spinner_QH_feedback);
        spinner_PX = view.findViewById(R.id.spinner_PX_feedback);
        // ánh xạ Button
        btn_ok = view.findViewById(R.id.btn_thongtin_feedback);
        btn_no = view.findViewById(R.id.no_btn_thongtin_feedback);
        // ánh xạ imageview
        img_date = view.findViewById(R.id.img_feedback);
        // khởi tạo các kiến kiểu string
        String strTruongHop = "Chọn các trường hợp phản ánh *";
        String strNoidung = "Nội dung phản ánh *";
        String strTime = "Thời gian *";
        String strAddress = "Địa chỉ phản ánh *";
        tv_datetime.setText("Chọn ngày giờ");
        // khởi tạo
        SpannableString ss_TruongHop = new SpannableString(strTruongHop);
        SpannableString ss_Time = new SpannableString(strTime);
        SpannableString ss_Address = new SpannableString(strAddress);
        SpannableString ss_Content = new SpannableString(strNoidung);
        // Tạo màu chữ
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        // gán vị trí hiện màu chữ
        ss_TruongHop.setSpan(colorSpan, 29, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_Time.setSpan(colorSpan, 10, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_Address.setSpan(colorSpan, 17, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_Content.setSpan(colorSpan, 18, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // gán màu vào các textview
        tv_truonghop_fb.setText(ss_TruongHop);
        tv_content_fb.setText(ss_Content);
        tv_time_fb.setText(ss_Time);
        tv_address_fb.setText(ss_Address);
        // ánh xạ checkbox
        cb_feedback_1 = view.findViewById(R.id.cb_feedback_1);
        cb_feedback_2 = view.findViewById(R.id.cb_feedback_2);
        cb_feedback_3 = view.findViewById(R.id.cb_feedback_3);
        cb_feedback_ok = view.findViewById(R.id.cb_thongtin_feedback);
        //
        img_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener dialog = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);
                                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
                                tv_datetime.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        };
                        new TimePickerDialog(getActivity(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
                    }
                };
                new DatePickerDialog(getActivity(), dialog, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });
        cb_feedback_ok.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb_feedback_ok.isChecked()) {
                    btn_no.setVisibility(View.GONE);
                    btn_ok.setVisibility(View.VISIBLE);
                    btn_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressDialog.show();
                            idMember = users.getIdMember();
                            th1 = String.valueOf(cb_feedback_1.isChecked());
                            th2 = String.valueOf(cb_feedback_2.isChecked());
                            th3 = String.valueOf(cb_feedback_3.isChecked());
                            noidung = edt_thongtin.getText().toString().trim();
                            tinh = tv_spinner_TP.getText().toString().trim();
                            huyen = tv_spinner_QH.getText().toString().trim();
                            xa = tv_spinner_PX.getText().toString().trim();
                            diachi = edt_address.getText().toString().trim();
                            thoigian = tv_datetime.getText().toString().trim();
                            if (!cb_feedback_1.isChecked() && !cb_feedback_2.isChecked() && !cb_feedback_3.isChecked()) {
                                Toast.makeText(getActivity(), "Vui lòng chọn nội dung", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                            if (noidung.isEmpty() || tinh.isEmpty() || huyen.isEmpty() || xa.isEmpty() || diachi.isEmpty() || thoigian.isEmpty()) {
                                Toast.makeText(getActivity(), "Vui lòng chọn nội dung", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            } else {
                                feedbackViewModel.getFeedback().observe(getViewLifecycleOwner(), new Observer<phananh>() {
                                    @Override
                                    public void onChanged(phananh phananh) {
                                        if (phananh!=null) {
                                            if (phananh.getSuccess().equals("200")) {
                                                Toast.makeText(getActivity(), "Phản Hồi Thành Công", Toast.LENGTH_SHORT).show();
                                                reset();
                                                progressDialog.dismiss();
                                            } else {
                                                Toast.makeText(getActivity(), "Phản Hồi Thất Bại", Toast.LENGTH_SHORT).show();
                                                progressDialog.dismiss();
                                            }
                                        }
                                    }
                                });
                                feedbackViewModel.iniInsertFeedback(idMember, th1, th2, th3, noidung, thoigian, tinh, huyen, xa, diachi);
                            }
                        }
                    });
                } else {
                    btn_no.setVisibility(View.VISIBLE);
                    btn_ok.setVisibility(View.GONE);
                }
            }
        });

    }

    private void reset() {
        tv_address_fb.setText("");
        tv_content_fb.setText("");
        edt_thongtin.setText("");
        tv_spinner_QH.setText("");
        tv_spinner_TP.setText("");
        tv_spinner_PX.setText("");
        edt_address.setText("");
        tv_datetime.setText("Chọn ngày giờ");
        cb_feedback_1.setChecked(false);
        cb_feedback_2.setChecked(false);
        cb_feedback_3.setChecked(false);
        cb_feedback_ok.setChecked(false);
        btn_ok.setVisibility(View.GONE);
    }

    private void insertData() {
        feedbackViewModel.getFeedback().observe(getViewLifecycleOwner(), new Observer<phananh>() {
            @Override
            public void onChanged(phananh phananh) {
                if (phananh.getMessage().equals("success")) {
                    Toast.makeText(getActivity(), "Phản Hồi Thành Công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Phản Hồi Thất Bại", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void getDataTinh() {
        addressViewModel.getListThanhPho().observe(getViewLifecycleOwner(), new Observer<List<thanhpho>>() {
            @Override
            public void onChanged(List<thanhpho> listTP) {
                mListTP = listTP;
                if (mListTP != null) {
                    thanhPhoAdaptor = new ThanhPhoAdaptor(getContext(), R.layout.item_thanhpho, mListTP);
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

    private void loadDataQH(String getId) {
        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        addressViewModel.getListQuanHuyen().observe(this, new Observer<List<quanhuyen>>() {
            @Override
            public void onChanged(List<quanhuyen> quanhuyens) {
                mListQH = quanhuyens;
                if (mListQH != null) {
                    quanHuyenAdaptor = new QuanHuyenAdaptor(getContext(), R.layout.item_quanhuyen, mListQH);
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
                    phuongXaAdaptor = new PhuongXaAdaptor(getContext(), R.layout.item_phuongxa, mListPX);
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
}