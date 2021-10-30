package com.example.mathme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {

    static TextView score, tview;
    String scoreFromHard;
    static int x, i=0;
    Button END;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        score = findViewById(R.id.scoreResult);
        //score.setText(HardLevel.scoreText.getText().toString());
       // score.setText(EasyLevel.scoreText.getText().toString());

        tview=findViewById(R.id.textView4);
        x=Levels.i;

        if(x==0)
        {
           score.setText(EasyLevel.scoreText.getText().toString());


        }
        if(x==1)
        {
           score.setText(HardLevel.scoreText.getText().toString());

        }



        if(LoginActivity.str1==null)
        {
            tview.setText(RegisterActivity.str1);
        }
        else
        {
            tview.setText(LoginActivity.str1);
        }



    }
    public void rest(View view)
    {
        startActivity(new Intent(getApplicationContext(), Levels.class));
        Levels.i=5;
    }
    public void end(View view)
    {
        startActivity(new Intent(Result.this, Home.class));
        Levels.i=5;
    }

}