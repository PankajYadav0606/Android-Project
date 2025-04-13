package com.example.listviewplanet;

public class planet {

    int planetImage;
    String title, mooncount;

    public planet(int planetImage, String title, String mooncount) {
        this.planetImage = planetImage;
        this.title = title;
        this.mooncount = mooncount;
    }

    public int getPlanetImage() {

        return planetImage;
    }

    public void setPlanetImage(int planetImage) {

        this.planetImage = planetImage;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getMooncount() {

        return mooncount;
    }

    public void setMooncount(String mooncount) {

        this.mooncount = mooncount;
    }
}
