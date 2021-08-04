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

import com.example.musicstream.ui.login.LoginActivity;

import java.util.ArrayList;

public class Authenticaion extends AppCompatActivity {

    EditText loginusername;
    EditText loginpassword;
    Button loginpenis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("HEebus","GO DIE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginusername = findViewById(R.id.username);
        loginpassword = findViewById(R.id.password);
        loginpenis  = findViewById(R.id.btn_login_login);

    }


//    public void goSignUp (View view){
//        Intent signupIntent = new Intent(this, SignUPactivity.class);
//        startActivity(signupIntent);
//    }

    public void logInTolong(View view) {
        Log.d("HEebus","Gojek");
        // check that both the fields are not empty
        if (loginusername.getText().toString().length() != 0 &&
                loginpassword.getText().toString().length() != 0) {

            startActivity(new Intent(Authenticaion.this, MainActivity.class));


//            DatabaseReference wDatabase =
//                    FirebaseDatabase.getInstance().getReference().child("users");
//            wDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
//                        ArrayList<String> names = new ArrayList<String>();
//                        ArrayList<String> password = new ArrayList<String>();
//                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                            User user = ds.getValue(User.class);
//                            names.add(user.username);
//                            password.add(user.passwort);
//                        }
//
//                        String[] userrnames = names.toArray(new String[names.size()]);
//                        String[] passswords = password.toArray(new String[password.size()]);
//                        for (int i = 0 ; i < userrnames.length; i++) {
//                            if (userrnames[i].matches(loginusername.getText().toString())) {
//
//                                for (int x = 0 ; x < passswords.length; x++){
//                                    if (userrnames[i].matches(loginusername.getText().toString()) && passswords[i].matches(loginpassword.getText().toString())){
//                                        //Toast.makeText(getApplicationContext(), "Password successfully", Toast.LENGTH_LONG).show();
//                                        Intent homeIntent = new Intent(v.getContext(), HomeActivity.class);
//                                        homeIntent.putExtra("STRING_I_NEED", userrnames[i]);
//
//
//                                        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("mypref", MODE_PRIVATE).edit();
//                                        editor.putString("name", loginusername.getText().toString());
//                                        editor.commit();
//
//                                        startActivity(homeIntent);
//                                        //Toast.makeText(getApplicationContext(), userrnames[i], Toast.LENGTH_LONG).show();
//                                        finish();
//                                        return;
//                                    }
//                                    else if ((x == passswords.length-1) && (loginusername.getText().toString() != userrnames[i].toString()) && (loginpassword.getText().toString() != passswords[i].toString())){
//                                        Toast.makeText(getApplicationContext(), "Wrong username or password!", Toast.LENGTH_LONG).show();
//                                    }
//                                }
//                            }
//                            else if ((i == userrnames.length-1) && (loginusername.getText().toString() != userrnames[i].toString())){
//                                Toast.makeText(getApplicationContext(), "User does not exist!", Toast.LENGTH_LONG).show();
//
//                            }
//                        }
//                    }
//                }
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    System.out.println("The read failed: " + databaseError.getCode());
//                }
//            });
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Username and password cannot be empty", Toast.LENGTH_LONG).show();
        }
    }

}
