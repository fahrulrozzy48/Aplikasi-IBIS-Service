package com.example.ibisfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ibisfood.AdminUI.AdminActivity;
import com.example.ibisfood.ManagerUI.ManagerActivity;
import com.example.ibisfood.StaffUI.StaffActivity;
import com.example.ibisfood.UserUI.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText mUsername, mPassword;
    private Button btnLogin;
//    private TextView pindahRegister;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseUser user;
//    private Intent HomeActivity;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //ini
        mUsername = findViewById(R.id.edtNoKamar);
        mPassword = findViewById(R.id.edtPwNoKamar);
        btnLogin = findViewById(R.id.btnLoginUser);
//        pindahRegister = findViewById(R.id.pindah_register);
//        HomeActivity = new Intent(this, com.example.ibisfood.UserUI.MainActivity.class);

        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();


        //versi auth email
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    mUsername.setError("Email is required");
                } else if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                } else {
                    Login(email,password);
                }

            }
        });

//        pindahRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
//                startActivity(intent);
//            }
//        });










        //versi no auth

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//                databaseReference.child("login").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        String input1 = username.getText().toString();
//                        String input2 = password.getText().toString();
//
//                        if (dataSnapshot.child(input1).exists()){
//                            if(dataSnapshot.child(input1).child("password").getValue(String.class).equals(input2)){
//                                if (active.isChecked()){
//
//                                    if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("admin")){
//                                        preferences.setDataLogin(SignInActivity.this, true);
//                                        preferences.setDataAs(SignInActivity.this,"admin");
//                                        startActivity(new Intent(SignInActivity.this, AdminActivity.class));
//
//                                    } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("user")) {
//
//                                        preferences.setDataLogin(SignInActivity.this, true);
//                                        preferences.setDataAs(SignInActivity.this,"user");
//                                        startActivity(new Intent(SignInActivity.this, MainActivity.class));
//                                    }
//
//                                } else {
//
//                                    if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("admin")){
//                                        preferences.setDataLogin(SignInActivity.this, false);
//                                        startActivity(new Intent(SignInActivity.this, AdminActivity.class));
//
//                                    } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("user")) {
//
//                                        preferences.setDataLogin(SignInActivity.this, false);
//                                        startActivity(new Intent(SignInActivity.this, MainActivity.class));
//                                    }
//
//                                }
//
//                            } else {
//                                Toast.makeText(SignInActivity.this,"Kata Sandi Salah",Toast.LENGTH_SHORT).show();
//                            }
//
//
//                        } else {
//                            Toast.makeText(SignInActivity.this,"Data belum terdaftar",Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });

        //akhir versi no auth



    }

    private void Login(String email, String password) {
        progressDialog.setTitle("Please wait...");
        progressDialog.show();


        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        user = FirebaseAuth.getInstance().getCurrentUser();
                        database = FirebaseDatabase.getInstance();
                        databaseReference = database.getReference();

                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            if(user != null){
                                databaseReference.child("User").child(user.getUid())
                                        .addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.child("UserLevel").exists()) {
                                                    Boolean admin = snapshot.child("UserLevel")
                                                            .getValue().toString().equals("Admin");
                                                    Boolean manager = snapshot.child("UserLevel")
                                                            .getValue().toString().equals("Manager");
                                                    Boolean staff = snapshot.child("UserLevel")
                                                            .getValue().toString().equals("Staff");
                                                    Boolean kamar = snapshot.child("UserLevel")
                                                            .getValue().toString().equals("Kamar");
                                                    if (admin) {
                                                        startActivity(new Intent(SignInActivity.this,
                                                                AdminActivity.class));
                                                        Toast.makeText(SignInActivity.this,"Welcome Admin", Toast.LENGTH_SHORT).show();
                                                        finish();
                                                    } else if (manager) {
                                                        startActivity(new Intent(SignInActivity.this,
                                                                ManagerActivity.class));
                                                        Toast.makeText(SignInActivity.this,"Welcome Manager", Toast.LENGTH_SHORT).show();
                                                        finish();
                                                    }  else if (staff) {
                                                        startActivity(new Intent(SignInActivity.this,
                                                                StaffActivity.class));
                                                        Toast.makeText(SignInActivity.this,"Welcome Staff", Toast.LENGTH_SHORT).show();
                                                        finish();
                                                    } else if (kamar)  {
                                                        startActivity(new Intent(SignInActivity.this,
                                                                MainActivity.class));
                                                        Toast.makeText(SignInActivity.this,"Welcome User", Toast.LENGTH_SHORT).show();
                                                        finish();
                                                    } else {
                                                        Toast.makeText(SignInActivity.this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                                                        progressDialog.dismiss();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                            }

                        } else {
                            Toast.makeText(SignInActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignInActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });


//        auth.signInWithEmailAndPassword(email,password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        if (task.isSuccessful()){
//                            progressDialog.dismiss();
//                            Toast.makeText(SignInActivity.this, "Login Succesful", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(SignInActivity.this, MainActivity.class));
//                        } else {
//                            Toast.makeText(SignInActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
//                            progressDialog.dismiss();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(SignInActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();
//            }
//        });
    }

//
//    @Override
//    protected void onStart () {
//        super.onStart();
//        if (user != null) {
//            // User is signed in
//            databaseReference.child("User").child(user.getUid()).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (snapshot.child("UserLevel").exists()) {
//                        Boolean admin = snapshot.child("UserLevel")
//                                .getValue().toString().equals("Admin");
//                        if (admin) {
//                            startActivity(new Intent(SignInActivity.this,
//                                    AdminActivity.class));
//                            finish();
//                        } else {
//                            startActivity(new Intent(SignInActivity.this,
//                                    MainActivity.class));
//                            finish();
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        } else {
//            // No user is signed in
//        }
//
//    }
//

    //versi no auth

//    @Override
//    protected void onStart () {
//        super.onStart();
//
//        if(preferences.getDataLogin(this)){
//            if(preferences.getDataAs(this).equals("Admin")){
//                startActivity(new Intent(SignInActivity.this, AdminActivity.class));
//                finish();
//            } else if (preferences.getDataAs(this).equals("Kamar")) {
//                startActivity(new Intent(SignInActivity.this, MainActivity.class));
//                finish();
//            }
//        }
//
//
//    }

    // akhir versi no auth

}

