package com.freez.customcountdowntimer

import android.os.CountDownTimer
import android.util.Log
import java.util.concurrent.atomic.AtomicBoolean

class DynamicCountDownTimer(
    private val millisInFuture: Long,
    private val countDownInterval: Long,
    private val callback: DynamicCountDownTimerCallback
) :
    CountDownTimer(millisInFuture, countDownInterval) {


    private val TAG: String = "DynamicCountDownTimer"
    var biasTimeout: Long = 0;
    var isFinished: AtomicBoolean = AtomicBoolean(false)

    override fun onTick(millisUntilFinished: Long) {
        Log.d(TAG, "onTick: $millisUntilFinished  bias: $biasTimeout")
        var untilFinish = millisUntilFinished + biasTimeout;
        if (untilFinish < 0) {
            onFinish()
        } else {
            callback.onTick(untilFinish)
        }
    }

    fun startTimer(): DynamicCountDownTimer? {
        isFinished.set(false)
        this.start()
        return this

    }

    override fun onFinish() {
        Log.d(TAG, "onFinish:  bias: $biasTimeout")
        if (biasTimeout > 0) {
            biasTimeout -= this.millisInFuture
            this.start()
        } else {
            timerFinished()
        }
    }

    private fun timerFinished() {
        if (!isFinished.get()) {
            callback.onFinish()
            isFinished.set(true)
        }
    }

    fun addToTimeout(addedTime: Long) {
        biasTimeout += addedTime
    }

    interface DynamicCountDownTimerCallback {
        fun onTick(millisSecondUntilFinish: Long);
        fun onFinish();
    }

}