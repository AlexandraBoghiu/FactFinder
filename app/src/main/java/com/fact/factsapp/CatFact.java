package com.fact.factsapp;

import com.google.gson.annotations.SerializedName;

public class CatFact {
    @SerializedName("fact")
    private String factText;

    public String getFactText() {
        return factText;
    }

    public int getFactLength() {
        return factText.length();
    }
}
