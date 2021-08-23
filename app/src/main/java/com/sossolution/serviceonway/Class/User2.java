package com.sossolution.serviceonway.Class;

import android.content.Context;
import android.content.SharedPreferences;

public  class User2 {

    String name;
    String email;
    String phone;
    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    public User2(Context context)
    {
        this.context=context;
        preferences=context.getSharedPreferences("store value",context.MODE_PRIVATE);
    }
    public String getName()
    {
        name=preferences.getString("userdata1","");
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
        preferences.edit().putString("userdata1",name).apply();
    }
    public String getEmail()
    {
        email=preferences.getString("userdata1","");
        return email;
    }
    public void setEmail(String email)
    {
        this.email=email;
        preferences.edit().putString("userdata1",email).apply();
    }
    public String getPhone()
    {
        phone=preferences.getString("userdata1","");
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone=phone;
        preferences.edit().putString("userdata1",phone).apply();
    }
    public void removeuser()
    {
        preferences.edit().remove("userdata1").apply();
    }

}
