package com.fact.factsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.fact.factsapp.FirstFragment;
import com.fact.factsapp.ProfileFragment;
import com.fact.factsapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Navbar extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav_bar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.client_bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);

        loadClientFragment(new FirstFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.categories:
                fragment = new FirstFragment();
                break;
            case R.id.profile:
                fragment = new ProfileFragment();
                break;
        }
        return loadClientFragment(fragment);
    }

    private boolean loadClientFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
            return true;
        }
        return false;
    }
}