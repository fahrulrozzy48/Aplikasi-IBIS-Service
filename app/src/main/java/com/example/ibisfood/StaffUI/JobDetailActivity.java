package com.example.ibisfood.StaffUI;

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
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ibisfood.AdminUI.AdminActivity;
import com.example.ibisfood.Model.PostMessageModel;
import com.example.ibisfood.R;
import com.example.ibisfood.UserUI.AddPostServicesActivity;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class JobDetailActivity extends AppCompatActivity {

    ImageView detailJobImage;
    TextView detailJobDescription, detailJobNoKamar, detailJoblTime, detailJobTitle, detailJobEmailUser;
    TextView detailJobKategori;
    String PostKey;

    //terima edit job
    TextView updateStatusJob, updateDescJob, updateEmailStaffJob;
    ImageView updateImageJob;
    String jobRb = "";
    String jobImage, jobDesc;

    Calendar calendar = Calendar.getInstance();
    Locale id = new Locale("in","ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY",id);
    Date tgl_pengajuan_date;

    RadioButton rbSelesai;
    ImageView jobStaffImage;
    TextView jobStaffDescription;
    Button kirimDataJob;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    ArrayList<String> arrayList = new ArrayList<>();
    PostMessageModel postMessageModel;

    private static final int PReqCode = 2;
    private static final int REQUESCODE = 2 ;
    private Uri pickedImgUri = null;
    private static final int CAMERA_IMAGE_CODE = 200;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);


        detailJobNoKamar = findViewById(R.id.job_detail_no_kamar);
        detailJobEmailUser = findViewById(R.id.job_detail_email);
        detailJobKategori = findViewById(R.id.job_detail_kategori);
        detailJobImage = findViewById(R.id.job_detail_image);
        detailJobTitle = findViewById(R.id.job_detail_title);
        detailJobDescription = findViewById(R.id.job_detail_description);
        detailJoblTime = findViewById(R.id.job_detail_time);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("PostMessage");


        String postImage = getIntent().getExtras().getString("pImage") ;
        Glide.with(this).load(postImage).into(detailJobImage);

        String postTitle = getIntent().getExtras().getString("pTitle");
        detailJobTitle.setText(postTitle);

        String postEmailUser = getIntent().getExtras().getString("uEmail");
        detailJobEmailUser.setText(postEmailUser);


        //spiner kategori
        String postKategori = getIntent().getExtras().getString("pKategori");
        detailJobKategori.setText(postKategori);

        String postDescription = getIntent().getExtras().getString("pDescription");
        detailJobDescription.setText(postDescription);

        String postNoRoom = getIntent().getExtras().getString("pNoRoom");
        detailJobNoKamar.setText(postNoRoom);

        // get post id
        PostKey = getIntent().getExtras().getString("postKey");

        final String date = timestampToString(getIntent().getExtras().getLong("pTime"));
        detailJoblTime.setText(date);


        postMessageModel = new PostMessageModel();
        pd = new ProgressDialog(this);


        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.set(year,month,dayOfMonth);
        tgl_pengajuan_date = calendar.getTime();



        //staff kirim data untuk di update
        jobStaffImage = findViewById(R.id.job_image_service);
        jobStaffDescription = findViewById(R.id.job_description_service);
        rbSelesai = findViewById(R.id.job_radio_status);
        kirimDataJob = findViewById(R.id.btn_send_accept_job);

        //ini teks dan image yg akan di edit
        updateStatusJob = findViewById(R.id.job_detail_status_staff);
        updateDescJob = findViewById(R.id.job_detail_description_staff);
        updateImageJob = findViewById(R.id.job_detail_image_staff);
        updateEmailStaffJob = findViewById(R.id.job_detail_email_staff);

        if(rbSelesai.isChecked()){
            jobRb = "Selesai";
        }

        //ketika image di klik
        setupImageClick();

        //Menampilkan data yang akan di update
        String DescJobStaff = getIntent().getExtras().getString("staffDescriptionJob");
        updateDescJob.setText(DescJobStaff);

        String StatusJobStaff = getIntent().getExtras().getString("staffStatusJob");
        updateStatusJob.setText(StatusJobStaff);

        String EmailJobStaff = getIntent().getExtras().getString("staffEmailJob");
        updateEmailStaffJob.setText(EmailJobStaff);

        String ImageJobStaff = getIntent().getExtras().getString("staffImageJob") ;
        Glide.with(this).load(ImageJobStaff).into(updateImageJob);

        // button untuk update data
        kirimDataJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd.setMessage("Send Message...");
                pd.show();

                if (
                        !jobStaffDescription.getText().toString().isEmpty()
                                && jobStaffImage != null) {

                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("job_desk_image");
                    final StorageReference imageFilePath = storageReference.child(pickedImgUri.getLastPathSegment());
                    imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageDownlaodLink = uri.toString();
                                    jobDesc = jobStaffDescription.getText().toString();
                                    String imageDownlaod = getIntent().getExtras().getString("pImage");
                                    String no_room = detailJobNoKamar.getText().toString();
                                    String title = detailJobTitle.getText().toString();
                                    String description = detailJobDescription.getText().toString();
                                    String kategori = detailJobKategori.getText().toString();
                                    String email = detailJobEmailUser.getText().toString();

                                    PostMessageModel postUpdateJob = new PostMessageModel();
                                    postUpdateJob.setpNoRoom(no_room);
                                    postUpdateJob.setpTitle(title);
                                    postUpdateJob.setuEmail(email);
                                    postUpdateJob.setpTime(tgl_pengajuan_date.getTime());
                                    postUpdateJob.setpImage(imageDownlaod);
                                    postUpdateJob.setpKategori(kategori);
                                    postUpdateJob.setpDescription(description);
                                    postUpdateJob.setStaffEmailJob(firebaseUser.getEmail());
                                    postUpdateJob.setStaffStatusJob(jobRb = "Selesai");
                                    postUpdateJob.setStaffImageJob(imageDownlaodLink);
                                    postUpdateJob.setStaffDescriptionJob(jobDesc);

                                    updatePekerjaan(postUpdateJob);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // something goes wrong uploading picture

                                    pd.dismiss();
                                    showMessage(e.getMessage());


                                }
                            });
                        }
                    });

                } else {
                    pd.dismiss();
                    showMessage("Please verify all input fields, Date and choose Post Image") ;
                }
            }
        });



    }

    private void updatePekerjaan(PostMessageModel postUpdateJob) {
        String userID = firebaseAuth.getUid();
        PostKey = getIntent().getExtras().getString("postKey");

        databaseReference.child(PostKey).setValue(postUpdateJob).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                Toast.makeText(JobDetailActivity.this, "Data Berhasil Update", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), StaffActivity.class));
            }
        });

    }


    private String timestampToString(long time) {

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd/MM/yyyy HH:mm",calendar).toString();
        return date;



    }

    private void showMessage(String message) {

        Toast.makeText(JobDetailActivity.this,message,Toast.LENGTH_LONG).show();

    }


    private void setupImageClick() {

        jobStaffImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAndRequestForPermission();
            }
        });
    }

    private void checkAndRequestForPermission() {
        if ((ContextCompat.checkSelfPermission(JobDetailActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(JobDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(JobDetailActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(JobDetailActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(JobDetailActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            } else if (ActivityCompat.shouldShowRequestPermissionRationale(JobDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Toast.makeText(JobDetailActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            } else if (ActivityCompat.shouldShowRequestPermissionRationale(JobDetailActivity.this, Manifest.permission.CAMERA)) {

                Toast.makeText(JobDetailActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            }

            else
            {
                ActivityCompat.requestPermissions(JobDetailActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                        PReqCode);
            }

        }
        else
//            openGallery();

            imagePickDialog();
    }

    private void imagePickDialog() {
        String[] option = {"Camera","Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    cameraPick();
                }
                if (which == 1){
                    openGallery();
                }
            }
        });

        builder.create().show();
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);

    }

    private void cameraPick() {
        ContentValues contentValues= new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp No Room");
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp Pick");
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp Desc");
        pickedImgUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pickedImgUri);
        startActivityForResult(intent, CAMERA_IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null ) {

            // the user has successfully picked an image
            // we need to save its reference to a Uri variable
            pickedImgUri = data.getData() ;
            jobStaffImage.setImageURI(pickedImgUri);

        } else if ( requestCode == CAMERA_IMAGE_CODE){
            jobStaffImage.setImageURI(pickedImgUri);
        }


    }



}