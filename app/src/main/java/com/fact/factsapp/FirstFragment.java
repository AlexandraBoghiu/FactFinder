package com.fact.factsapp;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    public static List<CategoryModel> categoryModelList = new ArrayList<>();
    public FirstFragment() { super(R.layout.fragment_category_display);}
    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeList();

        CustomAdapter adapter = new CustomAdapter(categoryModelList);
        RecyclerView rv = view.findViewById(R.id.recycler_view);
        rv.setAdapter(adapter);
    }
    private void initializeList(){
        categoryModelList.add(new CategoryModel("Cats", R.drawable.cat));
        categoryModelList.add(new CategoryModel("Dogs", R.drawable.dog));
        categoryModelList.add(new CategoryModel("Numbers", R.drawable.numbers));
        categoryModelList.add(new CategoryModel("Random", R.drawable.random));
    }
}
