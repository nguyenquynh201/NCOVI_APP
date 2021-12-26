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

import com.example.ncovi.Model.phuongxa;
import com.example.ncovi.Model.quanhuyen;
import com.example.ncovi.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PhuongXaAdaptor extends ArrayAdapter<phuongxa> {
    private List<phuongxa> phuongxaList;
    public PhuongXaAdaptor(@NonNull Context context, int resource, @NonNull List<phuongxa> objects) {
        super(context, resource, objects);
        phuongxaList = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       if(convertView==null)
       {
           convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phuongxa , parent , false);
       }
        TextView textView = convertView.findViewById(R.id.txt_phuongXa);
        phuongxa phuongxa = getItem(position);
        textView.setText(phuongxa.getTenxa());
        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<phuongxa> mListPX = new ArrayList<>();
                if(constraint == null || constraint.length() == 0)
                {
                    mListPX.addAll(phuongxaList);
                }else {
                    String filter  =constraint.toString().toLowerCase(Locale.ROOT).trim();
                    for(phuongxa phuongxa : phuongxaList)
                    {
                        if (phuongxa.getTenxa().toLowerCase(Locale.ROOT).contains(filter))
                        {
                            mListPX.add(phuongxa);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListPX;
                filterResults.count = mListPX.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                addAll((List<phuongxa>)results.values);
                notifyDataSetInvalidated();
            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return ((quanhuyen) resultValue).getTenhuyen();
            }
        };
    }
}
