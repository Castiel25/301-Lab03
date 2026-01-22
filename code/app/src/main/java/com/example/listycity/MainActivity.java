package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;

    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }

    public void editCity(City oldCity, City newCity){
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {
                "Edmonton", "Vancouver", "Saskatoon"
        };
        String[] provinces = {
                "AB" , "BC", "SK"
        };

        dataList = new ArrayList<>();
        for (int i = 0; i < cities.length; i++){
            dataList.add(new City(cities[i],provinces[i]));
        }

        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);



        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(), "Add City");
        });
        // Same logic used in Lab 2 but instead of String, Using City
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            City clicked = dataList.get(position);
            /*Toast.makeText(this , "SELECTED: " + clicked.getName().toUpperCase(), Toast.LENGTH_SHORT).show();*/
            AddCityFragment fragment = AddCityFragment.newInstance(clicked);
            fragment.show(getSupportFragmentManager(), "Edit City");
        });
    }
}