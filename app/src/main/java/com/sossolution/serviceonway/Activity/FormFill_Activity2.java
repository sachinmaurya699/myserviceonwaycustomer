package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sossolution.serviceonway.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormFill_Activity2 extends AppCompatActivity
{

    ImageView back;
    TextView header;
    Button button;
    String user_id_s;
    String my_contact_s;
    String user_name_s;
    TextView textView_name,textView_contact;
    EditText editText_message,editText_model;
    String Name_1;
    String name_value;
    TextView textView_vechicle,textView_maker,textView_model;
    String vech;
    String maker;
    String model;
    String Message;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desing_form);
         dialog= new ProgressDialog(this,R.style.DialogTheme);
        textView_vechicle=findViewById(R.id.vehicle_id);
        textView_maker=findViewById(R.id.maker_id);
        textView_model=findViewById(R.id.model_id);
        //vechile name.......get
        SharedPreferences  mPrefs = getSharedPreferences("vec", MODE_PRIVATE);
         vech=mPrefs.getString("car","");
        textView_vechicle.setText(vech);
        //Toast.makeText(this, vech+"vechicle", Toast.LENGTH_SHORT).show();
        Log.d("vech",vech);



        SharedPreferences mPrefs1 = getSharedPreferences("makeitem", MODE_PRIVATE);
         maker=mPrefs1.getString("bike","");
       // Toast.makeText(this, Maker+"vechicle", Toast.LENGTH_SHORT).show();
        Log.d("vech",maker);
        textView_maker.setText(maker);

        SharedPreferences mPrefs2 = getSharedPreferences("model1_item", MODE_PRIVATE);
        model =mPrefs2.getString("model_1","");
        Log.d("vech",model);
       // Toast.makeText(this, Model+"vechicle", Toast.LENGTH_SHORT).show();
        textView_model.setText(model);


        //get user id
        SharedPreferences preferences= getSharedPreferences("myid",MODE_PRIVATE);
        user_id_s=preferences.getString("unicid","");
        Log.d("value",user_id_s);

        //get user name
        hitapiallcontent(user_id_s);

        textView_name=findViewById(R.id.name_id);
        textView_contact=findViewById(R.id.contact_id);
        editText_message=findViewById(R.id.my_sms_id);
       // editText_model=findViewById(R.id.my_model);
        button=findViewById(R.id.my_submit_id);

        //get contact
        SharedPreferences sharedPreferences3 = getSharedPreferences("myvalue", MODE_PRIVATE);
        my_contact_s=sharedPreferences3.getString("id1","");
        Log.d("my_contact",my_contact_s);
        textView_contact.setText(my_contact_s);

        back=findViewById(R.id.back);
        header=findViewById(R.id.header);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.setMessage("Please wait");
                dialog.show();

                //store name.......................
                SharedPreferences preferences=getSharedPreferences("u_name",MODE_PRIVATE);
                name_value=preferences.getString("name_value","");

                String User_id=user_id_s;
               // String Mode=editText_model.getText().toString();
                 Message=editText_message.getText().toString();
                if(Message.isEmpty())
                {
                    editText_message.setError("Enter the message");
                }
                else
                {
                    mysubmitapi(User_id,name_value,my_contact_s,model,Message,vech,maker);

                }
            }
        });
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        header.setText("Free Servicee Form");
    }


    private void hitapiallcontent(String ID)
    {

        String url="https://serviceonway.com/GetUserProfileAndroidApi?id="+ID;
        Log.d("url_profile",url);


        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST,
                url, null,new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                try {
                    JSONObject jsonObject= response.getJSONObject(0);
                    user_name_s=jsonObject.getString("name");
                    SharedPreferences preferences=getSharedPreferences("u_name",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("name_value",user_name_s);
                    editor.apply();


                    textView_name.setText(user_name_s);
                  //  Toast.makeText(FormFill_Activity2.this,user_name_s+"value", Toast.LENGTH_SHORT).show();
                    Log.d("user_name",user_name_s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("error",error.toString());
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap= new HashMap<String, String>();
                return hashMap;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy (
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache (false);
        Volley.newRequestQueue(this).add (jsonArrayRequest);


    }

    private void mysubmitapi(String user_id,String name,String contact,String mode,String message,String vehicle,String maker)
    {
        String url="http://serviceonway.com/StoreOfferDetails?user_id="+user_id+"&name="+name+"&contact="+contact+"&model="+mode+"&message="+message+"&vehicle="+vehicle+"&maker="+maker;
        Log.d("url_value_submit",url);
        StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                dialog.dismiss();

              Log.d("responce",response);
                Toast.makeText(FormFill_Activity2.this, "submitted successfully", Toast.LENGTH_SHORT).show();
                editText_message.getText().clear();

                }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("error",error.toString());
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                HashMap<String,String> hashMap= new HashMap<String, String>();
                return hashMap;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setShouldCache (false);
        Volley.newRequestQueue(this).add (request);

    }

}