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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LaporanBulananAdminActivity extends AppCompatActivity {

    TextView tambahKolom2, tambahKolom3, tambahKolom4, tambahKolom5;

    Spinner spinner1, spinner2, spinner3, spinner4, spinner5;
    ArrayList<String> arrayList = new ArrayList<>();


    EditText etKolom1, etKolom2, etKolom3, etKolom4, etKolom5;
    TextView pilihKategori1, pilihKategori2, pilihKategori3, pilihKategori4, pilihKategori5;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_bulanan_admin);

        //tambah kolom baru
        tambahKolom2 = findViewById(R.id.tambahData2);
        tambahKolom3 = findViewById(R.id.tambahData3);
        tambahKolom4 = findViewById(R.id.tambahData4);
        tambahKolom5 = findViewById(R.id.tambahData5);

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

        //pilih kategori
        pilihKategori1 = findViewById(R.id.pilihKategori1);
        pilihKategori2 = findViewById(R.id.pilihKategori2);
        pilihKategori3 = findViewById(R.id.pilihKategori3);
        pilihKategori4 = findViewById(R.id.pilihKategori4);
        pilihKategori5 = findViewById(R.id.pilihKategori5);

        //input kolom data spinner
        etKolom1 = findViewById(R.id.input_laporan_kategori1);
        etKolom2 = findViewById(R.id.input_laporan_kategori2);
        etKolom3 = findViewById(R.id.input_laporan_kategori3);
        etKolom4 = findViewById(R.id.input_laporan_kategori4);
        etKolom5 = findViewById(R.id.input_laporan_kategori5);
        namaPenanggungJawab = findViewById(R.id.laporan_penanggungjawab);

        //Button
        btnDariTanggalLaporan = findViewById(R.id.btn_pilihDariTanggalLaporan);
        btnSampaiTanggalLaporan = findViewById(R.id.btn_pilihSampaiTanggalLaporan);
//        btnPilihTanggalLapor = findViewById(R.id.btn_pilihTanggalLaporan);
        sendLaporan = findViewById(R.id.btn_send_laporanBulanan);

        tambahKolom3.setVisibility(View.INVISIBLE);
        tambahKolom4.setVisibility(View.INVISIBLE);
        tambahKolom5.setVisibility(View.INVISIBLE);
        pilihKategori2.setVisibility(View.INVISIBLE);
        pilihKategori3.setVisibility(View.INVISIBLE);
        pilihKategori4.setVisibility(View.INVISIBLE);
        pilihKategori5.setVisibility(View.INVISIBLE);
        spinner2.setVisibility(View.INVISIBLE);
        spinner3.setVisibility(View.INVISIBLE);
        spinner4.setVisibility(View.INVISIBLE);
        spinner5.setVisibility(View.INVISIBLE);
        etKolom2.setVisibility(View.INVISIBLE);
        etKolom3.setVisibility(View.INVISIBLE);
        etKolom4.setVisibility(View.INVISIBLE);
        etKolom5.setVisibility(View.INVISIBLE);



        tambahKolom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner2.setVisibility(View.VISIBLE);
                etKolom2.setVisibility(View.VISIBLE);
                pilihKategori2.setVisibility(View.VISIBLE);
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
                tambahKolom5.setVisibility(View.INVISIBLE);
            }
        });



        showDataSpinner();


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
                    String etKolomSatu = etKolom1.getText().toString();
                    String etKolomDua = etKolom2.getText().toString();
                    String etKolomTiga = etKolom3.getText().toString();
                    String etKolomEmpat = etKolom4.getText().toString();
                    String etKolomLima = etKolom5.getText().toString();
                    String kategoriSatu = postReportFromAdminToManagerModel.setAdminKategoriSatu(spinner1.getSelectedItem().toString());
                    String kategoriDua = postReportFromAdminToManagerModel.setAdminKategoriDua(spinner2.getSelectedItem().toString());
                    String kategoriTiga = postReportFromAdminToManagerModel.setAdminKategoriTiga(spinner3.getSelectedItem().toString());
                    String kategoriEmpat = postReportFromAdminToManagerModel.setAdminKategoriEmpat(spinner4.getSelectedItem().toString());
                    String kategoriLima = postReportFromAdminToManagerModel.setAdminKategoriLima(spinner5.getSelectedItem().toString());


                    PostReportFromAdminToManagerModel post = new PostReportFromAdminToManagerModel(
                            currentUser.getEmail(),
                            penanggungJawab,
                            kategoriSatu,
                            etKolomSatu,
                            kategoriDua,
                            etKolomDua,
                            kategoriTiga,
                            etKolomTiga,
                            kategoriEmpat,
                            etKolomEmpat,
                            kategoriLima,
                            etKolomLima,
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

    private void showDataSpinner() {
        DatabaseReference databaseReferences = FirebaseDatabase.getInstance().getReference();


        databaseReferences.child("Kategori").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot item: snapshot.getChildren()){
                    arrayList.add(item.child("nameKategori").getValue(String.class));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(LaporanBulananAdminActivity.this,R.layout.style_spinner,arrayList);
                spinner1.setAdapter(adapter);
                spinner2.setAdapter(adapter);
                spinner3.setAdapter(adapter);
                spinner4.setAdapter(adapter);
                spinner5.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}