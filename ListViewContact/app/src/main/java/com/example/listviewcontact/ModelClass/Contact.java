package com.example.listviewcontact.ModelClass;

public class Contact {


    String name;
    String no;
    int CImage;


    public Contact( int CImage, String name, String no) {
        this.name = name;
        this.no = no;
        this.CImage = CImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getCImage() {
        return CImage;
    }

    public void setCImage(int CImage) {
        this.CImage = CImage;
    }
}
