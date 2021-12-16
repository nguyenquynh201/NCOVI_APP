package com.example.ncovi.View.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ncovi.R;


public class Child1FeedBackFragment extends Fragment {
    private CheckBox cb_feedback_1 , cb_feedback_2 , cb_feedback_3 , cb_feedback_ok;
    private Spinner spinner_TP , spinner_QH , spinner_PX;
    private TextView tv_spinner_TP , tv_spinner_QH , tv_spinner_PX , tv_datetime , tv_address_fb ,tv_time_fb , tv_content_fb ,tv_truonghop_fb ;
    private Button btn_ok , btn_no;
    private EditText edt_thongtin , edt_address;
    private ImageView img_date;

    View view;
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
        String strTruongHop  = "Chọn các trường hợp phản ánh *";
        String strNoidung = "Nội dung phản ánh *";
        String strTime = "Thời gian *";
        String strAddress = "Địa chỉ phản ánh *";
        // khởi tạo
        SpannableString ss_TruongHop = new SpannableString(strTruongHop);
        SpannableString ss_Time = new SpannableString(strTime);
        SpannableString ss_Address = new SpannableString(strAddress);
        SpannableString ss_Content = new SpannableString(strNoidung);
        // Tạo màu chữ
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        // gán vị trí hiện màu chữ
        ss_TruongHop.setSpan(colorSpan , 29 ,30 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_Time.setSpan(colorSpan , 10 ,11 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_Address.setSpan(colorSpan , 17,18 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_Content.setSpan(colorSpan , 18 ,19 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // gán màu vào các textview
        tv_truonghop_fb.setText(ss_TruongHop);
        tv_content_fb.setText(ss_Content);
        tv_time_fb.setText(ss_Time);
        tv_address_fb.setText(ss_Address);

    }
}