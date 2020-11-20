package com.example.ibisfood.AdminUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibisfood.Model.PostReportFromAdminToManagerModel;
import com.example.ibisfood.R;
import com.example.ibisfood.UserUI.AddPostServicesActivity;
import com.example.ibisfood.UserUI.MainActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LaporanBulananAdminActivity extends AppCompatActivity {

    TextView tambahKolom2, tambahKolom3, tambahKolom4, tambahKolom5, tambahKolom6;

    Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6;
    ArrayList<String> arrayList = new ArrayList<>();


    EditText etKolom1, etKolom2, etKolom3, etKolom4, etKolom5, etKolom6;
    EditText selesaiKolom1, selesaiKolom2, selesaiKolom3, selesaiKolom4, selesaiKolom5, selesaiKolom6;
    EditText belumselesaiKolom1, belumselesaiKolom2, belumselesaiKolom3, belumselesaiKolom4, belumselesaiKolom5, belumselesaiKolom6;
    EditText deskripsiKolom1, deskripsiKolom2, deskripsiKolom3, deskripsiKolom4, deskripsiKolom5, deskripsiKolom6;

    TextView textSelesaiKolom1, textSelesaiKolom2, textSelesaiKolom3, textSelesaiKolom4, textSelesaiKolom5, textSelesaiKolom6;
    TextView textBelumSelesaiKolom1, textBelumSelesaiKolom2, textBelumSelesaiKolom3, textBelumSelesaiKolom4, textBelumSelesaiKolom5, textBelumSelesaiKolom6;

    TextView pilihKategori1, pilihKategori2, pilihKategori3, pilihKategori4, pilihKategori5, pilihKategori6;
    EditText namaPenanggungJawab;
    Button sendLaporan;

    ProgressDialog pd;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;

    //Date
    Date tgl_pengajuanLaporanWeekly_DariDate, tgl_pengajuanLaporanWeekly_SampaiDate;
//    Date tgl_pengajuanLaporanWeekly_DariDate, tgl_pengajuanLaporanWeekly_SampaiDate, tgl_pengajuanWeekly;
    TextView laporDateDariTanggal, laporDateSampaiTanggal;
//    TextView laporDateDariTanggal, laporDateSampaiTanggal, laporKirimTanggal;
//    Button  btnPilihTanggalLapor;
    CardView btnDariTanggalLaporan, btnSampaiTanggalLaporan;
    Calendar calendar = Calendar.getInstance();
    Locale id = new Locale("in","ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY",id);


    PostReportFromAdminToManagerModel postReportFromAdminToManagerModel;

    String[] dataKategori = {"", "Makanan", "Minuman", "Wifi", "Elektronik", "Kesehatan", "Kebersihan"};
    ArrayList<String> arrayListKategori;
    ArrayAdapter<String> arrayAdapterKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_bulanan_admin);

        //tambah kolom baru
        tambahKolom2 = findViewById(R.id.tambahData2);
        tambahKolom3 = findViewById(R.id.tambahData3);
        tambahKolom4 = findViewById(R.id.tambahData4);
        tambahKolom5 = findViewById(R.id.tambahData5);
        tambahKolom6 = findViewById(R.id.tambahData6);

        //calendar date
        laporDateDariTanggal = findViewById(R.id.laporan_dariTanggal);
        laporDateSampaiTanggal = findViewById(R.id.laporan_sampaiTanggal);
