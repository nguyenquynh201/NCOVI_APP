package com.example.ncovi.View.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ncovi.Model.quanhuyen;
import com.example.ncovi.Model.thanhpho;
import com.example.ncovi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QuanHuyenAdaptor extends ArrayAdapter<quanhuyen> {
    private List<quanhuyen> quanhuyenList;


    public QuanHuyenAdaptor(@NonNull Context context, int resource, @NonNull List<quanhuyen> objects) {
        super(context, resource, objects);

        quanhuyenList = new ArrayList<>();

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       if(convertView==null)
       {
           convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quanhuyen , parent , false);
       }
        TextView  textView = convertView.findViewById(R.id.txt_quanHuyen);
        quanhuyen quanhuyen = getItem(position);
        textView.setText(quanhuyen.getTenhuyen());
        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<quanhuyen> mListQH = new ArrayList<>();
                if(constraint == null || constraint.length() == 0)
                {
                    mListQH.addAll(quanhuyenList);
                }else {
                    String filter  =constraint.toString().toLowerCase(Locale.ROOT).trim();
                    for(quanhuyen quanhuyen : quanhuyenList)
                    {
                        if (quanhuyen.getTenhuyen().toLowerCase(Locale.ROOT).contains(filter))
                        {
                            mListQH.add(quanhuyen);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListQH;
                filterResults.count = mListQH.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                addAll((List<quanhuyen>)results.values);
                notifyDataSetInvalidated();
            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return ((quanhuyen) resultValue).getTenhuyen();
            }
        };
    }
}
