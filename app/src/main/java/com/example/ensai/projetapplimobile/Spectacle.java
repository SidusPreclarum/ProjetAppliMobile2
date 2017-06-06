package com.example.ensai.projetapplimobile;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Created by ensai on 27/05/17.
 */

public class Spectacle implements Parcelable {

    private String latlon;
    private String lang;
    private String city;
    private String uid;
    private String title;
    private String pricing_info;
    private String image;
    private String date_start;
    private String updated_at;
    private String space_time_info;
    private String department;
    private String link;
    private String free_text;
    private String address;
    private String placename;
    private String timetable;
    private String image_thumb;
    private String region;
    private String date_end;
    private String tags;
    private String description;


    public Spectacle() {

    }

    public Spectacle(String latlon, String lang, String city, String uid, String title, String pricing_info, String image, String date_start, String updated_at, String space_time_info, String department, String link, String free_text, String address, String placename, String timetable, String image_thumb, String region, String date_end, String tags, String description) {
        this.latlon = latlon;
        this.lang = lang;
        this.city = city;
        this.uid = uid;
        this.title = title;
        this.pricing_info = pricing_info;
        this.image = image;
        this.date_start = date_start;
        this.updated_at = updated_at;
        this.space_time_info = space_time_info;
        this.department = department;
        this.link = link;
        this.free_text = free_text;
        this.address = address;
        this.placename = placename;
        this.timetable = timetable;
        this.image_thumb = image_thumb;
        this.region = region;
        this.date_end = date_end;
        this.tags = tags;
        this.description = description;

    }


