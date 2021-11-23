package com.example.stepcounter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        //타이틀바 삭제
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button button = findViewById(R.id.delete);
        button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("name","mike");
            setResult(RESULT_OK,intent);
            finish();
        });
    }
}