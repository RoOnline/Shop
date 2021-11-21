package com.example.shop_database_sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//инициализируем базу данных и наслудуемся от класса SQLiteOpenHelper
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Создаем базу данных
        db.execSQL(Constants.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME);
        onCreate(db);
    }
    //метод для добавления данных в базу данных (получаем данные из AddActivity)
    public long insertInfo(String name, String phone){


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(Constants.C_NAME, name);
        values.put(Constants.C_PHONE, phone);


        long id = db.insert(Constants.TABLE_NAME, null, values);
        db.close();
        return id;
    }
    //метод для обновления информаций (получаем данные из EditActivity)
    public void updateInfo(String id, String name, String phone){


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(Constants.C_NAME, name);
        values.put(Constants.C_PHONE, phone);

        db.update(Constants.TABLE_NAME, values, Constants.C_ID + " = ?", new String[]{id});
        db.close();
    }
    //метод для удаления данныех
    public void deleteInfo(String id){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.C_ID + " = ?", new String[]{id});
        db.close();

    }

    public ArrayList<Model>  getAllData(){

        ArrayList<Model> arrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME;

        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToNext()){
            do {
                @SuppressLint("Range") Model model = new Model(
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_NAME)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_PHONE))

                );

                arrayList.add(model);
                //Сортируем в алфавитном порядке
                Collections.sort(arrayList, new Comparator<Model>() {
                    @Override
                    public int compare(Model o1, Model o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });

            } while (cursor.moveToNext());
        }

        db.close();
        return arrayList;

    }

}
