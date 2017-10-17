package com.vipshah.locationexample;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient locationProviderClient;
    private MyLocationCallback myLocationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationProviderClient = LocationServices
                .getFusedLocationProviderClient(this);

        final LocationRequest request = new LocationRequest();
        request.setInterval(10000);
        request.setFastestInterval(5000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        myLocationCallback = new MyLocationCallback();
        Button fetchLocation = findViewById(R.id.fetchLocation);
        fetchLocation.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                locationProviderClient.requestLocationUpdates(request,
                        myLocationCallback,
                        Looper.myLooper());
            }
        });

    }

    @Override
    protected void onDestroy() {
        if (locationProviderClient != null) {
            locationProviderClient.removeLocationUpdates(myLocationCallback);
            locationProviderClient = null;
        }
        super.onDestroy();
    }

    class MyLocationCallback extends LocationCallback {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location location = locationResult.getLastLocation();
            if (location != null) {
                Log.i("vipul", "Latitude:" + location.getLatitude());
                Log.i("vipul", "Longitude:" + location.getLongitude());
            }
        }

        @Override
        public void onLocationAvailability(LocationAvailability locationAvailability) {
            super.onLocationAvailability(locationAvailability);
        }
    }
}
