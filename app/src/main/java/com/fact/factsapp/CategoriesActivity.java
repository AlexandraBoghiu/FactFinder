package com.fact.factsapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_categories);

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).
                add(R.id.fragment_container, FirstFragment.class, null)
                .commit();
    }


}
