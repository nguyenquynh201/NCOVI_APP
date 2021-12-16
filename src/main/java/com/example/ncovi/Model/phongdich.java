package com.example.ncovi.Model;

public class phongdich {
    int bg;
    int bg_icon;
    int icon;
    String title;

    public phongdich(int bg, int bg_icon, int icon, String title) {
        this.bg = bg;
        this.bg_icon = bg_icon;
        this.icon = icon;
        this.title = title;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    public int getBg_icon() {
        return bg_icon;
    }

    public void setBg_icon(int bg_icon) {
        this.bg_icon = bg_icon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
