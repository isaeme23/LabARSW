package edu.eci.arsw.math;

import edu.eci.arsw.bbp.PiThread;

///  <summary>
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits {

    public static int maxValue = 10;
    /**
     * Returns a range of hexadecimal digits of pi.
     * @param start The starting location of the range.
     * @param count The number of digits to return
     * @return An array containing the hexadecimal digits.
     */
    public static void getDigits(int n) {
        int div = maxValue/n;
        int init = 0;
        for (int i = 0; n > i; i++){
            PiThread threadP = new PiThread();
            threadP.setAB(init, div);
            threadP.setPosition(i);
            init+=div;
            threadP.start();
        }
    }
}
