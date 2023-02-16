package com.example.inclass_krishnan9285;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class HeavyWork implements Runnable {
    static final int COUNT = 9000000;
    public final static String KEY_MIN = "0x001";
    public final static String KEY_MAX = "0x002";
    public final static String KEY_AVG = "0x003";
    public final static String KEY_PROGRESS = "0x004";

    private int limit;
    private Handler messageQueue;

    public HeavyWork(int limit, Handler messageQueue) {
        this.limit = limit;
        this.messageQueue = messageQueue;
    }

    ArrayList<Double> getArrayNumbers(int n){
        Bundle bundle = new Bundle();

        ArrayList<Double> returnArray = new ArrayList<>();


        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        double avg = 0;

        for (int i = 0; i< n; i++){
            returnArray.add(getNumber());
            double current = returnArray.get(i);

            bundle.putDouble(KEY_PROGRESS, current);
            max = Math.max(max, current);
            min = Math.min(min, current);
            avg += current;

        }

        avg = avg / returnArray.size();


        bundle.putDouble(KEY_MIN, min);
        bundle.putDouble(KEY_MAX, max);
        bundle.putDouble(KEY_AVG, avg);

        Message progressMessage = new Message();
        progressMessage.setData(bundle);
        messageQueue.sendMessage(progressMessage);


        return returnArray;
    }


    static double getNumber(){
        double num = 0;
        Random ran = new Random();
        for(int i=0;i<COUNT; i++){
            num = num + (Math.random()*ran.nextDouble()*100+ran.nextInt(50))*1000;
        }
        return num / ((double) COUNT);
    }

    @Override
    public void run() {
        getArrayNumbers(limit);


    }

   /* public static void main(String[] args) {
        ArrayList<Double> arrayList = new ArrayList<>();
        arrayList = getArrayNumbers(200);
        for (double num : arrayList) {
                    System.out.println(num);
               }
    }*/
}


