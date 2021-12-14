package com.example.ncovi.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ncovi.Model.phuongxa;
import com.example.ncovi.Model.quanhuyen;
import com.example.ncovi.Model.thanhpho;
import com.example.ncovi.R;
import com.example.ncovi.View.Activity.ThongTinCaNhanActivity;
import com.example.ncovi.View.Adaptor.PhuongXaAdaptor;
import com.example.ncovi.View.Adaptor.QuanHuyenAdaptor;
import com.example.ncovi.View.Adaptor.ThanhPhoAdaptor;
import com.example.ncovi.ViewModel.Response.AddressViewModel;

import java.util.List;

public class Edit_Thongtin_DialogFragments extends DialogFragment implements View.OnClickListener{
    private ThanhPhoAdaptor thanhPhoAdaptor;
    private AddressViewModel addressViewModel;
    private QuanHuyenAdaptor quanHuyenAdaptor;
    private PhuongXaAdaptor phuongXaAdaptor;
    private List<thanhpho> mListTP;
    private List<com.example.ncovi.Model.quanhuyen> mListQH;
    private List<com.example.ncovi.Model.phuongxa> mListPX;
    private TextView tv_spinner_TP, tv_spinner_QH, tv_spinner_PX;
    private Spinner spinner_QH, spinner_PX, spinner_TP;
    Button btn_dongy , btn_clear;
    View view;
    private callBack callBack;
    public static Edit_Thongtin_DialogFragments newInstance()
    {
        return new Edit_Thongtin_DialogFragments();
    }
    public void setCallBack(callBack callBack)
    {
        this.callBack = callBack;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.custom_dialog_thongtin , container , false);
        // ánh xạ textview của dialog
        tv_spinner_TP = view.findViewById(R.id.tv_spinnerTP_update);
        tv_spinner_QH = view.findViewById(R.id.tv_spinnerQH_update);
        tv_spinner_PX = view.findViewById(R.id.tv_spinnerPX_update);
        //ánh xạ spinner
        spinner_TP = view.findViewById(R.id.spinner_TP_update);
        spinner_QH = view.findViewById(R.id.spinner_QH_update);
        spinner_PX = view.findViewById(R.id.spinner_PX_update);
        btn_dongy = view.findViewById(R.id.btn_ok);
        btn_clear = view.findViewById(R.id.btn_clear);
        btn_dongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.callback(tv_spinner_TP.getText().toString().trim() , tv_spinner_QH.getText().toString().trim(),tv_spinner_PX.getText().toString().trim());
                dismiss();
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        getDateTinh();
    }

    private void getDateTinh() {
        addressViewModel.getListThanhPho().observe(this, new Observer<List<thanhpho>>() {
            @Override
            public void onChanged(List<thanhpho> listTP) {
                mListTP = listTP;
                if (mListTP != null) {
                    thanhPhoAdaptor = new ThanhPhoAdaptor(getActivity(), R.layout.item_thanhpho, mListTP);
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
                    quanHuyenAdaptor = new QuanHuyenAdaptor(getActivity(), R.layout.item_quanhuyen, mListQH);
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
                    phuongXaAdaptor = new PhuongXaAdaptor(getActivity(), R.layout.item_phuongxa, mListPX);
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v) {

    }
    public interface callBack{
        void callback(String tinh , String quanhuyen ,String xa);
    }
}
