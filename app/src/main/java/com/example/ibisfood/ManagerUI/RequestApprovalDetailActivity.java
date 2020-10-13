package com.example.ibisfood.ManagerUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ibisfood.Model.PostRequestPermission;
import com.example.ibisfood.R;
import com.example.ibisfood.StaffUI.JobDetailActivity;
import com.example.ibisfood.StaffUI.StaffActivity;
import com.example.ibisfood.UserUI.AddPostServicesActivity;
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

public class RequestApprovalDetailActivity extends AppCompatActivity {

    TextView detailEmailAdminApproval, detailSubjekApproval, detailDescApproval, detilTimeApproval, detailAnswer;
    ImageView detailImageAdminApproval;
    String PostKey;

    Spinner spinner;
    ArrayList<String> arrayList = new ArrayList<>();

    TextView updateStatusManagerApproval, updateEmailManagerApproval;
    String answerRb = "";

    Calendar calendar = Calendar.getInstance();
    Locale id = new Locale("in","ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY",id);
    Date tgl_pengajuan_date_approval;


    RadioButton rbAnswerTerima, rbAnswerTolak;
    TextView teksPilihAnswer;
    Button btnDataAnswerRequest;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    PostRequestPermission postRequestPermission;

    String answerManager = "";

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_approval_detail);

        detailEmailAdminApproval = findViewById(R.id.detail_email_admin_approval);
        detilTimeApproval = findViewById(R.id.detail_time_detail_approval);
        detailSubjekApproval = findViewById(R.id.detail_subjek_detail_approval);
        detailAnswer = findViewById(R.id.detail_answer_approval);
        detailDescApproval = findViewById(R.id.detail_desc_detail_approval);

        spinner = findViewById(R.id.spinner_tindakan);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("PostRequest");


        String postEmailAdminApproval = getIntent().getExtras().getString("adminRequestEmail");
        detailEmailAdminApproval.setText(postEmailAdminApproval);

        String postSubjekAdminApproval = getIntent().getExtras().getString("adminRequestSubjek");
        detailSubjekApproval.setText(postSubjekAdminApproval);

        String postDescAdminApproval = getIntent().getExtras().getString("adminRequestDesc");
        detailDescApproval.setText(postDescAdminApproval);

        String postAnswerApproval = getIntent().getExtras().getString("managerAnswerStatus");
        detailAnswer.setText(postAnswerApproval);

//        String postImageAdminApproval = getIntent().getExtras().getString("adminRequestImage") ;
//        Glide.with(this).load(postImageAdminApproval).into(detailImageAdminApproval);

        PostKey = getIntent().getExtras().getString("postRequestKey");

        final String date = timestampToString(getIntent().getExtras().getLong("timeSendRequest"));
        detilTimeApproval.setText(date);



        showDataSpinner();



        //update
        postRequestPermission = new PostRequestPermission();
        pd = new ProgressDialog(this);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.set(year,month,dayOfMonth);
        tgl_pengajuan_date_approval = calendar.getTime();



//        rbAnswerTerima = findViewById(R.id.job_radio_status_detail_approval_TERIMA);
//        rbAnswerTolak = findViewById(R.id.job_radio_status_detail_approval_TOLAK);
        btnDataAnswerRequest = findViewById(R.id.btn_send_answer_detail_approval);


//        if(rbAnswerTerima.isChecked()){
//             answerRb = "Selesai";
//        } else if (rbAnswerTolak.isChecked()){
//            answerRb = "Tolak";
//        }


        btnDataAnswerRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Send Message...");
                pd.show();


                        String emailAdmin = detailEmailAdminApproval.getText().toString();
                        String subjekAdmin = detailSubjekApproval.getText().toString();
                        String descAdmin = detailDescApproval.getText().toString();
//                        String tindakan = postRequestPermission.setManagerAnswerStatus(spinner.getSelectedItem().toString());
                        answerManager = postRequestPermission.setManagerAnswerStatus(spinner.getSelectedItem().toString());

                        PostRequestPermission postRequest = new PostRequestPermission();
                        postRequest.setAdminRequestEmail(emailAdmin);
                        postRequest.setAdminRequestSubjek(subjekAdmin);
                        postRequest.setAdminRequestDesc(descAdmin);
                        postRequest.setManagerAnswerStatus(answerManager);
//                        postRequest.setAdminRequestImage(imageDownlaod);
//                        postRequest.setManagerAnswerStatusTerima(answerRb = "TERIMA");
//                        postRequest.setManagerAnswerStatusTolak(answerRb = "TOLAK");
                        postRequest.setTimeSendRequest(tgl_pengajuan_date_approval.getTime());
                        postRequest.setManagerAnswerEmail(firebaseUser.getEmail());


                        updateRequestApproval(postRequest);


            }
        });

    }

    private void showDataSpinner() {
        DatabaseReference databaseReferences = FirebaseDatabase.getInstance().getReference();


        databaseReferences.child("Tindakan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot item: snapshot.getChildren()){
                    arrayList.add(item.child("namaTindakan").getValue(String.class));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(RequestApprovalDetailActivity.this,R.layout.style_spinner,arrayList);
                spinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateRequestApproval(PostRequestPermission postRequest) {

        PostKey = getIntent().getExtras().getString("postRequestKey");

        databaseReference.child(PostKey).setValue(postRequest).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                Toast.makeText(RequestApprovalDetailActivity.this, "Data Berhasil Update", Toast.LENGTH_SHORT).show();
                
                startActivity(new Intent(getApplicationContext(), ManagerActivity.class));
            }
        });
    }


    private String timestampToString(long time) {

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd/MM/yyyy HH:mm",calendar).toString();
        return date;



    }
}