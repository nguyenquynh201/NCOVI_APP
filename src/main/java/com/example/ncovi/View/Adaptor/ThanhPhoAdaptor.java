package com.example.ncovi.View.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ncovi.Model.thanhpho;
import com.example.ncovi.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ThanhPhoAdaptor extends ArrayAdapter<thanhpho> {
    private List<thanhpho> thanhphoList;
    public ThanhPhoAdaptor(@NonNull Context context, int resource, @NonNull List<thanhpho> objects) {
        super(context, resource, objects);
        thanhphoList = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       if(convertView==null)
       {
           convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thanhpho , parent , false);
       }
        TextView  textView = convertView.findViewById(R.id.txt_thanhPho);
        thanhpho thanhpho = getItem(position);
        textView.setText(thanhpho.getName());
        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<thanhpho> mListTP = new ArrayList<>();
                if(constraint == null || constraint.length() == 0)
                {
                    mListTP.addAll(thanhphoList);
                }else {
                    String filter  =constraint.toString().toLowerCase(Locale.ROOT).trim();
                    for(thanhpho thanhpho : thanhphoList)
                    {
                        if (thanhpho.getName().toLowerCase(Locale.ROOT).contains(filter))
                        {
                            mListTP.add(thanhpho);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListTP;
                filterResults.count = mListTP.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                addAll((List<thanhpho>)results.values);
                notifyDataSetInvalidated();
            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return ((thanhpho) resultValue).getName();
            }
        };
    }
}
