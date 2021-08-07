package com.example.musicstream.ui.login;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.musicstream.MainActivity;
import com.example.musicstream.R;
import com.example.musicstream.authentication.ClassUser;
import com.example.musicstream.authentication.DbController;
import com.example.musicstream.databinding.ActivityLoginBinding;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    EditText loginusername;
    EditText loginpassword;
    Button loginpenis;
    List<String> names = new ArrayList<String>();
    List<String> passwords= new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d("HEebus","GO D2");
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        getSupportActionBar().hide();

        loginusername = findViewById(R.id.username);
        loginpassword = findViewById(R.id.password);
        loginpenis  = findViewById(R.id.btn_login_login);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.btnLoginLogin;
        final Button loginSignUp = binding.btnLoginSignup;

        loginViewModel.getLoginFormState().observe(this, loginFormState -> {
            if (loginFormState == null) {
                return;
            }
            loginButton.setEnabled(loginFormState.isDataValid());
            loginSignUp.setEnabled(loginFormState.isDataValid());
            if (loginFormState.getUsernameError() != null) {
                usernameEditText.setError(getString(loginFormState.getUsernameError()));
            }
            if (loginFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(loginFormState.getPasswordError()));
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
            return false;
        });

        loginButton.setOnClickListener(v -> {
            loginViewModel.login(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString());
            logInTolong(v);
        });

        loginSignUp.setOnClickListener(v -> {
            loginViewModel.login(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString());
            signUp(v);
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_SHORT).show();
                                //Intent homeIntent = new Intent(view.getContext(), MainActivity.class);
                                //homeIntent.putExtra("STRING_I_NEED", unames[i]);


                                SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("mypref", MODE_PRIVATE).edit();
                                editor.putString("name", loginusername.getText().toString());
                                editor.commit();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));

                                finish();
                                return; }
                            else if ((x == pwords.length-1) || (!loginusername.getText().toString().equals(unames[i].toString())) && (!loginpassword.getText().toString().equals(pwords[i].toString()))){
                                Toast.makeText(getApplicationContext(), "Wrong username or password!", Toast.LENGTH_LONG).show();
                                //tester
                                Log.d("auth_neue", "failed to login");
                                return;
                            }
                        }
                    }
                    else if ((i == unames.length-1) && (!loginusername.getText().toString().equals(unames[i].toString()))){
                        Toast.makeText(getApplicationContext(), "User does not exist!", Toast.LENGTH_LONG).show();
                        //tester
                        Log.d("auth_neue", "user dne");
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

                SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("mypref", MODE_PRIVATE).edit();
                editor.putString("name", loginusername.getText().toString());
                editor.commit();
                startActivity(homeIntent);

            }else{
                Toast.makeText(this, "Unable to save student information.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}