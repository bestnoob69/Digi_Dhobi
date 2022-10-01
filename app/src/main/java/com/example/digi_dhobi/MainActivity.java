package com.example.digi_dhobi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.digi_dhobi.api.UserService;
import com.example.digi_dhobi.context.GlobalContext;
import com.example.digi_dhobi.model.User;
import com.example.digi_dhobi.utils.Constants;
import com.example.digi_dhobi.utils.EncryptUtil;
import com.example.digi_dhobi.utils.HttpStatus;
import com.example.digi_dhobi.utils.MyRunnable;
import com.fasterxml.jackson.core.JsonProcessingException;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText rollNum;
    private ProgressDialog loadingBar;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit = findViewById(R.id.signup_btn);
        rollNum = findViewById(R.id.login_reg_number_input);
        userService = new UserService();
        loadingBar = new ProgressDialog(this);

        GlobalContext.httpClient = new OkHttpClient();
        GlobalContext.BIJAY_1 = new EncryptUtil().getBijay1();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verify rollnumber is not empty here
                loadingBar.show();
                userService.loginUser(rollNum.getText().toString(), new PostLoginRunnable());
//                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }

    class PostLoginRunnable extends MyRunnable {

        @Override
        public void run() {
            if (HttpStatus.SC_OK == statusCode) {
                try {
                    User user = Constants.OBJECT_MAPPER.readValue(jsonResponse, User.class);
                    GlobalContext.currentOnlineUser = user;
//                    Paper.book().write(GlobalContext.userKey, user);

                    String welcomeText = "Welcome " + user.getName();
                    Toast.makeText(MainActivity.this, welcomeText, Toast.LENGTH_SHORT).show();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            } else if (statusCode==400) {
                Toast.makeText(MainActivity.this, "Student needs to register first", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                intent.putExtra("roll", rollNum.getText().toString());
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Login Error. " + jsonResponse, Toast.LENGTH_LONG).show();
            }
            loadingBar.dismiss();
        }
    }
}