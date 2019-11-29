package com.data;

public class USER {
    private int id,gole,now;
    private String username;
    private String password;
    USER(){

    }
    USER(String username, String password){
        this.id=0;
        this.username=username;
        this.password=password;
    }
    public void setcount(String id,String pass) {
        this.username=id;
        this.password = pass;
    }
    public void setGole(int gole){
        this.gole=gole;
    }
    public String getPassword(){
        return  this.password;
    }
    public String getUsername(){
        return  this.username;
    }
}
