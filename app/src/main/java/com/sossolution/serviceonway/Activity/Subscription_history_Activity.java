package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sossolution.serviceonway.Adapter.Subscription_history_adapter;
import com.sossolution.serviceonway.Class.Subscription_history_item;
import com.sossolution.serviceonway.R;

import java.util.ArrayList;
import java.util.List;

public class Subscription_history_Activity extends AppCompatActivity
{

    RecyclerView recyclerView;
    List<Subscription_history_item>list_item;
    Subscription_history_adapter adapter;
    LinearLayoutManager manager;
    ImageView back;
    TextView header;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_history_);

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });
        header=findViewById(R.id.header);
        header.setText("Subscription History");

        recyclerView=findViewById(R.id.recyclerview_subscription_history);
        recyclerView.setHasFixedSize(true);
        manager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        list_item= new ArrayList<>();
        Subscription_history_item item= new Subscription_history_item();
        item.setId("1");
        item.setPrice("200");
        item.setPurches_date("2020-11-17 17:40:24");
        list_item.add(item);


        adapter= new Subscription_history_adapter(this,list_item);
        recyclerView.setAdapter(adapter);

    }
}