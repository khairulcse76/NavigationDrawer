package com.example.dnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {
    
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.drawerNavi);
        
        setSupportActionBar(toolbar);

        //Drawer Toogle 5 element Requred
        // Activity 2. Drawer Reference 3. Toolbar 4.Open and close Flag (Flag Value String Requred)
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,toolbar,R.string.OpenDrawer, R.string.CloseDrawer);

        drawerLayout.addDrawerListener(drawerToggle);

        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id==R.id.nav_home){
                    LoadFragment(new HomeFragment(),1);
                } else if (id == R.id.nav_search) {
                    LoadFragment(new SearchFragment(),0);
                }else if (id == R.id.nav_contact) {
                    LoadFragment(new notesFragment(),0);
                }else {
                    LoadFragment(new ProfilesFragment(),1);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        LoadFragment(new HomeFragment(),1);
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    public void LoadFragment(Fragment fragment, int i){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (i==1)
            ft.add(R.id.Container, fragment);
        else
            ft.replace(R.id.Container, fragment);
        ft.commit();
    }


}