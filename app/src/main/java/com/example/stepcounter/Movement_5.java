package com.example.stepcounter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class Movement_5 extends Fragment {
    public Movement_5(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_move_5,container,false);
        ImageView imageView = v.findViewById(R.id.lunge);
        Glide.with(imageView).asGif().load(R.raw.lunge).into(imageView);
        return v;
    }
}
