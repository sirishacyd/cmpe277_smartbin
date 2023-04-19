package com.cmpe277.garbagemanagementsystem.MainScreen;

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

import com.cmpe277.garbagemanagementsystem.MainActivity;
import com.cmpe277.garbagemanagementsystem.R;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityAdminRegistrationBinding;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityAdminScreenBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class AdminScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActivityAdminScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();



        // Navigation drawer task done no need bother with it
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout
                , toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);

        drawerLayout.addDrawerListener(toggle);
        drawerLayout.closeDrawer(GravityCompat.START);
        toggle.syncState();

        Fragment fragment = new AdminHomeFrag();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.home_content, fragment);
        ft.commit();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    // On Back button is pressed Drawer should be closed
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Intent intent = null;
        if(id == R.id.nav_about_us){
            intent = new Intent(AdminScreen.this,AboutUs.class);
            startActivity(intent);
        }if (id == R.id.nav_log_out){
            FirebaseAuth.getInstance().signOut();

            SharedPreferences.Editor editor = getSharedPreferences("Log",MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();
            intent = new Intent(AdminScreen.this, MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.nav_home) {
            fragment = new AdminHomeFrag();
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.home_content, fragment);
            ft.commit();
        }
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}