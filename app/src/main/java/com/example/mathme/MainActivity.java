package com.example.mathme;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signIn;
    private Button signUp;

    //For localization
    private ActionBar actionBar;
    private ImageButton chngLan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        signUp = findViewById(R.id.signUpBtn);
        signUp.setOnClickListener(this);

        signIn = findViewById(R.id.signInBtn);
        signIn.setOnClickListener(this);

        chngLan = findViewById(R.id.chngLanBtn);
        chngLan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();
            }
        });
    }

    public void onClick(View view) {
        if(view == signIn) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        if(view == signUp) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    public void showChangeLanguageDialog() {
        final String[] listItems = {"עברית", "English"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(R.string.chngLan);
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0) {
                    //Hebrew
                    setLocale("iw");
                    recreate();
                }
                else if(i == 1){
                    //English
                    setLocale("en");
                    recreate();
                }

                dialogInterface.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        //show alert dialog
        mDialog.show();
    }

    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext(). getResources().getDisplayMetrics());
        //save data to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My Lang", lang);
        editor.apply();
    }

    private void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("My Lang", "");
        setLocale(language);
    }





}