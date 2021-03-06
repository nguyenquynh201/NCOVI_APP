package com.example.ncovi.ViewModel.ApiService;

import com.example.ncovi.Model.TinhTrangSucKhoe;
import com.example.ncovi.Model.login;
import com.example.ncovi.Model.phananh;
import com.example.ncovi.Model.phuongxa;
import com.example.ncovi.Model.quanhuyen;
import com.example.ncovi.Model.thanhpho;
import com.example.ncovi.Model.user;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    // call dữ liệu thành phố thông qua hàm get
    @GET("thanhpho.php")
    Call<List<thanhpho>> getThanhPho();
    //call huyện theo thành phố
    @FormUrlEncoded
    @POST("huyen.php")
    Call<List<quanhuyen>> getQuanHuyen(@Field("matp") String matp);
    // call phường xã theo huyện
    @FormUrlEncoded
    @POST("phuongxa.php")
    Call<List<phuongxa>> getPhuongXa(@Field("maqh") String maqh);
    // đăng ký
    @FormUrlEncoded
    @POST("register.php")
    Call<user> LoadUser(@Field("name") String name,
                        @Field("sdt") String sdt ,
                        @Field("gioitinh") String gioitinh ,
                        @Field("cmnd") String cmnd,
                        @Field("ngaysinh") String ngaysinh,
                        @Field("idTinh") String idTinh,
                        @Field("idHuyen") String idHuyen,
                        @Field("idXa") String idXa ,
                        @Field("diachi") String diachi
                        );
    ///đăng nhập
    @FormUrlEncoded
    @POST("login.php")
    Call<login> loginUser(@Field("sdt") String sdt);
    // call list tình trạng sức khỏe
    @FormUrlEncoded
    @POST("showtinhtrang.php")
    Call<List<TinhTrangSucKhoe>> ListTinhTrang(@Field("idMember") String idMember);
    @FormUrlEncoded
    @POST("show_all_tinhtrang.php")
    Call<List<TinhTrangSucKhoe>> showListTinhTrang(@Field("idMember") String idMember);
    // call insert dữ liệu tình trạng sức khỏe
    @FormUrlEncoded
    @POST("tinhtrangsuckhoe.php")
    Call<List<TinhTrangSucKhoe>> AddListSucKhoe(@Field("idMember") String idMember ,
                                                @Field("tinhtrangsuckhoe") String tinhtrangsuckhoe,
                                                @Field("canhbao") String canhbao,
                                                @Field("ngay") String ngay,
                                                @Field("gio") String gio);

    //check sdt
    @FormUrlEncoded
    @POST("check_sdt.php")
    Call<String> CheckSdt(@Field("sdt") String sdt);

    /*update member*/
    @FormUrlEncoded
    @POST("updateMember.php")
    Call<login> updateMember(@Field("idMember") String idMember,
                             @Field("name") String name,
                             @Field("sdt") String sdt,
                             @Field("gioitinh") String gioitinh,
                             @Field("cmnd") String cmnd,
                             @Field("ngaysinh") String ngaysinh,
                             @Field("idTinh") String idTinh,
                             @Field("idHuyen") String idHuyeb,
                             @Field("idXa") String idXa,
                             @Field("diachi") String diachi,
                             @Field("email") String email);
    //insert phản ánh
    @FormUrlEncoded
    @POST("phananh.php")
    Call<phananh> insertFeedback(@Field("idMember") String idMember,
                                 @Field("truonghop1") String truonghop1,
                                 @Field("truonghop2") String truonghop2,
                                 @Field("truonghop3") String truonghop3,
                                 @Field("noidung") String noidung,
                                 @Field("thoigian") String thoigian,
                                 @Field("tinh") String tinh,
                                 @Field("huyen") String huyen,
                                 @Field("xa") String xa,
                                 @Field("diachi") String diachi
                                 );
    @Multipart
    @POST("uploadqrcode.php")
    Call<String> UploadQRCode(@Part MultipartBody.Part qrcode);
    @FormUrlEncoded
    @POST("khaibao.php")
    Call<String> InsertKhaiBao(@Field("idMember") String idMember ,
                               @Field("noidung1") String noidung1 ,
                               @Field("noidung2") String noidung2 ,
                               @Field("noidung3") String noidung3 ,
                               @Field("noidung4") String noidung4,
                               @Field("thoigian") String thoigian);
}
