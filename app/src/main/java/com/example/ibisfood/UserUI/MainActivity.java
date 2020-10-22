package com.example.ibisfood.UserUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ibisfood.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_user);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //tetap ke home
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_user,
                    new HomeFragment()).commit();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment pilihFragment = null;

            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    pilihFragment = new HomeFragment();
                    break;
                case R.id.nav_feeds_user:
                    pilihFragment = new FeedsUserFragment();
                    break;
                case R.id.nav_menu:
                    pilihFragment = new MenuFragment();
                    break;
                case R.id.nav_status:
                    pilihFragment = new StatusFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_user,
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
            Toast.makeText(getBaseContext(), "Please don't exit in application", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}
