package com.example.bit_bus.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bit_bus.IntroActivity;
import com.example.bit_bus.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class PofiActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Animation topAnim , bottomAnim;
    ImageView image;
    TextView logo;


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        UserMenuSelected(menuitem);
        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pofi);




        //Animation

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        image = findViewById(R.id.imageView5);
        logo=findViewById(R.id.textView4);


        logo.setAnimation(bottomAnim);




        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        drawerLayout =  findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(PofiActivity.this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //load defult fragment


        navigationView = findViewById(R.id.navigation_view);
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
        navigationView.setItemIconTintList(null);
        //
        navigationView.setNavigationItemSelectedListener((this));



    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //option click open diff windows
    public void UserMenuSelected(MenuItem menuItem)
    {
        switch(menuItem.getItemId())
        {
            case R.id.nav_Profile:
               Intent intent = new Intent(PofiActivity.this,ProfileActivity.class);
               startActivity(intent);
               Toast.makeText(this,"profile",Toast.LENGTH_SHORT).show();
              break;

            case R.id.nav_Map:
               Intent X = new Intent(PofiActivity.this,MapLoginActivity.class);
               startActivity(X);
               break;

            case R.id.nav_ContactUs:
                Intent intentk = new Intent(PofiActivity.this,ContactActivity.class);
                startActivity(intentk);
                break;

            case R.id.nav_Feedback:
                Toast.makeText(this,"THANK YOU FOR YOUR FEEDBACK",Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_LogOut:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this,MainActivity.class));
                Toast.makeText(PofiActivity.this,"Succesfully Logout",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_BusTiming:
                Intent ontent = new  Intent(PofiActivity.this , BUSActivity.class);
                startActivity(ontent);
                break;

            case R.id.nav_Share:
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.setType("text/plane");
                String shareBody = "https";
                String shareSubject = "BIT-BUS";
                share.putExtra(Intent.EXTRA_TEXT,shareBody);
                share.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
                startActivity(Intent.createChooser(share,"Share Using"));
                break;

            case R.id.nav_About:
                Intent intentss = new Intent(PofiActivity.this, IntroActivity.class);
                startActivity(intentss);

                break;


        }

    }


}
