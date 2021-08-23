package com.sossolution.serviceonway.Activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.sossolution.serviceonway.Class.Get_real_path;
import com.sossolution.serviceonway.Class.My_Retrofit;
import com.sossolution.serviceonway.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class Upload_Activity extends AppCompatActivity
{
    private TextView show_vechicle, show_maker, show_model;
    private Spinner spinner_fuel, spinner_transmission, spinner_No_owners;
    private ArrayAdapter<String> adapter;
    private ImageView back;
    private TextView header;
    private int YEAR, MONTH, DAY;
    private DatePicker picker;
    private DatePickerDialog dialog;
    private Calendar myCalendar;
    private DatePickerDialog dpd;
    private int mYear, mMonth, mDay;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private TabItem tab1;
    private TabLayout tabLayout;
    private Button upload_image;
    private int RESULT_LOAD_IMAGE = 1;
    private Bitmap bitmap;
    private String vechicle;
    private String maker;
    private String model;
    private Button btn_conform;
    private TextView textView_des;
    private TextView textView_km;
    private TextView year1;
    private TextView textView_price;
    private TextView textView_title;
    String MY_KM, MY_DES, MY_YEAR, MY_TITLE, MY_PRICE;
    //add image
    Bitmap bitmap1;
    View view;
    ByteArrayOutputStream bytearrayoutputstream;
    File file;
    FileOutputStream fileoutputstream;
    int RESULT_LOAD_IMAGE1 = 1;
    int REQUEST_CODE_BROWSE_PICTURE;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private String imageEncoded;
    private List<String> imagesEncodedList;
    private String message1;
    private Spinner spinner_first, spinner_second, spinner_thired;
    private String encoded;
    private String encoded1;
    private Bitmap decodedImage;
    private String my_new_url;
    private int pick_image=2;
    private String my_image_url;
    private String image_item;
    private String VEHICLE, MAKER, MODEL, FUEL, TRANSMISSION, NUMBER_OF_OWNERS, KM, My_YEAR, TITLE, DES, PRICE;
    private Get_real_path get_real_path;
    private MultipartBody.Part body1;
    private ProgressDialog dialog1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_vehicle_);
        checkPermission1();
        get_real_path= new Get_real_path();
        dialog1= new ProgressDialog(Upload_Activity.this);
        upload_image = findViewById(R.id.upload_image);

        /*..............spinner cast...............*/
        spinner_fuel = findViewById(R.id.spinner_1);
        spinner_transmission = findViewById(R.id.spinner_2);
        spinner_No_owners = findViewById(R.id.spinner_3);
        textView_km = findViewById(R.id.km);
        textView_price = findViewById(R.id.new_price);
        year1 = findViewById(R.id.text_year);
        textView_title = findViewById(R.id.title);
        textView_des = findViewById(R.id.des);

        permission();

        upload_image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

              Intent intent= new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
              intent.setType("image/*");
              startActivityForResult(Intent.createChooser(intent,"Select Picture"), pick_image);


            }
        });
        //tab1=findViewById(R.id.tab_1);

        preferences = getSharedPreferences("vec", MODE_PRIVATE);
        vechicle = preferences.getString("car", "");
        Log.d("vehicle_value", vechicle);

        tabLayout = findViewById(R.id.tabtwo);
        tabLayout.getTabAt(0).setText(vechicle);


        SharedPreferences preferences1 = getSharedPreferences("makeitem", MODE_PRIVATE);
        maker = preferences1.getString("bike", "");
        Log.d("vehicle_value", maker);
        tabLayout.getTabAt(1).setText(maker);


        SharedPreferences mPrefs = getSharedPreferences("model1_item", MODE_PRIVATE);
        model = mPrefs.getString("model_1", "");
        tabLayout.getTabAt(2).setText(model);
        Log.d("vehicle_value", model);

        btn_conform = findViewById(R.id.conform_form);
        btn_conform.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                dialog1.setMessage("please wait.....");
                dialog1.show();
                MY_KM = textView_km.getText().toString();
                MY_YEAR = year1.getText().toString();
                MY_TITLE = textView_title.getText().toString();
                MY_DES = textView_des.getText().toString();
                MY_PRICE = textView_price.getText().toString();

                if (MY_KM.isEmpty()) {
                    textView_km.setError("Enter the kilometre");
                    textView_km.setFocusable(true);
                } else if (MY_TITLE.isEmpty()) {
                    textView_title.setError("Enter the  title");
                    textView_title.setFocusable(true);
                } else if (MY_DES.isEmpty()) {
                    textView_des.setError("Enter the des");
                    textView_des.setFocusable(true);

                } else if (MY_PRICE.isEmpty()) {
                    textView_price.setError("Enter the price");
                    textView_price.setFocusable(true);
                } else
                    {
                    Toast.makeText(Upload_Activity.this, "button click", Toast.LENGTH_SHORT).show();

                    VEHICLE = vechicle;
                    MAKER = maker;
                    MODEL = model;
                    FUEL = spinner_fuel.getSelectedItem().toString();
                    TRANSMISSION = spinner_transmission.getSelectedItem().toString();
                    NUMBER_OF_OWNERS = spinner_No_owners.getSelectedItem().toString();
                    KM = textView_km.getText().toString();
                    My_YEAR = year1.getText().toString();
                    TITLE = textView_title.getText().toString();
                    DES = textView_des.getText().toString();
                    PRICE = textView_price.getText().toString();
/*
                    String IMAGE="data:image/png;base64,"+image_item;
*/


                  //  My_listing_details(VEHICLE, MAKER, MODEL, FUEL, TRANSMISSION, NUMBER_OF_OWNERS, KM, YEAR, TITLE, DES, PRICE);

                    //  My_listing_details(IMAGE);

                    get_update_form1();


                }


            }
        });


        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        year1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                Toast.makeText(Upload_Activity.this, "hello", Toast.LENGTH_SHORT).show();

                DatePickerDialog dialog = new DatePickerDialog(Upload_Activity.this, android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        //  myCalendar.set(Calendar.YEAR,monthOfYear);


                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, year);
                        //myCalendar.set(Calendar.MONTH, selectedmonth);
                        //  myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "yyyy"; //Change as you need
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
                        year1.setText(sdf.format(myCalendar.getTime()));


                    }
                }, mYear, mMonth, mDay);

                dialog.getDatePicker().findViewById(getResources().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
                dialog.getDatePicker().findViewById(Resources.getSystem().getIdentifier("month", "id", "android")).setVisibility(View.GONE);
                dialog.show();


            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        header = findViewById(R.id.header);
        header.setText("UPLOAD VEHICLE FORM ");


        String fuel[] = {"SELECT FUEL", "CNG&HYBRID", "DIESEL", "PETROL", "ELECTRIC", "LPG"};

        String transmission[] = {"SELECT TRANSMISSION", "AUTOMATIC", "MANUAL"};

        String No_of_owners[] = {"SELECT NO_OF OWNERS", "1", "2", "3", "4", "MORE THEN FOUR"};

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fuel);
        spinner_fuel.setAdapter(adapter);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, transmission);
        spinner_transmission.setAdapter(adapter);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, No_of_owners);
        spinner_No_owners.setAdapter(adapter);

    }
    private void permission()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if(this.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)
            {
                  Log.d("permission granted","Permmission is granted");
            }else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},201);
            }
        }else
        {
            Log.d("permission granted","granted");
        }


    }

    private void get_update_form1()
    {
        RequestBody vehicle = RequestBody.create(MediaType.parse("text/plain"), VEHICLE);
        RequestBody maker = RequestBody.create(MediaType.parse("text/plain"), MAKER);
        RequestBody model = RequestBody.create(MediaType.parse("text/plain"), MODEL);
        RequestBody fuel = RequestBody.create(MediaType.parse("text/plain"), FUEL);
        RequestBody transmission = RequestBody.create(MediaType.parse("text/plain"), TRANSMISSION);
        RequestBody number_of_owners = RequestBody.create(MediaType.parse("text/plain"), NUMBER_OF_OWNERS);
        RequestBody km = RequestBody.create(MediaType.parse("text/plain"), KM);
        RequestBody my_year = RequestBody.create(MediaType.parse("text/plain"), My_YEAR);
        RequestBody title = RequestBody.create(MediaType.parse("text/plain"), TITLE);
        RequestBody des = RequestBody.create(MediaType.parse("text/plain"), DES);
        RequestBody price = RequestBody.create(MediaType.parse("text/plain"), PRICE);

        Call<String> listcall = My_Retrofit.getInstance().getMyApi().get_update_form(vehicle, maker, model, fuel, transmission, number_of_owners, km, my_year, title, des, price, body1);

        listcall.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response)
            {
                dialog1.dismiss();
                Log.d("my_result", String.valueOf(response.body()));
                Log.d("call_data", listcall.request().toString());
                Log.e("message", " : " + response.message());
                Log.e("body", " : " + response.body());
                Toast.makeText(Upload_Activity.this, "Response " + response.raw().message(), Toast.LENGTH_LONG).show();
                Toast.makeText(Upload_Activity.this, "Kyc_update", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t)
            {
                Log.d("_error", t.toString());


            }
        });
    }

    private void My_listing_details(String vehicle,String maker,String model,String fuel,String transmission,String number_of_owners,String km,String year, String title,String des,String price)
    {
        String url3="https://serviceonway.com/insert_vehicle_listing_api?vehicle="+vehicle+"&maker="+maker+"&model="+model+"&fuel="+fuel+"&transmission="+transmission+"&number_of_owners="+number_of_owners+"&km="+km+"&year="+year+"&title="+title+"&des="+des+"&price="+price+"&file="+file;

        StringRequest request= new StringRequest(Request.Method.POST,url3, new Response.Listener<String>()
        {

            @Override
            public void onResponse(String response)
            {

                Toast.makeText(Upload_Activity.this,response+"submit", Toast.LENGTH_SHORT).show();
                 message1 =response;

                Intent intent= new Intent(Upload_Activity.this,MainActivity.class);
                startActivity(intent);


            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                 Log.d("error",error.toString());
                int  statusCode = error.networkResponse.statusCode;
                NetworkResponse response = error.networkResponse;

                Log.d("testerror",""+statusCode+" "+response.data);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                HashMap<String,String>  hashMap = new HashMap<String, String>();
                hashMap.put ("Content-Type","application/json; charset=utf-8");
              //  hashMap.put("Authorization", "Bearer " + Utils.readSharedSetting(context, "access_token", ""));
                return hashMap;

            }

        };
        //first timeout
        request.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setShouldCache(false);
        Volley.newRequestQueue(Upload_Activity.this).add(request);



     /*   request.setRetryPolicy(new DefaultRetryPolicy(8000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adding request to request queue
        request.setShouldCache(false);
        Volley.newRequestQueue(Upload_Activity.this).add(request);*/






        //second time out
     /*   request.setRetryPolicy(new DefaultRetryPolicy(
                1000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setShouldCache(false);
        Volley.newRequestQueue(Upload_Activity.this).add(request);
*/


    }

    public void selectImage()
    {

        galleryintent();

    }

    private boolean checkPermission1()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {

            if(ContextCompat.checkSelfPermission(Upload_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(Upload_Activity.this, "permission grant", Toast.LENGTH_SHORT).show();
            }
            else
            {
                requeststoresepermission();
            }

        }
        return true;


    }

    private void requeststoresepermission()
    {


        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE))
        {

        }else
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }

    }

    private void cameraIntent()
    {

        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,1);

    }

    public void galleryintent()
    {

      /*  Intent pickIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(pickIntent, "Select Picture"), PICK_IMAGE);*/



     /*   Intent intent= new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);*/


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == pick_image && resultCode == RESULT_OK && null != data)
        {
          Uri selectedImage=data.getData();

            String     realPath = get_real_path.getUriRealPath(Upload_Activity.this,selectedImage);
            //Log.i(TAG, "onActivityResult: file path : " + realPath);
            Log.d("onActivityResult",realPath);

            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream(Upload_Activity.this.getContentResolver().openInputStream(selectedImage));
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
           // imageView_upload.setImageBitmap(bitmap);
            String path = null;
            try {
                path = savebitmap1(bitmap,"2345.jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
            File f2=new File(path);
            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), f2);
            body1 = MultipartBody.Part.createFormData("file", f2.getName(), reqFile);
            Log.d("body", String.valueOf(body1));


        }

    }

    private String savebitmap1(Bitmap bitmap, String filename2) throws IOException
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        File f1 = new File(
                (Upload_Activity.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                        .toString() + File.separator + filename2)
        );
        try {
            f1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream(f1);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        try {
            fo.write(bytes.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fo.close();
        return f1.getAbsolutePath();
    }

    private String saveImage(Bitmap bitmap)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        byte[] byteArray = bytes.toByteArray();
        encoded = Base64.encodeToString(byteArray, Base64.NO_WRAP);

         my_image_url=encoded.replace("+","%2B");

         image_item=my_image_url.replaceAll("/","");
         Log.d("get_image_url",my_image_url);

       /*  my_new_url=my_image_url;
         Log.d("my_url",my_new_url);*/


       /* byte[] dataBytes = Base64.getEncoder().encode(data.getBytes());
        dataBytes = Base64.getDecoder().decode(dataBytes);*/


        //decode base64 string to image
       /*  byteArray = Base64.decode(encoded, Base64.DEFAULT);
         decodedImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
         Log.d("decode", String.valueOf(decodedImage));*/

        return encoded;
    }

}