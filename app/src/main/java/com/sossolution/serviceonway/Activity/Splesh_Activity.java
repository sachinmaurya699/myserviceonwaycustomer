package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.sossolution.serviceonway.R;
import com.sossolution.serviceonway.Class.User;

public class Splesh_Activity extends AppCompatActivity
{

    private static final int SPLASH_SCREEN_TIME_OUT=2000;
    private ImageView imageView,image_car,image_bike;
    private Animation animation;
    private TextView text_app_name;
    private  User user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh_);
        FirebaseApp.initializeApp(this);

      //  (Splesh_Activity.this).getSupportActionBar().hide();
        imageView=findViewById(R.id.imageview_logo);
        text_app_name=findViewById(R.id.text_app_name);
        image_car=findViewById(R.id.image_car);
        image_bike=findViewById(R.id.image_bike);

        if (savedInstanceState == null)
        {
            flyIn();
        }


        user = new User(Splesh_Activity.this);
          new Handler().postDelayed(new Runnable()
          {
            @Override
            public void run()
            {

                endSplash();
               /* if(user.getPhone()!="")
                {
                    Intent intent= new Intent(Splesh_Activity.this, MainActivity.class);
                    intent.putExtra("userdata",user.getPhone());
                    startActivity(intent);
                    finish();
                    endSplash();

                }
                else
                {
                    Intent intent= new Intent(Splesh_Activity.this, Login_Activity.class);
                    startActivity(intent);
                    finish();

                }*/

            }
        }, SPLASH_SCREEN_TIME_OUT);


    }

    private void endSplash() {


        animation = AnimationUtils.loadAnimation(this,
                R.anim.logo_back_animation);
        imageView.startAnimation(animation);

        //image_car...

        animation = AnimationUtils.loadAnimation(this,
                R.anim.appname_back_animation);
        image_car.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this,
                R.anim.appname_back_animation);

        //image_bike...

        image_bike.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this,
                R.anim.appname_back_animation);
        text_app_name.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
               // user.getPhone()!=""

                if(user.getPhone()!="")
                {
                    Intent intent= new Intent(Splesh_Activity.this, MainActivity.class);
                    intent.putExtra("userdata",user.getPhone());
                    Log.d("value_user",user.getPhone());
                   // Toast.makeText(Splesh_Activity.this,user.getName()+"value", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                    //Toast.makeText(Splesh_Activity.this, "value hai", Toast.LENGTH_SHORT).show();
                    //endSplash();

                }
                else
                {
                    Intent intent= new Intent(Splesh_Activity.this, Login_Activity.class);
                    startActivity(intent);
                    finish();
                   // Toast.makeText(Splesh_Activity.this,user.getName()+"v1", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });


    }

    private void flyIn() {


        animation = AnimationUtils.loadAnimation(this, R.anim.logo);
        imageView.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this,
                R.anim.appname);
        text_app_name.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this,
                R.anim.car_image);
        image_car.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this,
                R.anim.bike_image);
        image_bike.startAnimation(animation);


    }

}
