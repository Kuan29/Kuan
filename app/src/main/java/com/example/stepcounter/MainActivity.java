package com.example.stepcounter;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
        getHashKey();

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





//???????????? ??????
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();


//????????????
        View.OnClickListener reset = v -> {
            steps = 0;
            tvStepDetector.setText(String.valueOf(steps));
        };
        Button resetbutton = findViewById(R.id.resetbutton);
        resetbutton.setOnClickListener(reset);

        ImageButton mapbutton = findViewById(R.id.Mapbutton);
        mapbutton.setOnClickListener(v -> {
            Intent map = new Intent(getApplicationContext(), sdfsdf.class);
            startActivity(map);
        });

//BMI ??????
        ImageButton bmi = findViewById(R.id.BMI);
        bmi.bringToFront();
        bmi.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),bmi.class);
            intent.putExtra("chartdate",time);
            startActivity(intent);

        });
//setting ??????
        ImageButton setting = findViewById(R.id.setting);
        setting.bringToFront();
        setting.bringToFront();
        setting.setOnClickListener(view -> {
            Intent Setting = new Intent(getApplicationContext(),SettingActivity.class);
            startActivity(Setting);
        });

        //movement ??????
        ImageButton movement = findViewById(R.id.movement);
        movement.bringToFront();
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

//?????? ??????
        ToggleButton toggleButton = findViewById(R.id.button6);
        toggleButton.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    if (isChecked) {
                        Toast.makeText(getApplicationContext(), "????????? ??????", Toast.LENGTH_SHORT).show();
                        tvStepDetector.bringToFront();
                         button.setOnClickListener(v -> Glide.with(imageView)
                         .asGif()
                         .load(R.raw.step)
                         .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                         .into(imageView));
                    } else {
                        Toast.makeText(getApplicationContext(), "????????? ??????", Toast.LENGTH_SHORT).show();
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


    //???????????? 2??? ?????? ??? ??????
    private long lastTimeBackPressed; //???????????? ????????? ????????? ??????
    @Override
    public void onBackPressed()
    {
        //2??? ????????? ???????????? ????????? ??? ?????? ??? ??? ??????
        if (System.currentTimeMillis() - lastTimeBackPressed < 2000)
        {
            finish();
            return;
        }
        //'??????' ?????? ?????? ?????? ??? ?????????
        Toast.makeText(this, "'??????' ????????? ?????? ??? ???????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
        //lastTimeBackPressed??? '??????'????????? ?????? ????????? ??????
        lastTimeBackPressed = System.currentTimeMillis();
    }
    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

}


