package com.example.ensai.projetapplimobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.security.AccessController.getContext;

/**
 * Created by ensai on 27/05/17.
 */

public class CleActivity extends AppCompatActivity {
    Button cancel;
    Button recherche;
    EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cle);

        recherche = (Button)findViewById(R.id.buttonCle);
        edit   = (EditText)findViewById(R.id.motsCle);
        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CleActivity.this, ResultatCle.class);
                intent.putExtra("champ", edit.getText().toString());
                startActivity(intent);
               // Context context = getApplicationContext();
                //Toast.makeText(context, intent.getExtras().getString("champ"), Toast.LENGTH_SHORT).show();
            }
        });

        cancel = (Button)findViewById(R.id.buttonAnnulerRes);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

}