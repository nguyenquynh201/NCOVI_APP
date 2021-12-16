package com.example.ncovi.View.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ncovi.Model.phongdich;
import com.example.ncovi.R;

import java.util.List;

public class PhongChongDichAdaptor extends RecyclerView.Adapter<PhongChongDichAdaptor.ChongDichViewHolder>{
    private List<phongdich> mList;
    private Context context;

    public PhongChongDichAdaptor(List<phongdich> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<phongdich> mList)
    {
        this.mList = mList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ChongDichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phongdich , parent , false);
        return new ChongDichViewHolder(view);


    }


    @Override
    public void onBindViewHolder(@NonNull ChongDichViewHolder holder, int position) {
        phongdich phongdich = mList.get(position);
        if(phongdich == null)
        {
            return;
        }
        holder.tv_name.setText(phongdich.getTitle());
        holder.layout.setBackgroundResource(phongdich.getBg());
        holder.layout_child.setBackgroundResource(phongdich.getBg_icon());
        holder.img_icon.setImageResource(phongdich.getIcon());
    }

    @Override
    public int getItemCount() {
        if(mList !=null)
        {
            return mList.size();
        }
        return 0;


    }

    public class ChongDichViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout layout , layout_child;
        private TextView tv_name;
        private ImageView img_icon;
        public ChongDichViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.lnl_phongdich);
            layout_child = itemView.findViewById(R.id.lnl_child_phongdich);
            tv_name = itemView.findViewById(R.id.tv_name_phongdich);
            img_icon = itemView.findViewById(R.id.img_1);
        }
    }
}
