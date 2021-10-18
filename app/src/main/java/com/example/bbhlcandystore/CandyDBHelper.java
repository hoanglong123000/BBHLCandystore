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
                                                                    + DATE + " TEXT);";

        db.execSQL(createtableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public void InsertData(String name, String place, String address, String price, String date)
    {
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(PLACE, place);
        contentValues.put(ADDRESS, address);
        contentValues.put(PRICE, price);
        contentValues.put(DATE, date);

        long ins = mydb.insert("CandyData", null, contentValues);
        if(ins == -1)
        {
            Toast.makeText(context, "Có lỗi xảy ra xin hãy nhập lại", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(context, "Lưu dữ liệu thành công!", Toast.LENGTH_SHORT).show();

        }
    }

    Cursor readallData()
    {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void UpdateData(String row_id, String name, String place, String address, String price, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(PLACE, place);
        contentValues.put(ADDRESS, address);
        contentValues.put(PRICE, price);
        contentValues.put(DATE, date);

        long res = db.update(TABLE_NAME, contentValues, "_id=?", new String[]{row_id});
        if(res == -1)
        {
            Toast.makeText(context, "Có lỗi xảy ra xin hãy nhập lại", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(context, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();

        }
    }

    public void DeleteOneItem(String idcandy)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_NAME, "_id=?", new String[]{idcandy});
        if(res == -1)
        {
            Toast.makeText(context, "Có lỗi xảy ra xin hãy tắt máy và mở lại", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();

        }

    }

    public void DeleteAllItems()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
