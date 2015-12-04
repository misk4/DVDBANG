package com.example.misk.dvdbang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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
            db.execSQL("CREATE TABLE IF NOT EXISTS MOVIE (name TEXT PRIMARY KEY, genre TEXT, year INTEGER, runtime INTEGER);");
            db.execSQL("CREATE TABLE IF NOT EXISTS DVDBANG(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, province TEXT, city TEXT, dong TEXT, addtionalAddress TEXT, phoneNumber TEXT, image INTEGER);");
            db.execSQL("CREATE TABLE IF NOT EXISTS BANG_MOVIE(_id INTEGER, name TEXT, PRIMARY KEY(_id,name));");


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

    public void addMovie(String name, String genre, String year, String runtime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("genre",genre);
        values.put("year",year.toString());
        values.put("runtime",runtime.toString());

        db.insert("MOVIE", null, values);
        db.close();
    }

    public void addBang(String name, String province, String city, String dong, String realAddress, String phoneNumber, String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name",name);
        values.put("province",province);
        values.put("city",city);
        values.put("dong",dong);
        values.put("addtionalAddress",realAddress);
        values.put("phoneNumber",phoneNumber);
        values.put("image",image.toString());

        db.insert("DVDBANG", null, values);
        db.close();
    }

    public void addBang_Movie(String bang_name, String movie_name){
        SQLiteDatabase db = this.getReadableDatabase();
        int id;

        Cursor cursor = db.rawQuery("select _id from DVDBANG where name = '"+bang_name+"';",null);
        id = cursor.getInt(0);


        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id",id);
        values.put("name",movie_name);

        db.insert("MOVIE", null, values);
        db.close();
    }

    public ArrayList<String> getAddress(String province, String city, String dong){
        ArrayList<String> result = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String str="";
        Cursor cursor;
        Log.d("44444444",""+province.isEmpty()+city.isEmpty()+dong.isEmpty());
        if(!province.isEmpty() && !city.isEmpty() && !dong.isEmpty()) {
            cursor = db.rawQuery("select * from DVDBANG where province = '" + province + "' AND city = '" + city + "' AND dong = '" + dong + "';", null);
        }else if(!province.isEmpty() && !city.isEmpty() && dong.isEmpty()){
            cursor = db.rawQuery("select * from DVDBANG where province = '" + province + "' AND city = '" + city + "';", null);
        }else if(!city.isEmpty() && !dong.isEmpty() && province.isEmpty()){
            cursor = db.rawQuery("select * from DVDBANG where city = '" + city + "' AND dong = '" + dong + "';", null);
        }else if(!province.isEmpty() && !dong.isEmpty() && city.isEmpty()){
            cursor = db.rawQuery("select * from DVDBANG where province = '" + province + "' AND dong = '" + dong + "';", null);
        }else if(!province.isEmpty() && city.isEmpty() && dong.isEmpty()){
            cursor = db.rawQuery("select * from DVDBANG where province = '" + province + "';", null);
        }else if(province.isEmpty() && !city.isEmpty() && dong.isEmpty()){
            cursor= db.rawQuery("select * from DVDBANG where city = '" + city + "';", null);
        }else if(province.isEmpty() && city.isEmpty() && !dong.isEmpty()){
            cursor = db.rawQuery("select * from DVDBANG where dong = '" + dong + "';", null);
        }else{
            cursor = db.rawQuery("select * from DVDBANG;", null);
        }
            while (cursor.moveToNext()) {
                str += cursor.getString(1) + " "
                        + cursor.getString(2) + " "
                        + cursor.getString(3) + " "
                        + cursor.getString(4) + " "
                        + cursor.getString(5)
                        + "\n";
                Log.d("5551125251", str);
                result.add(str);
                str = "";
            }

        Log.d("666666", "" + result.size());
        db.close();
        return result;
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
