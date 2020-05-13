package com.company;

public class Check {
    public static void main(String [] args) throws InterruptedException {
        double start=System.currentTimeMillis();
        Thread.sleep(0);
        double end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
