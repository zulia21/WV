package com.example.widestapp.fragment;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

import com.example.widestapp.R;
import com.example.widestapp.adapter.SliderAdapter;
import com.example.widestapp.adapter.WorkAdapter;
import com.example.widestapp.model.Project;
import com.example.widestapp.model.SliderItem;
import com.example.widestapp.model.PreviousWork;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuFragment extends Fragment {

    RecyclerView recyclerView;

    WorkAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        List<Project> projects = Project.select(getContext());

        List<PreviousWork> recyclerViewItems = new ArrayList<>();
        List<SliderItem> viewPagerItems = new ArrayList<>();

       for (Project project : projects) {

            Date date;

            try {
                date = project.getDataObject();
            }
            catch (java.text.ParseException ex) {
                Log.e("beep", "ParseException", ex);
                continue;
            }

            if (date.compareTo(new Date()) > 0) {

                viewPagerItems.add(new SliderItem(Uri.parse(project.getCapa()), project.getName(), project.getTema()));
            }
            else {
                recyclerViewItems.add(new PreviousWork(project.getName(), project.getTema(), project.getCapa()));
            }

        }

       View view = inflater.inflate(R.layout.fragment_menu, container, false);

        ViewPager2 viewPager2 = view.findViewById(R.id.viewPagerImageSlider);

//         List<SliderItem> sliderItems = new ArrayList<>();
//         sliderItems.add(new SliderItem(R.drawable.capaool, "Out Of Lens", "Fotografia"));
//         sliderItems.add(new SliderItem(R.drawable.capapatolandia, "Patolândia", "Parque de Diversões"));

        viewPager2.setAdapter(new SliderAdapter(viewPagerItems));
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





        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WorkAdapter(getContext(), recyclerViewItems);
        recyclerView.setAdapter(adapter);

        return view;

    }

}
