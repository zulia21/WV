package com.example.widestapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;

    RecyclerView recyclerView;

    TrabAdapter adapter;

    List<Trab_Anterior_Item> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // carousel
        viewPager2 = findViewById(R.id.viewPagerImageSlider);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.capaool, "Out Of Lens", "Fotografia"));
        sliderItems.add(new SliderItem(R.drawable.capapatolandia, "Patolândia", "Parque de Diversões"));

        viewPager2.setAdapter(new com.example.widestapp.Adapter(sliderItems, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        compositePageTransformer.addTransformer((new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r  = 1 - Math.abs(position);
                page.setScaleY(0.75f + r * 0.25f);
            }
        }));
        viewPager2.setPageTransformer(compositePageTransformer);

        recyclerView = (RecyclerView) findViewById(R.id.trab_anterior_recyclerview);
        java.util.function.Function<Integer, Uri> uri = (resource) -> Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                        "://" + getResources().getResourcePackageName(resource)
                        + '/' + getResources().getResourceTypeName(resource) + '/'
                        + getResources().getResourceEntryName(resource));

        result.add(new Trab_Anterior_Item("Daydreamer Veg", "Alimentação", String.valueOf(uri.apply(R.drawable.back))));
        result.add(new Trab_Anterior_Item("Car Good", "Automobilístico", String.valueOf(uri.apply(R.drawable.back))));
        result.add(new Trab_Anterior_Item("Sheriff's Açaí", "Alimentação", String.valueOf(uri.apply(R.drawable.back))));
        result.add(new Trab_Anterior_Item("PetShow", "Animais", String.valueOf(uri.apply(R.drawable.back))));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TrabAdapter(this, result);
        recyclerView.setAdapter(adapter);

    }

}