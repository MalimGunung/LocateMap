package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Group_Members extends AppCompatActivity {
    private ImageButton openActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_members);

        openActivity = (ImageButton) findViewById(R.id.imageButton6);
        openActivity = (ImageButton) findViewById(R.id.imageButton7);
    }

    public void openActivity1(View view){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

    public void openActivity2(View view){
        Intent intent = new Intent (this, MainActivity2.class);
        startActivity(intent);
    }
}