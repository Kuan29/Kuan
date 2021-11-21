package com.example.stepcounter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class Movement_6 extends Fragment {
    public Movement_6(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_move_6,container,false);
        ImageView imageView = v.findViewById(R.id.crunch);
        Glide.with(imageView).asGif().load(R.raw.crunch).into(imageView);
        return v;
    }
}
