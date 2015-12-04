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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class SearchBangActivity extends Activity implements OnItemSelectedListener {
    Spinner spinner;
    ArrayAdapter adapter;
    Button searchButton;
    String province,city,dong;
    EditText cityInput,dongInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bang);

        cityInput = (EditText)findViewById(R.id.cityInput);
        dongInput = (EditText)findViewById(R.id.dongInput);

        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(
                this, R.array.province,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(1);
        spinner.setOnItemSelectedListener(this);

        searchButton = (Button)findViewById(R.id.searchButton2);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                city = cityInput.getText().toString();
                dong=dongInput.getText().toString();

                String [] address = {province,city,dong};

                Intent intent = new Intent(getApplicationContext(),SearchBangActivity2.class);
                intent.putExtra("address",address);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){
        switch(arg0.getId()){
            case R.id.spinner:
                String temp= (String) arg0.getSelectedItem();
                if(temp.equals("--선택해주십시오--")){
                    province = "";
                }else {
                    province = temp;
                }
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
        getMenuInflater().inflate(R.menu.menu_search_bang, menu);
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
