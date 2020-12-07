package com.example.widestapp.model;

import android.net.Uri;

public class SliderItem {

    private final Uri image;
    private final String title;
    private final String subject;


    public SliderItem(Uri image, String  title, String subject) {
        this.image = image;
        this.title = title;
        this.subject = subject;
    }
    public Uri getImage()
    {
        return image;
    }
    public String getTitle() { return title; }
    public String getSubject() { return subject; }
}
