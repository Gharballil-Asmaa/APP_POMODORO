package com.example.app_pomodoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var startTmMilis :Long= 25 *60 *1000
    var restTime :Long = startTmMilis
    var pd :CountDownTimer ?= null
    var isTimerRunning = false


    lateinit var Taketxt1 : TextView
    lateinit var Timetxt2 : TextView
    lateinit var btn : Button
    lateinit var Resettxt3 : TextView
    lateinit var pb :ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Taketxt1 = findViewById(R.id.txt1)
        Timetxt2 = findViewById(R.id.txt2)
        btn = findViewById(R.id.btn)
        Resettxt3 = findViewById(R.id.txt3)
        pb = findViewById(R.id.progressBar)

        btn.setOnClickListener {
            if (!isTimerRunning) {
                startTm()
                Taketxt1.text = resources.getText(R.string.going)

            }
        }
        Resettxt3.setOnClickListener{
            resetTime()
        }
    }

    private fun startTm() {
        pd = object : CountDownTimer(startTmMilis, 1000) {
            override fun onTick(timeLeft: Long) {
                restTime = timeLeft
                updateTimerTxt()
                pb.progress = restTime.toDouble().div(startTmMilis.toDouble()).times(100).toInt()

            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Finish !!!", Toast.LENGTH_SHORT).show()
                isTimerRunning = false

            }

        }.start()
        isTimerRunning = true
    }
    private fun resetTime(){
        pd?.cancel()
        restTime = startTmMilis
        updateTimerTxt()
        Taketxt1.text = resources.getText(R.string.take)
        isTimerRunning = false
    }
    private fun updateTimerTxt(){
        val mnt = restTime.div(1000).div(60)
        val scnd = restTime.div(1000) % 60
        val frmtdTime = String.format("%02d:%02d",mnt,scnd)
        Timetxt2.text = frmtdTime
    }
}
