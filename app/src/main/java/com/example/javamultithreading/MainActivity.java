package com.example.javamultithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Countdown countdown1 = new Countdown();
        Countdown countdown2 = new Countdown();

        CountdownThread t1 = new CountdownThread(countdown1);
        t1.setName("Thread1");
        CountdownThread t2 = new CountdownThread(countdown2);
        t2.setName("Thread2");

        t1.start();
        t2.start();

    }

}

class Countdown {

    //  private int i;

    public void doCountDown() {

        String color = "";

        switch (Thread.currentThread().getName()) {
            case "Thread1":
                color = "RED";
                break;
            case "Thread2":
                color = "BLUE";
                break;
            default:
                Thread.currentThread().setName("White" + Thread.currentThread().getName());
        }

        synchronized (this) {
            for (int i = 10; i > 0; i--) {
                Log.d("TAG", color + " " + Thread.currentThread().getName() + " " + i);
            }
        }

    }


}

class CountdownThread extends Thread {
    Countdown threadCountdown;

    public CountdownThread(Countdown countdown) {
        threadCountdown = countdown;
    }

    @Override
    public void run() {
        threadCountdown.doCountDown();
    }
}

