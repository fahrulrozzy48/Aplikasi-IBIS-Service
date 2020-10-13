package com.example.ibisfood;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.ibisfood.AdminUI.AdminActivity;
import com.example.ibisfood.ManagerUI.ManagerActivity;
import com.example.ibisfood.StaffUI.StaffActivity;
import com.example.ibisfood.UserUI.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreen extends Activity {


    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(4000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    if (user != null) {
                        // User is signed in
                        databaseReference.child("User").child(user.getUid()).addValueEventListener(new ValueEventListener() {
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
                                        startActivity(new Intent(SplashScreen.this,
                                                AdminActivity.class));
                                        finish();
                                    } else if (manager) {
                                        startActivity(new Intent(SplashScreen.this,
                                                ManagerActivity.class));
                                        finish();
                                    }  else if (staff) {
                                        startActivity(new Intent(SplashScreen.this,
                                                StaffActivity.class));
                                        finish();
                                    } else if(kamar) {
                                        startActivity(new Intent(SplashScreen.this,
                                                MainActivity.class));
                                        finish();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    } else {
                        // No user is signed in
                        startActivity(new Intent(SplashScreen.this, SignInActivity.class));
                        finish();
                    }
//                    startActivity(new Intent(SplashScreen.this, SignInActivity.class));
//                    finish();
                }
            }
        };
        thread.start();
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
//                            startActivity(new Intent(SplashScreen.this,
//                                    AdminActivity.class));
//                            finish();
//                        } else {
//                            startActivity(new Intent(SplashScreen.this,
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
//            startActivity(new Intent(SplashScreen.this, SignInActivity.class));
//        }

//    }


}
