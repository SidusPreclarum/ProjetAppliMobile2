package com.example.ensai.projetapplimobile;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ensai on 27/05/17.
 */

public class Spectacle {

    float[] latlon;
    private String  lang;
    private String  city;
    private String  uid ;
    private String  title;
    private String  pricing_info;
    private  String  image;
    private  Date date_start;
    private String  updated_at;
    private  String  space_time_info;
    private  String  department;
    private String  link;
    private  String  free_text;
    private  String  address;
    private  String  placename;
    private  String  timetable;
    private  String  image_thumb;
    private  String  region;
    private  Date date_end;
    private  String  tag;
    private   String  description;

    public Spectacle(){

    }

    public Spectacle(float[] latlon, String lang, String city, String uid, String title, String pricing_info, String image, Date date_start, String updated_at, String space_time_info, String department, String link, String free_text, String address, String placename, String timetable, String image_thumb, String region, Date date_end, String tag, String description) {
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
        this.tag = tag;
        this.description = description;
    }

    public float[] getLatlon() {

        return latlon;
    }

    public void setLatlon(float[] latlon) {
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

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
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

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


