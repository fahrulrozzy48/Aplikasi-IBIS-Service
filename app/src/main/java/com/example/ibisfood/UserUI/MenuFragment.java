package com.example.ibisfood.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.ibisfood.R;


public class MenuFragment extends Fragment {

    private CardView btnPanorama;
    private CardView btnService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);



        //V 2
        btnPanorama = view.findViewById(R.id.cardView_Panorama);
        btnService = view.findViewById(R.id.cardView_ServiceMessage);

        // Akhir V 2




        btnPanorama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),RoomTourListActivity.class);
                startActivity(intent);
//                Toast.makeText(getContext(), "Room Tour Coming Soon", Toast.LENGTH_SHORT).show();

            }
        });

        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "Service Coming Soon", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),AddPostServicesActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }

}
