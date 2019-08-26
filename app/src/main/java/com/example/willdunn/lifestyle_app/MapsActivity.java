package com.example.willdunn.lifestyle_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;


public class MapsActivity extends AppCompatActivity {

    private String userLocation = "geo:40.767778,-111.845205?q="; //TODO: change this to be the passed in the location, not default

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the intent that created this activity.
        Intent receivedIntent = getIntent();

        //Get the string data of the location
        userLocation = "geo:" + receivedIntent.getStringExtra("BTN_STRING_LOC") + "?q=";

        if(userLocation.isEmpty()){
            Toast.makeText(MapsActivity.this, "No location received", Toast.LENGTH_SHORT).show();
        } else {
            //We have to search for nearby hikes and construct a URI object from it.
            Uri searchUri = Uri.parse(userLocation + "hikes"); //hikes is a "magic number"

            //Create the implicit intent
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, searchUri);

            //If there's an activity associated with this intent, launch it
            if(mapIntent.resolveActivity(getPackageManager())!=null){
                startActivity(mapIntent);
            }
        }









    }



}
