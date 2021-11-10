package com.example.stepcounter

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.ProgressBar
import android.widget.EditText
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import android.graphics.Color
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarEntry
import java.util.*
import com.example.stepcounter.R
import kotlinx.android.synthetic.main.bmi_result.*
import kotlinx.android.synthetic.main.bmi_result_1.*
import org.w3c.dom.Text

class bmiResultActivity1 : AppCompatActivity() {

    private var value = 0


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bmi_result_1)


        val bmigraph1 = intent.getDoubleExtra("bmigraph",0.0)
        val bmigraph = bmigraph1.toInt() * 10
        bmiResultTextView2.text = bmigraph1.toString()
        val seekBar : SeekBar = findViewById(R.id.seekBar)
        seekBar.progress = seekBar.progress
        seekBar.progress = bmigraph1.toInt()
        seekBar.setOnTouchListener{ v: View?, event: MotionEvent? -> true }



//타이틀바 삭제
        val actionBar = supportActionBar
        actionBar!!.hide()
    }




}
