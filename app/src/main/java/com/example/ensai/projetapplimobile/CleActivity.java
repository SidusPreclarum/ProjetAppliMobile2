package com.example.ensai.projetapplimobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ensai on 27/05/17.
 */

public class CleActivity extends AppCompatActivity {

    Button recherche;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cle);

        recherche = (Button)findViewById(R.id.buttonCle);
        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CleActivity.this, ResultatCle.class);
                intent.putExtra("champ", R.id.motsCle);
                startActivity(intent);

            }
        });
    }

}