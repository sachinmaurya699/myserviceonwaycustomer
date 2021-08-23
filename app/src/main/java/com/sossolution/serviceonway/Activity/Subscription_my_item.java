package com.sossolution.serviceonway.Activity;

public class Subscription_my_item {

    String year;
    String price;
    String text;

    public String getSubscription_id()
    {
        return subscription_id;
    }

    public void setSubscription_id(String subscription_id)
    {
        this.subscription_id = subscription_id;
    }

    String subscription_id;

    Subscription_my_item()
    {

    }

    public String getYear() {
        return year;
    }

    public String setYear(String year) {
        this.year = year;
        return year;
    }

    public String getPrice() {
        return price;
    }

    public String setPrice(String price) {
        this.price = price;
        return price;
    }

    public String getText() {
        return text;
    }

    public String setText(String text) {
        this.text = text;
        return text;
    }
}
