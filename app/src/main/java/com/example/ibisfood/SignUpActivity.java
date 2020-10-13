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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ibisfood.AdminUI.AdminActivity;
import com.example.ibisfood.ManagerUI.ManagerActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText txt_username, txt_email, txt_password;
    Button btnRegister;
    RadioButton rbAdmin, rbKamar, rbManager, rbStaff;
    RadioGroup rbGrupUserLevel;

    DatabaseReference databaseReference;
    FirebaseAuth auth;

    String userLevelAll="";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //ini
        txt_username = findViewById(R.id.txt_username);
        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);
        rbGrupUserLevel = findViewById(R.id.rb_grup_userLevel);
        rbAdmin = findViewById(R.id.radio_admin);
        rbStaff = findViewById(R.id.radio_staff);
        rbKamar = findViewById(R.id.radio_userkamar);
        rbManager = findViewById(R.id.radio_manager);
        btnRegister = findViewById(R.id.btnRegister);
        progressDialog = new ProgressDialog(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        auth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final String username = txt_username.getText().toString();
                final String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();






                    if (rbAdmin.isChecked()) {
                        userLevelAll = "Admin";
                    } else if (rbKamar.isChecked()) {
                        userLevelAll = "Kamar";
                    } else if (rbManager.isChecked()) {
                        userLevelAll = "Manager";
                    } else if (rbStaff.isChecked()) {
                        userLevelAll = "Staff";
                    }

//                    if ((TextUtils.isEmpty(username)) || (TextUtils.isEmpty(email)) || (TextUtils.isEmpty(password))) {
//                        Toast.makeText(SignUpActivity.this, "Isi dengan benar!", Toast.LENGTH_SHORT).show();
//                    } else if ((TextUtils.isEmpty(username)) && (TextUtils.isEmpty(email)) && (TextUtils.isEmpty(password))) {
//                        Toast.makeText(SignUpActivity.this, "Isi dengan benar!", Toast.LENGTH_SHORT).show();
//                    }
                if (TextUtils.isEmpty(username) || (TextUtils.isEmpty(email)) || (TextUtils.isEmpty(password))) {
                    Toast.makeText(SignUpActivity.this, "Please fill in correctly", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(username) && (TextUtils.isEmpty(email)) && (TextUtils.isEmpty(password))) {
                    Toast.makeText(SignUpActivity.this, "Please fill in correctly", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressDialog.setTitle("Please wait...");
                    progressDialog.show();

                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        progressDialog.dismiss();

                                        userlevel information = new userlevel(
                                                username, email, userLevelAll
                                        );

                                        FirebaseDatabase.getInstance().getReference("User")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(SignUpActivity.this, "Register Completed", Toast.LENGTH_SHORT).show();

                                                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                                            }
                                        });


                                    } else {
                                        // If sign in fails, display a message to the user.
                                        progressDialog.dismiss();
                                        Toast.makeText(SignUpActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });

                }
            }
        });






    }
}