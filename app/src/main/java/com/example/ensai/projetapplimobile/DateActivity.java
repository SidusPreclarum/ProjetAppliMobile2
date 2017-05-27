package com.example.ensai.projetapplimobile;

import android.app.Activity;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.TimeZone;


/**
 * Created by ensai on 22/05/17.
 */

public class DateActivity extends AppCompatActivity implements View.OnClickListener {

    Button date = null;
    Spinner spinnerDate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_date);
        date = (Button) findViewById(R.id.buttonDate);




    }


    @Override
    public void onClick(View v) {

    }

}



