package com.example.myarapp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }
    public void show3DObject(View view) {
        Intent intent = new Intent(MainMenuActivity.this, ListTopicActivity.class);
        startActivity(intent);
    }
    public void showSticker(View view) {
        Intent intent = new Intent(MainMenuActivity.this, PlayVideoInArScene.class);
//        Intent intent = new Intent(MainMenuActivity.this, TestRealtime.class);
        startActivity(intent);
    }
    public void showAnimation(View view) {
        Intent intent = new Intent(MainMenuActivity.this, ListAnimationActivity.class);
        startActivity(intent);
    }
}
