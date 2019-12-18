package com.jldemos.themeabledemo

import android.content.Intent
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import timber.log.Timber

class SplashActivity: AppCompatActivity() {


    lateinit var myHardware: HomeParcelableModel.HardwareInfoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        Timber.d("JL_ Splash Screen is being created....")

        //Load saved theme
        AppCompatDelegate.setDefaultNightMode(StoredPrefs().getAppTheme(context = this))


        //Prep work to get the screen dimensions
        val dm = DisplayMetrics()
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        display.getMetrics(dm)
        val density = (dm.density * 160f).toInt()

        /** Populate hardware info here, and pass it to the MainActivity **/
        myHardware = HomeParcelableModel.HardwareInfoModel(
            oem = resources.getString(R.string.hw_oem, Build.MANUFACTURER),
            model = resources.getString(R.string.hw_model, Build.MODEL),
            androidVersion = resources.getString(R.string.hw_android, Utils().getAndroidVersion()),
            apiLevel = resources.getString(R.string.hw_apilevel, Build.VERSION.SDK_INT.toString()),
            screenRes = resources.getString(R.string.hw_screen_res, size.y.toString(), size.x.toString()),
            density = resources.getString(R.string.hw_density, density.toString()),
            cpu = resources.getString(R.string.hw_cpu, Build.HARDWARE)
        )

        Handler().postDelayed({
            launchApp()
        },1500)
    }

    private fun launchApp() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("homemodel", myHardware)

        startActivity(intent)
        overridePendingTransition(
            R.anim.fade_in,
            R.anim.fade_out
        )
        finish()
    }
}