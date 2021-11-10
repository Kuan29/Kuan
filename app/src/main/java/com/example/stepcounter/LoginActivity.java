package com.example.stepcounter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 101;
    int LOGIN_CODE_MENU = 102;

    private EditText login_email, login_password;
    private Button login_button, join_button;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //타이틀바 삭제
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);

        join_button = findViewById(R.id.btn_register);
        join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login_button = findViewById(R.id.btn_login);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserEmail = login_email.getText().toString();
                String UserPwd = login_password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("sucess");

                            if(success){
                                String UserEmail = jsonObject.getString( "UserEmail" );
                                String UserPwd = jsonObject.getString( "UserPwd" );
                                String UserName = jsonObject.getString( "UserName" );

                                Toast.makeText( getApplicationContext(), String.format("%s님 환영합니다.", UserName), Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( LoginActivity.this, MainActivity.class );

                                intent.putExtra( "UserEmail", UserEmail );
                                intent.putExtra( "UserPwd", UserPwd );
                                intent.putExtra( "UserName", UserName );

                                startActivity( intent );
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"로그인에 실패했습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(UserEmail,UserPwd, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}