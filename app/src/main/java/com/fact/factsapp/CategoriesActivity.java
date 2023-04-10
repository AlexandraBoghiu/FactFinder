package com.fact.factsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CategoriesActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

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
            case R.id.logout:
                Session session = Session.getInstance(getApplicationContext());
                session.setLoggedIn(false);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
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

