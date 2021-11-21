package com.example.stepcounter

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.bmi_result_1.*


class bmiResultActivity1 : AppCompatActivity() {

    private var value = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bmi_result_1)


        val bmigraph1 = intent.getDoubleExtra("bmigraph",0.0)
        val bmiresult = intent.getStringExtra("bmiresult")
        bmiResultTextView2.text = bmigraph1.toString()
        val seekBar : SeekBar = findViewById(R.id.seekBar)
        seekBar.progress = seekBar.progress
        seekBar.progress = bmigraph1.toInt()
        seekBar.setOnTouchListener{ v: View?, event: MotionEvent? -> true }

        val resultStringTextView: TextView = findViewById(R.id.resultTextView2)
        resultStringTextView.text = bmiresult

        graphanalysis.setOnClickListener {
            val intent = Intent(this,CombinedChartActivity::class.java)
            intent.putExtra("bmigraph1",bmigraph1)
            startActivity(intent)
        }




//타이틀바 삭제
        val actionBar = supportActionBar
        actionBar!!.hide()
    }
    //버튼
    fun mOnPopupClick(v: View?) {
        //데이터 담아서 팝업(액티비티) 호출
        val intent = Intent(this, com.example.stepcounter.standard_weight::class.java)
        startActivity(intent)
    }
    fun mOnPopupClick2(v: View?) {
        //데이터 담아서 팝업(액티비티) 호출
        val intent = Intent(this, standard_weight2::class.java)
        startActivity(intent)
    }

}





