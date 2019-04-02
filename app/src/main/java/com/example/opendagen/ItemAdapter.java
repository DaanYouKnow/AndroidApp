package com.example.opendagen;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.opendagen.R;
public class ItemAdapter extends BaseAdapter {
    LayoutInflater mInflator;
    String[] Dagen;
    String[] Info;

    public ItemAdapter(Context c, String[] i, String[] p){
        Dagen = i;
        Info = p;
        mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Dagen.length;
    }

    @Override
    public Object getItem(int i) {
        return Dagen[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View v = mInflator.inflate(R.layout.my_listview_detail, null);
        TextView NameTextView = (TextView) v.findViewById(R.id.NameTextView);
        TextView DescriptionTextView = (TextView) v.findViewById(R.id.DescriptionTextView);
        NameTextView.setTextColor(Color.WHITE);
        DescriptionTextView.setTextColor(Color.WHITE);

        String Name = Dagen[i];
        String Desc = Info[i];

        NameTextView.setText(Name);
        DescriptionTextView.setText(Desc);
        return v;
    }
}
