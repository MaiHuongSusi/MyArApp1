package com.example.myarapp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ListTopicActivity extends AppCompatActivity {
    ImageView solar, ocean, home, person, animal, plant, vehicle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_topic);

        solar = findViewById(R.id.solar);
        ocean = findViewById(R.id.ocean);
        home = findViewById(R.id.home);
        person = findViewById(R.id.person);
        animal = findViewById(R.id.animal);
        plant = findViewById(R.id.plant);
        vehicle = findViewById(R.id.vehicle);

        solar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListTopicActivity.this, SolarActivity.class);
                startActivity(intent);
            }
        });
        ocean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListTopicActivity.this, ListOceanActivity.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListTopicActivity.this, ListHomeActivity.class);
                startActivity(intent);
            }
        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListTopicActivity.this, ListPersonActivity.class);
                startActivity(intent);
            }
        });
        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListTopicActivity.this, ListAnimalActivity.class);
                startActivity(intent);
            }
        });
        plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListTopicActivity.this, ListPlantActivity.class);
                startActivity(intent);
            }
        });
        vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListTopicActivity.this, ListVehicleActivity.class);
                startActivity(intent);
            }
        });
    }
}
