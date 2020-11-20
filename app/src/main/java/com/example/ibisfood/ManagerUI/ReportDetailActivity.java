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
            detailKategoriEnam,
            detailInputKategoriSatu,
            detailInputKategoriDua,
            detailInputKategoriTiga,
            detailInputKategoriEmpat,
            detailInputKategoriLima,
            detailInputKategoriEnam;
    TextView textSelesai1, textSelesai2, textSelesai3, textSelesai4,textSelesai5,textSelesai6;
    TextView textBelumSelesai1, textBelumSelesai2, textBelumSelesai3, textBelumSelesai4,textBelumSelesai5,textBelumSelesai6;
    TextView detailSelesaiSatu, detailSelesaiDua, detailSelesaiTiga, detailSelesaiEmpat, detailSelesaiLima, detailSelesaiEnam;
    TextView detailBelumSelesaiSatu, detailBelumSelesaiDua, detailBelumSelesaiTiga, detailBelumSelesaiEmpat, detailBelumSelesaiLima, detailBelumSelesaiEnam;
    TextView detailDeskripsiSatu, detailDeskripsiDua, detailDeskripsiTiga, detailDeskripsiEmpat, detailDeskripsiLima, detailDeskripsiEnam;
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
                detailKategoriEnam = findViewById( R.id.detail_kategori_enam);
                detailInputKategoriSatu = findViewById( R.id.detail_input_kategori_satu);
                detailInputKategoriDua = findViewById( R.id.detail_input_kategori_dua);
                detailInputKategoriTiga = findViewById( R.id.detail_input_kategori_tiga);
                detailInputKategoriEmpat = findViewById( R.id.detail_input_kategori_empat);
                detailInputKategoriLima = findViewById( R.id.detail_input_kategori_lima);
                detailInputKategoriEnam = findViewById( R.id.detail_input_kategori_enam);
                textSelesai1 = findViewById(R.id.detail_textselsai_satu);
                textSelesai2 = findViewById(R.id.detail_textselsai_dua);
                textSelesai3 = findViewById(R.id.detail_textselsai_tiga);
                textSelesai4 = findViewById(R.id.detail_textselsai_empat);
                textSelesai5 = findViewById(R.id.detail_textselsai_lima);
                textSelesai6 = findViewById(R.id.detail_textselsai_enam);
                textBelumSelesai1 = findViewById(R.id.detail_textbelumselsai_satu);
                textBelumSelesai2 = findViewById(R.id.detail_textbelumselsai_dua);
                textBelumSelesai3 = findViewById(R.id.detail_textbelumselsai_tiga);
                textBelumSelesai4 = findViewById(R.id.detail_textbelumselsai_empat);
                textBelumSelesai5 = findViewById(R.id.detail_textbelumselsai_lima);
                textBelumSelesai6 = findViewById(R.id.detail_textbelumselsai_enam);
                detailSelesaiSatu = findViewById(R.id.detail_input_kategori_selesai_satu);
                detailSelesaiDua = findViewById(R.id.detail_input_kategori_selesai_dua);
                detailSelesaiTiga = findViewById(R.id.detail_input_kategori_selesai_tiga);
                detailSelesaiEmpat = findViewById(R.id.detail_input_kategori_selesai_empat);
                detailSelesaiLima = findViewById(R.id.detail_input_kategori_selesai_lima);
                detailSelesaiEnam = findViewById(R.id.detail_input_kategori_selesai_enam);
                detailBelumSelesaiSatu = findViewById(R.id.detail_input_kategori_belumselesai_satu);
                detailBelumSelesaiDua = findViewById(R.id.detail_input_kategori_belumselesai_dua);
                detailBelumSelesaiTiga = findViewById(R.id.detail_input_kategori_belumselesai_tiga);
                detailBelumSelesaiEmpat = findViewById(R.id.detail_input_kategori_belumselesai_empat);
                detailBelumSelesaiLima = findViewById(R.id.detail_input_kategori_belumselesai_lima);
                detailBelumSelesaiEnam = findViewById(R.id.detail_input_kategori_belumselesai_enam);
                detailDeskripsiSatu = findViewById(R.id.detail_deskripsi_satu);
                detailDeskripsiDua = findViewById(R.id.detail_deskripsi_dua);
                detailDeskripsiTiga = findViewById(R.id.detail_deskripsi_tiga);
                detailDeskripsiEmpat = findViewById(R.id.detail_deskripsi_empat);
                detailDeskripsiLima = findViewById(R.id.detail_deskripsi_lima);
                detailDeskripsiEnam = findViewById(R.id.detail_deskripsi_enam);




        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();


        //email
        String postEmail = getIntent().getExtras().getString("uAdminEmail");
        detailEmailAdmin.setText(postEmail);

        //nama
        String postPenanggungJawab = getIntent().getExtras().getString("adminPenanggungJawab");
        detailPenanggungJawab.setText(postPenanggungJawab);

        //kategori
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

        String postKategoriEnam = getIntent().getExtras().getString("adminKategoriEnam");
        detailKategoriEnam.setText(postKategoriEnam);

        //detail input kategori
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

        String postInputKategoriEnam = getIntent().getExtras().getString("adminInputKategoriEnam");
        detailInputKategoriEnam.setText(postInputKategoriEnam);

        //text selesai kategori
        String selesai1 = getIntent().getExtras().getString("textSelesaiKategoriSatu");
        textSelesai1.setText(selesai1);

        String selesai2 = getIntent().getExtras().getString("textSelesaiKategoriDua");
        textSelesai2.setText(selesai2);

        String selesai3 = getIntent().getExtras().getString("textSelesaiKategoriTiga");
        textSelesai3.setText(selesai3);

        String selesai4 = getIntent().getExtras().getString("textSelesaiKategoriEmpat");
        textSelesai4.setText(selesai4);

        String selesai5 = getIntent().getExtras().getString("textSelesaiKategoriLima");
        textSelesai5.setText(selesai5);

        String selesai6 = getIntent().getExtras().getString("textSelesaiKategoriEnam");
        textSelesai6.setText(selesai6);

        //text belum selesai kategori
        String belumSelesai1 = getIntent().getExtras().getString("textBelumSelesaiKategoriSatu");
        textBelumSelesai1.setText(belumSelesai1);

        String belumSelesai2 = getIntent().getExtras().getString("textBelumSelesaiKategoriDua");
        textBelumSelesai2.setText(belumSelesai2);

        String belumSelesai3 = getIntent().getExtras().getString("textBelumSelesaiKategoriTiga");
        textBelumSelesai3.setText(belumSelesai3);

        String belumSelesai4 = getIntent().getExtras().getString("textBelumSelesaiKategoriEmpat");
        textBelumSelesai4.setText(belumSelesai4);

        String belumSelesai5 = getIntent().getExtras().getString("textBelumSelesaiKategoriLima");
        textBelumSelesai5.setText(belumSelesai5);

        String belumSelesai6 = getIntent().getExtras().getString("textBelumSelesaiKategoriEnam");
        textBelumSelesai6.setText(belumSelesai6);


        //isi selesai
        String angkaSelesai1 = getIntent().getExtras().getString("adminInputSelesaiKategoriSatu");
        detailSelesaiSatu.setText(angkaSelesai1);

        String angkaSelesai2 = getIntent().getExtras().getString("adminInputSelesaiKategoriDua");
        detailSelesaiDua.setText(angkaSelesai2);

        String angkaSelesai3 = getIntent().getExtras().getString("adminInputSelesaiKategoriTiga");
        detailSelesaiTiga.setText(angkaSelesai3);

        String angkaSelesai4 = getIntent().getExtras().getString("adminInputSelesaiKategoriEmpat");
        detailSelesaiEmpat.setText(angkaSelesai4);

        String angkaSelesai5 = getIntent().getExtras().getString("adminInputSelesaiKategoriLima");
        detailSelesaiLima.setText(angkaSelesai5);

        String angkaSelesai6 = getIntent().getExtras().getString("adminInputSelesaiKategoriEnam");
        detailSelesaiEnam.setText(angkaSelesai6);


        //isi belum selesai
        String angkaBelumSelesai1 = getIntent().getExtras().getString("adminInputBelumSelesaiKategoriSatu");
        detailBelumSelesaiSatu.setText(angkaBelumSelesai1);

        String angkaBelumSelesai2 = getIntent().getExtras().getString("adminInputBelumSelesaiKategoriDua");
        detailBelumSelesaiDua.setText(angkaBelumSelesai2);

        String angkaBelumSelesai3 = getIntent().getExtras().getString("adminInputBelumSelesaiKategoriTiga");
        detailBelumSelesaiTiga.setText(angkaBelumSelesai3);

        String angkaBelumSelesai4 = getIntent().getExtras().getString("adminInputBelumSelesaiKategoriEmpat");
        detailBelumSelesaiEmpat.setText(angkaBelumSelesai4);

        String angkaBelumSelesai5 = getIntent().getExtras().getString("adminInputBelumSelesaiKategoriLima");
        detailBelumSelesaiLima.setText(angkaBelumSelesai5);

        String angkaBelumSelesai6 = getIntent().getExtras().getString("adminInputBelumSelesaiKategoriEnam");
        detailBelumSelesaiEnam.setText(angkaBelumSelesai6);

        //isi deskripsi
        String deskripsi1 = getIntent().getExtras().getString("adminInputDeskripsiKategoriSatu");
        detailDeskripsiSatu.setText(deskripsi1);

        String deskripsi2 = getIntent().getExtras().getString("adminInputDeskripsiKategoriDua");
        detailDeskripsiDua.setText(deskripsi2);

        String deskripsi3 = getIntent().getExtras().getString("adminInputDeskripsiKategoriTiga");
        detailDeskripsiTiga.setText(deskripsi3);

        String deskripsi4 = getIntent().getExtras().getString("adminInputDeskripsiKategoriEmpat");
        detailDeskripsiEmpat.setText(deskripsi4);

        String deskripsi5 = getIntent().getExtras().getString("adminInputDeskripsiKategoriLima");
        detailDeskripsiLima.setText(deskripsi5);

        String deskripsi6 = getIntent().getExtras().getString("adminInputDeskripsiKategoriEnam");
        detailDeskripsiEnam.setText(deskripsi6);




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
        String date = DateFormat.format("dd MMMM yyyy",calendar).toString();
        return date;



    }
}