package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sossolution.serviceonway.Adapter.Condition_adapter;
import com.sossolution.serviceonway.Class.Subscription_adapter;
import com.sossolution.serviceonway.Class.Subscription_adapter_grid;
import com.sossolution.serviceonway.Class.Subscription_grid_item;
import com.sossolution.serviceonway.Class.Subscription_item;
import com.sossolution.serviceonway.R;
import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.DecompositionType.VERTICAL;

public class SubsCription_Activity extends AppCompatActivity
{
    ImageView back;
    TextView tex_tool;
    /*..........recyclerview.....*/
    RecyclerView recyclerView_grid,recyclerView_list,recyclerView_condition;
    List<String> list1;
    List<String>list_item;
    List<String>list_condition;
    LinearLayoutManager manager,manager_condition;
    GridLayoutManager manager_grid;
    Subscription_adapter adapter;
    Condition_adapter condition_adapter;
    Button button;

    String item[]={"GENERAL SERVICE","BATTERY JUMPSTART"};

   // int image[]={R.drawable.ic_card,R.drawable.car,R.drawable.ic_check1,R.drawable.ic_user};

   // String text[]={"Go Digital","Pick-Up & Drop","Our Promise","Expert Service"};

    Subscription_adapter_grid grid_adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subs_cription_);

        list_item= new ArrayList<>();

        recyclerView_grid=findViewById(R.id.grid_recyclerview);
        recyclerView_grid.setHasFixedSize(true);
        manager_grid= new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView_grid.setLayoutManager(manager_grid);

        List<Subscription_grid_item> list_item= new ArrayList<>();
        list_item.add(new Subscription_grid_item("Go Digital",R.drawable.ic_phone,"Convenient online\npayment option"));
        list_item.add(new Subscription_grid_item("Pick-up & Drop",R.drawable.ic_car,"Service from the \ncomfort to your\nhome/office"));
        list_item.add(new Subscription_grid_item("Our Promise",R.drawable.ic_baseline_check_circle_24,"100% satisfaction \nguaranteed"));
        list_item.add(new Subscription_grid_item("Expert Service",R.drawable.ic_baseline_user,"Skilled mechanics \nfor your every need"));

        grid_adapter= new Subscription_adapter_grid(this,list_item);
        recyclerView_grid.setAdapter(grid_adapter);
        button=findViewById(R.id.buy_your_service);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                /*Intent intent= new Intent(SubsCription_Activity.this,Subscription_history_Activity.class);
                startActivity(intent);*/

            }
        });




        recyclerView_list=findViewById(R.id.list_recyclerview);
        recyclerView_list.setHasFixedSize(true);
        manager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView_list.setLayoutManager(manager);


        Subscription_item s1= new Subscription_item();
        List<String> list1= new ArrayList<>();
        list1.add("GENERAL SERVICE");
        list1.add("Towing up to 40km ");
        list1.add("Fuel delivery up to 40km ");
        list1.add("Flat tyre up to 40km ");
        list1.add("Minor repair on the spot");
        list1.add("Emergency message");
        list1.add("Battery jump start up to 40 km");
        list1.add("24*7 Assistance");
        list1.add("Lost key or locked up to 40 km");



        adapter= new Subscription_adapter(this,list1);
        recyclerView_list.setAdapter(adapter);


        //add condition recyclerview
        recyclerView_condition=findViewById(R.id.recyclerview_condition);
        recyclerView_condition.setHasFixedSize(true);
        manager_condition= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView_condition.setLayoutManager(manager_condition);







        List<String> list_condition= new ArrayList<>();

        list_condition.add("No parts include");
        list_condition.add("Only locked window can be open");
        list_condition.add("No new Key change will be paid");

        condition_adapter= new Condition_adapter(this,list_condition);
        recyclerView_condition.setAdapter(condition_adapter);








        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
        tex_tool=findViewById(R.id.header);
        tex_tool.setText("SubsCription");
    }
}