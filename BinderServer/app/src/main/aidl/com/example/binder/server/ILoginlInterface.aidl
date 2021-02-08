// ILoginlInterface.aidl
package com.example.binder.server;

// Declare any non-default types here with import statements

interface ILoginlInterface {

    LoginResponse login(LoginRequest request);
}