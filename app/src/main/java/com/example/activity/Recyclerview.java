package com.example.activity;

public class Recyclerview {
      private int img;
    private String description;
    public Recyclerview(int img,String description){
        this.img=img;
        this.description=description;
    }
    public int getImg(){
        return this.img;
    }
    public  String getDescription(){
        return  this.description;
    }
}
