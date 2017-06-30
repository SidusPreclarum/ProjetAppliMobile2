package com.example.ensai.projetapplimobile;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button rechercher;
    private Button mesSpectacles;
    Context context;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("main : ", "done");
       // this.deleteDatabase(BDD.getInstance(getApplicationContext()).getDatabaseName());
        ;

        rechercher = (Button)findViewById(R.id.buttonCle);
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RechercheActivityImp.class);
                startActivity(intent);
            }
        });


    }



    public void clickVoirMesSpectacles(View v) {

    }


}
