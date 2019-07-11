package com.sparrowpaul.alc4phase1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CardView aboutAlcBtn, myProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews(); // initialize the views

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //changing the color of the nav keys
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        aboutAlcBtn.setOnClickListener(new View.OnClickListener() { // setting a click event on the about alc button
            @Override
            public void onClick(View view) {
                Intent aboutAlcIntent = new Intent(MainActivity.this, AboutAlcActivity.class);
                startActivity(aboutAlcIntent);
            }
        });

        myProfileBtn.setOnClickListener(new View.OnClickListener() { // setting click event on my profile button
            @Override
            public void onClick(View view) {
                Intent myProfileIntent = new Intent(MainActivity.this, MyProfileActivity.class);
                startActivity(myProfileIntent);
            }
        });
    }

    private void initViews() { // function to find views
        aboutAlcBtn = findViewById(R.id.aboutAlcButton);
        myProfileBtn = findViewById(R.id.myProfileButton);
    }
}
