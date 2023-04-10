package com.fact.factsapp;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements OnItemClickListenerCategory {

    public static List<CategoryModel> categoryModelList = new ArrayList<>();
    public static String CATEGORY_NAME = "category_name";
    public static String CATEGORY = "category";
    public FirstFragment() { super(R.layout.fragment_category_display);}
    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeList();

        CustomAdapter adapter = new CustomAdapter(categoryModelList, this);
        RecyclerView rv = view.findViewById(R.id.recycler_view);
        rv.setAdapter(adapter);
    }
    private void initializeList(){
        categoryModelList.clear();
        categoryModelList.add(new CategoryModel("Cats", R.drawable.cat));
        categoryModelList.add(new CategoryModel("Dogs", R.drawable.dog));
        categoryModelList.add(new CategoryModel("Numbers", R.drawable.numbers));
        categoryModelList.add(new CategoryModel("Random", R.drawable.random));
        categoryModelList.add(new CategoryModel("", R.color.white));
    }

    @Override
    public void onItemClick(CategoryModel item) {
        Bundle bundle = new Bundle();

        bundle.putString(CATEGORY_NAME, item.getName());
        bundle.putParcelable(CATEGORY, item);

        SecondFragment secondFragment = new SecondFragment();
        secondFragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frame_container, secondFragment).addToBackStack(null);
//        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
        fragmentTransaction.commit();
    }


}
