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
public class CountThread extends Thread{

    int a,b;
    @Override
    public void run() {
        for (int i = getA()+1; i < getB(); i++ ) {
            System.out.println(i);
        }
    }

    public static void main(String[] args){
        CountThread obj = new CountThread();
        obj.start();
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getB() {
        return b;
    }

    public void setAB(int a, int b){
        setA(a);
        setB(b);
    }
}
