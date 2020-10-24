package com.example.ibisfood.ManagerUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ibisfood.AdminUI.DataServiceFragment;
import com.example.ibisfood.AdminUI.MenuListFragment;
import com.example.ibisfood.AdminUI.ProfilFragment;
import com.example.ibisfood.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ManagerActivity extends AppCompatActivity {


    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_manager);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //tetap ke home
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_manager,
                    new DataReportFragment()).commit();
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment pilihFragment = null;

            switch (menuItem.getItemId()){
                case R.id.nav_report_list:
                    pilihFragment = new DataReportFragment();
                    break;
                case R.id.nav_approval:
                    pilihFragment = new RequestAnswerFragment();
                    break;
                case R.id.nav_profil_manager:
                    pilihFragment = new ProfilManagerFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_manager,
                    pilihFragment).commit();
            return true;
        }
    };

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        finishAffinity();
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Please click back again to exit",    Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}