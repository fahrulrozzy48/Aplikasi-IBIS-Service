package com.example.ibisfood.UserUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ibisfood.R;
import com.example.ibisfood.UserUI.RoomTourView.BedRoomTypeAFullView;
import com.example.ibisfood.UserUI.RoomTourView.BedRoomTypeBFullView;
import com.example.ibisfood.UserUI.RoomTourView.LobbyFullView;
import com.example.ibisfood.UserUI.RoomTourView.ResepsionisFullView;
import com.example.ibisfood.UserUI.RoomTourView.ToiletFullView;

import java.util.ArrayList;

public class RoomTourListActivity extends AppCompatActivity {


    //manual
    CardView btnResepsionis, btnLobby, btnBedroomA, btnBedroomB, btnToilet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_tour_list);


//        initImageBitmaps();

        btnResepsionis =  findViewById(R.id.cv_pan_Resepsionis);
        btnLobby =  findViewById(R.id.cv_pan_Lobby);
        btnBedroomA =  findViewById(R.id.cv_pan_Kamar_A);
        btnBedroomB =  findViewById(R.id.cv_pan_Kamar_B);
        btnToilet =  findViewById(R.id.cv_pan_Toilet);


        btnResepsionis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoomTourListActivity.this, ResepsionisFullView.class);
                startActivity(intent);
                Toast.makeText(RoomTourListActivity.this, "Resepsionis",Toast.LENGTH_SHORT).show();
            }
        });

        btnLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoomTourListActivity.this, LobbyFullView.class);
                startActivity(intent);
                Toast.makeText(RoomTourListActivity.this, "Lobby",Toast.LENGTH_SHORT).show();
            }
        });

        btnBedroomA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoomTourListActivity.this, BedRoomTypeAFullView.class);
                startActivity(intent);
                Toast.makeText(RoomTourListActivity.this, "Bedroom Type A",Toast.LENGTH_SHORT).show();
            }
        });

        btnBedroomB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoomTourListActivity.this, BedRoomTypeBFullView.class);
                startActivity(intent);
                Toast.makeText(RoomTourListActivity.this, "Bedroom Type B",Toast.LENGTH_SHORT).show();
            }
        });

        btnToilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoomTourListActivity.this, ToiletFullView.class);
                startActivity(intent);
                Toast.makeText(RoomTourListActivity.this, "Toilet",Toast.LENGTH_SHORT).show();
            }
        });


    }

}