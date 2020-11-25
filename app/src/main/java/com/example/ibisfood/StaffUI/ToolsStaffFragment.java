package com.example.ibisfood.StaffUI;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibisfood.R;


public class ToolsStaffFragment extends Fragment {


    CardView btnEmail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_tools_staff, container, false);


        btnEmail = rootView.findViewById(R.id.cardView_EmailStaff);


        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), StaffEmailActivity.class);
                startActivity(i);
            }
        });


        return rootView;
    }
}