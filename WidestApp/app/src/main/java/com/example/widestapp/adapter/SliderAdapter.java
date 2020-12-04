package com.example.widestapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.widestapp.R;
import com.example.widestapp.model.SliderItem;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private final List<SliderItem> sliderItems;



    public SliderAdapter(List<SliderItem> sliderItems) {
        this.sliderItems = sliderItems;
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
        holder.setDados(sliderItems.get(position));


    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }



    static class SliderViewHolder extends RecyclerView.ViewHolder {

        private final RoundedImageView imageView;
        public final TextView txtTema;
        public final TextView txtTitulo;

        SliderViewHolder(@NonNull View itemView)  {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
            txtTema = itemView.findViewById(R.id.txttema);
            txtTitulo = itemView.findViewById(R.id.txttitulo);
        }

        void setDados(SliderItem sliderItem) {
            imageView.setImageResource(sliderItem.getImage());
            txtTema.setText(sliderItem.getSubject());
            txtTitulo.setText(sliderItem.getTitle());
        }




    }


}

