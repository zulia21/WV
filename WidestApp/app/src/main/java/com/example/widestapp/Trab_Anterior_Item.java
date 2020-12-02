package com.example.widestapp;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;

public class Trab_Anterior_Item {
    public final static String IMAGE_DIRECTORY = "trabalhos_images";
    private String nomeTrabalho;
    private String temaTrabalho;
    private String imagemTrabalho;

    Trab_Anterior_Item(String nomeTrabalho, String temaTrabalho, String imagemTrabalho) {
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
        File directory = new File(context.getFilesDir(), IMAGE_DIRECTORY);

        File file = new File(directory, getImagemTrabalho());

        if (!directory.exists() || !file.exists()) {
            return null;
        } else {
            return Uri.fromFile(file);
        }
    }

}
