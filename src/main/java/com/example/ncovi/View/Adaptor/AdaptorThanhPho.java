package com.example.ncovi.View.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ncovi.Model.thanhpho;
import com.example.ncovi.R;

import java.util.List;

public class AdaptorThanhPho extends BaseAdapter {
  LayoutInflater layoutInflater;
  Context context;
  List<thanhpho> list;

    public AdaptorThanhPho(Context context, List<thanhpho> list) {
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
        convertView = layoutInflater.inflate(R.layout.item_thanhpho , null);
        thanhpho thanhpho = list.get(position);
        TextView textView = convertView.findViewById(R.id.txt_thanhPho);
        textView.setText(thanhpho.getName());
        return convertView;
    }
}
