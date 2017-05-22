package com.example.ensai.projetapplimobile;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by ensai on 22/05/17.
 */

public class DateActivity extends AppCompatActivity implements View.OnClickListener{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button date = (Button) findViewById(R.id.buttonDate);
        date.setOnClickListener((View.OnClickListener) this);

    }


    @Override
    public void onClick(View v) {

    }
}
