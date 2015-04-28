package v.assignment_4;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Planet implements Serializable {
    String name;
    String size;
    String meanTemperature;
    String nfo;
    Drawable image;

    public Planet(String name, String info, String size, String meanTemperature, Drawable image) {
        this.name = name;
        this.nfo = info;
        this.size = size;
        this.meanTemperature = meanTemperature;
        this.image = image;
    }

    public String getInfo() {
        return nfo;
    }

    public void setInfo(String isbn) {
        this.nfo = isbn;
    }

    public Drawable getImage() {
        return image;
    }

    public void setName(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getMeanTemperature() {
        return meanTemperature;
    }

    public String getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getSummary() {
        return "Size: "+size+"\n"+"Mean temperature: "+meanTemperature;
    }
}