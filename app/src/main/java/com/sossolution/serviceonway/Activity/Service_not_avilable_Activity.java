package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sossolution.serviceonway.R;

public class Service_not_avilable_Activity extends AppCompatActivity {

    ImageView back;
    ImageView crose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_not_avilable_);
        back=findViewById(R.id.back);
        crose=findViewById(R.id.share_pic);
        crose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent= new Intent(Service_not_avilable_Activity.this,Maker_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               onBackPressed();
               finish();

            }
        });






    }


}