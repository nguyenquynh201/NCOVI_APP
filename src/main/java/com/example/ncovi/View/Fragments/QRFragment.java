package com.example.ncovi.View.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ncovi.Class.Capture;
import com.example.ncovi.Model.user;
import com.example.ncovi.R;
import com.example.ncovi.View.SharedPreference.DataManager;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class QRFragment extends Fragment {
private Button btn_qr;
private TextView tv_name , tv_time , tv_history , tv_danhsach;
private user users;
private Date date;
private String strDate;
View mView;
    public QRFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_qrcode, container, false);
        iniUI();
        return mView;
    }

    private void iniUI() {
        users = DataManager.loadUser();
        // ánh xạ button
        btn_qr = mView.findViewById(R.id.btn_qr);
        // ánh xạ textview
        tv_name = mView.findViewById(R.id.username_qr);
        tv_time = mView.findViewById(R.id.time_qr);
        tv_history = mView.findViewById(R.id.history_qr);
        tv_danhsach = mView.findViewById(R.id.danhsach_qr);
        // gán dữ liệu cứng
        String strHistory = "Lịch sử quét mã QR";
        String strListQR = "Danh sách QR Code";
        // khai báo spannable
        SpannableString ss_history = new SpannableString(strHistory);
        SpannableString ss_listQR = new SpannableString(strListQR);
        // khai báo khiểu text
        UnderlineSpan underlineSpan = new UnderlineSpan();
        // sét giá trị bắt đầu và giá trị kết thúc
        ss_history.setSpan(underlineSpan , 0 , 18 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_listQR.setSpan(underlineSpan , 0 , 17 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //truyền dữ liệu vừa sét vào textview
        tv_history.setText(ss_history);
        tv_danhsach.setText(ss_listQR);
        //
        date = Calendar.getInstance().getTime();
        // tạo format cho ngày
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        // gán dư liệu vừa tạo vào kiểu string
        strDate = dateFormat.format(date);
        // đẩy dữ liệu vào textview
        tv_time.setText(strDate);
        tv_name.setText(users.getName());
        btn_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(
                        getActivity()
                );
                intentIntegrator.setPrompt("Helllo");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode ,resultCode ,data);
        if(intentResult.getContents() !=null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(
              getActivity()
            );
            builder.setTitle("Result")
                    .setMessage(intentResult.getContents())
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();

        }else {
            Toast.makeText(getActivity(), "OOPS...", Toast.LENGTH_SHORT).show();
        }
    }
}