package com.example.probono;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.probono.DAO.NetworkTask.LoginNetworkTaskHandler;
import com.example.probono.DTO.UserInfo;
import com.example.probono.LoginPage.LoginFormState;
import com.example.probono.LoginPage.LoginViewModel;
import com.example.probono.LoginPage.SaveSharedPreference;

public class LoginActivity extends AppCompatActivity {

    Activity loginActivity ;
    CheckBox remember;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginActivity = this;

        remember = findViewById(R.id.rememberUserInfo);

        final EditText usernameEditText = findViewById(R.id.userId);
        final EditText passwordEditText = findViewById(R.id.userPassword);
        final Button loginButton = findViewById(R.id.loginButton);
        final Button signUpButton = findViewById(R.id.signUpButton);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        final LoginViewModel loginViewModel = new LoginViewModel();
        String username = SaveSharedPreference.getUserName(this);
        String userpass = SaveSharedPreference.getUserPassword(this);

        if(!username.isEmpty()) {
            usernameEditText.setText(username);
            passwordEditText.setText(userpass);
            remember.setChecked(true);
        }



        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


/*
                // 테스트용 코드  ,  정상 구동시 삭제할 것 !
                Intent intent = new Intent(loginActivity.getApplicationContext(), MainActivity.class);
                intent.putExtra("userId", usernameEditText.getText());
                intent.putExtra("userRole", passwordEditText.getText());
                loginActivity.startActivityForResult(intent , 201);

*/


                loadingProgressBar.setVisibility(View.VISIBLE);

                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(usernameEditText.getText().toString());
                userInfo.setPassword(passwordEditText.getText().toString());

                if(remember.isChecked()){
                    SaveSharedPreference.setUserName(LoginActivity.this, usernameEditText.getText().toString());
                    SaveSharedPreference.setUserPassword(LoginActivity.this, passwordEditText.getText().toString());
                }else{
                    SaveSharedPreference.clearUserName(LoginActivity.this);
                }


                new LoginNetworkTaskHandler(loginActivity, userInfo,loadingProgressBar).execute();




            }
        });
    }


}
