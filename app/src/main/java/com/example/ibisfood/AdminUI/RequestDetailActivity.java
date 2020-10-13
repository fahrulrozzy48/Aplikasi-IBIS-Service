package com.example.ibisfood.AdminUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibisfood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;

public class RequestDetailActivity extends AppCompatActivity {

    TextView  detailEmailManagerRequest, detailEmailAdminRequest, detailSubjekRequest, detailDescRequest, detailTimeRequest, detailAnswerRequest;
    String PostKey;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail);

        detailEmailManagerRequest = findViewById(R.id.detail_email_manager_request);
        detailEmailAdminRequest = findViewById(R.id.detail_email_admin_request);
        detailTimeRequest = findViewById(R.id.detail_time_detail_request);
        detailSubjekRequest = findViewById(R.id.detail_subjek_detail_request);
        detailDescRequest = findViewById(R.id.detail_desc_detail_request);
        detailAnswerRequest = findViewById(R.id.detail_answer_request);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("PostRequest");

        String postEmailManagerRequest = getIntent().getExtras().getString("managerAnswerEmail");
        detailEmailManagerRequest.setText(postEmailManagerRequest);

        String postEmailAdminRequest = getIntent().getExtras().getString("adminRequestEmail");
        detailEmailAdminRequest.setText(postEmailAdminRequest);

        String postEmailSubjekRequest = getIntent().getExtras().getString("adminRequestSubjek");
        detailSubjekRequest.setText(postEmailSubjekRequest);

        String postEmailDescRequest = getIntent().getExtras().getString("adminRequestDesc");
        detailDescRequest.setText(postEmailDescRequest);

        String postEmailAnswerRequest = getIntent().getExtras().getString("managerAnswerStatus");
        detailAnswerRequest.setText(postEmailAnswerRequest);

        PostKey = getIntent().getExtras().getString("postRequestKey");

        final String date = timestampToString(getIntent().getExtras().getLong("timeSendRequest"));
        detailTimeRequest.setText(date);




    }

    private String timestampToString(long time) {

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd/MM/yyyy HH:mm",calendar).toString();
        return date;



    }
}