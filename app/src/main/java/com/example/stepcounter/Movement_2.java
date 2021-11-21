package com.example.stepcounter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class Movement_2 extends Fragment {

    public Movement_2(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_move_2,container,false);
        ImageView imageView = v.findViewById(R.id.situp);
        Glide.with(imageView).asGif().load(R.raw.situp).into(imageView);
        return v;
    }






}
