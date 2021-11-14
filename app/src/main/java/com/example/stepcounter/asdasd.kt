package com.example.stepcounter

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.example.stepcounter.R
import android.content.Intent
import com.example.stepcounter.standard_weight
import android.app.Activity
import android.view.View

class asdasd : AppCompatActivity() {
    var txtResult: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bmi_result_1)
    }

    //버튼
    fun mOnPopupClick(v: View?) {
        //데이터 담아서 팝업(액티비티) 호출
        val intent = Intent(this, standard_weight::class.java)
        intent.putExtra("data", "Test Popup")
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                val result = data!!.getStringExtra("result")
                txtResult!!.text = result
            }
        }
    }
}