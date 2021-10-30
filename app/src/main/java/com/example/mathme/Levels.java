package com.example.mathme;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Levels extends AppCompatActivity implements View.OnClickListener {
    private Button easyLevel, hardLevel;
    FloatingActionButton backButton;
    public static int i=5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        easyLevel = findViewById(R.id.easyBtn);
        easyLevel.setOnClickListener(this);

        hardLevel = findViewById(R.id.hardBtn);
        hardLevel.setOnClickListener(this);

        backButton = findViewById(R.id.fabBackBtn);
        backButton.setOnClickListener(this);
    }


    public void onClick(View view) {
        if (view == backButton) {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }

        if(view == easyLevel){
            Intent intent = new Intent(this, EasyLevel.class);
            i=0;

            startActivity(intent);
        }

        if(view == hardLevel){
            Intent intent = new Intent(this, HardLevel.class);
            i=1;

            startActivity(intent);
        }
    }

}