package com.example.willdunn.lifestyle_app;

import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient client;
    private String userLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this); //grab lat/lon from user
        Button btn = findViewById(R.id.btn_update_Profile);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                switch(v.getId()) {

                    case R.id.btn_update_Profile: {
                        if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        //todo this will need to be changed - goes directly to hikes after profile setup. Needs
                        client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if(location != null){
                                    userLocation = "" + location.getLatitude() + "," + location.getLongitude();
                                    //Start an activity and pass the EditText string to it.
                                    Intent messageIntent = new Intent(MainActivity.this, MapsActivity.class);
                                    messageIntent.putExtra("BTN_STRING_LOC",userLocation);
                                    MainActivity.this.startActivity(messageIntent); //this is not needed here, but is helpful for readability
                                } else {
                                    Toast.makeText(MainActivity.this,"Could not get your location",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        //todo error handle the rest of the fields (aside from location)


                        //todo propagate the fields of the other texts


                        break;
                    }
                    /*
                    //todo create the passage of location when the correct module is selected
                    case R.id.btn_module_Hikes: {

                        //Start an activity and pass the location string to it.
                        Intent locIntent = new Intent(this, MapsActivity.class);
                        locIntent.putExtra("BTN_STRING_LOC", userLocation);
                        this.startActivity(locIntent); //this is not needed here, but is helpful for readability

                        break;
                    }
                     */
                }
            }
        });
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{ ACCESS_FINE_LOCATION}, 1);
    }
}
