package com.example.binder.client

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.example.binder.client.databinding.ActivityMainBinding
import com.example.binder.server.ILoginlInterface
import com.example.binder.server.LoginRequest

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private var mBound = false

    private var mBinder: ILoginlInterface? = null

    private val mConnection = object: ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            enableLayout(true)
            mBound = true
            mBinder = ILoginlInterface.Stub.asInterface(service)

            mBinding.result.text = "remote service connected"
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            enableLayout(false)
            mBound = false
            mBinder = null
            mBinding.result.text = "remote service disconnected"
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(mBinding.root)

        enableLayout(false)

        mBinding.bindRemoteService.setOnClickListener {
            if (mBound) {
                mBinding.result.text = "service has been bounded"
            } else {
                bindService(getRemoteIntent(), mConnection, Context.BIND_AUTO_CREATE)
            }
        }

        mBinding.unbindRemoteService.setOnClickListener {
            Log.e("test", "mBound: $mBound")
            if (mBound) {
                unbindService(mConnection)
                mBound = false
                enableLayout(false)
            } else {
                mBinding.result.text = "remote service disconnected"
            }
        }

        mBinding.loginSuccess.setOnClickListener {
            val loginResponse = mBinder!!.login(
                LoginRequest().apply {
                    mUserName = "admin"
                    mPassword = "12345678"
                }
            )

            if (loginResponse.mStatusCode == "200") {
                mBinding.result.text = "login success"
            } else {
                mBinding.result.text = "login failed"
            }
        }

        mBinding.loginFailed.setOnClickListener {
            val loginResponse = mBinder!!.login(
                LoginRequest().apply {
                    mUserName = ""
                    mPassword = ""
                }
            )

            if (loginResponse.mStatusCode == "200") {
                mBinding.result.text = "login success"
            } else {
                mBinding.result.text = "login failed"
            }
        }
    }

    private fun getRemoteIntent(): Intent {
        val intent = Intent()
        intent.component = ComponentName("com.example.binder.server", "com.example.binder.server.LoginService")
        return intent
    }

    private fun enableLayout(enable: Boolean) {
        mBinding.bindRemoteService.isEnabled = !enable
        mBinding.unbindRemoteService.isEnabled = enable
        mBinding.loginSuccess.isEnabled = enable
        mBinding.loginFailed.isEnabled = enable
    }
}