package com.example.stepcounter;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView tvStepDetector,DATATEST;
    private SensorManager sensorManager;
    private  Sensor stepDetectorSensor;
    private int mStepDetector;
    private int msteps;
    ImageView imageView;

    int steps = 0;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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


        Intent test = getIntent();
        String nametest = test.getStringExtra("userName");

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat format = new SimpleDateFormat( "yyyy/M/dd");
        String time = format.format(date);
        DATATEST = findViewById(R.id.datatext);
        DATATEST.setText(time);





//타이틀바 삭제
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();


//리셋버튼
        View.OnClickListener reset = v -> {
            steps = 0;
            tvStepDetector.setText(String.valueOf(steps));
        };
        Button resetbutton = findViewById(R.id.resetbutton);
        resetbutton.setOnClickListener(reset);

//BMI 이동
        ImageButton bmi = findViewById(R.id.BMI);
        bmi.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),bmi.class);
            intent.putExtra("chartdate",time);
            startActivity(intent);

        });
//setting 이동
        ImageButton setting = findViewById(R.id.setting);
        setting.setOnClickListener(view -> {
            Intent Setting = new Intent(getApplicationContext(),SettingActivity.class);
            startActivity(Setting);
        });

        //movement 이동
        ImageButton movement = findViewById(R.id.movement);
        movement.setOnClickListener(v -> {
            Intent movementintent = new Intent(getApplicationContext(), Movement.class);
            startActivity(movementintent);
        });

        ImageButton calendar = findViewById(R.id.main_calendar);
        calendar.setOnClickListener(view -> {
            Intent calendarintent = new Intent(getApplicationContext(),Calendar.class);
            calendarintent.putExtra("userName",nametest);
            startActivity(calendarintent);
        });

//버튼 토글
        ToggleButton toggleButton = findViewById(R.id.button6);
        toggleButton.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
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
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            if(mStepDetector < 1){
                mStepDetector = (int)event.values[0];
            }
            msteps = (int) event.values[0] - mStepDetector;
            tvStepDetector.setText(Integer.toString(msteps));
            Log.i("log: ", "New step detected by STEP_COUNTER sensor. Total step count: " + msteps);
            /*if (event.values[0] == 1.0f) {
                mStepDetector++;
                tvStepDetector.setText(String.valueOf(mStepDetector));
            }*/
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


