package com.example.javamultithreading;

import android.util.Log;

public class myRunnable implements Runnable {

    @Override
    public void run() {

        Log.d("TAG","Hello from myRunnable");

    }
}
