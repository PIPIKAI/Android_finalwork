package com.data;

public class USER {

    private int gola,now,had;
    private String username;
    private String password;

    public USER() {
    }

    public USER(int gola, int now, String username, String password,int had) {
        this.gola = gola;
        this.now = now;
        this.had=had;
        this.username = username;
        this.password = password;
    }

    public int getGola() {
        return gola;
    }

    public void setGola(int gola) {
        this.gola = gola;
    }

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public int getHad() {
        return had;
    }

    public void setHad(int had) {
        this.had = had;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "USER{" +
                "gola=" + gola +
                ", now=" + now +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
