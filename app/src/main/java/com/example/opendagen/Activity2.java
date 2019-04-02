package com.example.opendagen;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.opendagen.R;

public class Activity2 extends AppCompatActivity {

    ListView MyListView;
    String[] Dagen;
    String[] Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Resources res = getResources();
        MyListView =(ListView) findViewById(R.id.MyListView);
        Dagen = res.getStringArray(R.array.Dagen);
        Info = res.getStringArray(R.array.Info);

        ItemAdapter ItemAdapter = new ItemAdapter(this, Dagen, Info);
        MyListView.setAdapter(ItemAdapter);

        MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent ShowOpenDagendetail = new Intent(getApplicationContext(), ActivityOpenDagenInfo.class);
                ShowOpenDagendetail.putExtra("com.example.myapplication.ITEM_INDEX", i);
                startActivity(ShowOpenDagendetail);
            }
        });
    }
}
