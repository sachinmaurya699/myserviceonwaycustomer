package com.sossolution.serviceonway.Class;

public  class Subscription_grid_item
{


    String title;
    String text;
    int image;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage()
    {
        return image;
    }

    public void setImage(int image)
    {
        this.image = image;
    }

    public Subscription_grid_item(String text, int image,String title)
    {
        this.text = text;
        this.image = image;
        this.title= title;
    }
}
