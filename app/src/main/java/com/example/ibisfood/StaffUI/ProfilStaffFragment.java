package com.example.ibisfood.StaffUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ibisfood.Model.UserModel;
import com.example.ibisfood.R;
import com.example.ibisfood.SignInActivity;
import com.example.ibisfood.preferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilStaffFragment extends Fragment {


    private Button btnLogout;
    private Context context;


    TextView identitas;

    private FirebaseAuth mAuth;
    FirebaseUser currentUser ;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profil_staff, container, false);

        btnLogout = rootView.findViewById(R.id.buttonLogout);
        context = rootView.getContext();

        identitas = rootView.findViewById(R.id.staffIdentity);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("User");


        mAuth = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();

        userId = currentUser.getUid();

        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userNama = snapshot.getValue(UserModel.class);

                identitas.setText(userNama.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Logout();

        return rootView;
    }

    private void Logout() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                preferences.clearData(context);
                startActivity(intent);


            }

        });

    }
}