package com.viralandroid.tieproperty;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class MainActivity extends Activity {
    ListView listView;
    PropertyListAdapter propertyListAdapter;
    TextView select_city;
    String cities,mobile,city_id,cat_id,areas_id;
    ImageView back_btn,location,select_category;
    ArrayList<Properties> propertiesfrom_api;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_list);
        listView = (ListView) findViewById(R.id.property_list);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        select_city = (TextView) findViewById(R.id.select_city);
        location = (ImageView) findViewById(R.id.location);
        select_category = (ImageView) findViewById(R.id.select_category);
        propertiesfrom_api = new ArrayList<>();



        if (getIntent()!=null && getIntent().hasExtra("city")){
            cities = getIntent().getStringExtra("city");
            city_id = getIntent().getStringExtra("id");
            mobile = getIntent().getStringExtra("phone");
            cat_id = getIntent().getStringExtra("category_id");
            areas_id = getIntent().getStringExtra("area_id");
        }


        select_city.setText(cities);
        select_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CityPage.class);
                startActivity(intent);
            }
        });

        select_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CategoryPage.class);
                startActivity(intent);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CityPage.class);
                startActivity(intent);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               MainActivity.this.onBackPressed();
            }
        });

        propertyListAdapter = new PropertyListAdapter(MainActivity.this,mobile,propertiesfrom_api);
        listView.setAdapter(propertyListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,PropertyDetailPage.class);
                intent.putExtra("property",propertiesfrom_api.get(i));
                startActivity(intent);
            }
        });

        get_properties();
    }

    public void get_properties(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Ion.with(this)
                .load(Session.SERVER_URL+"properties.php")
                .setBodyParameter("city",city_id)
                .setBodyParameter("area",areas_id)
                .setBodyParameter("category",cat_id)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (progressDialog!=null)
                            progressDialog.dismiss();
                        try {
                            Log.e("propertieslist",result.toString());
                            for (int i=0;i<result.size();i++){
                                Properties properties = new Properties(result.get(i).getAsJsonObject(),MainActivity.this);
                                propertiesfrom_api.add(properties);
                            }
                            propertyListAdapter.notifyDataSetChanged();

                        }catch (Exception e1){
                            e1.printStackTrace();
                        }


                    }
                });
    }


}
