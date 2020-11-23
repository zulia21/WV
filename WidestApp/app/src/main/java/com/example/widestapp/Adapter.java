package com.example.widestapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.SliderViewHolder> {
    private List<SliderItem> slideritems;
    private ViewPager2 viewPager2;


    Adapter(List<SliderItem> slideritems, ViewPager2 viewPager2) {
        this.viewPager2 = viewPager2;
        this.slideritems = slideritems;

    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slider_item_container,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setDados(slideritems.get(position));


    }

    @Override
    public int getItemCount() {
        return slideritems.size();
    }



    class SliderViewHolder extends RecyclerView.ViewHolder {

        private RoundedImageView imageView;
        public TextView txtTema;
        public TextView txtTitulo;

        SliderViewHolder(@NonNull View itemView)  {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
            txtTema = itemView.findViewById(R.id.txttema);
            txtTitulo = itemView.findViewById(R.id.txttitulo);
        }

        void setDados(SliderItem sliderItem) {
            imageView.setImageResource(sliderItem.getImagem());
            txtTema.setText(sliderItem.getTema());
            txtTitulo.setText(sliderItem.getTitle());
        }




    }


}

