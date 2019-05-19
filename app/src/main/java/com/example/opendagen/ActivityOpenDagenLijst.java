package com.example.opendagen;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ActivityOpenDagenLijst extends AppCompatActivity {

    ListView MyListView;
    String[] Dagen;
    String[] Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.HROTheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.opendagenlijstactivity);

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
