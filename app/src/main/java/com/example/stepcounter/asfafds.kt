package com.example.stepcounter

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View.OnTouchListener
import android.view.MotionEvent
import android.view.View
import android.widget.Button

class asfafds : AppCompatActivity() {
    var cha_Btn: Button? = null
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cha_Btn!!.setOnTouchListener { v: View?, event: MotionEvent? -> true }
    }
}