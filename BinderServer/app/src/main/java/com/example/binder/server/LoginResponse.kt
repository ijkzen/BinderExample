package com.example.binder.server

import android.os.Parcel
import android.os.Parcelable

class LoginResponse() : Parcelable {

    var mStatusCode: String? = null
    var mErrorMessage: String? = null

    constructor(parcel: Parcel) : this() {
        readFromParcel(parcel)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(mStatusCode)
        parcel.writeString(mErrorMessage)
    }

    override fun describeContents(): Int {
        return 0
    }

    private fun readFromParcel(input: Parcel) {
        mStatusCode = input.readString()
        mErrorMessage = input.readString()
    }

    companion object CREATOR : Parcelable.Creator<LoginResponse> {
        override fun createFromParcel(parcel: Parcel): LoginResponse {
            return LoginResponse(parcel)
        }

        override fun newArray(size: Int): Array<LoginResponse?> {
            return arrayOfNulls(size)
        }
    }
}