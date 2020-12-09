package com.example.widestapp.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.example.widestapp.activity.ProjectActivity;
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

    String nomeproj;

    public final static String NOME_PROJ = null;
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

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                SliderItem sliderItem = viewPagerItems.get(position);
                nomeproj = sliderItem.getTitle();

            }
        });
        viewPager2.performClick();
        viewPager2.setOnClickListener(this::uniaoProjetos);

        // recyclerview
        recyclerView = view.findViewById(R.id.trab_anterior_recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WorkAdapter(getContext(), recyclerViewItems);
        recyclerView.setAdapter(adapter);


        return view;

    }

    public void uniaoProjetos(View view)
    {

        Intent intent = new Intent(getContext(), ProjectActivity.class);
        intent.putExtra(NOME_PROJ, nomeproj);
        startActivity(intent);
    }

}
