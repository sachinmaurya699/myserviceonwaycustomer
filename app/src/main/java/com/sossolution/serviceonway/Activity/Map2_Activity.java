package com.sossolution.serviceonway.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.widget.Toolbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sossolution.serviceonway.Class.Mapitem;
import com.sossolution.serviceonway.Class.NewShopAdapter;
import com.sossolution.serviceonway.Class.Shop_item;
import com.sossolution.serviceonway.Interface.Shotingdata;
import com.sossolution.serviceonway.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import static com.sossolution.serviceonway.R.layout.activity_maps;

public class Map2_Activity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleMap.OnCameraMoveStartedListener,GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraMoveCanceledListener,
        GoogleMap.OnCameraIdleListener,PopupMenu.OnMenuItemClickListener
{

    //All class Object
    String image5;
    String photo;
    String CHECK;
    String List;
    String Service_provider;
    double lt1;
    double lng1;
    LatLng latLng;
    Marker marker22;
    double lat1;
    LatLng latLng1;
    double lag1;
    List<Mapitem> al = null;
    Mapitem mapitem;
    Button button;
    Marker marker;
    String DataAll = "";
    String MyServiceList = "";
    String Value;
    String Servicecar;
    double p1, p2;
    String sp;
    ImageView imgPinUp;
    TextView textView;
    LatLng latLng123;
    MarkerOptions markerOptions;
    Location location;
    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;

    PlacesClient placesClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationManager locationManager;
    FusedLocationProviderClient mFusedLocationClient;
    AutocompleteSupportFragment supportFragment;
    List<Address> addresses = null;
    List<Address> addresses1 = null;
    List<Location> locationList;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private List<Address> list = null;
    String Type = "0";
    String image1;
    String sp1;
    String sp2;
    String s1;
    LatLng latLng2;
    //new recyclerview add

    RecyclerView recyclerView;
    List<Shop_item> shoplist;
    NewShopAdapter newShopAdapter;
    ProgressBar progressBar;
    String Index = "5";
    String shopname;
    String reating;
    String distance;
    String image;
    String Lat;
    String Service;
    String Vehicle;
    String ID, Lng;
    String id;
    SwipeRefreshLayout refreshLayout;
    String getImage;
    int referesh = 500;
    int referesh1 = 0;
    private static final int REQUEST_LOCATION = 1;
    Toolbar toolbar;
    String provider_id;
    Dialog dialog;
    ImageView markerimage,filter_image;
    Button Loadmore_button;
    Boolean scrolling = false;
    int current_item, scroll_out, tottle_item;
    LinearLayoutManager manager;
    LatLng l2;
    FloatingActionButton actionButton;
    final static int LOCATION_SETTINGS_REQUEST = 199;
    Shop_item shop_item;
   // NestedScrollView nestedScrollView;

    ProgressBar my_progress;
     //ProgressBar progressBar_item;
     int page=1;
     int privestotal;
     int count=0;

    //first method..........................
    //@SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
         setContentView(activity_maps);
         //user details fetch otp activity......
        SharedPreferences sharedPreferences=getSharedPreferences("myid",MODE_PRIVATE);
        ID=sharedPreferences.getString("unicid","");
         user_details(ID);
         actionButton=findViewById(R.id.floating_btn);
         progressBar = findViewById(R.id.progressitem);
         my_progress=findViewById(R.id.my_progress);
         my_progress.setVisibility(View.GONE);
         actionButton.setVisibility(View.GONE);
         markerimage = findViewById(R.id.location_get);
         filter_image=findViewById(R.id.imagebutton);
         //action button  recyclerview......
         actionButton.setOnClickListener(new View.OnClickListener()
         {
            @Override
            public void onClick(View view) {

                mapFrag.getView().setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                actionButton.setVisibility(View.GONE);

            }
        });

        filter_image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PopupMenu popup = new PopupMenu(Map2_Activity.this, view);
                popup.setOnMenuItemClickListener(Map2_Activity.this);
                popup.inflate(R.menu.shorting);
                popup.show();
            }
        });
        markerimage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (shoplist != null)
                {
                    shoplist.clear();
                }
                getLLocation();
            }
        });



        SharedPreferences mPrefs = getSharedPreferences("vec", MODE_PRIVATE);
        String s2 = mPrefs.getString("car", "");
        s1 = "service_provider_" + s2 + "_service";
        Vehicle = s1;

        /*...........................map......................*/

        SharedPreferences mPrefs2 = getSharedPreferences("map", MODE_PRIVATE);
        CHECK = mPrefs2.getString("mp", "");
        CHECK = CHECK.replaceAll(",$", "");
        List = CHECK;
        Service = CHECK;
        //Service=CHECK;
        Log.d("getservice", Service.toString());

        Service_provider = s1;

        hitapiIdprovide(List, Service_provider);
        recyclerView = findViewById(R.id.recyclerviewshoplist);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(Map2_Activity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);


        progressBar.setVisibility(View.VISIBLE);
        shoplist = new ArrayList<>();

        SharedPreferences mPrefs1 = getSharedPreferences("Em", MODE_PRIVATE);
        String s3 = mPrefs1.getString("Emer", "");


        //place show data..........
        String placesKey = "AIzaSyAhJW0BL0uuVzXfhkhiQb3ZXF8f4pQ0vYQ";
        locationManager = (LocationManager) Map2_Activity.this.getSystemService(LOCATION_SERVICE);
        if (!Places.isInitialized())
        {
            Places.initialize(this, placesKey);
        }
        placesClient = Places.createClient(this);
        supportFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        supportFragment.getView().setBackgroundColor(Color.WHITE);
        supportFragment.setTypeFilter(TypeFilter.ADDRESS);
        //String s1 = supportFragment.toString();
        //Toast.makeText(this, s1, Toast.LENGTH_SHORT).show();

        assert supportFragment != null;
        supportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.LAT_LNG, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.ADDRESS_COMPONENTS));
        supportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener()
        {
            @Override
            public void onPlaceSelected(@NonNull Place place)
            {

                LatLng latLng2 = place.getLatLng();
                double s1 = latLng2.latitude;
                double s2 = latLng2.longitude;


              //  Toast.makeText(Map2_Activity.this, "place select ", Toast.LENGTH_SHORT).show();

                Lat = String.valueOf(s1);
                Lng = String.valueOf(s2);

                //////////data set sharedprfrence..................
                SharedPreferences preferences= getSharedPreferences("getvalue",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("Lat",Lat);
                editor.putString("Lng",Lng);
                editor.apply();

                l2 = new LatLng(s1, s2);
                if (shoplist != null)
                {
                    shoplist.clear();
                }
               // data();
                     /*..................Place_vala.............*/

                /*......................first4.......................*/

                My_shop1(Lat,Lng);

             //  shopdetailsapi(DataAll, Type, Lat, Lng, Service, Vehicle, Index);




                //data();
                mGoogleMap.addMarker(new MarkerOptions()
                        .position(l2)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        .title("My Location"));
                //Toast.makeText(Map_Activity.this, latLng2.toString(), Toast.LENGTH_SHORT).show();
                // mGoogleMap.addMarker(new MarkerOptions().position(latLng2).title(place.getAddress()));
               // mGoogleMap.animateCamera(CameraUpdateFactory.zoomIn());
                mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15), null);
               // mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(l2, 15.0f));

                if (latLng2 == null)
                {
                    Toast.makeText(Map2_Activity.this, "no value", Toast.LENGTH_SHORT).show();
                } else
                    {
                    Geocoder gcd = new Geocoder(getBaseContext(),
                            Locale.getDefault());

                    try {
                        addresses = gcd.getFromLocation(latLng2.latitude,
                                latLng2.longitude, 1);
                        if (addresses.size() > 0)
                        {
                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            String locality = addresses.get(0).getLocality();
                            String subLocality = addresses.get(0).getSubLocality();
                            String state = addresses.get(0).getAdminArea();
                            String country = addresses.get(0).getCountryName();
                            String postalCode = addresses.get(0).getPostalCode();
                            String all = address + "" + locality + "" + subLocality + "" + state + "" + country + "" + postalCode;
                            supportFragment.setText(subLocality);
                            // Toast.makeText(Map_Activity.this, all.toString() + "alladdress", Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sharedPreferences.edit();
                            editor1.putString("place", all);
                            editor1.apply();

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("error",e.toString());

                    }
                 //   Toast.makeText(Map2_Activity.this, "yes value", Toast.LENGTH_SHORT).show();


                }


                //Toast.makeText(Map_Activity.this, place.getAddress() + toString() + "placename", Toast.LENGTH_SHORT).show();

                if (mGoogleMap != null)
                {
                    mGoogleMap.clear();
                }
                //Toast.makeText(Map_Activity.this, latLng2.toString(), Toast.LENGTH_SHORT).show();
                // mGoogleMap.addMarker(new MarkerOptions().position(latLng2).title(place.getAddress()));
                mGoogleMap.animateCamera(CameraUpdateFactory.zoomIn());
                mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15), null);
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng2, 15.0f));

                //geocoder1();
            }

            @Override
            public void onError(@NonNull Status status)
            {
                Log.d("error",status.toString());
               // Toast.makeText(Map2_Activity.this, status.toString() + "not see", Toast.LENGTH_LONG).show();

            }

        });


        /* ...................show place..................*/

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map1);
        mapFrag.getMapAsync(this);
        mapFrag.getView().setVisibility(View.INVISIBLE);


        //Toast.makeText(this,DataAll+"oncreate method", Toast.LENGTH_SHORT).show();




    }

    private void user_details(String id)
    {

        String url="https://serviceonway.com/GetUserProfileAndroidApi?id="+ID;
        Log.d("url_profile",url);

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST,
                url, null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject= response.getJSONObject(0);
                    String user_name=jsonObject.getString("name");
                    String user_contact=jsonObject.getString("contact");
                    String user_address=jsonObject.getString("address");

                    SharedPreferences preferences= getSharedPreferences("Map2_Activity",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("user_name",user_name);
                    editor.putString("user_contact",user_contact);
                    editor.putString("user_address",user_address);
                    editor.apply();



                    /*SharedPreferences sharedPreferences=getSharedPreferences("username",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("name",name);
                    editor.apply();*/

                   String email1=jsonObject.getString("email");
                    Log.d("email",email1);

                    /*SharedPreferences preferences= getSharedPreferences("Email",MODE_PRIVATE);
                    SharedPreferences.Editor editor2=preferences.edit();
                    editor2.putString("myemail",email1);
                    editor2.putString("contect",Num);
                    editor2.apply();*/
                    //Toast.makeText(Profile_Activity.this,email1.toString()+"emialcomming", Toast.LENGTH_SHORT).show();

                    String address=jsonObject.getString("address");
                    SharedPreferences sharedPreferences3= getSharedPreferences("profile_Activity",MODE_PRIVATE);
                    SharedPreferences.Editor editor1=sharedPreferences3.edit();
                    editor1.putString("getaddress",address);
                    editor1.apply();
                    Log.d("address",address);
                   /* editText_name.setText(name);
                    editText_email.setText(email1);
                    editText_address.setText(address);*/

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

    //oncreate finished

  /*  private void getNext()
    {

        Toast.makeText(this, "load data", Toast.LENGTH_SHORT).show();
        int Index = 8;
     //   String referesh5= String.valueOf(Index);
        referesh1 += Index;

       // shopdetailsapi(DataAll, Type, Lat, Lng, Service, Vehicle,String.valueOf(referesh1));
       // newShopAdapter.funupdateList((ArrayList<Shop_item>)shoplist);
    }*/

    private void My_shop1(String lat, String lng)
    {

      //  Toast.makeText(this, "Place vala..", Toast.LENGTH_SHORT).show();

        shopdetailsapi(DataAll, Type, lat, lng, Service, Vehicle, String.valueOf(referesh));



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //supportFragment.setText(place.getAddress());
        //Toast.makeText(MapsActivity.this,place.getAddress()+"getadd", Toast.LENGTH_SHORT).show();
    }

    //.........................oncreate fenished...............


    //onpause method....................

    @Override
    public void onPause()
    {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mFusedLocationClient != null)
        {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }


    }

    /* ...................onMap ready................*/


    @Override
    public void onMapReady(final GoogleMap googleMap)
    {

        mGoogleMap = googleMap;

        Service_provider = s1;

        //Marker pr click lisnear.............
        mGoogleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener()
        {
            @Override
            public void onCameraIdle() {
              //  Toast.makeText(Map2_Activity.this, "oncameraIdle", Toast.LENGTH_SHORT).show();

                LatLng latLng1;

                latLng1 = mGoogleMap.getProjection().getVisibleRegion().latLngBounds.getCenter();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng1);
                //geo coder use...............................

                Geocoder gc = new Geocoder(Map2_Activity.this, Locale.getDefault());
                try {

                    List<Address> addresses = gc.getFromLocation(lt1, lng1, 1);
                    StringBuilder sb = new StringBuilder();
                    if (addresses.size() > 0) {
                        Address address = addresses.get(0);
                        for (int i = 0; i < addresses.size(); i++)
                            sb.append(address.getAddressLine(0));
                        sb.append(address.getLocality());
                        sb.append(address.getSubLocality());
                        sb.append(address.getAdminArea());
                        sb.append(address.getPostalCode());
                        sb.append(address.getCountryName());
                        String s1 = address.getAddressLine(0);
                        SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("place", s1);
                        editor.apply();

                        //Toast.makeText(MapsActivity.this,s1.toString()+"pk", Toast.LENGTH_SHORT).show();

                        supportFragment.setText(s1);
                        // textView.setText(s1);


                        //Toast.makeText(MapsActivity.this,s1+"spp", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            private void hitvalue(String s1)
            {

            }
        });

        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mGoogleMap.moveCamera(CameraUpdateFactory.zoomIn());
        // mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f)
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(120000); // two minute interval
        mLocationRequest.setFastestInterval(120000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);



        /* //permission popup map loaction.......................*/

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)
            {
                //Location Permission already granted
               // Toast.makeText(this, "location", Toast.LENGTH_SHORT).show();
                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                mGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
            mGoogleMap.setMyLocationEnabled(true);
        }


    }

    //first api..........................
    private void hitapiIdprovide(String List, String Service_provider) {
        String url = "https://serviceonway.com/get_selected_service_location_json?list=" + List + "&vehicle=" + Service_provider;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(MapsActivity.this, "first api", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = response.getJSONObject("map");
                    //Toast.makeText(Map_Activity.this, "hitapiIdprovide", Toast.LENGTH_SHORT).show();
                    DataAll = jsonObject.getString("id");
                    //Toast.makeText(Map_Activity.this,DataAll.toString(), Toast.LENGTH_SHORT).show();
                    // shopdetailsapi(DataAll,Type,Lat,Lng, Service,Vehicle,Index);
                    //hitapimapshow(DataAll, Type, lt1, lng1);
                    getLocation();
                    //new_location();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("error",e.toString());
                    //Toast.makeText(Map2_Activity.this, e.toString() + "exception", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.toString());

               // Toast.makeText(Map2_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                return hashMap;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjectRequest.setShouldCache(false);
        Volley.newRequestQueue(Map2_Activity.this).add(jsonObjectRequest);

    }

    private void getLocation()
    {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        assert locationManager != null;
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            OnGPS();



        } else
            {
            getLLocation();

           }


    }

    private void getLLocation()
    {

        if (ActivityCompat.checkSelfPermission
                (
                Map2_Activity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                Map2_Activity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
           // Toast.makeText(this, "getlocation", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }

        else
            {

            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (locationGPS != null)
            {
                double lat = locationGPS.getLatitude();
                double lng = locationGPS.getLongitude();
                SharedPreferences preferences=getSharedPreferences("location",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("getlatitude",String.valueOf(lat));
                Log.d("lat",String.valueOf(lat));
                editor.putString("getlongitude",String.valueOf(lng));
                Log.d("lat",String.valueOf(lng));
                editor.apply();

                getaddress(lat, lng);
                Lat = String.valueOf(lat);
                Lng = String.valueOf(lng);

               SharedPreferences preferences1= getSharedPreferences("sencond",MODE_PRIVATE);
               SharedPreferences.Editor editor1=preferences1.edit();
               editor1.putString("second_Lat",Lat);
               editor1.putString("second_Lng",Lng);
               editor1.apply();





                // Toast.makeText(this,Lat.toString(), Toast.LENGTH_SHORT).show();
                // Toast.makeText(this,Lng.toString(), Toast.LENGTH_SHORT).show();

                /*.................first2.................................*/
                My_shop2(Lat,Lng);

                //shopdetailsapi(DataAll, Type, Lat, Lng, Service, Vehicle, Index);


                //   showLocation.setText("Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            } else
                {
                //Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void My_shop2(String lat, String lng)
    {


       // Toast.makeText(this, "Location vala", Toast.LENGTH_SHORT).show();
        shopdetailsapi(DataAll, Type, lat, lng, Service, Vehicle, String.valueOf(referesh));




    }

    private void getaddress(double lat, double lng)
    {

        // Toast.makeText(this, "getaddress", Toast.LENGTH_SHORT).show();

        Geocoder geocoder = new Geocoder(Map2_Activity.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getPostalCode();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();

            String l1=obj.getSubLocality();
            String l2=obj.getLocality();
            // =obj.
            supportFragment.setText(l1+","+l2);

            Log.v("IGA", "Address" + add);
            // Toast.makeText(this,obj.toString(), Toast.LENGTH_SHORT).show();


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            // Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }




    }

    private void OnGPS()
    {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS")
                .setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener()
               {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

               gpson();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
       /* final AlertDialog alertDialog = builder.create();
        alertDialog.show();*/


    }

    private void gpson()
    {

        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_MODE);

        if(!provider.contains("gps")){ //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }

    }

    //shop detais api hit
    private void shopdetailsapi(String ID, String Type, String Lat, String Lng, String Service, String Vechical, String Index)
    {
        String url = "https://serviceonway.com/FetchShopDetailsUsingGoogleDistanceApiDesktop?id=" + ID + "&type=" + Type + "&lat=" + Lat + "&lng=" + Lng + "&service=" + Service + "&vehicle=" + Vechical + "&indexL=" + Index;
        Log.d("shop_detailurl",url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,
                null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                progressBar.setVisibility(View.GONE);
                Log.d("get_shop_responce",response.toString());
                try {

                    JSONArray jsonArray = response.getJSONArray("data");
                    if (jsonArray.length() == 0)
                    {
                        // Setting Dialog Titdialog.setMessage(hop");
                        // Setting Icon to Dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(Map2_Activity.this);
                        String text = "NEAR YOU FILL THE FORM OUR EXECUTIVE WILL CONNECT YOU SOON click here";


                        // dialog.setText(spanable);
                        // dialog.setMovementMethod(LinkMovementMethod.getInstance());

                        builder.setMessage(text);
                        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Map2_Activity.this, ContectUs_Activity.class);
                                startActivity(intent);
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {

                                dialogInterface.dismiss();


                            }
                        });
                        builder.setIcon(R.drawable.ic_close_black_24dp);
                        builder.setTitle("NO SHOP AVAILABLE ");
                        builder.show();

                        // Toast.makeText(Map_Activity.this, "null shop", Toast.LENGTH_SHORT).show();
                    }

                    else {

                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            //Toast.makeText(Map_Activity.this,jsonArray.toString(), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(Map_Activity.this,"api hit ", Toast.LENGTH_SHORT).show();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Log.d("length",String.valueOf(jsonArray.length()));
                            Shop_item shop_item = new Shop_item();
                            id = jsonObject.getString("sid");
                            String provider_id = jsonObject.getString("provider_id");
                            shop_item.setName(jsonObject.getString("shop_name"));
                            String shopname = jsonObject.getString("shop_name");
                            shop_item.setReating(jsonObject.getString("rating"));
                            String reating = jsonObject.getString("rating");
                            // String provider=
                            shop_item.setProvideid(jsonObject.getString("provider_id"));
                            shop_item.setImage(jsonObject.getString("image"));
                            shop_item.setButton("booknow");
                            getImage = jsonObject.getString("image");
                            shop_item.setDistance(String.valueOf(jsonObject.getInt("distance")));
                            String distance = String.valueOf(jsonObject.getInt("distance"));
                            //Shop_item.setInclude_item(jsonObject.getString("service"));
                            shop_item.setInclude_item(jsonObject.getString("service"));
                            shop_item.setLatitude(jsonObject.getString("latitude"));
                            String get_latitude=jsonObject.getString("latitude");
                            String get_longitude=jsonObject.getString("longitude");
                            shop_item.setLangitude(jsonObject.getString("longitude"));

                            //new add map details
                            LatLng latLng2 = new LatLng(Double.parseDouble(get_latitude), Double.parseDouble(get_longitude));
                            Log.d("latlng", latLng2.toString());

                            if (i == 0) {
                                CameraPosition cameraPosition = new CameraPosition.Builder()
                                        .target(latLng2).zoom(50).build();

                                mGoogleMap.animateCamera(CameraUpdateFactory
                                        .newCameraPosition(cameraPosition));
                            }
                            int height = 100;
                            int width = 100;
                            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.maker3);
                            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                            //BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);

                            mGoogleMap.addMarker(new MarkerOptions()
                                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                                    .position(latLng2));
                            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f), 3000, null);
                            shoplist.add(shop_item);
                            Log.d("shopitem",shopname);

                        }
                        newShopAdapter = new NewShopAdapter(getApplicationContext(), (ArrayList<Shop_item>) shoplist, new Shotingdata()
                        {
                            @Override
                            public void item(Shop_item shop_item1)
                            {
                                Intent intent = new Intent(Map2_Activity.this, ShopDetails_Activity.class);
                                startActivity(intent);
                                SharedPreferences sharedPreferences = getSharedPreferences("MyAllgetdetails1", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("getshopname", shop_item1.getName());
                                editor.putString("getreating", shop_item1.getReating());
                                editor.putString("getdestence", shop_item1.getDistance());
                                editor.putString("getproviderid", shop_item1.getProvideid());
                                editor.putString("getimage", shop_item1.getImage());
                                editor.putString("getservice", shop_item1.getInclude_item());
                                editor.putString("getbutton", shop_item1.getButton());
                                editor.putString("getlat", shop_item1.getLatitude());
                                editor.putString("getlang", shop_item1.getLangitude());
                                editor.apply();
                                editor.commit();

                            }
                        });
                    }
                } catch (JSONException e)
                {
                    e.printStackTrace();
                    Log.e("get_error",e.toString());
                }

                recyclerView.setAdapter(newShopAdapter);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.e("error", error.toString());

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                HashMap<String, String> hashMap = new HashMap<>();
                return hashMap;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjectRequest.setShouldCache(false);
        Volley.newRequestQueue(Map2_Activity.this).add(jsonObjectRequest);

    }

    //second api......................................
    //new Lat lang change
    private void geocoder()
    {

        Geocoder gcd = new Geocoder(getBaseContext(),
                Locale.getDefault());

        try {
            addresses = gcd.getFromLocation(mLastLocation.getLatitude(),
                    mLastLocation.getLongitude(), 1);
            if (addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String locality = addresses.get(0).getLocality();
                String subLocality = addresses.get(0).getSubLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String all = address + "" + locality + "" + subLocality + "" + state + "" + country + "" + postalCode;
                textView.setText(all);

            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
           // Toast.makeText(Map2_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }

    LocationCallback mLocationCallback = new LocationCallback()
    {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            locationList = locationResult.getLocations();
            if (locationList.size() > 0) {
                //The last location in the list is the newest
                final Location location = locationList.get(locationList.size() - 1);
               // Toast.makeText(Map2_Activity.this, "" + location.getLatitude(), Toast.LENGTH_SHORT).show();

              //  Toast.makeText(Map2_Activity.this, "" + location.getLongitude(), Toast.LENGTH_SHORT).show();

                Log.i("MapsActivity", "Location: " + location.getLatitude() + " "
                        + location.getLongitude());
                mLastLocation = location;
                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }
                //address convert in lat lang
                Geocoder gcd = new Geocoder(getBaseContext(),
                        Locale.getDefault());

                try {
                    addresses = gcd.getFromLocation(mLastLocation.getLatitude(),
                            mLastLocation.getLongitude(), 1);
                    if (addresses.size() > 0) {
                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        String locality = addresses.get(0).getLocality();
                        String subLocality = addresses.get(0).getSubLocality();
                        String state = addresses.get(0).getAdminArea();
                        String country = addresses.get(0).getCountryName();
                        String postalCode = addresses.get(0).getPostalCode();
                        String all = address + "" + locality + "" + subLocality + "" + state + "" + country + "" + postalCode;
                        supportFragment.setText(all);
                    }

                } catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("error",e.toString());
                   // Toast.makeText(Map2_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                //Place current location marker
                double lat = mLastLocation.getLatitude();
                double longi = mLastLocation.getLongitude();
                Lat = String.valueOf(lat);
                Lng = String.valueOf(longi);

                SharedPreferences preferences=getSharedPreferences("location",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("getlatitude",String.valueOf(lat));
                Log.d("lat",String.valueOf(lat));
                editor.putString("getlongitude",Lat);
                Log.d("lat",Lng);
                editor.apply();


                SharedPreferences preferences1= getSharedPreferences("Third",MODE_PRIVATE);
                SharedPreferences.Editor editor1=preferences1.edit();
                editor1.putString("third_lat",Lat);
                editor1.putString("third_lng",Lng);
                editor1.apply();




                if(shoplist!=null)
                {
                    shoplist.clear();
                }

              /*  ........first3....................*/

              //  My_shop(Lat,Lng);
               // shopdetailsapi(DataAll, Type, Lat, Lng, Service, Vehicle, Index);

                LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                final MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                //markerOptions.anchor(0.5f, 0.5f);
                //markerOptions.title(addresses.get(0).getAddressLine(0));
                CameraPosition currentPlace = new CameraPosition.Builder()
                        .target(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()))
                        .tilt(70.0f).zoom(10.0f).build();
                mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(currentPlace));

            }
        }

    };

    //checkself permission.................
    private boolean checkLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(Map2_Activity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                   // Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }

            }

        }
    }

    @Override
    public void onCameraIdle() {
        Log.v("Onmove Idle", "Onmove ");
        // MarkerOptions markerOptions = new MarkerOptions();
        //Toast.makeText(this, "mauryaji"+"oncameraidle", Toast.LENGTH_SHORT).show();

        // mGoogleMap.addMarker(markerOptions);

    }

    @Override
    public void onCameraMoveCanceled() {
        // Toast.makeText(this, "oncameraMoveCanceled"+"mauryaji", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraMove() {
        Log.v("Onmove cancel", "Onmove ");
        //Toast.makeText(this, "onCameraMove"+"mauryaji", Toast.LENGTH_SHORT).show();
        mCurrLocationMarker.remove();
        LatLng midLatLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        mCurrLocationMarker = mGoogleMap.addMarker(new MarkerOptions().title("I am here ").
                position(midLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.back_arrow)));

    }

    @Override
    public void onCameraMoveStarted(int i) {
        Log.v("Onmove start", "Onmove ");
        // mGoogleMap.clear();
        // Toast.makeText(this, "oncameramovestart"+"mauryaji", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //  Toast.makeText(MapsActivity.this, "ya", Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shorting, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.distence:


                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                    distanceshort();
                    newShopAdapter.notifyDataSetChanged();
                }

                return true;

            case R.id.reating:
                // write your code here
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                    reatingshort();
                    newShopAdapter.notifyDataSetChanged();
                }
                return true;

            case R.id.price:
                // write your code here
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                    priceshort();
                    newShopAdapter.notifyDataSetChanged();
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void priceshort() {

        Collections.sort(shoplist, new Comparator<Shop_item>() {
            @Override
            public int compare(Shop_item a, Shop_item b) {
                return b.getDistance().compareTo(a.getDistance());
            }
        });
    }

    private void reatingshort() {

        Collections.sort(shoplist, new Comparator<Shop_item>() {
            @Override
            public int compare(Shop_item a, Shop_item b) {
                return a.getReating().compareTo(b.getReating());
            }
        });
    }


    private void distanceshort()
    {

        Collections.sort(shoplist, new Comparator<Shop_item>()
        {
            @Override
            public int compare(Shop_item a, Shop_item b)
            {
                return a.getDistance().compareTo(b.getDistance());
            }
        });


    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onMenuItemClick(MenuItem menuItem)
    {

        switch (menuItem.getItemId()) {
            case R.id.distence:
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                    distanceshort();
                    newShopAdapter.notifyDataSetChanged();
                }

                break;
            case R.id.reating:
               // Toast.makeText(this, "reating", Toast.LENGTH_SHORT).show();
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                    reatingshort();
                    newShopAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.price:
               // Toast.makeText(this, "price", Toast.LENGTH_SHORT).show();
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                    priceshort();
                    newShopAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.map1:
               // Toast.makeText(this, "onMenuItemClick", Toast.LENGTH_SHORT).show();
                mapFrag.getView().setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                actionButton.setVisibility(View.VISIBLE);

            default:
        }

        return false;

    }

}

