package com.sossolution.serviceonway.Class;

public class Vehicle_listing {

    String price;
    String maker;
    String model;
    String image;
    String year;
    String km;
    String location;
    String id;
    String create_date;

    public String getCreate_date()
    {
        return create_date;
    }
    public void setCreate_date(String create_date)
    {
        this.create_date=create_date;
    }

    public String getContact()
    {
        return contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    String contact;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public  Vehicle_listing()
    {

    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModeel() {
        return model;
    }

    public void setModeel(String modeel) {
        this.model = modeel;
    }
}
