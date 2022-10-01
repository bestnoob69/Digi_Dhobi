package com.example.digi_dhobi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.digi_dhobi.api.UserService;
import com.example.digi_dhobi.context.GlobalContext;
import com.example.digi_dhobi.model.User;
import com.example.digi_dhobi.utils.Constants;
import com.example.digi_dhobi.utils.HttpStatus;
import com.example.digi_dhobi.utils.MyRunnable;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {
    private EditText name, mobile, block, room;
    private TextView roll;
    private Button submit;
    private ProgressDialog loadingBar;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.register_username_input);
        mobile = findViewById(R.id.register_phone_number_input);
        block=findViewById(R.id.register_block_input);
        room=findViewById(R.id.register_room_number_input);
        roll=findViewById(R.id.register_registration_number_display);
        submit=findViewById(R.id.register_btn);
        loadingBar = new ProgressDialog(this);
        userService = new UserService();

        String rollString = getIntent().getStringExtra("roll");
        roll.setText(rollString);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add validation on all fields are not empty
                loadingBar.setTitle("Registering student");
                loadingBar.show();

                userService.registerUser(new User(UUID.randomUUID().toString(),
                                rollString,
                                name.getText().toString(),
                                mobile.getText().toString(),
                                block.getText().toString(),
                                room.getText().toString()),
                        new PostRegisterRunnable());
            }
        });
    }

    class PostRegisterRunnable extends MyRunnable {

        @Override
        public void run() {
            if (HttpStatus.SC_OK == statusCode) {
                try {
                    User user = Constants.OBJECT_MAPPER.readValue(jsonResponse, User.class);
                    GlobalContext.currentOnlineUser = user;
//                    Paper.book().write(GlobalContext.userKey, user);

                    String welcomeText = "Registration Complete";
                    Toast.makeText(RegisterActivity.this, welcomeText, Toast.LENGTH_SHORT).show();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(RegisterActivity.this, "Registration Error. " + jsonResponse, Toast.LENGTH_LONG).show();
            }
            loadingBar.dismiss();
        }
    }

}
