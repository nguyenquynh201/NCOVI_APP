package com.example.ncovi.View.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ncovi.Class.RealPathUtil;
import com.example.ncovi.Model.user;
import com.example.ncovi.R;
import com.example.ncovi.View.SharedPreference.DataManager;
import com.google.flatbuffers.Utf8;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import androidmads.library.qrgenearator.QRGEncoder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class NewQRCodeActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 123;
    private ImageView img_qr_code;
    private TextView txt_name_qr_code, tv_edit_address_qr_code, txt_tinh_qr_code, txt_huyen_qr_code, txt_xaphuong_qr_code, txt_diachi_qr_code, txt_sdt_qr_code;
    private TextView tv_name_qr_code, tv_address_qr, tv_sdt_qr_code;
    private Button btn_qr_code;
    private ProgressDialog progressDialog;
    String name , tinh , huyen , xa , diachi , sdt;
    user users;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    FileOutputStream outputStream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_qrcode);
        users = DataManager.loadUser();
        iniUI();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        ActivityCompat.requestPermissions(NewQRCodeActivity.this , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE} ,REQUEST_CODE);
        ActivityCompat.requestPermissions(NewQRCodeActivity.this , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE} ,REQUEST_CODE);
    }

    @SuppressLint("SetTextI18n")
    private void iniUI() {
        //ánh xạ textView
        txt_name_qr_code = findViewById(R.id.txt_name_qr_code);
        txt_tinh_qr_code = findViewById(R.id.txt_tinh_qr_code);
        txt_huyen_qr_code = findViewById(R.id.txt_huyen_qr_code);
        txt_xaphuong_qr_code = findViewById(R.id.txt_xaphuong_qr_code);
        txt_diachi_qr_code = findViewById(R.id.txt_diachi_qr_code);
        txt_sdt_qr_code = findViewById(R.id.txt_sdt_qr_code);
        //
        tv_name_qr_code = findViewById(R.id.tv_name_qr_code);
        tv_address_qr = findViewById(R.id.tv_address_qr);
        tv_sdt_qr_code = findViewById(R.id.tv_sdt_qr_code);
        tv_edit_address_qr_code = findViewById(R.id.tv_edit_address_qr_code);
        // set cứng text
        String st_name = "Họ và tên *";
        String st_address = "Địa chỉ hiện tại *";
        String ss_sdt = "Số điện thoại *";
        // tạo các spannable
        SpannableString ss_name = new SpannableString(st_name);
        SpannableString ss_address = new SpannableString(st_address);
        SpannableString ss_mSdt = new SpannableString(ss_sdt);
        // tạo màu
        ForegroundColorSpan fcs_name = new ForegroundColorSpan(Color.RED);
        UnderlineSpan line = new UnderlineSpan(); // tạo kiểu chữ
        // set thời điểm bắt đầu và kết thúc
        ss_name.setSpan(fcs_name, 10, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_address.setSpan(fcs_name, 17, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_mSdt.setSpan(fcs_name, 14, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // truyền giá trị của spannable vào text
        tv_name_qr_code.setText(ss_name);
        tv_address_qr.setText(ss_address);
        tv_sdt_qr_code.setText(ss_mSdt);
        // gán biến string vừa tạo ở trên bằng giá trị của đổi tượng user
        name = users.getName();
        tinh = users.getIdTinh();
        huyen = users.getIdHuyen();
        xa = users.getIdXa();
        sdt = users.getSdt();
        diachi = users.getDiachi();
        // load dữ liệu user lên các view
        txt_name_qr_code.setText(name);
        txt_tinh_qr_code.setText(tinh);
        txt_huyen_qr_code.setText(huyen);
        txt_xaphuong_qr_code.setText(xa);
        txt_diachi_qr_code.setText(diachi);
        txt_sdt_qr_code.setText(sdt);
        img_qr_code = findViewById(R.id.img_qr_code);
        btn_qr_code = findViewById(R.id.btn_qr_code);
        btn_qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiFormatWriter writer = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = writer.encode("Họ Tên :"+name +"\n"
                            +"Thành Phố/Tỉnh :"+tinh +"\n"
                            +"Quận/Huyện :"+huyen +"\n"
                            +"Xã/Phường :"+xa +"\n"
                            +"Địa Chỉ :"+diachi +"\n"
                            +"Số Điện Thoại :"+sdt , BarcodeFormat.QR_CODE , 750 ,750
                            );
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

                    bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    Dialog(Gravity.CENTER);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
        tv_edit_address_qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewQRCodeActivity.this , ThongTinCaNhanActivity.class);
                startActivity(intent);
            }
        });
    }
    public void Dialog(int gravity)
    {
        Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_qrcode);
        img_qr_code = dialog.findViewById(R.id.img_qr_code);
        img_qr_code.setImageBitmap(bitmap);
        Button btn_save = dialog.findViewById(R.id.btn_save);
        Button btn_dismiss = dialog.findViewById(R.id.btn_dismiss);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = gravity;
        window.setAttributes(attributes);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UploadQRCode();
//                progressDialog.show();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) img_qr_code.getDrawable();
                Bitmap bitmap1 = bitmapDrawable.getBitmap();
                File file = Environment.getExternalStorageDirectory();
                File dir = new File(file.getAbsolutePath() + "/Pictures/");
                dir.mkdirs();
                @SuppressLint("DefaultLocale") String filename =System.currentTimeMillis()+".png" ;
                File outFile = new File(dir,filename);
                try {
                    progressDialog.dismiss();
                    outputStream = new FileOutputStream(outFile);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                bitmap1.compress(Bitmap.CompressFormat.PNG , 100 , outputStream);
                Toast.makeText(NewQRCodeActivity.this, "Lưu Thành Công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                try {
                    outputStream.flush();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                try {
                    outputStream.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        btn_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}