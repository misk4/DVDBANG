package com.example.misk.dvdbang;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchMovieActivity extends Activity {

    Button searchButton,positionButton;
    TextView resultTextView;
    EditText bangInput,movieInput;
    String bang,movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        bangInput = (EditText)findViewById(R.id.bangInput);
        movieInput = (EditText)findViewById(R.id.movieInput);
        resultTextView = (TextView)findViewById(R.id.resultTextView);

        searchButton = (Button)findViewById(R.id.searchButton2);
        positionButton=(Button)findViewById(R.id.positionButton);
        positionButton.setVisibility(View.INVISIBLE);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bang = bangInput.getText().toString();
                movie = movieInput.getText().toString();

                resultTextView.setText("찾으시는 영화가 있습니다.");
                positionButton.setVisibility(View.VISIBLE);
            }
        });

        positionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_movie, menu);
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
