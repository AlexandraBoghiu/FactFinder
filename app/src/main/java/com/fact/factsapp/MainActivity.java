package com.fact.factsapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

public class MainActivity extends AppCompatActivity implements UserOperations {

    private TextView textView;
    private Button loginButton;
    private Button registerButton;
    private AppCompatEditText email;
    private AppCompatEditText password;

    public static String getEmailAddress() {
        return emailAddress;
    }

    public static void setEmailAddress(String emailAddress) {
        MainActivity.emailAddress = emailAddress;
    }

    private static String emailAddress;
    public final static String PREFERENCES_KEY = "register prefs";
    public final static String PREFERENCES_ID_KEY = "pref key id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        SharedPreferences preferences = getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("email", email.getText().toString());
                editor.putString("password", password.getText().toString());
                editor.apply();
                System.out.println(email.getText().toString());
                findUserLogin(email.getText().toString(), password.getText().toString());
                finish();
                MainActivity.setEmailAddress(email.getText().toString());
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                editor.putString("email", email.getText().toString());
                editor.putString("password", password.getText().toString());
                editor.apply();
                System.out.println(email.getText().toString());
                findUserRegister(email.getText().toString(), password.getText().toString());
                finish();
                MainActivity.setEmailAddress(email.getText().toString());
            }
        });
    }

    private void showErrorPopup() {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(R.string.error_msg)
                .setPositiveButton(android.R.string.yes, dismissPopup())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private DialogInterface.OnClickListener dismissPopup() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        };
    }

    private void insertUsers() {
        User user = new User(email.getText().toString(), password.getText().toString());
        new InsertUserOperation(this).execute(user);
    }

    @Override
    public void insertUsers(String result) {
        if (result.equals("success")) {
            Toast.makeText(MainActivity.this, "Your account has been created!", Toast.LENGTH_SHORT).show();

        }
    }

    private void findUserRegister(String email, String password) {
        new FindUserOperationRegister(this).execute(email, password);
    }

    private void findUserLogin(String email, String password) {
        new FindUserOperationLogin(this).execute(email, password);
    }

    @Override
    public void findUserRegister(User user) {
        if (user == null) {
            insertUsers();
            Session session = Session.getInstance(getApplicationContext());
            session.setLoggedIn(true);
            Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "User already exists", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void findUserLogin(User user) {
        if (user != null) {
            Toast.makeText(this, "Hello " + user.email, Toast.LENGTH_LONG).show();
            Session session = Session.getInstance(getApplicationContext());
            session.setLoggedIn(true);
            Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}