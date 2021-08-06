package com.example.musicstream;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicstream.authentication.ClassUser;
import com.example.musicstream.authentication.DbController;
import com.example.musicstream.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class Settingsprofile extends AppCompatActivity {

    TextView usernameText;
    EditText passwordText;
    int id;

    List<String> names = new ArrayList<String>();
    List<String> passwords= new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_profile);
        getSupportActionBar().hide();

        usernameText = findViewById(R.id.txt_profile_username);
        passwordText = findViewById(R.id.profile_change_password);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("mypref", MODE_PRIVATE);
        String name = sharedPref.getString("name", "");
        Log.d("profil", "name: " + name);


        passwordText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    changePassword();
                    return true;
                }
                return false;
            }
        });

        displayProfileInfo(name);
    }


    public void displayProfileInfo(String uname){
        List<ClassUser> usersList = new DbController(this).read();
        String name = uname;

        if (usersList.size() > 0) {
            for (ClassUser obj : usersList){
                id = obj.id;
                String studentFirstname = obj.userName;
                String studentPass = obj.passWord;
                names.add(studentFirstname);
                passwords.add(studentPass);}

            String[] unames = names.toArray(new String[names.size()]);
            String[] pwords = passwords.toArray(new String[passwords.size()]);

            for (int i = 0 ; i < unames.length; i++){
                if (unames[i].matches(name.toString())){

                    Log.d("profil", "name: " + name);
                    Log.d("profil", "Success!");

                    String password = pwords[i].toString();

                    usernameText.setText(name);
                    passwordText.setText(password);


                    return;
                }
                else if ((i == unames.length-1) && (!name.toString().equals(unames[i].toString()))){
                    Toast.makeText(getApplicationContext(), "User does not exist!", Toast.LENGTH_LONG).show();
                    //tester
                    Log.d("auth_neue", "user dne");
                }
            }

        }

    }




    public void changePassword(){
        passwordText = findViewById(R.id.profile_change_password);
        String passpass = passwordText.getText().toString();
        Log.d("profil", "changed password "+ passwordText.getText().toString());


        if (passpass.length() > 5){
            ClassUser objectUser = new ClassUser();
            objectUser.id = id;
            objectUser.userName = usernameText.getText().toString();
            objectUser.passWord = passpass;

            boolean updateSuccessful = new DbController(this).update(objectUser);
            if(updateSuccessful){

                //displaying alert dialog to notify users to relogin
                new AlertDialog.Builder(this)
                        .setTitle("Logging out")
                        .setMessage("Your password has changed, you will need to re-login")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // send users back to login page
                                Intent newIntent = new Intent(Settingsprofile.this, LoginActivity.class);
                                finishAffinity();
                                startActivity(newIntent);
                            }
                        })
                        .show();
            }else{
                Toast.makeText(getApplicationContext(), "Unable to update Password.", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "New password must be more than 5 characters long.", Toast.LENGTH_SHORT).show();
        }


    }

    public void goBack(View view){
        finish();
    }

    public void goToSettings(View view){finish();}
    public void homePage(View view){startActivity(new Intent(Settingsprofile.this, MainActivity.class));}
    public void collectionsPage(View view){startActivity(new Intent(Settingsprofile.this,CollectionsActivity.class));}


}
