package com.example.ensai.projetapplimobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ensai on 27/05/17.
 */

public class ViewSpectacle extends Activity {

    private Button cancel ;

    public Spectacle getSpectacle() {
        return spectacle;
    }

    public void setSpectacle(Spectacle spectacle) {
        this.spectacle = spectacle;
    }

    private Spectacle spectacle;



    protected void onCreate(Bundle savedInstanceState) {
        Intent i = this.getIntent();
        final Spectacle spectacle = i.getParcelableExtra("spectacle");
        this.setSpectacle(spectacle);
        Log.d("extra", spectacle.toString());
        final Context context = getApplicationContext();
        //Toast.makeText(context, spectacle.getDescription(), Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_spectacle);

        // if (getIntent().getExtras().getString("activite").equals("ResultatCle")){
        //spectacle = ((ResultatCle)chopeParent()).getListeSpectacles().get(/*Integer.parseInt(getIntent().getExtras().getString("position"))*/0);
        // }
        runOnUiThread(new Runnable() {


            public Context getContext() {
                return context;
            }

            @Override
            public void run() {
                setContentView(R.layout.view_spectacle);
                final ArrayList<String> listeDesDetails = new ArrayList<String>();
                for (Field f : Spectacle.class.getDeclaredFields()) {
                    Log.d("ViewS", f.getName());
                    for (Method m : spectacle.getClass().getMethods())
                        if (m.getName().toLowerCase().equals("get" + f.getName())) {
                            try {
                                Log.d("lecture de", f.getName());
                                Log.d("lu", m.invoke(spectacle).toString());

                                switch (f.getName()) {


                                    case ("title"):
                                        Log.d("on en est à", f.getName());
                                        TextView title = (TextView) findViewById(R.id.title);
                                        title.setText(spectacle.getTitle());
                                        break;
                                    case ("image_thumb"):
                                        Log.d("on en est à", f.getName());
                                        ImageView thumb = (ImageView) findViewById(R.id.image_thumb);
                                        String url = spectacle.getImage_thumb();
                                        Picasso.with(context).load(url).into(thumb);
                                        break;
                                    default:
                                        Log.d("on en est à", f.getName());
                                        final Object r = m.invoke(spectacle);
                                        int ressourceId = getResources().getIdentifier(f.getName(), "id", this.getContext().getPackageName());
                                        try {
                                            TextView def = (TextView) findViewById(ressourceId);
                                            def.setText(r.toString());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        break;

                                }

                                //listeDesDetails.add(f.getName() + " : " + r);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }

                }

                Log.d("liste", listeDesDetails.toString());
                Log.d("longeur liste", "" + listeDesDetails.size());
                Log.d("image", spectacle.getImage());

                // setContentView(R.layout.view_spectacle);
                // TextView title = (TextView) findViewById(R.id.title);
                // title.setText(spectacle.getTitle());
                // ImageView thumb = (ImageView) findViewById(R.id.image_thumb);
                // String url = spectacle.getImage_thumb();
                // Picasso.with(context).load(url).into(thumb);

                //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewSpectacle.this,
                //       android.R.layout.simple_list_item_1, android.R.id.text1, listeDesDetails);
                //Log.d("adapter", adapter.getItem(0).toString());
                //lv.lsetAdapter(adapter);



                cancel = (Button)findViewById(R.id.buttonAnnulerVS);


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();

                    }
                });
            }
        });

    }

    public void clickThumb(View v) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.getSpectacle().getImage()));
            startActivity(browserIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void clickLink(View v) {

        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.getSpectacle().getLink()));
            startActivity(browserIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clickAddress(View v) {

        String lat = this.getSpectacle().getLatlon().split(",")[0].substring(1);
        String lon = this.getSpectacle().getLatlon().split(",")[1].substring(0, this.getSpectacle().getLatlon().split(",")[1].length()-1);
        Log.d("Lat-lon", lat + "-" + lon);
        String adresse = this.getSpectacle().getAddress().replace(" ", "+");
        Uri gmmIntentUri = Uri.parse("geo:" + lat +"," + lon + "?q=" + adresse);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);


    }

    public void clickDescription(View v){

        String description = "pas de description disponible";
        Log.d("coucou","je suis clickDescrpition");
        if (this.getSpectacle().getFree_text().length()>0){
            description=this.getSpectacle().getFree_text();
        }
        new AlertDialog.Builder(ViewSpectacle.this)
                .setTitle("description : ")
                .setMessage(description)
                .setNegativeButton(R.string.revenir, null) // dismisses by default
                //.setPositiveButton(android.R.string.ok, new OnClickListener() {
                   // @Override public void onClick(DialogInterface dialog, int which) {
                        // do the acknowledged action, beware, this is run on UI thread
                   // }
                //})
                .create()
                .show();

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


