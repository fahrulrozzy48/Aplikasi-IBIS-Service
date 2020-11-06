package com.example.ibisfood.AdminUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ibisfood.Model.SpinnerModel;
import com.example.ibisfood.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateKategoriActivity extends AppCompatActivity {

    //Deklarasi Variable
    private EditText namaKategoriBaru;
    private Button update;
    private DatabaseReference database;
    private FirebaseAuth auth;
    private String cekNamaKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kategori);

        namaKategoriBaru = findViewById(R.id.new_kategori);
        update = findViewById(R.id.updateKategori);

        //Mendapatkan Instance autentikasi dan Referensi dari Database
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mendapatkan Data yang akan dicek
                cekNamaKategori = namaKategoriBaru.getText().toString();

                //Mengecek agar tidak ada data yang kosong, saat proses update
                if( isEmpty(cekNamaKategori)){
                    Toast.makeText(UpdateKategoriActivity.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    /*
                      Menjalankan proses update data.
                      Method Setter digunakan untuk mendapakan data baru yang diinputkan User.
                    */
                    SpinnerModel baruKategori = new SpinnerModel();
                    baruKategori.setNameKategori(namaKategoriBaru.getText().toString());
                    updateData(baruKategori);
                }
            }
        });
    }

    private void updateData(SpinnerModel baruKategori) {
        String userID = auth.getUid();
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("Kategori").child(getKey).setValue(baruKategori).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                namaKategoriBaru.setText("");
                Toast.makeText(UpdateKategoriActivity.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void getData() {
        //Menampilkan data dari item yang dipilih sebelumnya
        final String getNama = getIntent().getExtras().getString("nameKategori");
        namaKategoriBaru.setText(getNama);
    }

    // Mengecek apakah ada data yang kosong, sebelum diupdate
    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }
}