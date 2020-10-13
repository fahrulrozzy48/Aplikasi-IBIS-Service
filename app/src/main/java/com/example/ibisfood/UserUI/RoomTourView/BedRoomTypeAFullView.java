package com.example.ibisfood.UserUI.RoomTourView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ibisfood.R;

import co.gofynd.gravityview.GravityView;

public class BedRoomTypeAFullView extends AppCompatActivity {

    private ImageView bgPanFull;
    private GravityView gravityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_room_type_a_full_view);

        bgPanFull = (ImageView) findViewById(R.id.bg_pan_fullview_kamarA);
        gravityView = GravityView.getInstance(this)
                .setImage(bgPanFull, R.drawable.pan_kamar1)
                .center();

        if (!gravityView.deviceSupported()) {
            Toast.makeText(getBaseContext(), "Gyroscope sensor not available in your device", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        gravityView.registerListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        gravityView.unRegisterListener();
    }
}