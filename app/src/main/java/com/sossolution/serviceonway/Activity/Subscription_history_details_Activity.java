package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sossolution.serviceonway.Adapter.Subscription_not_include_adapter;
import com.sossolution.serviceonway.Class.Subscription_include_item;
import com.sossolution.serviceonway.Class.Subscriptn_not_include;
import com.sossolution.serviceonway.R;

import java.util.ArrayList;
import java.util.List;

public class Subscription_history_details_Activity extends AppCompatActivity {

      ImageView back;
      TextView header;
      RecyclerView recyclerView_include,recyclerView_not_include;
      LinearLayoutManager  manager1,manager2;
      List<Subscription_include_item> list_include_item;
      Subscription_adapter adapter;
      Subscription_not_include_adapter adapter1;
      List<Subscriptn_not_include> list_not_include_item;
      ProgressBar dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_history_details_);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });
        header=findViewById(R.id.header);
        header.setText("Subscription Details");


        recyclerView_include=findViewById(R.id.recyclerview_include_service);
        recyclerView_include.setHasFixedSize(true);
        manager1= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView_include.setLayoutManager(manager1);

        list_include_item= new ArrayList<>();

        Subscription_include_item item= new Subscription_include_item();
        item.setText("Towing up to 40km");
        item.setPrice("500");
        list_include_item.add(item);

        adapter= new Subscription_adapter(this,list_include_item);
        recyclerView_include.setAdapter(adapter);



        recyclerView_not_include=findViewById(R.id.recyclerview_not_include_service);
        recyclerView_not_include.setHasFixedSize(true);
        manager2= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView_not_include.setLayoutManager(manager2);

        list_not_include_item= new ArrayList<>();

        Subscriptn_not_include include= new Subscriptn_not_include();
        include.setText("Towing up to 40km");
        list_not_include_item.add(include);

        adapter1= new Subscription_not_include_adapter(this,list_not_include_item);
        recyclerView_not_include.setAdapter(adapter1);


        }
}