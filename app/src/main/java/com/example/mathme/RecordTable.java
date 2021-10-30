package com.example.mathme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RecordTable extends AppCompatActivity {

    static ListView listView;
    String str, str1;
    int Score, i;
    TextView Tview;
    int x;
    Button Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_table);


        Tview=findViewById(R.id.textView5);
        if(LoginActivity.str1==null)
        {
            Tview.setText(RegisterActivity.str1);
        }
        else
        {
            Tview.setText(LoginActivity.str1);
        }


        listView=(ListView) findViewById(R.id.listViewTable);

        ArrayList<String> arrayList=new ArrayList<>() ;


        if (EasyLevel.y==0)
        {
            arrayList.add(EasyLevel.scoreText.getText().toString());
        }
        if(HardLevel.y==1)
        {
            arrayList.add(HardLevel.scoreText.getText().toString());
        }




        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
       listView.setAdapter(arrayAdapter);
        //ArrayAdapter arrayAdapter1=new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList1);
       //listView.setAdapter(arrayAdapter1);
       // Back = findViewById(R.id.button);
        //Back.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View view) {
            //    Intent intent = new Intent(RecordTable.this, Home.class);
              //  startActivity(intent);
           // }
       // });
    }

}