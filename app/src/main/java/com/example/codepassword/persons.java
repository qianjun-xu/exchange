package com.example.codepassword;

public class persons {
    private String name;
    private String money;
    private String photo;
    private String time;

    public persons(String name, String money, String photo, String time) {
        this.name=name;
        this.money=money;
        this.photo=photo;
        this.time=time;
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

}