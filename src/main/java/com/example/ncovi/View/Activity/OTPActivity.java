package com.example.ncovi.View.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ncovi.Model.user;
import com.example.ncovi.R;
import com.example.ncovi.View.BroadcastReceiver.OTPBroadCast;
import com.example.ncovi.View.SharedPreference.DataManager;
import com.example.ncovi.ViewModel.Response.UserApplyViewModel;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OTPActivity extends AppCompatActivity {
    private EditText input_one, input_two, input_three, input_four, input_five, input_six;
    private TextView textView, tv_phone;
    private Button btn_checkOTP;
    private ProgressDialog progressDialog;
    private static final int REQ_USER = 200;
    private String phone_number;
    OTPBroadCast otpBroadCast;
    String verificationId;
    private UserApplyViewModel userApplyViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);
        iniUI();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
    }


    private void iniUI() {
        input_one = findViewById(R.id.input_1);
        input_two = findViewById(R.id.input_2);
        input_three = findViewById(R.id.input_3);
        input_four = findViewById(R.id.input_4);
        input_five = findViewById(R.id.input_5);
        input_six = findViewById(R.id.input_6);
        btn_checkOTP = findViewById(R.id.btn_otp);
        textView = findViewById(R.id.tv_Resend);
        tv_phone = findViewById(R.id.tv_phone);
        tv_phone.setText(getIntent().getStringExtra("sdt"));
        setDataInput();
        verificationId = getIntent().getStringExtra("verification");
        btn_checkOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String one = input_one.getText().toString().trim();
                String two = input_two.getText().toString().trim();
                String three = input_three.getText().toString().trim();
                String four = input_four.getText().toString().trim();
                String five = input_five.getText().toString().trim();
                String six = input_six.getText().toString().trim();
                if (one.isEmpty() || two.isEmpty() || three.isEmpty() || four.isEmpty() || five.isEmpty() || six.isEmpty()) {
                    Toast.makeText(OTPActivity.this, "Vui lòng không bỏ trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = one + two + three + four + five + six;
                if (verificationId != null) {
                    progressDialog.show();
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verificationId, code);
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        phone_number = tv_phone.getText().toString().trim();
                                        Intent intent = new Intent(getApplicationContext(), InformationActivity.class);
                                        intent.putExtra("sdt_name", phone_number);
//                                        DataManager.savePhone(phone_number);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(OTPActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }


            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+84" + getIntent().getStringExtra("sdt")
                        , 60, TimeUnit.SECONDS
                        , OTPActivity.this
                        , new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressDialog.dismiss();
                                Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String newS, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressDialog.dismiss();
                                verificationId = newS;
                                Toast.makeText(OTPActivity.this, "OTP Sent", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void setDataInput() {
        input_one.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input_two.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input_two.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input_three.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input_three.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input_four.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input_four.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input_five.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input_five.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input_six.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}