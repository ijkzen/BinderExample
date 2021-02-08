package com.example.binder.server

import android.app.Service
import android.content.Intent
import android.os.IBinder

class LoginService : Service() {

    private val mBinder = object: ILoginlInterface.Stub(){
        override fun login(request: LoginRequest?): LoginResponse {
            return if ("admin" == request?.mUserName && "12345678" == request.mPassword) {
                LoginResponse().apply {
                    mStatusCode = "200"
                    mErrorMessage = ""
                }
            } else {
                LoginResponse().apply {
                    mStatusCode = "403"
                    mErrorMessage = "username or password is invalid"
                }
            }
        }

    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }
}