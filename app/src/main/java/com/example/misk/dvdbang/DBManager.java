package com.example.misk.dvdbang;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Min-Soo on 2015-12-01.
 */
public class DBManager extends SQLiteOpenHelper {

        public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version ){
            super(context, name, factory, version);
        }

        @Override

        public void onCreate(SQLiteDatabase db) {

            //테이블 생성
            //create table 테이블명 (컬럼)
            //db.execSQL("CREATE TABLE FOOD_LIST(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public void makeTable(String foodName)
        {
            SQLiteDatabase db = getWritableDatabase();
            //db.execSQL("CREATE TABLE '"+foodName+"'(_id INTEGER PRIMARY KEY AUTOINCREMENT,ingredient TEXT);");
            db.close();
        }

        public void insert(String _query) {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(_query);
            db.close();
        }

        public void update(String _query) {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(_query);
            db.close();
        }

        public void delete(String _query) {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(_query);
            db.close();
        }

        public String PrintData() {
            SQLiteDatabase db = getReadableDatabase();
            String str = "";

            /*Cursor cursor = db.rawQuery("select * from FOOD_LIST", null);
            while(cursor.moveToNext()) {
                str += cursor.getInt(0)
                        + " : foodName "
                        + cursor.getString(1)
                        + "\n";
            }*/

            return str;
        }

    }
