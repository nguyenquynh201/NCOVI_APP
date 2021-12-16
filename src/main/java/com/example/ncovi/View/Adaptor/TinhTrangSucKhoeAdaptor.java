package com.example.ncovi.View.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ncovi.Model.TinhTrangSucKhoe;
import com.example.ncovi.R;
import com.example.ncovi.View.SharedPreference.DataManager;

import java.util.ArrayList;
import java.util.List;

public class TinhTrangSucKhoeAdaptor extends RecyclerView.Adapter<TinhTrangSucKhoeAdaptor.TinhTrangViewHolder> {
    private List<TinhTrangSucKhoe> ListTinhTrangs;
    private Context context;

    public TinhTrangSucKhoeAdaptor(Context context) {
        this.context = context;
    }
    public void searchTinhTrang(ArrayList<TinhTrangSucKhoe> tinhTrangSucKhoes){
        if(tinhTrangSucKhoes!= null)
        {
            ListTinhTrangs  = tinhTrangSucKhoes;
            notifyDataSetChanged();
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setDataTinhTrang(List<TinhTrangSucKhoe> ListTinhTrang) {
        this.ListTinhTrangs = ListTinhTrang;
//        int initialSize = ListTinhTrang.size();
//        ListTinhTrangs.addAll(ListTinhTrang);
//        notifyItemRangeInserted(initialSize, ListTinhTrangs.size() - 1); //Correct position
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TinhTrangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suckhoe, parent, false);
        return new TinhTrangViewHolder(view);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull TinhTrangViewHolder holder, int position) {
        TinhTrangSucKhoe tinhTrangSucKhoe = ListTinhTrangs.get(position);
        if (tinhTrangSucKhoe == null) {
            return;
        }
        holder.tv_date.setText(tinhTrangSucKhoe.getNgay());
        holder.tv_canhbao.setText(tinhTrangSucKhoe.getCanhbao());
        holder.tv_tinhtrang.setText(tinhTrangSucKhoe.getTinhtrangsuckhoe());
        holder.tv_time.setText(tinhTrangSucKhoe.getGio());
        if (ListTinhTrangs.get(position).getCanhbao().equals("Nguy Hiểm")) {
            holder.relativeLayout.setBackgroundResource(R.drawable.custom_bg_sk_canhbao);
            holder.btn_lienhe.setVisibility(View.VISIBLE);
            holder.btn_lienhe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context.getApplicationContext(), "0763021524", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (ListTinhTrangs.get(position).getCanhbao().equals("An Toàn")) {
            holder.relativeLayout.setBackgroundResource(R.drawable.custom_bg_sk);
            holder.btn_lienhe.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        if (ListTinhTrangs != null) {
            return ListTinhTrangs.size();
        }
        return 0;
    }

    public class TinhTrangViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_date, tv_time, tv_tinhtrang, tv_canhbao;
        private RelativeLayout relativeLayout;
        private Button btn_lienhe;

        public TinhTrangViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_date_sk);
            tv_time = itemView.findViewById(R.id.tv_time_sk);
            tv_tinhtrang = itemView.findViewById(R.id.tv_tinhtrang_sk);
            tv_canhbao = itemView.findViewById(R.id.tv_canhbao_sk);
            relativeLayout = itemView.findViewById(R.id.rlt_tt_1);
            btn_lienhe = itemView.findViewById(R.id.btn_lienhe_sk);
        }

    }
}
