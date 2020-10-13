package com.example.ibisfood.UserUI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ibisfood.R;
import com.example.ibisfood.SignInActivity;
import com.example.ibisfood.preferences;
import com.example.ibisfood.userlevel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusFragment extends Fragment {

    private Button btnLogout;
    private Context context;
    private FirebaseAuth mAuth;

    TextView identitas;

    FirebaseUser currentUser ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_status, container,false);

        btnLogout = rootView.findViewById(R.id.buttonLogout);
        context = rootView.getContext();


        identitas = rootView.findViewById(R.id.userIdentity);

        mAuth = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();


        identitas.setText(currentUser.getEmail());




        Logout();


        return rootView;






    }




    private void Logout() {

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                mAuth.signOut();
                    Intent intent = new Intent(getActivity(), SignInActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    preferences.clearData(context);
                    startActivity(intent);


            }

        });


    }
}
