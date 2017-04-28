package com.viralandroid.tieproperty;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by T on 27-04-2017.
 */

public class Properties implements Serializable{
    public String id,title,prop_code,area_id,area_title,address,location,latitude,longitude,category_id,
            category_title,description,offer,offer_desc,possession,stats,stats_type;
    public ArrayList<Images> images;
    public ArrayList<Floorplans> floorplans;
    public ArrayList<Flats> flats;
    public Properties(JsonObject jsonObject, Context context){
        id = jsonObject.get("id").getAsString();
        title = jsonObject.get("title").getAsString();
        prop_code = jsonObject.get("prop_code").getAsString();
        area_id = jsonObject.get("area").getAsJsonObject().get("area_id").getAsString();
        area_title = jsonObject.get("area").getAsJsonObject().get("title").getAsString();
        address = jsonObject.get("address").getAsString();
        location = jsonObject.get("location").getAsString();
        latitude = jsonObject.get("latitude").getAsString();
        longitude = jsonObject.get("longitude").getAsString();
        category_id = jsonObject.get("category").getAsJsonObject().get("category_id").getAsString();
        category_title = jsonObject.get("category").getAsJsonObject().get("title").getAsString();
        description = jsonObject.get("description").getAsString();
        offer = jsonObject.get("offer").getAsString();
        offer_desc = jsonObject.get("offer_desc").getAsString();
        possession = jsonObject.get("possession").getAsString();
        stats = jsonObject.get("stats").getAsString();
        stats_type = jsonObject.get("stats_type").getAsString();

        images = new ArrayList<>();
        for(int i=0;i<jsonObject.get("images").getAsJsonArray().size();i++){
            Images comp_image = new Images(jsonObject.get("images").getAsJsonArray().get(i).getAsJsonObject(),context);
            images.add(comp_image);

        }

        floorplans = new ArrayList<>();
        for (int i=0; i<jsonObject.get("floorplans").getAsJsonArray().size();i++){
            Floorplans floor = new Floorplans(jsonObject.get("floorplans").getAsJsonArray().get(i).getAsJsonObject(),context);
            floorplans.add(floor);
        }

        flats = new ArrayList<>();
        for (int i=0;i<jsonObject.get("flats").getAsJsonArray().size();i++){
            Flats flat = new Flats(jsonObject.get("flats").getAsJsonArray().get(i).getAsJsonObject(),context);
            flats.add(flat);
        }


    }

    public class Images implements Serializable {
        public String image,thumb;
        public Images(JsonObject jsonObject,Context context){
            if (jsonObject.has("image")) {
                image = jsonObject.get("image").getAsString();
            }else {
                image = "no-image";
            }
            if (jsonObject.has("thumb")) {
                thumb = jsonObject.get("thumb").getAsString();
            }else {
                thumb = "no-thumb";
            }
        }
    }

    public class Floorplans implements Serializable{
        public String image,thumb;
        public Floorplans(JsonObject jsonObject,Context context){
            image = jsonObject.get("image").getAsString();
            thumb = jsonObject.get("thumb").getAsString();
        }
    }

    public class Flats implements Serializable{
        public String flat_id,title,area,type,price,description;
        public ArrayList<FlatImages> flatImages;
        public Flats(JsonObject jsonObject,Context context){
            if (jsonObject.has("flat_id")) {
                flat_id = jsonObject.get("flat_id").getAsString();
            }else {
                flat_id = "no-id";
            }
            if (jsonObject.has("title")) {
                title = jsonObject.get("title").getAsString();
            }else {
                title = "no-title";
            }
            if (jsonObject.has("area")) {
                area = jsonObject.get("area").getAsString();
            }else {
                area = "no-area";
            }
            if (jsonObject.has("type")) {
                type = jsonObject.get("type").getAsString();
            }else {
                type = "no-type";
            }
            if (jsonObject.has("price")) {
                price = jsonObject.get("price").getAsString();
            }else {
                price = "no-price";
            }
            if (jsonObject.has("description")) {
                description = jsonObject.get("description").getAsString();
            }else {
                description = "no-description";
            }

            flatImages = new ArrayList<>();
            for (int i=0;i<jsonObject.get("images").getAsJsonArray().size();i++){
                FlatImages flatimage = new FlatImages(jsonObject.get("images").getAsJsonArray().get(i).getAsJsonObject(),context);
                flatImages.add(flatimage);
            }
        }
    }

    public class FlatImages implements Serializable{
        public String image,thumb;
        public FlatImages(JsonObject jsonObject,Context context){
            if (jsonObject.has("image")) {
                image = jsonObject.get("image").getAsString();
            }else {
                image = "no-image";
            }
            if (jsonObject.has("thumb")) {
                thumb = jsonObject.get("thumb").getAsString();
            }else {
                thumb = "no-thumb";
            }
        }
    }
}
