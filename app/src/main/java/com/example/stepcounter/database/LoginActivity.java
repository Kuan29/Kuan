package com.example.stepcounter.database;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stepcounter.MainActivity;
import com.example.stepcounter.R;


public class LoginActivity extends AppCompatActivity {

    EditText userName,userPassword;
    Button login, register;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        userName = (EditText) findViewById(R.id.login_username);
        userPassword = (EditText) findViewById(R.id.login_password);
        register = (Button) findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Regitser.class);
                startActivity(intent);
            }
        });
        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String pwd = userPassword.getText().toString();

                userDbHelper = new UserDbHelper(context);
                sqLiteDatabase = userDbHelper.getReadableDatabase();

                Cursor result = userDbHelper.getPassword(name,sqLiteDatabase);
                if(result.moveToFirst()){
                    if(pwd.equals(result.getString(1))) {
                        Toast.makeText(context, "Successful Login", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(context,"Wrong password",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(context,"Not a registered user",Toast.LENGTH_SHORT).show();

                userName.setText("");
                userPassword.setText("");
            }
        });
    }

}
