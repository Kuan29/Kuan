package com.example.stepcounter;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stepcounter.databinding.ActivityMainBinding;

import net.daum.mf.map.api.MapView;


public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);
        final

        //타이틀바 삭제
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initView();
    }
    private void initView() {
        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.mapView);
        mapViewContainer.addView(mapView);
    }

}
