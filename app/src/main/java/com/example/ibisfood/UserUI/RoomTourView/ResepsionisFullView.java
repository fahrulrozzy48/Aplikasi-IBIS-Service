package com.example.ibisfood.UserUI.RoomTourView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ibisfood.R;

import co.gofynd.gravityview.GravityView;

public class ResepsionisFullView extends AppCompatActivity {

    private ImageView bgPanFull;
    private GravityView gravityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resepsionis_full_view);

        bgPanFull = (ImageView) findViewById(R.id.bg_pan_fullview_resepsionis);
        gravityView = GravityView.getInstance(this)
                .setImage(bgPanFull, R.drawable.pan_resepsionis)
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