package com.example.ncovi.View.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ncovi.Model.quanhuyen;
import com.example.ncovi.Model.thanhpho;
import com.example.ncovi.R;

import java.util.List;

public class AdaptorQuanHuyen extends BaseAdapter {
  LayoutInflater layoutInflater;
  Context context;
  List<quanhuyen> list;

    public AdaptorQuanHuyen(Context context, List<quanhuyen> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @SuppressLint({"InflateParams", "ViewHolder"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.item_quanhuyen , null);
        quanhuyen quanhuyen = list.get(position);
        TextView textView = convertView.findViewById(R.id.txt_quanHuyen);
        textView.setText(quanhuyen.getTenhuyen());
        return convertView;
    }
}
