/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    
    public static void main(String a[]){
        CountThread t1 = new CountThread();
        t1.setAB(0, 99);
        CountThread t2 = new CountThread();
        t2.setAB(100, 199);
        CountThread t3 = new CountThread();
        t3.setAB(200, 299);
        t3.start();
        t1.start();
        t2.start();
    }
    
}
