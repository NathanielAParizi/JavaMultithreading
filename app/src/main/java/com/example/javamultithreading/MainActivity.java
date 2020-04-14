package com.example.javamultithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final Thread redThread = new RedThread();
        redThread.setName(" **** COOL THREAD ****");

        redThread.start();

        Thread blueThread = new Thread(new myRunnable() {

            @Override
            public void run() {
                try {
                    redThread.join();
                    Log.d("TAG", "hello from anonymous class's implementation of run()" +
                            " RED THREAD TERMINATED / TIMEDOUT");

                } catch (InterruptedException e) {
                    Log.d("TAG", "I never ran afterall");

                }

            }
        });

        blueThread.start();

    }


}