//        laporKirimTanggal = findViewById(R.id.laporan_pilihTanggal);

        //spinner
        spinner1 = findViewById(R.id.spinner_kategori1);
        spinner2 = findViewById(R.id.spinner_kategori2);
        spinner3 = findViewById(R.id.spinner_kategori3);
        spinner4 = findViewById(R.id.spinner_kategori4);
        spinner5 = findViewById(R.id.spinner_kategori5);
        spinner6 = findViewById(R.id.spinner_kategori6);

        //pilih kategori
        pilihKategori1 = findViewById(R.id.pilihKategori1);
        pilihKategori2 = findViewById(R.id.pilihKategori2);
        pilihKategori3 = findViewById(R.id.pilihKategori3);
        pilihKategori4 = findViewById(R.id.pilihKategori4);
        pilihKategori5 = findViewById(R.id.pilihKategori5);
        pilihKategori6 = findViewById(R.id.pilihKategori6);

        //input kolom data spinner
        etKolom1 = findViewById(R.id.input_laporan_kategori1);
        etKolom2 = findViewById(R.id.input_laporan_kategori2);
        etKolom3 = findViewById(R.id.input_laporan_kategori3);
        etKolom4 = findViewById(R.id.input_laporan_kategori4);
        etKolom5 = findViewById(R.id.input_laporan_kategori5);
        etKolom6 = findViewById(R.id.input_laporan_kategori6);


        //text selesai kolom
        textSelesaiKolom1 = findViewById(R.id.textSelesaiKolom1);
        textSelesaiKolom2 = findViewById(R.id.textSelesaiKolom2);
        textSelesaiKolom3 = findViewById(R.id.textSelesaiKolom3);
        textSelesaiKolom4 = findViewById(R.id.textSelesaiKolom4);
        textSelesaiKolom5 = findViewById(R.id.textSelesaiKolom5);
        textSelesaiKolom6 = findViewById(R.id.textSelesaiKolom6);

        //input selesai kolom
        selesaiKolom1 = findViewById(R.id.input_laporan_selesai_kategori1);
        selesaiKolom2 = findViewById(R.id.input_laporan_selesai_kategori2);
        selesaiKolom3 = findViewById(R.id.input_laporan_selesai_kategori3);
        selesaiKolom4 = findViewById(R.id.input_laporan_selesai_kategori4);
        selesaiKolom5 = findViewById(R.id.input_laporan_selesai_kategori5);
        selesaiKolom6 = findViewById(R.id.input_laporan_selesai_kategori6);

        //text belum selesai kolom
        textBelumSelesaiKolom1 = findViewById(R.id.textBelumSelesaiKolom1);
        textBelumSelesaiKolom2 = findViewById(R.id.textBelumSelesaiKolom2);
        textBelumSelesaiKolom3 = findViewById(R.id.textBelumSelesaiKolom3);
        textBelumSelesaiKolom4 = findViewById(R.id.textBelumSelesaiKolom4);
        textBelumSelesaiKolom5 = findViewById(R.id.textBelumSelesaiKolom5);
        textBelumSelesaiKolom6 = findViewById(R.id.textBelumSelesaiKolom6);

        //input belum selesai kolom
        belumselesaiKolom1 = findViewById(R.id.input_laporan_belum_kategori1);
        belumselesaiKolom2 = findViewById(R.id.input_laporan_belum_kategori2);
        belumselesaiKolom3 = findViewById(R.id.input_laporan_belum_kategori3);
        belumselesaiKolom4 = findViewById(R.id.input_laporan_belum_kategori4);
        belumselesaiKolom5 = findViewById(R.id.input_laporan_belum_kategori5);
        belumselesaiKolom6 = findViewById(R.id.input_laporan_belum_kategori6);

        //input deskripsi kolom
        deskripsiKolom1 = findViewById(R.id.description_laporan_kategori1);
        deskripsiKolom2 = findViewById(R.id.description_laporan_kategori2);
        deskripsiKolom3 = findViewById(R.id.description_laporan_kategori3);
        deskripsiKolom4 = findViewById(R.id.description_laporan_kategori4);
        deskripsiKolom5 = findViewById(R.id.description_laporan_kategori5);
        deskripsiKolom6 = findViewById(R.id.description_laporan_kategori6);

        namaPenanggungJawab = findViewById(R.id.laporan_penanggungjawab);

        //Button
        btnDariTanggalLaporan = findViewById(R.id.btn_pilihDariTanggalLaporan);
        btnSampaiTanggalLaporan = findViewById(R.id.btn_pilihSampaiTanggalLaporan);
