package com.sossolution.serviceonway.Activity;

import androidx.annotation.NonNull;

import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.sossolution.serviceonway.Adapter.SecondAdapter;
import com.sossolution.serviceonway.Class.Storedata;
import com.sossolution.serviceonway.Fragment.History_Fragment;
import com.sossolution.serviceonway.Fragment.Home_Fragment;
import com.sossolution.serviceonway.Fragment.Profile_Fragment;
import com.sossolution.serviceonway.Fragment.Wallet_Fragment;
import com.sossolution.serviceonway.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.dkaratzas.android.inapp.update.Constants;
import eu.dkaratzas.android.inapp.update.InAppUpdateManager;
import eu.dkaratzas.android.inapp.update.InAppUpdateStatus;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity implements InAppUpdateManager.InAppUpdateHandler
{
    Home_Fragment fm1;
    Wallet_Fragment fm2;
    List<Storedata> datalisttwo;
    SecondAdapter towdapter;
    BottomNavigationView bottomNavigationView;
    ActionBar toolbar;
    FloatingActionButton fab;
    //new update
    AppUpdateManager appUpdateManager;
    int RequestUpdate = 1;
    String s1;
    String token;
    String   User_id;
    String Type;
    String token1;
    Dialog dialog;
    Button button_dialog1,button_dialog2;
     public static final  String TAG ="mytag";
     public static String PACKAGE_NAME;
     ConstraintLayout layout;
     Toolbar mToolbar;
     ImageView back;
     TextView header;
     String  ID;


   //firebse remote config..........
    FirebaseRemoteConfig firebaseRemoteConfig;
    TextView current_version;
    private int REQUEST_CODE=10;
    InAppUpdateManager manager;
    String tag;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    // RecyclerView recyclerView;
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        current_version=findViewById(R.id.tv);
        current_version.setVisibility(View.GONE);


        manager=InAppUpdateManager.Builder(this,101)
                .resumeUpdates(true)
                 .mode(Constants.UpdateMode.FLEXIBLE)
                 .snackBarAction("An update has just been downloaded")
                 .snackBarAction("RESTART")
                  .handler(this);

        manager.checkForAppUpdate();



        //email get mainactivity
        /*SharedPreferences sharedPreferences=getSharedPreferences("myid",MODE_PRIVATE);
        ID=sharedPreferences.getString("unicid","");*/
       // hitapiallcontent(ID);


        PACKAGE_NAME = getApplicationContext().getPackageName();
        Log.d("package_name",PACKAGE_NAME);

        ///internet connection check.............
        ConnectivityManager connectivityManager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=  connectivityManager.getActiveNetworkInfo();

       if(networkInfo== null || !networkInfo.isConnected() || !networkInfo.isAvailable())
       {

           layout=findViewById(R.id.layout);
           Snackbar snackbar= Snackbar.make(layout," please Check Internet Connection",Snackbar.LENGTH_INDEFINITE);
           snackbar.show();

       }
       else
       {

           layout=findViewById(R.id.layout);
           Snackbar snackbar= Snackbar.make(layout,"you are online",Snackbar.LENGTH_LONG);
           snackbar.dismiss();

       }


       //app update popup......................

        //new update
       // appUpdateManager = AppUpdateManagerFactory.create(MainActivity.this);



          /*.......................Notification generate..........*/

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task)
            {
                if(task.isSuccessful())
                {
                     token= task.getResult().getToken();
                    Log.d("value",token+"oncomplete");

                }
                else
                {
                    Toast.makeText(MainActivity.this, "faild", Toast.LENGTH_SHORT).show();

                    Log.d("exception",task.getException().toString());
                }

            }
        });


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("My_Notification", "My_Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        SharedPreferences sharedPreferences1 = getSharedPreferences("myid", MODE_PRIVATE);
        User_id = sharedPreferences1.getString("unicid", "");
        Log.d("user",User_id);
        Type="user";


         token1= FirebaseInstanceId.getInstance().getToken();
          Log.d("token",token1);

            if(token1!=null)
            {
                SaveFCMToken(User_id,Type,token1);
            }

        fm1 = new Home_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fm1).commit();
        bottomNavigationView = findViewById(R.id.b1);

        SharedPreferences preferences=getSharedPreferences("v1",MODE_PRIVATE);
         s1=preferences.getString("value","");

        if(s1.contains("1"))
        {
           fm2=new Wallet_Fragment();
           getSupportFragmentManager().beginTransaction().replace(R.id.frame,fm2).commit();
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                Fragment fm=null;

                switch (menuItem.getItemId())
                {
                    case R.id.home:
                        fm=new Home_Fragment();
                        break;
                    case R.id.wallet:
                        fm=new Wallet_Fragment();
                        break;
                    case R.id.profile:
                        fm= new Profile_Fragment();

                        break;
                   case R.id.history:
                        fm= new History_Fragment();
                    //Intent intent= new Intent(MainActivity.this,Vehicle_Activity.class);
                    //startActivity(intent);

                    // break;
                }
                //getSupportFragmentManager().beginTransaction().replace(R.id.frame,fm).addToBackStack(null).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,fm).commit();


                return true;
            }
        });

          }

    @Override
    public void onBackPressed() {


        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
        {
            getSupportFragmentManager().popBackStack();

        } else {
            finish();
        }
     /*   if(bottomNavigationView.getSelectedItemId ()== R.id.home)
        {
            //bottomNavigationView.setSelectedItemId(R.id.home);
            super.onBackPressed();
            finish();
        }
        else
        {
            bottomNavigationView.setSelectedItemId(R.id.home);
        }*/

    }

    private void hitapiallcontent(String id)
    {
        String url="https://serviceonway.com/GetUserProfileAndroidApi?id="+ID;
        Log.d("url_profile",url);

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST,
                url, null,new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                //progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject= response.getJSONObject(0);
                    String name=jsonObject.getString("name");
                    String contact=jsonObject.getString("contact");

                    SharedPreferences sharedPreferences=getSharedPreferences("username",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("name",name);
                    editor.putString("contact",contact);
                    editor.apply();

                    String email=jsonObject.getString("email");
                    Log.d("email_id",email);

                    SharedPreferences preferences= getSharedPreferences("Email",MODE_PRIVATE);
                    SharedPreferences.Editor editor2=preferences.edit();
                    editor2.putString("get_email",email);
                    editor2.apply();


                    //Toast.makeText(Profile_Activity.this,email1.toString()+"emialcomming", Toast.LENGTH_SHORT).show();

                    String address=jsonObject.getString("address");
                    SharedPreferences sharedPreferences3= getSharedPreferences("profile_Activity",MODE_PRIVATE);
                    SharedPreferences.Editor editor1=sharedPreferences3.edit();
                    editor1.putString("getaddress",address);
                    editor1.apply();
                    Log.d("address",address);

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

    private void SaveFCMToken(String User_id,String Type,String Token)
    {

        String url="https://serviceonway.com/SaveFCMTokenAndroidApi?id="+User_id+"&type="+Type+"&token="+Token;
        Log.d("urltoken",url);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
              Log.d("responce",response.toString());

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
                HashMap<String,String> hashMap=new HashMap<>();
                return hashMap;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        stringRequest.setShouldCache(false);
        Volley.newRequestQueue(MainActivity.this).add(stringRequest);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener listener= new BottomNavigationView.OnNavigationItemSelectedListener()
    {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fm=null;
                String tag="home";

                switch (menuItem.getItemId())
                {
                     case R.id.home:
                         tag="home";
                        fm=new Home_Fragment();
                        break;
                  /*  case R.id.wallet:
                        fm=new Second_Fragment();
                        break;*/
                    case R.id.profile:

                        fm= new Profile_Fragment();
                        break;
                   /* case R.id.location:
                        fm= new Fourth_Fragment();*/
                        //Intent intent= new Intent(MainActivity.this,Vehicle_Activity.class);
                        //startActivity(intent);

                       // break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,fm).commit();
                //add code
               /* FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                ft.add(R.id.frame, fm, tag);
                ft.addToBackStack(tag);
                ft.commit();*/


                return true;
            }
        };


    @Override
    public void onInAppUpdateError(int code, Throwable error)
    {


    }
    //add new method
   /* public BaseFragment getActiveFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            return null;
        }
        String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        return (BaseFragment) getSupportFragmentManager().findFragmentByTag(tag);
    }*/

    @Override
    public void onInAppUpdateStatus(InAppUpdateStatus status)
    {

        if(status.isDownloaded())
        {
            View view= getWindow().getDecorView().findViewById(android.R.id.content);
            Snackbar snackbar= Snackbar.make(view,"An update has just been downloaded ",Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    manager.completeUpdate();

                }
            });
            snackbar.show();
        }

    }
}
