package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sossolution.serviceonway.Class.NewShopAdapter;
import com.sossolution.serviceonway.Class.Shop_item;
import com.sossolution.serviceonway.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop_Activity extends AppCompatActivity
{
    RecyclerView recyclerView;
    List<Shop_item> shoplist;
    NewShopAdapter newShopAdapter;
    String ID,Type,Let,Lng,Service,Vehicle,Index;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_);

        //recyclerview show
        recyclerView=findViewById(R.id.recyclerviewshoplist);
        recyclerView.setHasFixedSize(true);
        progressBar=findViewById(R.id.progressitem);
        progressBar.setVisibility(View.VISIBLE);
        
       // LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true);
       // shoplist= new ArrayList<>();
      //  recyclerView.setLayoutManager(layoutManager);
        //NewShopAdapter newShopAdapter= new NewShopAdapter(Shop_Activity.this)

       /* SharedPreferences sharedPreferences=getSharedPreferences("map",MODE_PRIVATE);
        String sp=sharedPreferences.getString("lat1","");
        Log.d("null",sp.toString());
        //Toast.makeText(this,s1.toString(), Toast.LENGTH_SHORT).show();
        //Log.d("s11",s1.toString());
        String s2=sharedPreferences.getString("lag1","");
        Log.d("s22",s2.toString());*/



        ID="0,69,71,74,79,80,81,83,84,85,87,91,92,93,96,98,99,102,105,106,123,129,132,134,135,136,137,138,140,141,143,144,145,146,148,149,243,150,151,153,154,155,156,162,163,164,166,169,66,245,170,171,172,174,176,177,182,184,186,187,188,195,196,199,201,203,205,208,211,218,219,220,221,222,223,224,225,229,231,238,240,244,257,259,261,266,269,272,273,276,278,279,280,290,291,293,298,299,306,307,315,316,324,327,329,333,338,341,342,343,346,358,360,361,369,371,372,374,375,380,381,383,398,400,405,411,413,419,422,423,427,428,431,439,447,452,232,455,457,464,465,467,468,472,475,165,192,209,300,304,317,309,68,331,349,352,382,442,445,446,94";
        Type="0";
        Let="30.2777596";
        Lng="77.9927285";
        Service="null,74983,75316";
        Vehicle="service_provider_car_service";
        Index="5";


        showAllshop(ID,Type,Let,Lng,Service,Vehicle,Index);
    }

    private void showAllshop(String id,String type,String lat,String lng,String service,String vehicle,String index )
    {
        String url="https://serviceonway.com/FetchShopDetailsUsingGoogleDistanceApiDesktop?id="+id+"&type="+type+"&lat="+lat+"&lng="+lng+"&service="+service+"&vehicle="+vehicle+"&indexL="+index;

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error3",error.toString());
                Toast.makeText(Shop_Activity.this,error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put ("Content-Type", "application/json; charset=utf-8");
                return hashMap;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjectRequest.setShouldCache(false);
        Volley.newRequestQueue(Shop_Activity.this).add(jsonObjectRequest);

    }
}
