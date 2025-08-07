package com.whatsapp.api.practice;

import java.util.Scanner;

class Cars{
    private String name;
    private String brand;
    private int length;
    private String engine;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
}

public class Practice {
    private static Cars[] c= new Cars[20];

    public static void main(String[] args) {
        Scanner s =new Scanner(System.in);
        for(int i=0;i<5;i++){
            c[i]=new Cars();
            c[i].setName(s.nextLine());
            System.out.println(c[i]);
            Object o=new Object();
        }
    }
}
