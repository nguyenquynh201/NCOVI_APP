package com.example.ncovi.View.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ncovi.R;


public class Child2FeedBackFragment extends Fragment {
    View view;
    private CheckBox cb_child2_fb_1 , cb_child2_fb_2 , cb_child2_fb_3;
    private Button btn_khaibao_fb , btn_no_khaibao;
    private TextView tv_khaibao_fb;
    String str_Text;
    public Child2FeedBackFragment() {
        // Required empty public constructor
    }

    public static Child2FeedBackFragment newInstance(String param1, String param2) {
        Child2FeedBackFragment fragment = new Child2FeedBackFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_child2_feed_back, container, false);
        // hàm ánh xạ
        iniUI();
        // hàm set sự kiện
        getEvent();
        return view;
    }
    private void iniUI() {
        // ánh xạ các checkbox
        cb_child2_fb_1 = view.findViewById(R.id.cb_feedback_1_1);
        cb_child2_fb_2 = view.findViewById(R.id.cb_feedback_1_2);
        cb_child2_fb_3 = view.findViewById(R.id.cb_feedback_1_3);
        // ánh xạ các button
        btn_khaibao_fb = view.findViewById(R.id.btn_thongtin_feedback_1);
        btn_no_khaibao = view.findViewById(R.id.no_btn_thongtin_feedback_1);
        // ánh xạ textview
        tv_khaibao_fb = view.findViewById(R.id.tv_khaibao_fb);
        // gán cứng text cho biến string
        str_Text = "Trong 14 ngày qua , bạn hoặc người nhà *";
        // khởi tạo
        SpannableString ss_TruongHop = new SpannableString(str_Text);
        // khởi tạo màu
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        // set vị trí
        ss_TruongHop.setSpan(colorSpan , 39 , 40 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // truyền text vào textview
        tv_khaibao_fb.setText(ss_TruongHop);
    }
    private void getEvent() {

    }
}
