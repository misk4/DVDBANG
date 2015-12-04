package com.example.misk.dvdbang;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    DBManager mydb;
    SQLiteDatabase db;
    //MainMenuActivity mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);


        mydb = new DBManager(getApplicationContext(),"dvdbang.db",null,1);
        db = mydb.getReadableDatabase();
        Cursor cursor = db.query("DVDBANG",null,null,null,null,null,null,null);
        startManagingCursor(cursor);
        if(cursor.getCount() == 0){
            setInitialDataBang();
        }
        db.close();

        db =mydb.getReadableDatabase();
        cursor = db.query("MOVIE",null,null,null,null,null,null,null);
        startManagingCursor(cursor);
        if(cursor.getCount() == 0){
            setInitialDataMovie();
        }
        db.close();

        db=mydb.getReadableDatabase();
        cursor = db.query("BANG_MOVIE",null,null,null,null,null,null,null);
        startManagingCursor(cursor);
        if(cursor.getCount() == 0){
            setInitialDataBang_Movie();
        }
        db.close();

        cursor.close();

        startActivity(intent);
        finish();

    }

    private void setInitialDataBang(){
        String []tuple = getResources().getStringArray(R.array.DVDBANG);
        for(int i=0; i<tuple.length;i++){
            String []temp=tuple[i].split("/",7);
            mydb.addBang(temp[0],temp[1],temp[2],temp[3],temp[4],temp[5],temp[6]);
        }
    }
    private void setInitialDataMovie(){
        String []tuple = getResources().getStringArray(R.array.MOVIE);
        for(int i=0; i<tuple.length;i++){
            String []temp=tuple[i].split("/",4);
            mydb.addMovie(temp[0],temp[1],temp[2],temp[3]);
        }
    }
    private void setInitialDataBang_Movie(){
        String []tuple = getResources().getStringArray(R.array.BANG_MOVIE);
        for(int i=0; i<tuple.length;i++){
            String []temp=tuple[i].split("/",2);
            mydb.addBang_Movie(temp[0],temp[1]);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
