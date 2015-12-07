package com.example.misk.dvdbang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchBangActivity2 extends Activity implements OnItemClickListener {

    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    ListView bangListView;
    String province,city,dong;
    String [] address;
    DBManager mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bang2);

        Intent intent = getIntent();
        address = intent.getStringArrayExtra("address");

        getList(address);//쿼리문 통해 list 에 쑤셔넣는다다

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, list);
        bangListView=(ListView)findViewById(R.id.bangListView);
        bangListView.setAdapter(adapter);
        //bangListView.setOnItemSelectedListener(this);
        bangListView.setOnItemClickListener(this);

    }

    public void getList(String[] address){
        mydb = new DBManager(getApplicationContext(),"dvdbang.db",null,1);
        province = address[0];
        city = address[1];
        dong = address[2];
        list = mydb.getAddress(address[0],address[1],address[2]);
        Log.d("44444", "" + list.size());
        mydb.close();

    }
    @Override
    public void onItemClick(AdapterView<?> parentView, View clickedView, int position, long id) {
        switch(parentView.getId()){
            case R.id.bangListView:
                String actualAddress=list.get(position);
                Intent intent = new Intent(getApplicationContext(),GoogleMap.class);
                startActivity(intent);
                break;
            default:break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_bang_activity2, menu);
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
    @Override
    public void onBackPressed(){
        finish();
    }
}
