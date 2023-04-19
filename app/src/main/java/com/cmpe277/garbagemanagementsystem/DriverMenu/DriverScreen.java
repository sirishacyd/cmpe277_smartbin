package com.cmpe277.garbagemanagementsystem.DriverMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.baig.garbagemanagementsystem.MainActivity;
import com.baig.garbagemanagementsystem.MainScreen.AboutUs;
import com.baig.garbagemanagementsystem.MainScreen.UserHomeFrag;
import com.baig.garbagemanagementsystem.MainScreen.UserScreen;
import com.baig.garbagemanagementsystem.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class DriverScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        toolbar.showOverflowMenu();

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout
                , toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);

        drawerLayout.addDrawerListener(toggle);
        drawerLayout.closeDrawer(GravityCompat.START);
        toggle.syncState();

        Fragment fragment = new DriverHomeFrag();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.driver_home_content, fragment);
        ft.commit();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Intent intent = null;
        if (id == R.id.nav_about_us) {
            intent = new Intent(DriverScreen.this, AboutUs.class);
            startActivity(intent);
        }
        if (id == R.id.nav_log_out) {
            FirebaseAuth.getInstance().signOut();

            SharedPreferences.Editor editor = getSharedPreferences("Log",MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();

            intent = new Intent(DriverScreen.this, MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.nav_home) {
            fragment = new DriverHomeFrag();
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.driver_home_content, fragment);
            ft.commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }
}