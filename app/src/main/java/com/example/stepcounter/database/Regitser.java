package com.example.stepcounter.database;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stepcounter.R;


public class Regitser extends AppCompatActivity {

    EditText name,password;
    Button register;
    Context context = this;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    TextView delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        name = (EditText) findViewById(R.id.join_Username);
        password = (EditText) findViewById(R.id.join_password);
        register = (Button) findViewById(R.id.join_button);
        delete = (TextView)findViewById(R.id.delete);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String userPassword = password.getText().toString();

                userDbHelper = new UserDbHelper(context);
                sqLiteDatabase = userDbHelper.getWritableDatabase();

                userDbHelper.addData(userName,userPassword,sqLiteDatabase);
                Toast.makeText(getApplicationContext(),"User registered sucessfully",Toast.LENGTH_SHORT).show();
                userDbHelper.close();
                name.setText("");
                password.setText("");
                Intent intent = new Intent(Regitser.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Regitser.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
