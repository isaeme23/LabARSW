package edu.eci.arsw.math;

import java.util.ArrayList;

///  <summary>
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits {

    //private static int DigitsPerSum = 8;
    //private static double Epsilon = 1e-17;

    public static byte[] getDigitsPi (int start, int amount, int n) throws InterruptedException {
        ArrayList<PiThread> threads =  createThreads(start, amount, n);
        joinThreads(threads);
        return concatenateResults(threads, amount, start);
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

    public static byte[] concatenateResults(ArrayList<PiThread> threads, int amount, int start){
        byte[] result = new byte[amount];
        for(PiThread t: threads){
            System.arraycopy(t.getResult(), 0, result, start, t.getResult().length);
            start+=t.getResult().length;
        }
        return result;
    }


    /*public static byte[] getDigits(int start, int count) {
        if (start < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        if (count < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        byte[] digits = new byte[count];
        double sum = 0;

        for (int i = 0; i < count; i++) {
            if (i % DigitsPerSum == 0) {
                sum = 4 * sum(1, start)
                        - 2 * sum(4, start)
                        - sum(5, start)
                        - sum(6, start);

                start += DigitsPerSum;
            }

            sum = 16 * (sum - Math.floor(sum));
            digits[i] = (byte) sum;
        }

        bytesToHex(digits);
        return digits;
    }

    private static double sum(int m, int n) {
        double sum = 0;
        int d = m;
        int power = n;

        while (true) {
            double term;

            if (power > 0) {
                term = (double) hexExponentModulo(power, d) / d;
            } else {
                term = Math.pow(16, power) / d;
                if (term < Epsilon) {
                    break;
                }
            }

            sum += term;
            power--;
            d += 8;
        }

        return sum;
    }

    private static int hexExponentModulo(int p, int m) {
        int power = 1;
        while (power * 2 <= p) {
            power *= 2;
        }

        int result = 1;

        while (power > 0) {
            if (p >= power) {
                result *= 16;
                result %= m;
                p -= power;
            }

            power /= 2;

            if (power > 0) {
                result *= result;
                result %= m;
            }
        }

        return result;
    }*/
}