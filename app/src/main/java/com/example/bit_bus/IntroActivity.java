package com.example.bit_bus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;


import com.example.bit_bus.Activities.IntroViewPageAdapter;
import com.example.bit_bus.Activities.PofiActivity;
import com.example.bit_bus.Activities.ScreenItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {



    IntroViewPageAdapter introViewPagerAdapter;
    private LinearLayout layoutOnboardingIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    Animation btnAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_intro);
        layoutOnboardingIndicator = findViewById(R.id.tab_indicator);
        btnNext = findViewById(R.id.btn_next);
        setupOnboardingItem();
        final ViewPager2 onboardingViewPager = findViewById(R.id.screen_viewpager);
        onboardingViewPager.setAdapter(introViewPagerAdapter);

        setupOnboardingIndicator();
        setCurrentOnboardingIndicator(0);

      onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
          @Override
          public void onPageSelected(int position) {
              super.onPageSelected(position);
              setCurrentOnboardingIndicator(position);
          }
      });


      btnNext.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if (onboardingViewPager.getCurrentItem() + 1 < introViewPagerAdapter.getItemCount()) {
                  onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
              } else
              {
                  startActivity(new Intent(getApplicationContext(), PofiActivity.class));
                  finish();
              }


          }
      });

    }

    private void setupOnboardingItem()
    {
        List<ScreenItem>screenItems = new ArrayList<>();
        ScreenItem juhi = new ScreenItem();
        juhi.setTitle("JUHI SINGH");
        juhi.setDescription("BCA/15032/17");
        juhi.setScreenImg(R.drawable.juhi);

        ScreenItem Nisha = new ScreenItem();
        Nisha.setTitle("NISHA");
        Nisha.setDescription("BCA/15016/17");
        Nisha.setScreenImg(R.drawable.nisha);

        ScreenItem nikita = new ScreenItem();
        nikita.setTitle("NIKITA ANAND");
        nikita.setDescription("BCA/15040/17");
        nikita.setScreenImg(R.drawable.nikita);

        ScreenItem sanya = new ScreenItem();
        sanya.setTitle("SANYA LAL");
        sanya.setDescription("BCA/15002/17");
        sanya.setScreenImg(R.drawable.sanya);

        screenItems.add(juhi);
        screenItems.add(Nisha);
        screenItems.add(nikita);
        screenItems.add(sanya);

        introViewPagerAdapter = new IntroViewPageAdapter(screenItems);




    }

    private void setupOnboardingIndicator() {

        ImageView[] indicators = new ImageView[introViewPagerAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(

                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(

                    getApplicationContext(),
                    R.drawable.indicator_default
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicator.addView(indicators[i]);
        }
    }
    @SuppressLint("SetTextI18n")
    private void setCurrentOnboardingIndicator(int index)
    {
        int childcount = layoutOnboardingIndicator.getChildCount();
        for(int i = 0;i<childcount;i++)
        {
            ImageView imageView = (ImageView)layoutOnboardingIndicator.getChildAt(i);
            if(i==index)
            {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.indicator_selected)
                );
            }else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.indicator_default)
                );
            }
        }
        if(index==introViewPagerAdapter.getItemCount()-1)
        {
            btnNext.setText("THANK YOU");
        }else
        {
            btnNext.setText("NEXT");
        }
    }
}