    public String getByField(Field f){
        String getFieldValue ="";
        for (Method m : this.getClass().getMethods())
            if (m.getName().startsWith("get") && m.getParameterTypes().length == 0) {
                    final Object r;
                    try {
                        r = m.invoke(this);
                        if (r != null) {
                            getFieldValue = r.toString();
                        }
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
            }
        return getFieldValue;
    }


    public void setByField(Field f, String fieldValue){
        for (Method m : this.getClass().getMethods())
            if (m.getName().startsWith("set") && m.getParameterTypes().length == 0) {
                final Object r;
                try {
                    r = m.invoke(this, fieldValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
    }

    public Spectacle(String[] params) {
        int size = this.getClass().getDeclaredFields().length - 1;
        Log.d("size",""+size);
        for (int i = 0; i < size-2; i++) {
            this.getClass().getDeclaredFields()[i].setAccessible(true);
            try {
                this.getClass().getDeclaredFields()[i].set(this, params[i]);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public Spectacle(JSONObject jsonObject) {
        for (Field f : this.getClass().getDeclaredFields()) {
            Log.d("champ recherché", f.getName());
            try {
                if (f.getName().equals("latlon")) {
                    //Log.d("Latlon est un ", jsonObject.get("latlon").getClass().toString());
                    //JSONArray latlon = jsonObject.get("latlon");
                    JSONArray j = (JSONArray) jsonObject.get(f.getName());
                    f.set(this, j.getLong(0) + ";" + j.getLong(1));
                } else {
                    f.set(this, jsonObject.get(f.getName()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getLatlon() {

        return latlon;
    }

    public void setLatlon(String latlon) {
        this.latlon = latlon;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPricing_info() {
        return pricing_info;
    }

    public void setPricing_info(String pricing_info) {
        this.pricing_info = pricing_info;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getSpace_time_info() {
        return space_time_info;
    }

    public void setSpace_time_info(String space_time_info) {
        this.space_time_info = space_time_info;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFree_text() {
        return free_text;
    }

    public void setFree_text(String free_text) {
        this.free_text = free_text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public String getImage_thumb() {
        return image_thumb;
    }

    public void setImage_thumb(String image_thumb) {
        this.image_thumb = image_thumb;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(latlon);
        dest.writeString(lang);
        dest.writeString(city);
        dest.writeString(uid);
        dest.writeString(title);
        dest.writeString(pricing_info);
        dest.writeString(image);
        //dest.writeLong(date_start.getTime());
        dest.writeString(date_start);
        dest.writeString(updated_at);
        dest.writeString(space_time_info);
        dest.writeString(department);
        dest.writeString(link);
        dest.writeString(free_text);
        dest.writeString(address);
        dest.writeString(placename);
        dest.writeString(timetable);
        dest.writeString(image_thumb);
        dest.writeString(region);
        dest.writeString(date_end);
        //dest.writeLong(date_end.getTime());
        dest.writeString(tags);
        dest.writeString(description);
    }

    public static final Parcelable.Creator<Spectacle> CREATOR = new Parcelable.Creator<Spectacle>() {
        public Spectacle createFromParcel(Parcel in) {
            return new Spectacle(in);
        }

        public Spectacle[] newArray(int size) {
            return new Spectacle[size];
        }

    };

    private Spectacle(Parcel in) {
        latlon = in.readString();
        lang = in.readString();
        city = in.readString();
        uid = in.readString();
        title = in.readString();
        pricing_info = in.readString();
        image = in.readString();
        date_start = in.readString();
        //date_start = new Date(in.readLong());
        updated_at = in.readString();
        space_time_info = in.readString();
        department = in.readString();
        link = in.readString();
        free_text = in.readString();
        address = in.readString();
        placename = in.readString();
        timetable = in.readString();
        image_thumb = in.readString();
        region = in.readString();
        date_end = in.readString();
        //date_end = new Date(in.readLong());
        tags = in.readString();
        description = in.readString();
    }

    public String[] fieldsSpectacle() {
        Log.d("fieldisation du spec", this.getTitle());

        int size = this.getClass().getDeclaredFields().length - 3;
        Log.d("size",""+size);
        String[] fields = new String[size];
        for (int i = 0; i < size; i++) {
            this.getClass().getDeclaredFields()[i].setAccessible(true);
            if (!this.getClass().getDeclaredFields()[i].getName().equals("CREATOR") &&
                    !this.getClass().getDeclaredFields()[i].getName().equals("serialVersionUID") &&
                    !this.getClass().getDeclaredFields()[i].getName().equals("$change")) {

                Log.d("fieldisation du champ", this.getClass().getDeclaredFields()[i].getName());

                try {
                    Log.d("valeur",(String) this.getClass().getDeclaredFields()[i].get(this));
                    fields[i] = (String) this.getClass().getDeclaredFields()[i].get(this);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < fields.length; i++) {
           // Log.d("Fields",  fields[i]);
            //Log.d("Fields", this.getClass().getDeclaredFields()[i].getName() + fields[i]);
        }

        return fields;
    }
    public String fieldsSpectaclePlat() {
        Log.d("fieldisation du spec", this.getTitle());
        int size = this.getClass().getDeclaredFields().length - 1;
        String fields = "";
        for (int i = 0; i < size; i++) {
            fields= fields + ", %";
            this.getClass().getDeclaredFields()[i].setAccessible(true);
            if (!this.getClass().getDeclaredFields()[i].getName().equals("CREATOR") &&
                    !this.getClass().getDeclaredFields()[i].getName().equals("serialVersionUID") &&
                    !this.getClass().getDeclaredFields()[i].getName().equals("$change")) {

                Log.d("fieldisation du champ", this.getClass().getDeclaredFields()[i].getName());
                try {
                    String field = (String) this.getClass().getDeclaredFields()[i].get(this);
                    String preparedfield = "'" +field.replace("'","`")+  "'";
                    fields = fields.substring(0,fields.length()-3) +","+ preparedfield;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } {
            Log.d("fsp Fields", fields);
            Log.d("fsp Fields", "fin");
        }

        return fields.substring(1);
    }
    public ContentValues getContentValues() {
        Log.d("ContVal du spec", this.getTitle());
        ContentValues contentValues = new ContentValues();
        int size = this.getClass().getDeclaredFields().length - 1;
        String[] fields = new String[size];
        for (int i = 0; i < size; i++) {
            fields[i] = "%";
            this.getClass().getDeclaredFields()[i].setAccessible(true);
            if (!this.getClass().getDeclaredFields()[i].getName().equals("CREATOR") &&
                    !this.getClass().getDeclaredFields()[i].getName().equals("serialVersionUID") &&
                    !this.getClass().getDeclaredFields()[i].getName().equals("$change")) {
                String preparedfield;
                Log.d("fieldisation du champ", this.getClass().getDeclaredFields()[i].getName());
                try {
                    String field = (String) this.getClass().getDeclaredFields()[i].get(this);
                    if (field!=null){
                        field = field.replace("'","`");
                        Log.d("field",field);
                        preparedfield = "'" +field.replace("'","`")+  "'";
                        Log.d("pfield",preparedfield);

                    }
                    else {field=new String();
                        preparedfield="";}
                    Log.d("prep", getClass().getDeclaredFields()[i].getName() + " "+preparedfield);
                    contentValues.put(this.getClass().getDeclaredFields()[i].getName(),preparedfield);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            //Log.d("Fields", this.getClass().getDeclaredFields()[i].getName() + fields[i]);
        }

        return contentValues;
    }


}


