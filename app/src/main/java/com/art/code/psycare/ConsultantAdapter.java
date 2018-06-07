package com.art.code.psycare;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class ConsultantAdapter extends RecyclerView.Adapter<ConsultantAdapter.ViewHolder>{

    private List<Consultant> mConsultantList;
    private Activity activity;

    public ConsultantAdapter(List<Consultant> consultants, Activity activity) {
        this.mConsultantList = consultants;
        this.activity = activity;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View consultantView;
        TextView name;
        TextView rank;
        TextView price;
        RatingBar rating;

        public ViewHolder(View view){
            super(view);
            consultantView = view;
            name = view.findViewById(R.id.name_consultant_item);
            rank = view.findViewById(R.id.rank_consultant_item);
            price = view.findViewById(R.id.price_consultant_item);
            rating = view.findViewById(R.id.ratingBar_consultant_item);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consultant_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.consultantView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Consultant consultant = mConsultantList.get(position);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Consultant consultant = mConsultantList.get(position);
        holder.name.setText(consultant.getName());
        holder.rank.setText(consultant.getRank());
        holder.price.setText(consultant.getPrice());
        holder.rating.setNumStars(consultant.getScore());
        holder.rating.setRating(consultant.getScore());
    }

    @Override
    public int getItemCount() {
        return mConsultantList.size();
    }
}
