package com.example.ensai.projetapplimobile;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button Rechercher;
    private Button date;
    Context context;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.deleteDatabase(BDD.getInstance(getApplicationContext()).getDatabaseName());
;


        //String myurl = "https://opendata.paris.fr/api/records/1.0/search/?dataset=evenements-a-paris&facet=tags&facet=placename&facet=department&facet=region&facet=city&facet=date_start&facet=date_end&facet=pricing_info&facet=updated_at";
        String myurl = "https://opendata.paris.fr/api/records/1.0/search/?dataset=evenements-a-paris&facet=tags&facet=placename&facet=department&facet=region&facet=city&facet=date_start&facet=date_end&facet=pricing_info&facet=updated_at";
        //final String champ = getIntent().getExtras().getString("table").toLowerCase();


        OkHttpClient okhttpClient = new OkHttpClient();
        Request myGetRequest = new Request.Builder().url(myurl).build();
        okhttpClient.newCall(myGetRequest).enqueue(new Callback() {


            public void onFailure(Request request, IOException e) {
                Log.d("http", "lol fail");
            }

            public void onResponse(Response response) throws IOException {
                ArrayList<Spectacle> spectacles = new ArrayList<Spectacle>();

                try {
                    String text = response.body().string();
                    //Log.d("text :", text);
                    JSONObject jsonTot = new JSONObject(text);
                    JSONArray json = jsonTot.getJSONArray("records");
                    Log.d("longueur", "" + json.length());
                    for (int i = 0; i < json.length(); i++) {
                        Log.d("json", json.getJSONObject(i).getJSONObject("fields").toString());
                        Spectacle spectacle = new Spectacle((json.getJSONObject(i).getJSONObject("fields")));
                        spectacles.add(spectacle);
                    }

                } catch (JSONException e) {
                }


                Log.d("nb de spectacles recup", String.valueOf(spectacles.size()));
                SpectacleDao spectacleDao = SpectacleDao.getInstance(getApplicationContext());
                Log.d("injection Json ds BDD", "go");
                spectacleDao.ajouterNouveauxSpectacles(spectacles);

            }

        });

        Rechercher = (Button)findViewById(R.id.buttonCle);
        Rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RechercheActivity.class);
                startActivity(intent);
            }
        });


    }



    public void clickVoirMesSpectacles(View v) {

    }


}
