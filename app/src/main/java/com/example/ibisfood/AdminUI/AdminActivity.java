package com.example.ibisfood.AdminUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ibisfood.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminActivity extends AppCompatActivity {
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_admin);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //tetap ke home
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_admin,
                    new DataServiceFragment()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment pilihFragment = null;

            switch (menuItem.getItemId()){
                case R.id.nav_message_client:
                    pilihFragment = new DataServiceFragment();
                    break;
                case R.id.nav_answer_manager:
                    pilihFragment = new RequestListFragment();
                    break;
                case R.id.nav_menu_tools:
                    pilihFragment = new MenuListFragment();
                    break;
                case R.id.nav_profil:
                    pilihFragment = new ProfilFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_admin,
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
            Toast.makeText(getBaseContext(), "Please don't exit in application",    Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}