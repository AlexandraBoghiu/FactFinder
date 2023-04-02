package com.fact.factsapp;

public class CategoryModel {
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


}
