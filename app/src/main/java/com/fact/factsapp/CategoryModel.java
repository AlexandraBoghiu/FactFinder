package com.fact.factsapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CategoryModel implements Parcelable {
    private String name;
    private Integer imageId;

    public CategoryModel(String name, Integer imageId) {
        this.name = name;
        this.imageId = imageId;
    }


    public String getName() {
        return name;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public void setName(String name, Integer imageId) {
        this.name = name;
        this.imageId = imageId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{this.name, this.imageId.toString()});
    }

    public CategoryModel(Parcel in) {
        String[] data = new String[3];
        in.readStringArray(data);

        this.name = data[0];
        this.imageId = Integer.valueOf(data[1]);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public Object createFromParcel(Parcel parcel) {
            return new CategoryModel(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return new CategoryModel[i];
        }
    };
}
