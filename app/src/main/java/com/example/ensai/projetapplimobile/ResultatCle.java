package com.example.ensai.projetapplimobile;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


/**
 * Created by ensai on 27/05/17.
 */

public class ResultatCle extends AppCompatActivity {

    ListView lv;

    public ArrayList<Spectacle> getListeSpectacles() {
        return listeSpectacles;
    }

    final ArrayList<Spectacle> listeSpectacles = new ArrayList<Spectacle>();

    public ListView getLv() {
        return lv;
    }

    public void setLv(ListView lv) {
        this.lv = lv;
    }

    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_cle);
        lv = (ListView) findViewById(R.id.listRes);


        //String myurl = "https://opendata.paris.fr/api/records/1.0/search/?dataset=evenements-a-paris&facet=tags&facet=placename&facet=department&facet=region&facet=city&facet=date_start&facet=date_end&facet=pricing_info&facet=updated_at";
        String myurl = "https://opendata.paris.fr/api/records/1.0/search/?dataset=evenements-a-paris&facet=tags&facet=placename&facet=department&facet=region&facet=city&facet=date_start&facet=date_end&facet=pricing_info&facet=updated_at";
        final String champ = getIntent().getExtras().getString("champ").toLowerCase();


        OkHttpClient okhttpClient = new OkHttpClient();
        Request myGetRequest = new Request.Builder().url(myurl).build();


        okhttpClient.newCall(myGetRequest).enqueue(new Callback() {


                                                       public void onFailure(Request request, IOException e) {
                                                           Log.d("http", "lol fail");
                                                       }

                                                       public void onResponse(Response response) throws IOException {


                                                           try {
                                                               String text = response.body().string();
                                                               //Log.d("text :", text);


                                                               JSONObject jsonTot = new JSONObject(text);
                                                               JSONArray json = jsonTot.getJSONArray("records");
                                                               Log.d("longueur", "" + json.length());
                                                               for (int i = 0; i < json.length(); i++) {

                                                                   JSONObject jsonobject = json.getJSONObject(i).getJSONObject("fields");

                                                                   Log.d("spectacle", jsonobject.optString("title"));
                                                                   Log.d("tags", jsonobject.optString("tags"));
                                                                   try {
                                                                       boolean added = false;
                                                                       if (!(jsonobject.optString("tags").isEmpty()) && !added) {
                                                                           Log.d("recherche dans tags", "yo");
                                                                           if (jsonobject.getString("tags").toLowerCase().contains(champ)) {

                                                                               Spectacle spectacle = new Spectacle();
//                                                                           spectacle.setTitle(jsonobject.getString("title"));
//                                                                           spectacle.setId(jsonobject.getString("uid"));

                                                                               for (Field f : Spectacle.class.getDeclaredFields()) {
                                                                                   Log.d("field :", f.getName());
                                                                                   Log.d("présent", String.valueOf(jsonobject.has(f.getName())));
                                                                                   // Log.d("address", jsonobject.getString("address"));

                                                                                   if (jsonobject.has(f.getName())  /*&&  !jsonobject.getString(f.getName()).isEmpty()*/)
                                                                                       // try {
                                                                                       Log.d("valeur", jsonobject.getString(f.getName()));
                                                                                   for (Method m : spectacle.getClass().getMethods()) {
                                                                                       if (m.getName().toLowerCase().equals("set" + f.getName())) {
                                                                                           m.invoke(spectacle, jsonobject.getString(f.getName()));
                                                                                       }
                                                                                   }
                                                                                   //} catch (IllegalAccessException e) {
                                                                                   //  e.printStackTrace();
                                                                                   // } catch (InvocationTargetException e) {
                                                                                   //  e.printStackTrace();
                                                                                   //}
                                                                               }
                                                                               Log.d("found", "yes");
                                                                               listeSpectacles.add(spectacle);
                                                                               added=true;

                                                                           }
                                                                       }
                                                                       if (/*!(jsonobject.getString("title").isEmpty()) && */!added) {
                                                                           Log.d("recherche dans titre", "yo");

                                                                           if (jsonobject.getString("title").toLowerCase().contains(champ)) {
                                                                               Spectacle spectacle = new Spectacle();
//                                                                           spectacle.setTitle(jsonobject.getString("title"));
//                                                                           spectacle.setId(jsonobject.getString("uid"));

                                                                               for (Field f : Spectacle.class.getDeclaredFields()) {
                                                                                   Log.d("spectacle", jsonobject.getString("title"));
                                                                                   Log.d("field :", f.getName());
                                                                                   Log.d("présent", String.valueOf(jsonobject.has(f.getName())));
                                                                                   // Log.d("address", jsonobject.getString("address"));

                                                                                   if (jsonobject.has(f.getName())  /*&&  !jsonobject.getString(f.getName()).isEmpty()*/)
                                                                                       try {
                                                                                           Log.d("valeur", jsonobject.getString(f.getName()));
                                                                                           for (Method m : spectacle.getClass().getMethods()) {
                                                                                               if (m.getName().toLowerCase().equals("set" + f.getName())) {
                                                                                                   m.invoke(spectacle, jsonobject.getString(f.getName()));
                                                                                               }
                                                                                           }
                                                                                       } catch (IllegalAccessException e) {
                                                                                           e.printStackTrace();
                                                                                       } catch (InvocationTargetException e) {
                                                                                           e.printStackTrace();
                                                                                       }
                                                                               }
                                                                               Log.d("founcd", "yes");
                                                                               listeSpectacles.add(spectacle);
                                                                               added=true;
                                                                           }
                                                                       }

                                                                   } catch (IllegalAccessException e) {
                                                                       e.printStackTrace();
                                                                   } catch (InvocationTargetException e) {
                                                                       e.printStackTrace();
                                                                   }
                                                               }


                                                           } catch (JSONException exc) {

                                                               exc.printStackTrace();
                                                           }

                                                           runOnUiThread(new Runnable() {
                                                               @Override
                                                               public void run() {
                                                                   final ArrayList<String> listeNomDesSpectacles = new ArrayList<String>();
                                                                   for (Spectacle b : listeSpectacles) {
                                                                       listeNomDesSpectacles.add(b.getTitle());
                                                                   }
                                                                   final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ResultatCle.this,
                                                                           android.R.layout.simple_list_item_1, android.R.id.text1, listeNomDesSpectacles);
                                                                   lv.setAdapter(adapter);
                                                                   lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                       @Override
                                                                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                           Intent i = new Intent(getApplicationContext(), ViewSpectacle.class);
                                                                           i.putExtra("spectacle", listeSpectacles.get(position));
                                                                           startActivity(i);
                                                                       }


                                                                   });

                                                               }
                                                           }); // fin runOnUiThread


                                                       }

                                                   }
        );
        cancel = (Button) findViewById(R.id.buttonNleRecherche);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }


}

