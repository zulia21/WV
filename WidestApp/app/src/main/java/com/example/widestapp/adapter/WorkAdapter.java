package com.example.widestapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.widestapp.R;
import com.example.widestapp.model.PreviousWork;

import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.TrabViewHolder> {

    private final Context context;
    private final List<PreviousWork> trabalhos;

    public WorkAdapter(Context context, List<PreviousWork> trabalhos) {
        this.context = context;
        this.trabalhos = trabalhos;
    }

    @NonNull
    @Override
    public WorkAdapter.TrabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new TrabViewHolder(inflater.inflate(R.layout.previous_work_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkAdapter.TrabViewHolder holder, int position) {

        int times2 = position * 2;

        holder.bind(trabalhos.get(times2), times2 + 1 < trabalhos.size() ? trabalhos.get(times2 + 1) : null);
    }

    @Override
    public int getItemCount() {
        return trabalhos.size() / 2;
    }

    public class TrabViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout firstLayout;
        public ConstraintLayout secondLayout;

        public TextView firstTitle;
        public TextView secondTitle;

        public TextView firstTema;
        public TextView secondTema;

        public ImageView firstImage;
        public ImageView secondImage;

        public TrabViewHolder(@NonNull View itemView) {
            super(itemView);

            firstLayout = itemView.findViewById(R.id.firstConstraint);
            firstTitle = itemView.findViewById(R.id.txttituloprincipalfirst);
            firstTema = itemView.findViewById(R.id.txttemaprincipalfirst);
            firstImage = itemView.findViewById(R.id.imgcapaprincipalfirst);

            secondLayout = itemView.findViewById(R.id.secondConstraint);
            secondTitle = itemView.findViewById(R.id.txttituloprincipalsecond);
            secondTema = itemView.findViewById(R.id.txttemaprincipalsecond);
            secondImage = itemView.findViewById(R.id.imgcapaprincipalsecond);
        }

        public void bind(@NonNull PreviousWork model1, @Nullable PreviousWork model2) {

            firstTitle.setText(model1.getNomeTrabalho());
            firstTema.setText(model1.getTemaTrabalho());
            firstImage.setImageURI(model1.getImageURI(context));

            if (model2 == null) {
                this.secondLayout.setVisibility(View.INVISIBLE);

            } else {
                secondTitle.setText(model2.getNomeTrabalho());
                secondTema.setText(model2.getTemaTrabalho());

                Uri image = model2.getImageURI(context);

                if (image != null) {
                    secondImage.setImageURI(image);
                }
            }


        }

    }
}
