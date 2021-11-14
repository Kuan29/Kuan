package com.example.stepcounter
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.bmi_result.*

import kotlin.math.pow
import kotlin.math.roundToInt


class bmiResultActivity : AppCompatActivity(){
    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.bmi_result)

        //타이틀바 삭제
        val actionBar = supportActionBar
        actionBar!!.hide()

        val height = intent.getDoubleExtra("height",0.0)
        val weight = intent.getDoubleExtra("weight",0.0)
        val bmi1:Double = weight / (height / 100).pow(2.0)
        val bmi : Double = ((bmi1*10).roundToInt()/10.0)
        val resultText:String = when{
            bmi >= 40.0 -> "초고도 비만"
            bmi >= 35.0 -> "고도 비만"
            bmi >= 30.0 -> "중도 비만"
            bmi >= 25.0 -> "경도 비만"
            bmi >= 23.0 -> "과체중"
            bmi >= 18.5 -> "정상"
            else -> "저체중"
        }
        val resultValueTextView: TextView = findViewById(R.id.bmiResultTextView)
        val resultStringTextView:TextView = findViewById(R.id.resultTextView)
        resultValueTextView.text = bmi.toString()
        resultStringTextView.text = resultText

        bmigraph.setOnClickListener {
            val intent = Intent(this,bmiResultActivity1::class.java)
            intent.putExtra("bmigraph",bmi)
            intent.putExtra("bmiresult",resultText)
            startActivity(intent)
        }
    }
}