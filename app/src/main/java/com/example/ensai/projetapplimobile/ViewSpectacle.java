package com.example.ensai.projetapplimobile;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewParent;
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
    Activity chopeParent(){
        return this.getParent();
    }

    static <T> void inspect(Class<T> maClasse) {
        Field[] fields = maClasse.getDeclaredFields();
    }

    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        Toast.makeText(context, getIntent().getExtras().getString("position"), Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_spectacle);
        if (getIntent().getExtras().getString("activite").equals("ResultatCle")){
            spectacle = ((ResultatCle)chopeParent()).getListeSpectacles().get(/*Integer.parseInt(getIntent().getExtras().getString("position"))*/0);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //if (chopeParent() instanceof(ResultatCle
                final ArrayList<String> detailsTitres = new ArrayList<String>();
                final ArrayList<String> detailsLibelles = new ArrayList<String>();
                for(Field f : spectacle.getClass().getDeclaredFields()       ){
                    for (Method m : spectacle.getClass().getMethods())
                        if (m.getName().startsWith("get") && m.getParameterTypes().length == 0) {
                            try {
                                final Object r = m.invoke(spectacle);
                                if (r!=null){
                                    detailsTitres.add(f.getName());
                                    String libelle;
                                    switch(r.getClass().toString().toLowerCase()){
                                        case "date" :
                                            java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
                                            detailsLibelles.add( dateFormat.format(r));
                                        default :
                                            detailsLibelles.add(r.toString());
                                    }

                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }

                        }

                    Context context = getApplicationContext();
                    Toast.makeText(context, detailsLibelles.toString(), Toast.LENGTH_SHORT).show();
               // final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ResultatCle.thi
                       // android.R.layout.simple_list_item_1, android.R.id.text1, listeNomDesS
                      //  lv.setAdapter(adapter);
                      //  lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  ///  @Override
                  //  public void onItemClick(AdapterView<?> parent, View view, int position, l
                       //     Intent i = new Intent(getApplicationContext(), ViewSpectacle.class);
                   // i.putExtra("id", listeSpectacles.get(listeNomDesSpectacles.indexOf(po
                }


            }

        });
    }; // fin runOnUiThread



}


