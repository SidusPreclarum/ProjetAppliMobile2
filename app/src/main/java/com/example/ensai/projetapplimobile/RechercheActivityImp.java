package com.example.ensai.projetapplimobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ensai on 27/06/17.
 */

public class RechercheActivityImp extends AppCompatActivity {
    private ListView lv;
    private Context context;
    private EditText titre, dateDebut, dateFin, lieu;
    private Button submit, clear;
    private String[] defaultSpectacle = {"%","%","%","%","%","%","%","%","%","%","%","%","%","%","%","%","%","%","%","%","%"};
    private String baseUrl = "https://opendata.paris.fr/api/records/1.0/search/?dataset=evenements-a-paris&rows=100&q=";
    private ArrayList<String> listeUids ;
    private ArrayList<String> listeTitles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);
        lv = (ListView) findViewById(R.id.listRes);


        titre = (EditText) findViewById(R.id.rechTitre);
        lieu = (EditText) findViewById(R.id.rechLieu);
        dateDebut = (EditText) findViewById(R.id.dateAfter);
        dateFin = (EditText) findViewById(R.id.dateBefore);




        submit = (Button)findViewById(R.id.button_submit);
        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                final Utils utils = new Utils().getInstance();
                final  Message msg = new Message();
                final Handler handler = new Handler(new Handler.Callback() {

                    @Override
                    public boolean handleMessage(Message msg) {
                        if(msg.arg1==1)
                        {
                            Toast.makeText(getApplicationContext(), "Pas de résultat", Toast.LENGTH_LONG).show();
                        }
                        return false;
                    }
                });


               // Spectacle dummySpectacle = new Spectacle(defaultSpectacle);

                /*récupération des champs de recherche*/

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String dateDuJour = df.format(Calendar.getInstance().getTime());
                Log.d("date du jour : ", dateDuJour);
                String tags = (utils.checkIfNullEmptyOrWhite(titre.getText().toString()) ? "" : titre.getText().toString());
                String loc = (utils.checkIfNullEmptyOrWhite(lieu.getText().toString()) ? "" : lieu.getText().toString());
                String aft = (utils.checkIfNullEmptyOrWhite(dateDebut.getText().toString()) ? "": dateDebut.getText().toString());
                String bef = (utils.checkIfNullEmptyOrWhite(dateFin.getText().toString()) ? dateDuJour : dateFin.getText().toString());
                final String packedUids = null;


                /*construction de l'URL de requête*/
                String myUrl = baseUrl;
                boolean fieldFilled = false;

                if (tags != "") {
                    myUrl = myUrl + "(titre:" + tags + " OR description:" + tags + " OR tags:" + tags + ")";
                    fieldFilled = true;
                }
                if (loc != "") {
                    if (fieldFilled) {
                        myUrl = myUrl + " AND ";
                    }
                    myUrl = myUrl + "loc:" + loc;
                    fieldFilled = true;
                }
                if (aft != "") {
                    if (fieldFilled) {
                        myUrl = myUrl + " AND ";
                    }
                    myUrl = myUrl + "date_start>=" + aft;
                    fieldFilled = true;
                }
                if (bef != "") {
                    if (fieldFilled) {
                        myUrl = myUrl + " AND ";
                    }
                    myUrl = myUrl + "date_end<=" + bef;
                }
                Log.d("url : " , myUrl);
                OkHttpClient okhttpClient = new OkHttpClient();
                Request myGetRequest = new Request.Builder().url(myUrl).build();
                okhttpClient.newCall(myGetRequest).enqueue(new Callback() {


                    public void onFailure(Request request, IOException e) {
                        Log.d("http", "lol fail");
                        Toast.makeText(getApplicationContext(), "Échec de la connexion au serveur", Toast.LENGTH_LONG).show();
                    }

                    public void onResponse(Response response) throws IOException {
                        ArrayList<Spectacle> spectacles = new ArrayList<Spectacle>();

                        try {
                            String text = response.body().string();
                            //Log.d("text :", text);
                            JSONObject jsonTot = new JSONObject(text);
                            JSONArray json = jsonTot.getJSONArray("records");
                            Log.d("longueur", "" + json.length());
                            if (json.length()>0) {
                                listeTitles = new ArrayList<String>(json.length());
                                listeUids = new ArrayList<String>(json.length());
                                for (int i = 0; i < json.length(); i++) {
                                    Log.d("json ", "n°" + i + " : " + json.getJSONObject(i).getJSONObject("fields").toString());
                                    Log.d("title", json.getJSONObject(i).getJSONObject("fields").getString("title"));
                                    listeTitles.add(i, json.getJSONObject(i).getJSONObject("fields").getString("title"));
                                    Log.d("title added", "");
                                    listeUids.add(i, json.getJSONObject(i).getJSONObject("fields").getString("uid"));

                                }
                                Intent intent = new Intent(RechercheActivityImp.this, ResultatRecherche.class);
                                intent.putExtra("listeUid", utils.packageList(listeUids));
                                Log.d("uids", utils.packageList(listeUids));
                                intent.putExtra("listeTitles", utils.packageList(listeTitles));
                                startActivity(intent);
                            }
                            else {
                                msg.arg1=1;
                                handler.sendMessage(msg);
                                //Toast.makeText(getApplicationContext(), "Pas de résultat", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            Log.d("jSonexception","aaaargh");
                            //Toast.makeText(getApplicationContext(), "Pas de résultat", Toast.LENGTH_LONG).show();
                        }


                        // Context context = getApplicationContext();
                        //Toast.makeText    (context, intent.getExtras().getString("champ"), Toast.LENGTH_SHORT).show();
                    }
                });

                clear = (Button) findViewById(R.id.clear);
                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        titre.setText("");
                        lieu.setText("");
                        dateDebut.setText("");
                        dateFin.setText("");

                    }


                });

            }

        });
    }
}
