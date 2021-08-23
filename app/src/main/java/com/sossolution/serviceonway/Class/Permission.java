package com.sossolution.serviceonway.Class;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permission {
    static Context context;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    public static boolean checkPermission(final Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {

            if(ContextCompat.checkSelfPermission(context,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(context, "permission grant", Toast.LENGTH_SHORT).show();
            }
            else
            {
                requeststoresepermission();
            }

        }
        return true;
    }

    private static void requeststoresepermission() {


        if(ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,Manifest.permission.READ_EXTERNAL_STORAGE))
        {


        }else
        {
            ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }





    }
}






