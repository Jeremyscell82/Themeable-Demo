package com.jldemos.themeabledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {


    lateinit var myHomeViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myHomeViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        Timber.d("JL_ Main Screen is being created....")


        Timber.tag("MainActivity")

        //Populate data ONLY on first launch, to ensure the viewmodel does its job and keep the data on relaunch
        if (savedInstanceState == null) {
            val myParcelableObject: HomeParcelableModel.HardwareInfoModel? =
                intent.getParcelableExtra("homemodel")
            if (myParcelableObject != null && !myHomeViewModel.areWePopulated) {
                myHomeViewModel.populateHardwareInfo(myParcelableObject)
            }
        }

        /** hardware info is populated in splash, ensuring creation is not in MainActivity. All data in this activity is passed from the SplashActivity **/
        //HARD INFO SECTION
        settings_hw_oem.text = myHomeViewModel.hardwareModel?.oem //myHomeViewModel.HardwareInfoModel.oem//resources.getString(R.string.hw_oem, Build.MANUFACTURER)
        settings_hw_model.text = myHomeViewModel.hardwareModel?.model //resources.getString(R.string.hw_model, Build.MODEL)
        settings_hw_android.text = myHomeViewModel.hardwareModel?.androidVersion //resources.getString(R.string.hw_android, Utils().getAndroidVersion())
        settings_hw_api.text = myHomeViewModel.hardwareModel?.apiLevel //resources.getString(R.string.hw_apilevel, Build.VERSION.SDK_INT.toString())
        settings_hw_screen.text = myHomeViewModel.hardwareModel?.screenRes //resources.getString(R.string.hw_screen_res, size.y.toString(), size.x.toString())
        settings_hw_density.text = myHomeViewModel.hardwareModel?.density //resources.getString(R.string.hw_density, density.toString())
        settings_hw_cpu.text = myHomeViewModel.hardwareModel?.cpu //resources.getString(R.string.hw_cpu, Build.HARDWARE)

        theme_switch.isChecked = Utils().areWeRunningDarkTheme(this)

        theme_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            Timber.d("JL_ is Checked: $isChecked")
            Utils().changeTheme(this, isChecked)
        }
    }


}
