package com.example.ensai.projetapplimobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button cle;
    private Button date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button cle = (Button) findViewById(R.id.buttonTag);
        cle.setOnClickListener((View.OnClickListener) this);
    }


    @Override
    public void onClick(View v) {

    }
}
