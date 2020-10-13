package com.example.ibisfood.StaffUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.ibisfood.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StaffActivity extends AppCompatActivity {

//    Handler mHandler;
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_staff);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

//        this.mHandler = new Handler();
//        m_Runnable.run();

        //tetap ke home
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_staff,
                    new ListWorkStaffFragment()).commit();
        }
    }
//
//    private final Runnable m_Runnable = new Runnable() {
//        @Override
//        public void run() {
//            StaffActivity.this.mHandler.postDelayed(m_Runnable,5000);
//        }
//    };



    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment pilihFragment = null;

            switch (menuItem.getItemId()){
                case R.id.nav_job_list_staff:
                    pilihFragment = new ListWorkStaffFragment();
                    break;
                case R.id.nav_profil_staff:
                    pilihFragment = new ProfilStaffFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_staff,
                    pilihFragment).commit();
            return true;
        }
    };

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        mHandler.removeCallbacks(m_Runnable);
//        finishAffinity();
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Please don't exit in application",    Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}