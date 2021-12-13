package com.example.ncovi.View.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ncovi.Model.TinhTrangSucKhoe;
import com.example.ncovi.R;

import java.util.List;

public class TinhTrangSucKhoeAdaptor extends RecyclerView.Adapter<TinhTrangSucKhoeAdaptor.TinhTrangViewHolder>{
private List<TinhTrangSucKhoe> ListTinhTrang;
private Context context;

    public TinhTrangSucKhoeAdaptor(Context context) {
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
public void setDataTinhTrang(List<TinhTrangSucKhoe> ListTinhTrang)
{
    this.ListTinhTrang = ListTinhTrang;
    notifyDataSetChanged();
}

    @NonNull
    @Override
    public TinhTrangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suckhoe , parent , false);
        return new TinhTrangViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TinhTrangViewHolder holder, int position) {
    TinhTrangSucKhoe tinhTrangSucKhoe = ListTinhTrang.get(position);
    if(tinhTrangSucKhoe== null)
    {
        return;
    }
    holder.tv_date.setText(tinhTrangSucKhoe.getNgay());
    holder.tv_canhbao.setText(tinhTrangSucKhoe.getCanhbao());
    holder.tv_tinhtrang.setText(tinhTrangSucKhoe.getTinhtrangsuckhoe());
    holder.tv_time.setText(tinhTrangSucKhoe.getGio());
    }

    @Override
    public int getItemCount() {
    if (ListTinhTrang!=null)
    {
        return ListTinhTrang.size();
    }
        return 0;
    }

    public class TinhTrangViewHolder extends RecyclerView.ViewHolder{
    private TextView tv_date , tv_time , tv_tinhtrang , tv_canhbao;
        public TinhTrangViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_date_sk);
            tv_time = itemView.findViewById(R.id.tv_time_sk);
            tv_tinhtrang = itemView.findViewById(R.id.tv_tinhtrang_sk);
            tv_canhbao = itemView.findViewById(R.id.tv_canhbao_sk);
        }
    }
}
