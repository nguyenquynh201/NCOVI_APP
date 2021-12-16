package com.example.ncovi.View.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ncovi.Model.ModelCovid.covid;
import com.example.ncovi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TheWorldAdaptor extends RecyclerView.Adapter<TheWorldAdaptor.TheWorldViewHolder>{
private ArrayList<covid> mListCovid;
private Context context;

    public TheWorldAdaptor(ArrayList<covid> mListCovid, Context context) {
        this.mListCovid = mListCovid;
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")

public void setData(ArrayList<covid> mListCovid){
    this.mListCovid = mListCovid;
    notifyDataSetChanged();
}
@SuppressLint("NotifyDataSetChanged")
public void search(ArrayList<covid> covids) {
    if (covids != null) {
        this.mListCovid = covids;
        notifyDataSetChanged();
    }
}
    @NonNull
    @Override
    public TheWorldViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_the_world , parent , false);
        return new TheWorldViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheWorldViewHolder holder, int position) {
    covid covid = mListCovid.get(position);
    if(covid == null)
    {
        return;
    }
        String tuvong = String.valueOf("Số ca tử vong :" +covid.getDeaths());
        String khoibenh = String.valueOf("Số ca khỏi bệnh :"+covid.getRecovered());
        String socanhiem = String.valueOf("Số ca nhiễm :"+covid.getCases());
        String name = covid.getCountry();
        Map<String , String> img = covid.getCountryInfo();
        Picasso.get().load(String.valueOf(img.get("flag"))).into(holder.img_bg);
        holder.tv_tuvong.setText(tuvong);
        holder.tv_khoibenh.setText(khoibenh);
        holder.tv_socanhiem.setText(socanhiem);
        holder.tv_nameCity.setText(name);
    }

    @Override
    public int getItemCount() {
        if(mListCovid!=null)
        {
            return mListCovid.size();
        }
        return 0;


    }

    public class TheWorldViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_bg ;
        private TextView tv_socanhiem , tv_tuvong , tv_khoibenh , tv_nameCity;
        public TheWorldViewHolder(@NonNull View itemView) {
            super(itemView);
            img_bg = itemView.findViewById(R.id.img_view);
            tv_socanhiem = itemView.findViewById(R.id.tv_socanhiem);
            tv_khoibenh = itemView.findViewById(R.id.tv_socakhoibenh);
            tv_tuvong = itemView.findViewById(R.id.tv_socatuvong);
            tv_nameCity = itemView.findViewById(R.id.name_city);
        }
    }
}
