package com.fact.factsapp;

import android.os.AsyncTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatFactTask extends AsyncTask<Void, Void, String> {
    private final CatFactService catFactService;
    private final Callback<String> callback;

    public CatFactTask(CatFactService catFactService, Callback<String> callback) {
        this.catFactService = catFactService;
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Call<CatFact> call = catFactService.getRandomCatFact();
        try {
            Response<CatFact> response = call.execute();
            if (response.isSuccessful()) {
                return response.body().getFactText();
            } else {
                throw new Exception("Error retrieving cat fact");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String catFact) {
        if (catFact != null) {
            callback.onResponse(null, Response.success(catFact));
        } else {
            callback.onFailure(null, new Throwable("Error retrieving cat fact"));
        }
    }
}
