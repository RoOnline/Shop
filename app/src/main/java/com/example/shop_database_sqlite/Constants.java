package com.example.shop_database_sqlite;

public class Constants {
    //Создаем переменные для базы данных
    public static final String DB_NAME = "PERSON_INFO";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "PERSON_INFO_TABLE";

    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_PHONE = "PHONE";



    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_NAME + " TEXT,"
            + C_PHONE + " TEXT"
            + ");";



}