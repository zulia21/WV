package com.example.widestapp;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class FragmentMenu extends Fragment {

    RecyclerView recyclerView;

    WorkAdapter adapter;

    List<Work> result = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        ViewPager2 viewPager2 = view.findViewById(R.id.viewPagerImageSlider);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.capaool, "Out Of Lens", "Fotografia"));
        sliderItems.add(new SliderItem(R.drawable.capapatolandia, "Patolândia", "Parque de Diversões"));

        viewPager2.setAdapter(new SliderAdapter(sliderItems));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));

        compositePageTransformer.addTransformer(((page, position) -> {
            float r  = 1 - Math.abs(position);
            page.setScaleY(0.75f + r * 0.25f);
        }));

        viewPager2.setPageTransformer(compositePageTransformer);
        // recyclerview
        recyclerView = view.findViewById(R.id.trab_anterior_recyclerview);
        java.util.function.Function<Integer, Uri> uri = (resource) -> Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(resource)
                + '/' + getResources().getResourceTypeName(resource) + '/'
                + getResources().getResourceEntryName(resource));

        result.add(new Work("Daydreamer Veg", "Alimentação", String.valueOf(uri.apply(R.drawable.back))));
        result.add(new Work("Car Good", "Automobilístico", String.valueOf(uri.apply(R.drawable.back))));
        result.add(new Work("Sheriff's Açaí", "Alimentação", String.valueOf(uri.apply(R.drawable.back))));
        result.add(new Work("PetShow", "Animais", String.valueOf(uri.apply(R.drawable.back))));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WorkAdapter(getContext(), result);
        recyclerView.setAdapter(adapter);

        return view;

    }

}
