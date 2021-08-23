package com.sossolution.serviceonway.Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DB_helper extends SQLiteOpenHelper
{
    private static final String DB_NAME="image.db";
    private static final int DB_Version=1;
    private static final String IMAGES_TABLE = "ImagesTable";
    public static final String IMAGE_ID = "id";
    public static final String IMAGE = "image";
    private SQLiteDatabase sqLiteDatabase;


    public DB_helper(@Nullable Context context)
    {
        super(context, DB_NAME,null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {


    }
    public void querydata(String sql)
    {
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        sqLiteDatabase.execSQL(sql);

    }
    public  void  insertdata(byte[] image)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String sql="INSERT INTO FOOD VALUES(NULL,?)";
        SQLiteStatement sqLiteStatement=sqLiteDatabase.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindBlob(1,image);

    }
    public Cursor getdata(String sql)
    {
        SQLiteDatabase sqLiteDatabase= getReadableDatabase();
        return sqLiteDatabase.rawQuery(sql,null);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        //db.execSQL("DROP TABLE IF EXISTS " + CREATE_IMAGES_TABLE);
        onCreate(sqLiteDatabase);
    }
}
