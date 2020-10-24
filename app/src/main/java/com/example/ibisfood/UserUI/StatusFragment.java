package com.example.ibisfood.UserUI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class StatusFragment extends Fragment {

    private Button btnLogout;
    private Context context;
    private FirebaseAuth mAuth;
//    CardView btnPengaturan;

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

//        btnPengaturan = rootView.findViewById(R.id.btn_pengaturanUser);
//
//        btnPengaturan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                showGantiBahasaDialog();
//                Intent intent = new Intent(context,SettingUserActivity.class);
//                startActivity(intent);
//            }
//        });




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
