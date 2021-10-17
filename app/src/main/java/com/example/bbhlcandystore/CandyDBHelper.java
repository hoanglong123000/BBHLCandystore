package com.example.bbhlcandystore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CandyDBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "CandyData.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "CandyData";
    private static final String ID = "_id";
    private static final String NAME = "Nameofcandy";
    private static final String PLACE = "Madein";
    private static final String PRICE = "Price";
    private static final String ADDRESS = "AddressofFactory";
    private static final String DATE = "ProductDate";


    public CandyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createtableQuery = "CREATE TABLE CandyData " + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                                    + NAME + " TEXT, "
                                                                    + PLACE + " TEXT, "
                                                                    + ADDRESS + " TEXT, "
                                                                    + PRICE + " TEXT, "
                                                                    + DATE + " TEXT, "
                                                                    + "CandyImage blob);";
        db.execSQL(createtableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public void InsertData(String name, String place, String address, String price, String date, byte[] image)
    {
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nameofcandy", name);
        contentValues.put("madeinplace", place);
        contentValues.put("addressoffactory", address);
        contentValues.put("priceofcandy", price);
        contentValues.put("producedate", date);
        contentValues.put("RepresentedImage", image);
        long ins = mydb.insert("CandyData", null, contentValues);
        if(ins == -1)
        {
            Toast.makeText(context, "Lưu dữ liệu thành công!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Có lỗi xảy ra xin hãy nhập lại", Toast.LENGTH_SHORT).show();

        }
    }

    public String getNameofCandy(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from CandyData Where Nameofcandy = ?", new String[]{name});
        cursor.moveToFirst();
        return cursor.getString(1);
    }

    public Bitmap getImageofCandy(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from CandyData Where Nameofcandy = ?", new String[]{name});
        cursor.moveToFirst();
        byte[] bitmap = cursor.getBlob(6);
        Bitmap image = BitmapFactory.decodeByteArray(bitmap, 1, bitmap.length);
        return image;
    }
}
