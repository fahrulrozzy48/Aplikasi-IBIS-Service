package com.example.ibisfood.UserUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.example.ibisfood.R;

import java.util.Locale;

public class SettingUserActivity extends AppCompatActivity {


    CardView btnGantiBahasa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_user);
        loadLocale();
        btnGantiBahasa = findViewById(R.id.btn_gantiBahasa);




        btnGantiBahasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGantiBahasaDialog();
            }
        });
    }

    private void showGantiBahasaDialog() {
        final String[] listItemBahasa = {"Bahasa Indonesia","English"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingUserActivity.this);
        mBuilder.setSingleChoiceItems(listItemBahasa, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    setLocale("in");
                    recreate();
                } else if (which == 1){
                    setLocale("en");
                    recreate();
                }

                dialog.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        //simpan data reference
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang","");
        setLocale(language);
    }
}