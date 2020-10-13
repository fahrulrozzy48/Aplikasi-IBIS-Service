package com.example.ibisfood.AdminUI;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibisfood.R;
import com.example.ibisfood.SignUpActivity;

public class MenuListFragment extends Fragment {


    CardView btnLaporanMingguan;
    private CardView btnRegister;
    private CardView btnKategoriManajemen;
    private CardView btnRequestPermission;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu_list, container, false);
        // Inflate the layout for this fragment

        btnLaporanMingguan = rootView.findViewById(R.id.cardView_LaporanMingguan);


        btnLaporanMingguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),LaporanBulananAdminActivity.class);
                startActivity(intent);
            }
        });

        btnRegister = rootView.findViewById(R.id.cardView_Register);
        btnKategoriManajemen = rootView.findViewById(R.id.cardView_SpinnerKategori);
        btnRequestPermission = rootView.findViewById(R.id.cardView_RequestManager);

        btnRequestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PermissionRequestActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnKategoriManajemen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ManajemenKategoriActivity.class);
                startActivity(intent);
            }
        });


        return rootView;

    }
}