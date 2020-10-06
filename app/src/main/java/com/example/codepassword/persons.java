package com.example.codepassword;

import android.graphics.Bitmap;

public class persons {
    private String name;
    private String money;
    private String photo;
    private String time;
    private String num;
    private Bitmap bitmap;
    public persons(String name, String money, String photo, String time,String num,Bitmap bitmap) {
        this.name=name;
        this.money=money;
        this.photo=photo;
        this.time=time;
        this.num=num;
        this.bitmap=bitmap;
    }
    public String getNum(){
        return num;
    }
    public void setNum(String num){
        this.num=num;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getMoney(){
        return money;
    }
    public void setMoney(String money){
        this.money=money;
    }
    public String getPhoto(){
        return photo;
    }
    public void setPhoto(String photo){
        this.photo=photo;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time=time;
    }
    public Bitmap getBitmap() {
        return bitmap;
    }
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
