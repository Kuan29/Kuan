package com.example.stepcounter;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stepcounter.database.LoginActivity;
import com.example.stepcounter.database.Regitser;

public class SettingActivity extends AppCompatActivity {

    int LOGIN_CODE_MENU = 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        SeekBar settingseekbar = findViewById(R.id.settingseekbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
//로그인 엑티비티로 이동
        Button login = findViewById(R.id.Login);
        login.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });
//회원가입 엑티비티로 이동
        Button register = findViewById(R.id.Register);
        register.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Regitser.class);
            startActivity(intent);
        });

        settingseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress<10){
                    progress=10;
                    settingseekbar.setProgress(progress);
                }
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.screenBrightness = (float) progress / 100;
                getWindow().setAttributes(params);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}
