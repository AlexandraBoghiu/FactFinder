package com.fact.factsapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CatFactService {
    @GET("fact")
    Call<CatFact> getRandomCatFact();
}
