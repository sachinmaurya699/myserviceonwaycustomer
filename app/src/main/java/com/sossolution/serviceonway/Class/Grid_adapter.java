package com.sossolution.serviceonway.Class;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.sossolution.serviceonway.R;

import java.util.ArrayList;

public  class Grid_adapter extends BaseAdapter{
    private Context context;
    private  int layout;
    private ArrayList<Food> foodList;

    public Grid_adapter(Context context, int gridview_design, ArrayList<Food> list)
    {
        this.context=context;
        this.layout=gridview_design;
        this.foodList=list;
    }

    @Override
    public int getCount()
    {
        return foodList.size() ;
    }

    @Override
    public Object getItem(int position)
    {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }
    private static class Viewholder
    {
        ImageView imageView;

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        View row=view;
        Viewholder viewholder= new Viewholder();

        if(row!=null)
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);
            viewholder.imageView=row.findViewById(R.id.my_img);
            row.setTag(viewholder);
        }
        else
        {
            viewholder= (Viewholder) row.getTag();

        }
        Food food=foodList.get(position);
        byte[] foodimage=food.getImage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(foodimage,0,foodimage.length);
        viewholder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
