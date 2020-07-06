package com.kisanthapa.connect;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.kisanthapa.connect.R;

public class AboutUsActivity extends AppCompatActivity {

    //Toolbar
    private Toolbar mProfileToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //Toolbar
        mProfileToolbar = (Toolbar)findViewById(R.id.about_us_toolbar);
        setSupportActionBar(mProfileToolbar);
        getSupportActionBar().setTitle("About Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }
}
