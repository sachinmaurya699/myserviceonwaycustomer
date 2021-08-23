package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.sossolution.serviceonway.Activity.Bike_subscription_Activity;
import com.sossolution.serviceonway.Activity.FormFill_Activity2;
import com.sossolution.serviceonway.Activity.Tezt_Activity;
import com.sossolution.serviceonway.Activity.Vechicle_listing_Activity;
import com.sossolution.serviceonway.Activity.Vehicle_Activity;
import com.sossolution.serviceonway.Fragment.Home_Fragment;
import com.sossolution.serviceonway.R;
import com.squareup.picasso.Picasso;

import static android.content.Context.MODE_PRIVATE;

public class SliderAdapter  extends PagerAdapter{

   private Context context;
   private LayoutInflater inflater;
   private int[] urls;

    public SliderAdapter(Context context, int[] urls)
    {
        this.context=context;
        this.urls=urls;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        Log.d("url", String.valueOf(urls.length));
        return urls.length;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {


        ViewPager vp = (ViewPager)container;
        View view = (View) object;
        container.removeView(view);


    }
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.slidernewimage,container,false);
        assert view != null;
        final ImageView imageView =view.findViewById(R.id.imageviewnew);
        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(position==1)
                {

                   // Toast.makeText(context, "hello how r u", Toast.LENGTH_SHORT).show();
                   Intent intent= new Intent(context, Vehicle_Activity.class);
                    context.startActivity(intent);

                  /*  Intent intent= new Intent(context, Bike_subscription_Activity.class);
                    context.startActivity(intent);
*/

                   /* SharedPreferences preferences1=context.getSharedPreferences("value1", MODE_PRIVATE);
                    SharedPreferences.Editor editor1=preferences1.edit();
                    editor1.putString("value","6");
                    editor1.apply();*/



                    SharedPreferences preferences=context.getSharedPreferences("value1",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("value","0");
                    editor.apply();


                }
                if(position==0)
                {

                    // Toast.makeText(context, "hello how r u", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(context, Vehicle_Activity.class);
                    context.startActivity(intent);


                    //store slider page value

                    SharedPreferences preferences=context.getSharedPreferences("value1", MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("value","5");
                    editor.apply();





                   /* SharedPreferences preferences=context.getSharedPreferences("value1",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("value","0");
                    editor.apply();
*/

                }

                if(position==2)
                {

                    Intent intent= new Intent(context, Vechicle_listing_Activity.class);
                    context.startActivity(intent);



                }


            }
        });
       // imageView.setImageResource(Integer.parseInt(String.valueOf(urls[position])));
        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);
        /*Glide.with(context)
                .load(urls[position])
                .resize(1000,400)
                .into(imageView);*/

        Picasso.get()
                .load(urls[position])
                .fit()
                .into(imageView);


      //  view.addView(view, 0);


        return view;
    }

    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader)
    {
        super.restoreState(state, loader);

    }

    @Nullable
    @Override
    public Parcelable saveState()
    {
        return super.saveState();
    }
}
