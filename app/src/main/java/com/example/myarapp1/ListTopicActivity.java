package com.example.myarapp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ListTopicActivity extends AppCompatActivity {
    Topic[] arrayTopic = new Topic[3];
    String[] list_topic = {"Ocean", "Home", "Person", "Tool", "Animal", "Plant", "Vehicle"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_topic);

        for ( int i=0; i<arrayTopic.length; i++) {
            arrayTopic[i]=new Topic();
        }

        for (int i=0; i<arrayTopic.length; i++) {
            arrayTopic[i].topicId=i+1;
            arrayTopic[i].topicName = list_topic[i];
            arrayTopic[i].size = 10;
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.topic, list_topic);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent = new Intent(ListTopicActivity.this, ListOceanActivity.class);
                        break;
                    case 1:
                        intent = new Intent(ListTopicActivity.this, ListHomeActivity.class);
                        break;
                    case 2:
                        intent = new Intent(ListTopicActivity.this, ListPersonActivity.class);
                        break;
                    case 3:
                        intent = new Intent(ListTopicActivity.this, ListToolActivity.class);
                        break;
                    case 4:
                        intent = new Intent(ListTopicActivity.this, ListAnimalActivity.class);
                        break;
                    case 5:
                        intent = new Intent(ListTopicActivity.this, ListPlantActivity.class);
                        break;
                    case 6:
                        intent = new Intent(ListTopicActivity.this, ListVehicleActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }
}
