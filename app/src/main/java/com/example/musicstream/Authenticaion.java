package com.example.musicstream;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.musicstream.authentication.ClassUser;
import com.example.musicstream.authentication.DbController;
import com.example.musicstream.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class Authenticaion extends AppCompatActivity {

    EditText loginusername;
    EditText loginpassword;
    Button loginpenis;
    List<String> names = new ArrayList<String>();
    List<String> passwords= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //tester//Log.d("heebus","Working?Yes");
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);
        loginusername = findViewById(R.id.username);
        loginpassword = findViewById(R.id.password);
        loginpenis  = findViewById(R.id.btn_login_login);

    }

    public void logInTolong(View view) {
        //tester//Log.d("Heebus","Gojek");
        //to check that both the fields are not empty
        if (loginusername.getText().toString().length() != 0 &&
                loginpassword.getText().toString().length() != 0) {

            List<ClassUser> students = new DbController(this).read();
            if (students.size() > 0) {
                for (ClassUser obj : students){
                    int id = obj.id;
                    String studentFirstname = obj.userName;
                    String studentPass = obj.passWord;
                    names.add(studentFirstname);
                    passwords.add(studentPass);}

                String[] unames = names.toArray(new String[names.size()]);
                String[] pwords = passwords.toArray(new String[passwords.size()]);

                for (int i = 0 ; i < unames.length; i++){
                    if (unames[i].matches(loginusername.getText().toString())){

                        for (int x = 0 ; x < pwords.length; x++){
                            if (unames[i].matches(loginusername.getText().toString()) && pwords[i].matches(loginpassword.getText().toString())){
                                //Toast.makeText(getApplicationContext(), "Password successfully", Toast.LENGTH_LONG).show();
                                //Intent homeIntent = new Intent(view.getContext(), MainActivity.class);
                                //homeIntent.putExtra("STRING_I_NEED", unames[i]);


//                                SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("mypref", MODE_PRIVATE).edit();
//                                editor.putString("name", loginusername.getText().toString());
//                                editor.commit();
                                startActivity(new Intent(Authenticaion.this, MainActivity.class));

                                finish();
                                return; }
                            else if ((x == pwords.length-1) && (!loginusername.getText().toString().equals(unames[i].toString())) && (!loginpassword.getText().toString().equals(pwords[i].toString()))){
                                Toast.makeText(getBaseContext(), "Wrong username or password!", Toast.LENGTH_LONG).show();
                                //tester//Log.d("auth_neue", "failed to login");
                            }
                        }
                    }
                    else if ((i == unames.length-1) && (!loginusername.getText().toString().equals(unames[i].toString()))){
                        Toast.makeText(getBaseContext(), "User does not exist!", Toast.LENGTH_LONG).show();
                        //tester//Log.d("auth_neue", "user dne");
                    }
                }

            }

        }
        else {Toast.makeText(getApplicationContext(), "Username and password cannot be empty", Toast.LENGTH_LONG).show();}
    }

    public void signUp(View view){
        String newusername = loginusername.getText().toString();
        String newpassword = loginpassword.getText().toString();

        Log.d("auth_neue", "uname to add: " + newusername);
        Log.d("auth_neue", "pword to add: " + newpassword);

        if (!newusername.equals("") && !newpassword.equals("")){
            //set username and password under custom class
            ClassUser objectUser = new ClassUser();
            objectUser.userName = newusername;
            objectUser.passWord = newpassword;

            boolean createSuccessful = new DbController(this).create(objectUser);

            if(createSuccessful){
                Toast.makeText(this, "User information was saved.", Toast.LENGTH_SHORT).show();
                Intent homeIntent = new Intent(view.getContext(), MainActivity.class);
                startActivity(homeIntent);

            }else{
                Toast.makeText(this, "Unable to save student information.", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
