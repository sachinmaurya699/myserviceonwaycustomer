package com.sossolution.serviceonway.Class;

public class Shop_item {
   private String image;
   private   String name;
   private String include_item;
   private String price;
    private String Reating;

    public String getMarker_image() {
        return marker_image;
    }

    public void setMarker_image(String marker_image) {
        this.marker_image = marker_image;
    }

    private String marker_image;

    public String getLatitude()
    {
        return Latitude;
    }

    public void setLatitude(String latitude)
    {
        Latitude = latitude;
    }

    public String getLangitude() {
        return Langitude;
    }

    public void setLangitude(String langitude) {
        Langitude = langitude;
    }

    private String Latitude;
    private String Langitude;

    public String getReating()
    {
        return Reating;
    }

    public void setReating(String reating)
    {
        Reating = reating;
    }



    public String getProvideid() {
        return provideid;
    }

    public void setProvideid(String provideid) {
        this.provideid = provideid;
    }

    private String provideid;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    private String button;

    public String getDistance()
    {
        return distance;
    }

    public void setDistance(String distance)

    {
        this.distance = distance;
    }

    private String distance;

    public Shop_item()
    {

    }

    public String getImage() {
        return image;
    }

    public String setImage(String image) {
        this.image = image;
        return image;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public String getInclude_item() {
        return include_item;
    }

    public void setInclude_item(String include_item)
    {
        this.include_item = include_item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