//        btnPilihTanggalLapor = findViewById(R.id.btn_pilihTanggalLaporan);
        sendLaporan = findViewById(R.id.btn_send_laporanBulanan);

        tambahKolom3.setVisibility(View.INVISIBLE);
        tambahKolom4.setVisibility(View.INVISIBLE);
        tambahKolom5.setVisibility(View.INVISIBLE);
        tambahKolom6.setVisibility(View.INVISIBLE);
        pilihKategori2.setVisibility(View.INVISIBLE);
        pilihKategori3.setVisibility(View.INVISIBLE);
        pilihKategori4.setVisibility(View.INVISIBLE);
        pilihKategori5.setVisibility(View.INVISIBLE);
        pilihKategori6.setVisibility(View.INVISIBLE);
        spinner2.setVisibility(View.INVISIBLE);
        spinner3.setVisibility(View.INVISIBLE);
        spinner4.setVisibility(View.INVISIBLE);
        spinner5.setVisibility(View.INVISIBLE);
        spinner6.setVisibility(View.INVISIBLE);
        etKolom2.setVisibility(View.INVISIBLE);
        etKolom3.setVisibility(View.INVISIBLE);
        etKolom4.setVisibility(View.INVISIBLE);
        etKolom5.setVisibility(View.INVISIBLE);
        etKolom6.setVisibility(View.INVISIBLE);
        selesaiKolom2.setVisibility(View.INVISIBLE);
        selesaiKolom3.setVisibility(View.INVISIBLE);
        selesaiKolom4.setVisibility(View.INVISIBLE);
        selesaiKolom5.setVisibility(View.INVISIBLE);
        selesaiKolom6.setVisibility(View.INVISIBLE);
        belumselesaiKolom2.setVisibility(View.INVISIBLE);
        belumselesaiKolom3.setVisibility(View.INVISIBLE);
        belumselesaiKolom4.setVisibility(View.INVISIBLE);
        belumselesaiKolom5.setVisibility(View.INVISIBLE);
        belumselesaiKolom6.setVisibility(View.INVISIBLE);
        deskripsiKolom2.setVisibility(View.INVISIBLE);
        deskripsiKolom3.setVisibility(View.INVISIBLE);
        deskripsiKolom4.setVisibility(View.INVISIBLE);
        deskripsiKolom5.setVisibility(View.INVISIBLE);
        deskripsiKolom6.setVisibility(View.INVISIBLE);
        textSelesaiKolom2.setVisibility(View.INVISIBLE);
        textSelesaiKolom3.setVisibility(View.INVISIBLE);
        textSelesaiKolom4.setVisibility(View.INVISIBLE);
        textSelesaiKolom5.setVisibility(View.INVISIBLE);
        textSelesaiKolom6.setVisibility(View.INVISIBLE);
        textBelumSelesaiKolom2.setVisibility(View.INVISIBLE);
        textBelumSelesaiKolom3.setVisibility(View.INVISIBLE);
        textBelumSelesaiKolom4.setVisibility(View.INVISIBLE);
        textBelumSelesaiKolom5.setVisibility(View.INVISIBLE);
        textBelumSelesaiKolom6.setVisibility(View.INVISIBLE);



        tambahKolom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner2.setVisibility(View.VISIBLE);
                etKolom2.setVisibility(View.VISIBLE);
                pilihKategori2.setVisibility(View.VISIBLE);
                textSelesaiKolom2.setVisibility(View.VISIBLE);
                textBelumSelesaiKolom2.setVisibility(View.VISIBLE);
                selesaiKolom2.setVisibility(View.VISIBLE);
                belumselesaiKolom2.setVisibility(View.VISIBLE);
                deskripsiKolom2.setVisibility(View.VISIBLE);
                tambahKolom2.setVisibility(View.INVISIBLE);
                tambahKolom3.setVisibility(View.VISIBLE);
            }
        });

        tambahKolom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner3.setVisibility(View.VISIBLE);
                etKolom3.setVisibility(View.VISIBLE);
                pilihKategori3.setVisibility(View.VISIBLE);
                textSelesaiKolom3.setVisibility(View.VISIBLE);
                textBelumSelesaiKolom3.setVisibility(View.VISIBLE);
                selesaiKolom3.setVisibility(View.VISIBLE);
                belumselesaiKolom3.setVisibility(View.VISIBLE);
                deskripsiKolom3.setVisibility(View.VISIBLE);
                tambahKolom3.setVisibility(View.INVISIBLE);
                tambahKolom4.setVisibility(View.VISIBLE);
            }
        });

        tambahKolom4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner4.setVisibility(View.VISIBLE);
                etKolom4.setVisibility(View.VISIBLE);
                pilihKategori4.setVisibility(View.VISIBLE);
                textSelesaiKolom4.setVisibility(View.VISIBLE);
                textBelumSelesaiKolom4.setVisibility(View.VISIBLE);
                selesaiKolom4.setVisibility(View.VISIBLE);
                belumselesaiKolom4.setVisibility(View.VISIBLE);
                deskripsiKolom4.setVisibility(View.VISIBLE);
                tambahKolom4.setVisibility(View.INVISIBLE);
                tambahKolom5.setVisibility(View.VISIBLE);
            }
        });

        tambahKolom5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner5.setVisibility(View.VISIBLE);
                etKolom5.setVisibility(View.VISIBLE);
                pilihKategori5.setVisibility(View.VISIBLE);
                textSelesaiKolom5.setVisibility(View.VISIBLE);
                textBelumSelesaiKolom5.setVisibility(View.VISIBLE);
                selesaiKolom5.setVisibility(View.VISIBLE);
                belumselesaiKolom5.setVisibility(View.VISIBLE);
                deskripsiKolom5.setVisibility(View.VISIBLE);
                tambahKolom5.setVisibility(View.INVISIBLE);
                tambahKolom6.setVisibility(View.VISIBLE);
            }
        });

        tambahKolom6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner6.setVisibility(View.VISIBLE);
                etKolom6.setVisibility(View.VISIBLE);
                textSelesaiKolom6.setVisibility(View.VISIBLE);
                textBelumSelesaiKolom6.setVisibility(View.VISIBLE);
                selesaiKolom6.setVisibility(View.VISIBLE);
                belumselesaiKolom6.setVisibility(View.VISIBLE);
                deskripsiKolom6.setVisibility(View.VISIBLE);
                pilihKategori6.setVisibility(View.VISIBLE);
                tambahKolom6.setVisibility(View.INVISIBLE);
            }
        });





