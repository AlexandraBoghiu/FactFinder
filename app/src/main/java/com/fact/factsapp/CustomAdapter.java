package com.fact.factsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CategoryViewHolder> {

    private List<CategoryModel> localCategorySet;

    public CustomAdapter(List<CategoryModel> localCategorySet) {
        this.localCategorySet = localCategorySet;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(localCategorySet.get(position));
    }

    @Override
    public int getItemCount() {
        return localCategorySet.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final ImageView image;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.category_name);
            image = itemView.findViewById(R.id.category_picture);
        }

        public void bind(CategoryModel item) {
            name.setText(item.getName());

           // Integer imageId = item.getImageId();
            //if (imageId != null) {
                image.setImageDrawable(ContextCompat.getDrawable(image.getContext(), item.getImageId()));
           // }
        }
    }
}
