package com.example.stepcounter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class Movement_7 extends Fragment {
    public Movement_7(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_move_7,container,false);
        ImageView imageView = v.findViewById(R.id.mountainclimbing);
        Glide.with(imageView).asGif().load(R.raw.mountainclimbing).into(imageView);
        return v;
    }
}
