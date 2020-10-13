package com.example.ibisfood.ManagerUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibisfood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;

public class ReportDetailActivity extends AppCompatActivity {

    TextView detailEmailAdmin,
            detailPenanggungJawab,
            detailTime,
            detailTimeAwal,
            detailTimeAkhir,
            detailKategoriSatu,
            detailKategoriDua,
            detailKategoriTiga,
            detailKategoriEmpat,
            detailKategoriLima,
            detailInputKategoriSatu,
            detailInputKategoriDua,
            detailInputKategoriTiga,
            detailInputKategoriEmpat,
            detailInputKategoriLima;
    String PostKey;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);

                detailEmailAdmin = findViewById(R.id.detail_email_admin);
                detailPenanggungJawab = findViewById(R.id.detail_penanggung_jawab);
                detailTime = findViewById(R.id.detail_time);
                detailTimeAwal = findViewById(R.id.detail_awal_tanggal);
                detailTimeAkhir = findViewById( R.id.detail_akhir_tanggal);
                detailKategoriSatu = findViewById( R.id.detail_kategori_satu);
                detailKategoriDua = findViewById( R.id.detail_kategori_dua);
                detailKategoriTiga = findViewById( R.id.detail_kategori_tiga);
                detailKategoriEmpat = findViewById( R.id.detail_kategori_empat);
                detailKategoriLima = findViewById( R.id.detail_kategori_lima);
                detailInputKategoriSatu = findViewById( R.id.detail_input_kategori_satu);
                detailInputKategoriDua = findViewById( R.id.detail_input_kategori_dua);
                detailInputKategoriTiga = findViewById( R.id.detail_input_kategori_tiga);
                detailInputKategoriEmpat = findViewById( R.id.detail_input_kategori_empat);
                detailInputKategoriLima = findViewById( R.id.detail_input_kategori_lima);




        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();


        String postEmail = getIntent().getExtras().getString("uAdminEmail");
        detailEmailAdmin.setText(postEmail);

        String postPenanggungJawab = getIntent().getExtras().getString("adminPenanggungJawab");
        detailPenanggungJawab.setText(postPenanggungJawab);

        String postKategoriSatu = getIntent().getExtras().getString("adminKategoriSatu");
        detailKategoriSatu.setText(postKategoriSatu);

        String postKategoriDua = getIntent().getExtras().getString("adminKategoriDua");
        detailKategoriDua.setText(postKategoriDua);

        String postKategoriTiga = getIntent().getExtras().getString("adminKategoriTiga");
        detailKategoriTiga.setText(postKategoriTiga);

        String postKategoriEmpat = getIntent().getExtras().getString("adminKategoriEmpat");
        detailKategoriEmpat.setText(postKategoriEmpat);

        String postKategoriLima = getIntent().getExtras().getString("adminKategoriLima");
        detailKategoriLima.setText(postKategoriLima);

        String postInputKategoriSatu = getIntent().getExtras().getString("adminInputKategoriSatu");
        detailInputKategoriSatu.setText(postInputKategoriSatu);

        String postInputKategoriDua = getIntent().getExtras().getString("adminInputKategoriDua");
        detailInputKategoriDua.setText(postInputKategoriDua);

        String postInputKategoriTiga = getIntent().getExtras().getString("adminInputKategoriTiga");
        detailInputKategoriTiga.setText(postInputKategoriTiga);

        String postInputKategoriEmpat = getIntent().getExtras().getString("adminInputKategoriEmpat");
        detailInputKategoriEmpat.setText(postInputKategoriEmpat);

        String postInputKategoriLima = getIntent().getExtras().getString("adminInputKategoriLima");
        detailInputKategoriLima.setText(postInputKategoriLima);



        // get post id
        PostKey = getIntent().getExtras().getString("postAdminKey");

        String date = timestampToString(getIntent().getExtras().getLong("adminPostTime"));
        detailTime.setText(date);

        String dateAwal = timestampToStringDua(getIntent().getExtras().getLong("adminAwalTime"));
        detailTimeAwal.setText(dateAwal);

        String dateAkhir = timestampToStringDua(getIntent().getExtras().getLong("adminAkhirTime"));
        detailTimeAkhir.setText(dateAkhir);

    }

    private String timestampToString(long time) {

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd/MM/yyyy HH:mm",calendar).toString();
        return date;



    }

    private String timestampToStringDua(long time) {

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd/MM/yyyy",calendar).toString();
        return date;



    }
}