package com.example.ensai.projetapplimobile;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by ensai on 30/05/17.
 */

public class Utils {

    private static Utils instance;


    public Utils() {
    }

    public Utils getInstance(){
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    boolean checkIfNullEmptyOrWhite(String string){
        return ( string == null || string.trim().isEmpty());
    }

    public String packageList(ArrayList<String> strings){
        Log.d("longueur du string", ""+strings.size());
        String paquet = "";
        for (String string : strings){
            Log.d("String",string);
            paquet = paquet + "|"+ string;
        }
        paquet = paquet.substring(1);
        Log.d("paquet",paquet);
        return paquet;

    }

    public ArrayList<String> unPackageList(String stringUid){
        ArrayList<String> listeUids = new ArrayList<>();
        Log.d("paquet reçu",stringUid);
;        if (stringUid.contains("|"))
        {
            String[] tabUid = stringUid.split("\\|");
            Log.d("tabUid", ""+tabUid.length);
            for (int i = 0; i < tabUid.length; i++)
            {
                listeUids.add(tabUid[i]);
            }
        }

        else{
            listeUids.add(stringUid);
        }
        return listeUids;
    }

}
