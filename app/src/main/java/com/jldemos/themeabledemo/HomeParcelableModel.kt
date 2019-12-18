package com.jldemos.themeabledemo

import android.os.Parcel
import android.os.Parcelable

class HomeParcelableModel {

    data class HardwareInfoModel(
        val oem: String,
        val model: String,
        val androidVersion: String,
        val apiLevel: String,
        val screenRes: String,
        val density: String,
        val cpu: String
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(oem)
            parcel.writeString(model)
            parcel.writeString(androidVersion)
            parcel.writeString(apiLevel)
            parcel.writeString(screenRes)
            parcel.writeString(density)
            parcel.writeString(cpu)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<HardwareInfoModel> {
            override fun createFromParcel(parcel: Parcel): HardwareInfoModel {
                return HardwareInfoModel(parcel)
            }

            override fun newArray(size: Int): Array<HardwareInfoModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}