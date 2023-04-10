package com.fact.factsapp;

import android.os.AsyncTask;

public class FindUserOperationRegister extends AsyncTask<String, Void, User> {
    UserOperations listener;

    public FindUserOperationRegister(UserOperations listener) {
        this.listener = listener;
    }

    @Override
    protected User doInBackground(String... strings) {
        String email = strings[0];
        String password = strings[1];
        return MyApp.getmAppDatabase().userDao().findByEmailAndPassword(email, password);
    }

    @Override
    protected void onPostExecute(User user) {
        listener.findUserRegister(user);
    }
}
