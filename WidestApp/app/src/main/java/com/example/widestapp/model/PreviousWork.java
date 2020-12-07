package com.example.widestapp.model;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;

public class PreviousWork {
    public final static String IMAGE_DIRECTORY = "trabalhos_images";

    private final String nomeTrabalho;
    private final String temaTrabalho;
    private final String imagemTrabalho;

    public PreviousWork(String nomeTrabalho, String temaTrabalho, String imagemTrabalho) {
        this.nomeTrabalho = nomeTrabalho;
        this.temaTrabalho = temaTrabalho;
        this.imagemTrabalho = imagemTrabalho;
    }

    public String getNomeTrabalho() {
        return nomeTrabalho;
    }

    public String getTemaTrabalho() {
        return temaTrabalho;
    }

    public String getImagemTrabalho() {
        return imagemTrabalho;
    }

    @Nullable
    public Uri getImageURI(@NonNull Context context) {
        return Uri.parse(this.imagemTrabalho);
    }

}
