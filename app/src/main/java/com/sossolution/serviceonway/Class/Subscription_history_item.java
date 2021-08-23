package com.sossolution.serviceonway.Class;

public class Subscription_history_item
{

    String id;
    String purches_date;
    String price;

    public Subscription_history_item()
    {

    }
    public String getId()
    {
        return id;
    }
    public String getPurches_date()
    {
        return purches_date;
    }
    public  String getPrice()
    {
        return price;
    }
    public void setId(String id)
    {
        this.id=id;
    }
    public void setPurches_date(String purches_date)
    {
        this.purches_date=purches_date;
    }
    public void setPrice(String price)
    {
        this.price=price;
    }

}
