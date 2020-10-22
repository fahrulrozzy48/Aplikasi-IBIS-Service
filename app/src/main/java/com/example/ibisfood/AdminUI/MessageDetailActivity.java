package com.example.ibisfood.AdminUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ibisfood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MessageDetailActivity extends AppCompatActivity {

    ImageView detailImage;
    TextView detailEmail, detailDescription, detailTime, detailTitle;
    TextView detailKategori;
    String PostKey;

    TextView updateStatusJobAdmin, updateDescJobAdmin, updateEmailStaffJobAdmin;
    ImageView updateImageJobAdmin;


    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);


        detailEmail = findViewById(R.id.detail_email);
//        detailNoKamar = findViewById(R.id.detail_no_kamar);
        detailDescription = findViewById(R.id.detail_description);
        detailTitle = findViewById(R.id.detail_title);
        detailTime = findViewById(R.id.detail_time);
        detailImage = findViewById(R.id.detail_image);

        detailKategori = findViewById(R.id.detail_kategori);



        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();


        String postImage = getIntent().getExtras().getString("pImage") ;
        Glide.with(this).load(postImage).into(detailImage);

        String postTitle = getIntent().getExtras().getString("pTitle");
        detailTitle.setText(postTitle);

        //spiner kategori
        String postKategori = getIntent().getExtras().getString("pKategori");
        detailKategori.setText(postKategori);

        String postEmail = getIntent().getExtras().getString("uEmail");
        detailEmail.setText(postEmail);

        String postDescription = getIntent().getExtras().getString("pDescription");
        detailDescription.setText(postDescription);

//        String postNoRoom = getIntent().getExtras().getString("pNoRoom");
//        detailNoKamar.setText(postNoRoom);

        // get post id
        PostKey = getIntent().getExtras().getString("postKey");

        String date = timestampToString(getIntent().getExtras().getLong("pTime"));
        detailTime.setText(date);




        //ini update job
        updateEmailStaffJobAdmin = findViewById(R.id.job_detail_email_staff_admin);
        updateStatusJobAdmin = findViewById(R.id.job_detail_status_staff_admin);
        updateDescJobAdmin = findViewById(R.id.job_detail_description_staff_admin);
        updateImageJobAdmin = findViewById(R.id.job_detail_image_staff_admin);

        String ImageJobStaffAdmin = getIntent().getExtras().getString("staffImageJob") ;
        Glide.with(this).load(ImageJobStaffAdmin).into(updateImageJobAdmin);

        String DescJobStaffAdmin = getIntent().getExtras().getString("staffDescriptionJob");
        updateDescJobAdmin.setText(DescJobStaffAdmin);

        String StatusJobStaffAdmin = getIntent().getExtras().getString("staffStatusJob");
        updateStatusJobAdmin.setText(StatusJobStaffAdmin);

        String EmailJobStaffAdmin = getIntent().getExtras().getString("staffEmailJob");
        updateEmailStaffJobAdmin.setText(EmailJobStaffAdmin);





    }


    private String timestampToString(long time) {

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd/MM/yyyy HH:mm",calendar).toString();
        return date;



    }
}