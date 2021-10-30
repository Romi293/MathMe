package com.example.mathme;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    ImageButton speaker;
    MediaPlayer song;
    boolean play = true;
    Button diffiLevels, Totable;
    static TextView tview;
    String str;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        song = MediaPlayer.create(Home.this, R.raw.clas);

        tview=findViewById(R.id.textView);



        if(LoginActivity.str1==null)
        {
            tview.setText(RegisterActivity.str1);
        }
        else
        {
            tview.setText(LoginActivity.str1);
        }


        diffiLevels = findViewById(R.id.diffiLevelBtn);
        diffiLevels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Levels.class);
                startActivity(intent);
            }
        });

        speaker = findViewById(R.id.speakerBtn);
        PlayIt();
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(play)
                    StopIt();
                else
                    PlayIt();
            }
        });
        Totable = findViewById(R.id.button3);
        Totable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, RecordTable.class);
                startActivity(intent);
            }
        });

    }
    public void Instructions (View view)
    {

        AlertDialog alertDialog = new AlertDialog.Builder(Home.this).create(); //הוראות הפעלה. ברגע שלוחצים על כפתור אינסטרקשיין אז זה נכנס למתודה הזאת.
        alertDialog.setTitle(R.string.Instructions);
        alertDialog.setMessage(Home.this.getString(R.string.InstrucText));

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alertDialog.show();

    }

    public void PlayIt () {
        song.start();
        speaker.setBackgroundResource(R.drawable.speaker_on);
        play = true;
    }

    public void StopIt ()  {
        song.pause();
        speaker.setBackgroundResource(R.drawable.speaker_off);
        play = false;
    }
}