package com.example.ibisfood.ManagerUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ibisfood.R;
import com.example.ibisfood.SignInActivity;
import com.example.ibisfood.preferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfilManagerFragment extends Fragment {

    private Button btnLogout;
    private Context context;
    private FirebaseAuth mAuth;

    TextView identitas;

    FirebaseUser currentUser ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_profil_manager, container, false);

        btnLogout = rootView.findViewById(R.id.buttonLogoutManager);
        context = rootView.getContext();

        identitas = rootView.findViewById(R.id.managerIdentity);

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
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                preferences.clearData(context);
                startActivity(intent);


            }

        });
    }
}