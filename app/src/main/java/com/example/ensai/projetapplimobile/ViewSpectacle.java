package com.example.ensai.projetapplimobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ensai on 27/05/17.
 */

public class ViewSpectacle extends Activity {

    Spectacle spectacle;
    ListView lv;


    protected void onCreate(Bundle savedInstanceState) {
        Intent i = this.getIntent();
        final Spectacle spectacle = i.getParcelableExtra("spectacle");
        Log.d("extra", spectacle.toString());
        Context context = getApplicationContext();
        Toast.makeText(context, spectacle.getDescription(), Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_spectacle);
        // if (getIntent().getExtras().getString("activite").equals("ResultatCle")){
        //spectacle = ((ResultatCle)chopeParent()).getListeSpectacles().get(/*Integer.parseInt(getIntent().getExtras().getString("position"))*/0);
        // }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<String> listeDesDetails = new ArrayList<String>();
                for (Field f : Spectacle.class.getDeclaredFields()) {
                    Log.d("ViewS", f.getName());
                    for (Method m : spectacle.getClass().getMethods())
                        if (m.getName().toLowerCase().equals("get"+f.getName())) {
                            try {
                                Log.d("lecture de", f.getName() );
                                Log.d("lu", m.invoke(spectacle).toString());
                                final Object r = m.invoke(spectacle);
                                listeDesDetails.add(f.getName() + " : " + r);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }

                }

                Log.d("liste",listeDesDetails.toString());
                Log.d("longeur liste",""+listeDesDetails.size());
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewSpectacle.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, listeDesDetails);
                Log.d("adapter", adapter.getItem(0).toString());
                lv.setAdapter(adapter);

            }
        });

    }
}


//if (chopeParent() instanceof(ResultatCle
//                final ArrayList<String> detailsTitres = new ArrayList<String>();
//                final ArrayList<String> detailsLibelles = new ArrayList<String>();
//                for(Field f : spectacle.getClass().getDeclaredFields()       ){
//                    for (Method m : spectacle.getClass().getMethods())
//                        if (m.getName().startsWith("get") && m.getParameterTypes().length == 0) {
//                            try {
//                                final Object r = m.invoke(spectacle);
//                                if (r!=null){
//                                    detailsTitres.add(f.getName());
//                                    String libelle;
//                                    switch(r.getClass().toString().toLowerCase()){
//                                        case "date" :
//                                            java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
//                                            detailsLibelles.add( dateFormat.format(r));
//                                        default :
//                                            detailsLibelles.add(r.toString());
//                                    }
//
//                                }
//                            } catch (IllegalAccessException e) {
//                                e.printStackTrace();
//                            } catch (InvocationTargetException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//
//                    Context context = getApplicationContext();
//                    Toast.makeText(context, detailsLibelles.toString(), Toast.LENGTH_SHORT).show();
// final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ResultatCle.thi
// android.R.layout.simple_list_item_1, android.R.id.text1, listeNomDesS
//  lv.setAdapter(adapter);
//  lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
///  @Override
//  public void onItemClick(AdapterView<?> parent, View view, int position, l
//     Intent i = new Intent(getApplicationContext(), ViewSpectacle.class);
// i.putExtra("id", listeSpectacles.get(listeNomDesSpectacles.indexOf(po


