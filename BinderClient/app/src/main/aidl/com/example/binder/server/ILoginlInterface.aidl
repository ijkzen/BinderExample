// ILoginlInterface.aidl
package com.example.binder.server;

import com.example.binder.server.LoginRequest;
import com.example.binder.server.LoginResponse;
// Declare any non-default types here with import statements

interface ILoginlInterface {

     LoginResponse login(in LoginRequest request);
}