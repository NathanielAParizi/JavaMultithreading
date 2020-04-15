package com.example.javamultithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    // Example of a deadlock condition

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Message msg = new Message();
        (new Thread( new Writer(msg))).start();
        (new Thread(new Reader(msg))).start();

    }

}

class Message {
    private String message;
    private boolean empty = true;

    public synchronized String read() {
        while (empty) {
        }
        empty = true;
        return message;

    }

    public synchronized void write(String message) {
        while (!empty) {
        }
        empty = false;
        this.message = message;
    }
}

class Writer implements Runnable {

    private Message msg;

    public Writer(Message msg) {
        this.msg = msg;
    }

    public void run() {
        String[] messages = {"Konnichiha", "Salam", "Hello"};
        Random rand = new Random();
        for (int i = 0; i < messages.length; i++) {
            msg.write(messages[i]);
            try {
                Thread.sleep(rand.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        msg.write("Finished writing");
    }

}

class Reader implements Runnable {

    private Message msg;

    public Reader(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {

        Random rand = new Random();
        for (String latestMessage = msg.read(); !latestMessage.equals("Finished writing");
             latestMessage = msg.read()) {
            Log.d("TAG", "latest message: " + latestMessage);
            try {
                Thread.sleep(rand.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}


