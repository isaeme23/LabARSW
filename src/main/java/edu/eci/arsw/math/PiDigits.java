package edu.eci.arsw.math;

import java.util.ArrayList;

///  <summary>
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits {


    public static byte[] getDigits (int start, int amount, int n) throws InterruptedException {
        ArrayList<PiThread> threads =  createThreads(start, amount, n);
        joinThreads(threads);
        return concatenateResults(threads, amount);
    }

    public static ArrayList<PiThread> createThreads(int start, int amount, int n){
        ArrayList<PiThread> threads = new ArrayList<>();
        int module = (amount) % n;
        int range = (amount / n);
        int threadStart = start;
        for(int i = 0;  i < n; i++){
            if(module > 0){
                range++;
                module--;
            }
            PiThread piThread = new PiThread(threadStart, range);
            threads.add(piThread);
            piThread.start();
            threadStart = threadStart + range;
            range = amount / n;
        }
        return threads;
    }

    public static void joinThreads(ArrayList<PiThread> threads) throws InterruptedException {
        for (PiThread t: threads) {
            t.join();
        }
    }

    public static byte[] concatenateResults(ArrayList<PiThread> threads, int amount){
        byte[] result = new byte[amount];
        int begin = 0;
        for(PiThread t: threads){
            System.arraycopy(t.getResult(), 0, result, begin, t.getResult().length);
            begin+=t.getResult().length;
        }
        return result;
    }
}