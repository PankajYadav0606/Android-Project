package com.example.listviewcontact.ModelClass;

public class Sports {
    int sportImg;
    String sportName;

    public Sports(int sportImg, String sportName) {
        this.sportImg = sportImg;
        this.sportName = sportName;
    }

    public int getSportImg() {
        return sportImg;
    }

    public void setSportImg(int sportImg) {
        this.sportImg = sportImg;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }
}
