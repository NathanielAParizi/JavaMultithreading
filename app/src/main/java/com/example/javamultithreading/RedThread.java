package com.example.javamultithreading;

import android.util.Log;

public class RedThread extends Thread {

    @Override
    public void run() {

        Log.d("TAG", "hello from " + currentThread().getName() + " thread" + currentThread().getState());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Log.d("TAG","Another thread woke me up!");
            return; // terminates this thread instance
        }

            Log.d("TAG","FINALLY IM AWAKE!");

    }
}
