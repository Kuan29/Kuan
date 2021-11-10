package com.example.stepcounter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.view.View
import android.widget.*
import com.example.stepcounter.R

class bmi : AppCompatActivity() {
    private val EditText: Any? = null
    var RESULT_CODE_MENU = 104
    var ageText: EditText? = null
    var heightText: EditText? = null
    var weightText: EditText? = null
    var textView: TextView? = null
    var resultButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bmi)
        val r_btn_girl: RadioButton
        val r_btn_man: RadioButton
        val radionGroup: RadioGroup
        val ageText : EditText= findViewById(R.id.editTextAge)
        val heightText  : EditText= findViewById(R.id.editTextHeight)
        val weightText : EditText= findViewById(R.id.editTextWeight)
        val resultButton : Button= findViewById(R.id.RESULT)

        resultButton.setOnClickListener{
            Log.d("BMI","Result버튼이 클릭되었음")
            if (heightText.text.isEmpty() || weightText.text.isEmpty()) {
                Toast.makeText(this, "빈 값이 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            val height : Double = heightText.text.toString().toDouble()
            val weight : Double = weightText.text.toString().toDouble()

            val intent = Intent(this,bmiResultActivity::class.java)
            intent.putExtra("height",height)
            intent.putExtra("weight",weight)
            startActivity(intent)
        }



//타이틀 바 삭제
        val actionBar = supportActionBar
        actionBar!!.hide()
        r_btn_girl = findViewById<View>(R.id.radioButton) as RadioButton
        r_btn_man = findViewById<View>(R.id.radioButton2) as RadioButton
        radionGroup = findViewById<View>(R.id.radioGroup) as RadioGroup
    }





/*
    public void onBtnResult(View view){
        String strNum = heightText.getText().toString();
        double height = Integer.parseInt(strNum);
        strNum = weightText.getText().toString();
        double weight = Integer.parseInt(strNum);
        double result = weight / height / height * 10000;
        //strNum = Double.toString(result);
        strNum = String.format("%.2f",result);
        textView.setText(strNum);
    }*/
}