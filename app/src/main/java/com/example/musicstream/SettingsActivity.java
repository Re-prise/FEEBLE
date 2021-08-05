package com.example.musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicstream.ui.login.LoginActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_main);
        getSupportActionBar().hide(); }

    public void homePage(View view){startActivity(new Intent(SettingsActivity.this, MainActivity.class));}
    public void collectionsPage(View view){startActivity(new Intent(SettingsActivity.this,CollectionsActivity.class));}
    public void bacK(View view){
        finish();
    }

    public void moveToProfile(View view){startActivity(new Intent(SettingsActivity.this, Settingsprofile.class));}

    public void logOut(View view){
        Intent newIntent = new Intent(SettingsActivity.this, LoginActivity.class);
        finishAffinity();
        startActivity(newIntent);}
}
