package com.example.widestapp;

public class SliderItem {

    private final int imagem;
    private final String title;
    private final String tema;


    SliderItem(int imagem, String  title, String tema) {
        this.imagem = imagem;
        this.title = title;
        this.tema = tema;
    }
    public int getImagem()
    {
        return imagem;
    }
    public String getTitle() { return title; }
    public String getTema() { return tema; }
}
