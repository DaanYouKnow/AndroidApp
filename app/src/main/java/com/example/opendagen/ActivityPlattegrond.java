package com.example.opendagen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ActivityPlattegrond extends AppCompatActivity {

    private ImageView Plattegrond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plattegrond);
        Plattegrond = findViewById(R.id.imageViewPlattegrond);
        Plattegrond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfo();
            }
        });
    }
    public void openInfo() {
        Intent intent = new Intent(this, ActivityInfo.class);
        startActivity(intent);
    }
}
