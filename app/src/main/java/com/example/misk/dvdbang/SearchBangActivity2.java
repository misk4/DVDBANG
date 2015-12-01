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
        province = address[0];
        city = address[1];
        dong = address[2];
        list = new ArrayList<String>();

        list.add("경기도 평택시 평택동 49-1");
        list.add("경기도 평택시 평택동 49-12");
        list.add("경기도 평택시 평택동 49-13");
        list.add("경기도 평택시 평택동 49-14");
        list.add("경기도 평택시 평택동 49-15");
        list.add("경기도 평택시 평택동 49-16");
        list.add("경기도 평택시 평택동 49-17");
        list.add("경기도 평택시 평택동 49-18");
        list.add("경기도 평택시 평택동 49-19");
        list.add("경기도 평택시 평택동 49-10");
        list.add("경기도 평택시 평택동 49-122");
        list.add("경기도 평택시 평택동 49-133");

        list.add("서울특별시 동작구 흑석동 111");
        list.add("서울특별시 동작구 상도1동 460-14");
        list.add("경기도 평택시 평택동 111");
        list.add("경기도 평택시 비전1동 293");
        list.add("서울특별시 송파구 방이동 33");
        list.add("경기도 평택시 원평동 333");


    }
    @Override
    public void onItemClick(AdapterView<?> parentView, View clickedView, int position, long id) {
        switch(parentView.getId()){
            case R.id.bangListView:
                String actualAddress=list.get(position);
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
}
