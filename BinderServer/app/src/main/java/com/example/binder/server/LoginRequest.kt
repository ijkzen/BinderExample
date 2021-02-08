package com.example.binder.server

import android.os.Parcel
import android.os.Parcelable

class LoginRequest() : Parcelable {

    var mUserName: String? = null
    var mPassword: String? = null

    constructor(parcel: Parcel) : this() {
        readFromParcel(parcel)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(mUserName)
        dest?.writeString(mPassword)
    }

    private fun readFromParcel(input: Parcel) {
        mUserName = input.readString()
        mPassword = input.readString()
    }

    companion object CREATOR : Parcelable.Creator<LoginRequest> {
        override fun createFromParcel(parcel: Parcel): LoginRequest {
            return LoginRequest(parcel)
        }

        override fun newArray(size: Int): Array<LoginRequest?> {
            return arrayOfNulls(size)
        }
    }
}