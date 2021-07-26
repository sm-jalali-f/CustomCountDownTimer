package com.freez.customcountdowntimer

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), DynamicCountDownTimer.DynamicCountDownTimerCallback {
    lateinit var ct: DynamicCountDownTimer;
    lateinit var tv: TextView
    lateinit var addBtn: Button
    lateinit var minusBtn: Button
    lateinit var startBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.tv)
        addBtn = findViewById(R.id.add_btn)
        minusBtn = findViewById(R.id.minus_btn)
        startBtn = findViewById(R.id.start_btn)
        ct = DynamicCountDownTimer(20 * 1000, 1000, this)
        addBtn.setOnClickListener {
            ct.addToTimeout(10 * 1000)
        }
        minusBtn.setOnClickListener {
            ct.addToTimeout(-10 * 1000)
        }
        startBtn.setOnClickListener {
            ct.startTimer()
        }
    }

    override fun onTick(millisSecondUntilFinish: Long) {
        tv.text = "$millisSecondUntilFinish MilliSeconds"
    }

    override fun onFinish() {
        tv.text = "Finished"

    }
}