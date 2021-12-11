package com.example.ncovi.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ncovi.Model.user;
import com.example.ncovi.R;
import com.example.ncovi.View.BroadcastReceiver.OTPBroadCast;
import com.example.ncovi.View.SharedPreference.DataManager;
import com.example.ncovi.ViewModel.Response.UserApplyViewModel;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    private Button btn_login, btn_register;
    private EditText edt_phone;
    private ProgressDialog progressDialog;
    String sdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniUI();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void iniUI() {
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        edt_phone = findViewById(R.id.txt_sdt_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sdt = edt_phone.getText().toString().trim();
                if (edt_phone.getText().toString().trim().isEmpty()) {

                    Toast.makeText(LoginActivity.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    return;

                } else if (sdt.length() > 11) {
                    Toast.makeText(LoginActivity.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                } else if (sdt.length() < 10) {
                    Toast.makeText(LoginActivity.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    progressDialog.show();
                    PhoneAuthProvider.getInstance().verifyPhoneNumber("+84" + edt_phone.getText().toString().trim()
                            , 60, TimeUnit.SECONDS
                            , LoginActivity.this
                            , new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    progressDialog.dismiss();
                                    Intent intent = new Intent(LoginActivity.this, OTPActivity.class);
                                    intent.putExtra("sdt", sdt);
                                    DataManager.savePhone(sdt);
                                    intent.putExtra("verification", s);
                                    startActivity(intent);
                                }

                            });

                }
            }
        });
    }
}