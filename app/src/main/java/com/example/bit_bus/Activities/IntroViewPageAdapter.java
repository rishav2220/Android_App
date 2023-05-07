package com.example.bit_bus.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;


import com.example.bit_bus.R;

import java.util.List;

public class IntroViewPageAdapter extends RecyclerView.Adapter<IntroViewPageAdapter.IntroViewPageViewHolder> {

    private List<ScreenItem>screenItems;

    public IntroViewPageAdapter(List<ScreenItem> screenItems) {
        this.screenItems = screenItems;
    }

    @NonNull
    @Override
    public IntroViewPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IntroViewPageViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.layout_screen,parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull IntroViewPageViewHolder holder, int position) {
                holder.setOnboardingData(screenItems.get(position));
    }

    @Override
    public int getItemCount() {
        return screenItems.size();
    }

    class IntroViewPageViewHolder extends RecyclerView.ViewHolder

    {
         private TextView textTitle;
         private TextView textDescription;
         private ImageView imageOnboarding;

         IntroViewPageViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle=itemView.findViewById(R.id.intro_title);
            textDescription =  itemView.findViewById(R.id.intro_description);
            imageOnboarding = itemView.findViewById(R.id.intro_img);

        }

        void setOnboardingData(ScreenItem screenItem){

            textTitle.setText(screenItem.getTitle());
            textDescription.setText(screenItem.getDescription());
            imageOnboarding.setImageResource(screenItem.getScreenImg());
        }
    }
    }


