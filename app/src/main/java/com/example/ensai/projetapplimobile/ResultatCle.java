package com.example.ensai.projetapplimobile;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;


import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;

import java.io.IOException;


/**
 * Created by ensai on 27/05/17.
 */

public class ResultatCle extends AppCompatActivity {

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_cle);
        lv = (ListView) findViewById(R.id.listRes);

        String myurl = "http://alban.guichard.free.fr/BeerChallenge/json.php?query=?&date=?";

        OkHttpClient okhttpClient = new OkHttpClient();
        Request myGetRequest = new Request.Builder().url(myurl).build();

        okhttpClient.newCall(myGetRequest).enqueue(new Callback() {

                                                       public void onFailure(Request request, IOException e) {
                                                       }

                                                       public void onResponse(Response response) throws IOException {
                                                           final ArrayList<Biere> listeBieres=new ArrayList<Biere>();

                                                           try {
                                                               String text = response.body().string();
                                                               JSONArray json = new JSONArray(text);

                                                               for (int i = 0; i < json.length(); i++) {
                                                                   JSONObject jsonobject = json.getJSONObject(i);
                                                                   Biere biere = new Biere();


                                                                   listeBieres.add(biere);

                                                               }


                                                           } catch (JSONException exc) {

                                                               exc.printStackTrace();
                                                           }

                                                           runOnUiThread(new Runnable() {
                                                               @Override
                                                               public void run() {
                                                                   ArrayList<String> listeNomDesBieres= new ArrayList<String>();
                                                                   for(Biere b: listeBieres){
                                                                       listeNomDesBieres.add(b.getNom());
                                                                   }
                                                                   final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ToutesLesBieres.this,
                                                                           android.R.layout.simple_list_item_1, listeNomDesBieres);
                                                                   lv.setAdapter(adapter);
                                                               }
                                                           }); // fin runOnUiThread


                                                           listeDesBieres=listeBieres;
                                                       }

                                                   }
        );
    }

}

