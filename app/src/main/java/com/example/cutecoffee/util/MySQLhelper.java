package com.example.cutecoffee.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * 创建SQLite数据库的工具类
 */
public class MySQLhelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "db";
    public static final int VERSION = 1;

    public MySQLhelper(@Nullable Context context, @Nullable String DB_NAME, @Nullable SQLiteDatabase.CursorFactory factory, int VERSION) {
        super(context, DB_NAME, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE userInfo(_id INTEGER PRIMARY KEY AUTOINCREMENT ,userName VERVHAR(20),password VERVHAR(20),phoneNumb VERVHAR(20),money REAL)");
        db.execSQL("CREATE TABLE storeInfo(_id INTEGER PRIMARY KEY autoincrement ,ID VERVHAR(20),picNumb VERVHAR(20),storeName VERVHAR(20),storeScore VERVHAR(20),storeSell VERVHAR(20) ,storeSign VERVHAR(20) ,storeIntro text )");
        db.execSQL("CREATE TABLE goodsInfo(_id INTEGER PRIMARY KEY autoincrement ,ID VERVHAR(20),goodsJson text)");
        db.execSQL("CREATE TABLE messagesInfo(_id INTEGER PRIMARY KEY autoincrement ,ID INTEGER,img_id VERVHAR(20),message text,userName VERVHAR(20),time VERVHAR(20))");
        db.execSQL("CREATE TABLE orderInfo(_id INTEGER PRIMARY KEY autoincrement ,userName VERVHAR(20),time VERVHAR(20),goodsJson text)");


        db.execSQL("INSERT INTO userInfo(userName,password,phoneNumb,money) VALUES ('aaa','111','126702095',0)");
        db.execSQL("INSERT INTO messagesInfo(ID,img_id,message,userName,time) VALUES (1,'1','I like the coffee here~~','aaa','2021.12.15 12:08')");


        db.execSQL("INSERT INTO storeInfo (ID,picNumb,storeName,storeScore,storeSell)values ('0','0','Cute Coffee','5.0','125')");

        db.execSQL("INSERT INTO goodsInfo (ID,goodsJson)values(0,'{\"itemsLeft\":[{\"title\":\"Coffee\"},{\"title\":\"Drinks\"},{\"title\":\"Cakes\"},{\"title\":\"Cookie\"}],\"itemsRight\":[{\"content\":\"Coffee\",\"name\":\"Americano\",\"number\":0,\"picNumb\":\"6\",\"price\":\"6.50\",\"title\":\"Coffee\"},{\"content\":\"Coffee\",\"name\":\"Cappuccino\",\"number\":0,\"picNumb\":\"2\",\"price\":\"10.00\",\"title\":\"Coffee\"},{\"content\":\"Coffee\",\"name\":\"Caramel Macchiato\",\"number\":0,\"picNumb\":\"8\",\"price\":\"10.00\",\"title\":\"Coffee\"},{\"content\":\"Coffee\",\"name\":\"Latté\",\"number\":0,\"picNumb\":\"8\",\"price\":\"11.00\",\"title\":\"Coffee\"},{\"content\":\"Drinks\",\"name\":\"Milo\",\"number\":0,\"picNumb\":\"6\",\"price\":\"4.00\",\"title\":\"Drinks\"},{\"content\":\"Cake\",\"name\":\"Small cake\",\"number\":0,\"picNumb\":\"6\",\"price\":\"10.99\",\"title\":\"Cake\"},{\"content\":\"Cookie\",\"name\":\"Sweet cookie\",\"number\":0,\"picNumb\":\"5\",\"price\":\"10.90\",\"title\":\"Cookie\"}]}\n')");

        }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