//        showDataSpinner();

        arrayListKategori = new ArrayList<>(Arrays.asList(dataKategori));
        arrayAdapterKategori = new ArrayAdapter<>(this, R.layout.style_spinner, arrayListKategori);
        spinner1.setAdapter(arrayAdapterKategori);
        spinner2.setAdapter(arrayAdapterKategori);
        spinner3.setAdapter(arrayAdapterKategori);
        spinner4.setAdapter(arrayAdapterKategori);
        spinner5.setAdapter(arrayAdapterKategori);
        spinner6.setAdapter(arrayAdapterKategori);


        btnDariTanggalLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(LaporanBulananAdminActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calendar.set(year,month,dayOfMonth);
                        laporDateDariTanggal.setText(simpleDateFormat.format(calendar.getTime()));
                        tgl_pengajuanLaporanWeekly_DariDate = calendar.getTime();



                    }
                }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        btnSampaiTanggalLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(LaporanBulananAdminActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calendar.set(year,month,dayOfMonth);
                        laporDateSampaiTanggal.setText(simpleDateFormat.format(calendar.getTime()));
                        tgl_pengajuanLaporanWeekly_SampaiDate = calendar.getTime();



                    }
                }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() );
                datePickerDialog.show();

            }
        });


//        btnPilihTanggalLapor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(LaporanBulananAdminActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                        calendar.set(year,month,dayOfMonth);
//                        laporKirimTanggal.setText(simpleDateFormat.format(calendar.getTime()));
//                        tgl_pengajuanWeekly = calendar.getTime();
//
//
//
//                    }
//                }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
//                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
//                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
//                datePickerDialog.show();
//            }
//        });


        //ini send data
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        pd = new ProgressDialog(this);
        postReportFromAdminToManagerModel = new PostReportFromAdminToManagerModel();

        sendLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Send Report...");
                pd.show();

                if(     !namaPenanggungJawab.getText().toString().isEmpty() &&
                        !etKolom1.getText().toString().isEmpty()
                ){
                    //data masuk
                    String penanggungJawab = namaPenanggungJawab.getText().toString();
                    // data masuk jumlah kasus
                    String etKolomSatu = etKolom1.getText().toString();
                    String etKolomDua = etKolom2.getText().toString();
                    String etKolomTiga = etKolom3.getText().toString();
                    String etKolomEmpat = etKolom4.getText().toString();
                    String etKolomLima = etKolom5.getText().toString();
                    String etKolomEnam = etKolom6.getText().toString();
                    //data masuk kategori
                    String kategoriSatu = spinner1.getSelectedItem().toString();
                    String kategoriDua = spinner2.getSelectedItem().toString();
                    String kategoriTiga = spinner3.getSelectedItem().toString();
                    String kategoriEmpat = spinner4.getSelectedItem().toString();
                    String kategoriLima = spinner5.getSelectedItem().toString();
                    String kategoriEnam = spinner6.getSelectedItem().toString();
                    //data masuk jumlah selesai kategori
                    String selesaiKolomSatu = selesaiKolom1.getText().toString();
                    String selesaiKolomDua = selesaiKolom2.getText().toString();
                    String selesaiKolomTiga = selesaiKolom3.getText().toString();
                    String selesaiKolomEmpat = selesaiKolom4.getText().toString();
                    String selesaiKolomLima = selesaiKolom5.getText().toString();
                    String selesaiKolomEnam = selesaiKolom6.getText().toString();
                    //data masuk belum selesai kategori
                    String belumSelesaiKolomSatu = belumselesaiKolom1.getText().toString();
                    String belumSelesaiKolomDua = belumselesaiKolom2.getText().toString();
                    String belumSelesaiKolomTiga = belumselesaiKolom3.getText().toString();
                    String belumSelesaiKolomEmpat = belumselesaiKolom4.getText().toString();
                    String belumSelesaiKolomLima = belumselesaiKolom5.getText().toString();
                    String belumSelesaiKolomEnam = belumselesaiKolom6.getText().toString();
                    //data masuk deskripsi kategori
                    String deskripsiKolomSatu = deskripsiKolom1.getText().toString();
                    String deskripsiKolomDua = deskripsiKolom2.getText().toString();
                    String deskripsiKolomTiga = deskripsiKolom3.getText().toString();
                    String deskripsiKolomEmpat = deskripsiKolom4.getText().toString();
                    String deskripsiKolomLima = deskripsiKolom5.getText().toString();
                    String deskripsiKolomEnam = deskripsiKolom6.getText().toString();
                    //data masuk text selesai
                    String textSelesai1 = textSelesaiKolom1.getText().toString();
                    String textSelesai2 = textSelesaiKolom2.getText().toString();
                    String textSelesai3 = textSelesaiKolom3.getText().toString();
                    String textSelesai4 = textSelesaiKolom4.getText().toString();
                    String textSelesai5 = textSelesaiKolom5.getText().toString();
                    String textSelesai6 = textSelesaiKolom6.getText().toString();
                    //data masuk text belum selesai
                    String textBelumSelesai1 = textBelumSelesaiKolom1.getText().toString();
                    String textBelumSelesai2 = textBelumSelesaiKolom2.getText().toString();
                    String textBelumSelesai3 = textBelumSelesaiKolom3.getText().toString();
                    String textBelumSelesai4 = textBelumSelesaiKolom4.getText().toString();
                    String textBelumSelesai5 = textBelumSelesaiKolom5.getText().toString();
                    String textBelumSelesai6 = textBelumSelesaiKolom6.getText().toString();



                    PostReportFromAdminToManagerModel post = new PostReportFromAdminToManagerModel(
                            currentUser.getEmail(),
                            penanggungJawab,
                            kategoriSatu,
                            etKolomSatu,
                            textSelesai1,
                            textBelumSelesai1,
                            selesaiKolomSatu,
                            belumSelesaiKolomSatu,
                            deskripsiKolomSatu,
                            kategoriDua,
                            etKolomDua,
                            textSelesai2,
                            textBelumSelesai2,
                            selesaiKolomDua,
                            belumSelesaiKolomDua,
                            deskripsiKolomDua,
                            kategoriTiga,
                            etKolomTiga,
                            textSelesai3,
                            textBelumSelesai3,
                            selesaiKolomTiga,
                            belumSelesaiKolomTiga,
                            deskripsiKolomTiga,
                            kategoriEmpat,
                            etKolomEmpat,
                            textSelesai4,
                            textBelumSelesai4,
                            selesaiKolomEmpat,
                            belumSelesaiKolomEmpat,
                            deskripsiKolomEmpat,
                            kategoriLima,
                            etKolomLima,
                            textSelesai5,
                            textBelumSelesai5,
                            selesaiKolomLima,
                            belumSelesaiKolomLima,
                            deskripsiKolomLima,
                            kategoriEnam,
                            etKolomEnam,
                            textSelesai6,
                            textBelumSelesai6,
                            selesaiKolomEnam,
                            belumSelesaiKolomEnam,
                            deskripsiKolomEnam,
                            tgl_pengajuanLaporanWeekly_DariDate.getTime(),
                            tgl_pengajuanLaporanWeekly_SampaiDate.getTime()

                    );

                    addPost(post);

                } else {
                        pd.dismiss();
                        showMessage("Please verify all input fields");
                }

            }
        });

    }

    private void addPost(PostReportFromAdminToManagerModel post) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("PostReportWeekly").push();

        // get post unique ID and upadte post key
        String key = myRef.getKey();
        post.setPostAdminKey(key);

        myRef.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                showMessage("Report Send Successfully");
                startActivity(new Intent(LaporanBulananAdminActivity.this, AdminActivity.class));
            }
        });
    }

    private void showMessage(String message) {

            Toast.makeText(LaporanBulananAdminActivity.this,message,Toast.LENGTH_LONG).show();

        }


}