package com.example.misk.dvdbang;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchDetailedActivity extends Activity implements AdapterView.OnItemSelectedListener {

    Button detailedSearchButton;
    Spinner spinnerGenre,spinnerYear,spinnerTime;
    ArrayAdapter adapterGenre,adapterYear,adapterTime;
    EditText keywordInput;
    String genre,year,time,keyword;
    ArrayList<String> result;
    ArrayAdapter<String> adapter;
    ListView dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detailed);


        keywordInput=(EditText)findViewById(R.id.keywordInput);
        detailedSearchButton=(Button)findViewById(R.id.detailedSearchButton);
        detailedSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword = keywordInput.getText().toString();
                if(keyword.isEmpty()){
                    keyword = "";
                }
                findData(genre, year, time, keyword);
                getData();
            }
        });

        spinnerGenre=(Spinner)findViewById(R.id.genreSpinner);
        spinnerTime=(Spinner)findViewById(R.id.movieTimeSpinner);
        spinnerYear=(Spinner)findViewById(R.id.movieYearSpinner);

        adapterGenre = ArrayAdapter.createFromResource(
                this, R.array.genre,android.R.layout.simple_spinner_item);
        adapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(adapterGenre);
        spinnerGenre.setSelection(1);
        spinnerGenre.setOnItemSelectedListener(this);

        adapterTime = ArrayAdapter.createFromResource(
                this, R.array.time,android.R.layout.simple_spinner_item);
        adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTime.setAdapter(adapterTime);
        spinnerTime.setSelection(1);
        spinnerTime.setOnItemSelectedListener(this);

        adapterYear = ArrayAdapter.createFromResource(
                this, R.array.year,android.R.layout.simple_spinner_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(adapterYear);
        spinnerYear.setSelection(1);
        spinnerYear.setOnItemSelectedListener(this);

    }

    public void getData(){
        dataList = (ListView)findViewById(R.id.detailedSearchListView);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, result);
        dataList.setAdapter(adapter);
    }

    public void findData(String genre, String year, String time, String keyword){
        DBManager mydb = new DBManager(getApplicationContext(), "dvdbang.db", null, 1);
        int intYear,intTime;
        if(year.equals("------------------------")){
            intYear = 0;
        }else {
            intYear = Integer.parseInt(year.split(" ")[0]);
        }

        if(time.equals("------------------------")){
            intTime = 0;
        }else {
            intTime = Integer.parseInt(time.split(" ")[0]);
        }


        result =  mydb.findMovie(genre,intYear,intTime,keyword);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){
        switch(arg0.getId()){
            case R.id.genreSpinner:
                genre = (String) arg0.getSelectedItem();
                break;
            case R.id.movieTimeSpinner:
                time=(String)arg0.getSelectedItem();
                break;
            case R.id.movieYearSpinner:
                year=(String)arg0.getSelectedItem();
                break;
            default:break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0){
        Toast.makeText(this, "하나를 선택해 주십시오", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_detailed, menu);
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
