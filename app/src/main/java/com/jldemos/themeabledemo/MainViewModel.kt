package com.jldemos.themeabledemo

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var areWePopulated = false
    var hardwareModel: HardwareInfoModel? = null


    fun populateHardwareInfo(myParcelableObject: HomeParcelableModel.HardwareInfoModel){
        hardwareModel = HardwareInfoModel(
            oem = myParcelableObject.oem,
            model = myParcelableObject.model,
            androidVersion = myParcelableObject.androidVersion,
            apiLevel = myParcelableObject.apiLevel,
            screenRes = myParcelableObject.screenRes,
            density = myParcelableObject.density,
            cpu = myParcelableObject.cpu
        )
        areWePopulated = true
    }

    data class HardwareInfoModel(
        val oem: String = "",
        val model: String= "",
        val androidVersion: String= "",
        val apiLevel: String= "",
        val screenRes: String= "",
        val density: String= "",
        val cpu: String= ""
    )

}