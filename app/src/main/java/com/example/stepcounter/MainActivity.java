package com.example.stepcounter;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView tvStepDetector;
    private SensorManager sensorManager;
    private  Sensor stepDetectorSensor;
    private int mStepDetector;
    ImageView imageView;
    public static final int BMI_CODE_MENU = 103;
    public static final int SETTING_CODE_MENU = 104;
    public static final int MOVEMENT_CODE_MENU = 105;
    public static final int CALENDAR_CODE_MENU = 106;


    int steps = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStepDetector = findViewById(R.id.sensor);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (stepDetectorSensor == null){
            Toast.makeText(this, "No Step Detect Sensor", Toast.LENGTH_SHORT).show();
        }
        @SuppressLint("CutPasteId") Button button = findViewById(R.id.button6);
        imageView = findViewById(R.id.stepcounter2);
        tvStepDetector.bringToFront();
        imageView.bringToFront();




//타이틀바 삭제
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


//리셋버튼
        View.OnClickListener reset = v -> {
            steps = 0;
            tvStepDetector.setText(String.valueOf(steps));
        };
        Button resetbutton = (Button)findViewById(R.id.resetbutton);
        resetbutton.setOnClickListener(reset);

//BMI 이동
        ImageButton bmi = (ImageButton)findViewById(R.id.BMI);
        bmi.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),bmi.class);
            startActivityForResult(intent,BMI_CODE_MENU);

        });
//setting 이동
        ImageButton setting = (ImageButton)findViewById(R.id.setting);
        setting.setOnClickListener(view -> {
            Intent Setting = new Intent(getApplicationContext(),SettingActivity.class);
            startActivityForResult(Setting,SETTING_CODE_MENU);
        });

        //movement 이동
        ImageButton movement = (ImageButton)findViewById(R.id.movement);
        movement.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Movement.class);
            startActivityForResult(intent,MOVEMENT_CODE_MENU);
        });

        ImageButton calendar = (ImageButton)findViewById(R.id.main_calendar);
        calendar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),Calendar.class);
            startActivityForResult(intent,CALENDAR_CODE_MENU);
        });



//버튼 토글
        ToggleButton toggleButton = findViewById(R.id.button6);
        toggleButton.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            Toast.makeText(getApplicationContext(), "만보기 시작", Toast.LENGTH_SHORT).show();
                            tvStepDetector.bringToFront();
                             button.setOnClickListener(v -> Glide.with(imageView)
                             .asGif()
                             .load(R.raw.step)
                             .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                             .into(imageView));
                        } else {
                            Toast.makeText(getApplicationContext(), "만보기 종료", Toast.LENGTH_SHORT).show();
                            tvStepDetector.bringToFront();
                            button.setOnClickListener(v -> Glide.with(imageView)
                            .load(R.drawable.earth)
                            .into(imageView));
                        }
                    }
                }
        );
    }
    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, stepDetectorSensor, SensorManager.SENSOR_DELAY_UI);
    }
    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            if (event.values[0] == 1.0f) {
                mStepDetector++;
                tvStepDetector.setText(String.valueOf(mStepDetector));
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){
    }


    //뒤로가기 2번 클릭 시 종료
    private long lastTimeBackPressed; //뒤로가기 버튼이 클릭된 시간
    @Override
    public void onBackPressed()
    {
        //2초 이내에 뒤로가기 버튼을 재 클릭 시 앱 종료
        if (System.currentTimeMillis() - lastTimeBackPressed < 2000)
        {
            finish();
            return;
        }
        //'뒤로' 버튼 한번 클릭 시 메시지
        Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        //lastTimeBackPressed에 '뒤로'버튼이 눌린 시간을 기록
        lastTimeBackPressed = System.currentTimeMillis();
    }
}


