package com.sossolution.serviceonway.Class;


public  class Vehicle_name
{

    int imageView;
    String name;

    public Vehicle_name(int imageView, String name)
    {
        this.imageView=imageView;
        this.name=name;

    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
