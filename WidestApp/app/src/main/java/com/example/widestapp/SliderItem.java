package com.example.widestapp;

public class SliderItem {

    private final int image;
    private final String title;
    private final String subject;


    SliderItem(int image, String  title, String subject) {
        this.image = image;
        this.title = title;
        this.subject = subject;
    }
    public int getImage()
    {
        return image;
    }
    public String getTitle() { return title; }
    public String getSubject() { return subject; }
}
