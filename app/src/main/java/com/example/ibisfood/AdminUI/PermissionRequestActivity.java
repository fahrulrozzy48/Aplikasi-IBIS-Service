package com.example.ibisfood.AdminUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibisfood.Model.PostRequestPermission;
import com.example.ibisfood.R;
import com.example.ibisfood.StaffUI.JobDetailActivity;
import com.example.ibisfood.UserUI.AddPostServicesActivity;
import com.example.ibisfood.UserUI.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PermissionRequestActivity extends AppCompatActivity {

    EditText subjectRequest, descRequest;
//    TextView tanggalRequest;
    Button btnSendRequest;
//    Button btnSendRequest, btnPilihTanggalRequest;
//    ImageView imagePickRequest;


//    Date tgl_pengajuan_date;
    Calendar calendar = Calendar.getInstance();
    Locale id = new Locale("in","ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY",id);

    ProgressDialog pd;


    FirebaseUser currentUser;
    FirebaseAuth mAuth;

    private static final int PReqCode = 2;
    private static final int REQUESCODE = 2 ;
    private Uri pickedImgUri = null;
    private static final int CAMERA_IMAGE_CODE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_request);

        subjectRequest = findViewById(R.id.subject_request_permission);
        descRequest = findViewById(R.id.description_request_permission);
//        imagePickRequest = findViewById(R.id.job_image_request_permission);
        btnSendRequest = findViewById(R.id.btn_send_request_permission);

        //date
//        tanggalRequest = findViewById(R.id.date_pilihTanggal_request);
//        btnPilihTanggalRequest = findViewById(R.id.btn_pilihTanggal_request);

        pd = new ProgressDialog(this);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        //waktu

//        btnPilihTanggalRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(PermissionRequestActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                        calendar.set(year,month,dayOfMonth);
//                        tanggalRequest.setText(simpleDateFormat.format(calendar.getTime()));
//                        tgl_pengajuan_date = calendar.getTime();
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



        //ketika image di klik
//        setupImageClick();





        btnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Send Message...");
                pd.show();

                if (
                        !subjectRequest.getText().toString().isEmpty() &&
                                !descRequest.getText().toString().isEmpty()) {

                                    String subjek = subjectRequest.getText().toString();
                                    String deskripsi = descRequest.getText().toString();
                                    String emailManager = "";
                                    String answerManager = "";

                                    PostRequestPermission postRequest = new PostRequestPermission(
                                            currentUser.getEmail(),
                                            subjek,
                                            deskripsi,
                                            answerManager,
                                            emailManager

                                    );

                    addRequestPermission(postRequest);


                } else {
                    pd.dismiss();
                    showMessage("Please input subject and description") ;
                }
            }
        });

    }

    private void addRequestPermission(PostRequestPermission postRequest) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("PostRequest").push();

        // get post unique ID and upadte post key
        String key = myRef.getKey();
        postRequest.setPostRequestKey(key);


        // add post data to firebase database
        myRef.setValue(postRequest).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                showMessage("Message Request Successfully");
                startActivity(new Intent(PermissionRequestActivity.this, AdminActivity.class));
            }
        });
    }

    private void showMessage(String message) {

        Toast.makeText(PermissionRequestActivity.this,message,Toast.LENGTH_LONG).show();

    }

}