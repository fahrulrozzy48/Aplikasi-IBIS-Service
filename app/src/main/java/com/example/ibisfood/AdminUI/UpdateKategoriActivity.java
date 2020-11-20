package com.example.ibisfood.AdminUI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ibisfood.Model.SpinnerModel;
import com.example.ibisfood.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateKategoriActivity extends AppCompatActivity {

    //Deklarasi Variable
    private EditText namaKategoriBaru;
    private Button update;
    private DatabaseReference database;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kategori);

        namaKategoriBaru = findViewById(R.id.new_kategori);
        update = findViewById(R.id.updateKategori);

        //Mendapatkan Instance autentikasi dan Referensi dari Database
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        final SpinnerModel spinnerModel = (SpinnerModel)  getIntent().getSerializableExtra("data");

        if (spinnerModel != null){
            namaKategoriBaru.setText(spinnerModel.getNameKategori());
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spinnerModel.setNameKategori(namaKategoriBaru.getText().toString());

                    updateKategori(spinnerModel);
                }
            });
        } else {
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (!(namaKategoriBaru.getText().toString().isEmpty())){
//
//                    }
                }
            });
        }
    }

    private void updateKategori(SpinnerModel spinnerModel) {
        database.child("Kategori").child(spinnerModel.getKey()).setValue(spinnerModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Snackbar.make(findViewById(R.id.updateKategori),"Data berhasil diupdate", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }).show();
            }
        });

    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, UpdateKategoriActivity.class);
    }


}