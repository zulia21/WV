package com.example.widestapp;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class FragmentMenu extends Fragment {
    private ViewPager2 viewPager2;

    DrawerLayout drawer;
    NavigationView navigationView;

    RecyclerView recyclerView;

    TrabAdapter adapter;

    List<Trab_Anterior_Item> result = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        viewPager2 = (ViewPager2) view.findViewById(R.id.viewPagerImageSlider);

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
        // recyclerview
        recyclerView = (RecyclerView) view.findViewById(R.id.trab_anterior_recyclerview);
        java.util.function.Function<Integer, Uri> uri = (resource) -> Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(resource)
                + '/' + getResources().getResourceTypeName(resource) + '/'
                + getResources().getResourceEntryName(resource));

        result.add(new Trab_Anterior_Item("Daydreamer Veg", "Alimentação", String.valueOf(uri.apply(R.drawable.back))));
        result.add(new Trab_Anterior_Item("Car Good", "Automobilístico", String.valueOf(uri.apply(R.drawable.back))));
        result.add(new Trab_Anterior_Item("Sheriff's Açaí", "Alimentação", String.valueOf(uri.apply(R.drawable.back))));
        result.add(new Trab_Anterior_Item("PetShow", "Animais", String.valueOf(uri.apply(R.drawable.back))));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TrabAdapter(getContext(), result);
        recyclerView.setAdapter(adapter);

        return view;

    }

}
