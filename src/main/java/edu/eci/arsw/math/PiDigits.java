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

    public static void main(String[] args) {
        PiThread threadP = new PiThread();
        threadP.setAB(2, 1);
        threadP.start();
    }
}