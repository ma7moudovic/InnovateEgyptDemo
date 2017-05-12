package com.shar2wy.andtask.models;

/**
 * Created by shar2wy on 5/12/17.
 */

public class NavItem {

    String title;
    int image;

    public NavItem(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
