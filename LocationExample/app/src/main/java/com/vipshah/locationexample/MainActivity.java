package com.vipshah.locationexample;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

// https://guides.codepath.com/android/Google-Maps-API-v2-Usage

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient locationProviderClient;
    private MyLocationCallback myLocationCallback;
    private ProgressBar progressBar;
    private LocationRequest locationRequest;
    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapFragment);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mGoogleMap = googleMap;
                mGoogleMap.getUiSettings().setZoomControlsEnabled(true);

                new GetLatLonFromAddress().execute("Taj Mahal");
            }
        });

        progressBar = findViewById(R.id.progressBar);

        locationProviderClient = LocationServices
                .getFusedLocationProviderClient(this);

        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        myLocationCallback = new MyLocationCallback();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
    }

    @SuppressLint("MissingPermission")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_location:
                locationProviderClient.requestLocationUpdates(locationRequest,
                        myLocationCallback,
                        Looper.myLooper());
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onDestroy() {
        if (locationProviderClient != null) {
            locationProviderClient.removeLocationUpdates(myLocationCallback);
            locationProviderClient = null;
        }
        super.onDestroy();
    }

    private void displayLocationInfo(Location location) {
        Log.i("vipul", "Latitude:" + location.getLatitude());
        Log.i("vipul", "Longitude:" + location.getLongitude());

        displayMarkers(location.getLatitude(), location.getLongitude());

        new GetAddressFromLatLon().execute(location);
    }

    private void displayMarkers(double latitude, double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(latLng);
        mGoogleMap.animateCamera(cameraUpdate);

        BitmapDescriptor defaultMarker =
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);

        mGoogleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Statue of liberty")
                .snippet("Liberty Island")
                .icon(defaultMarker));
    }

    class MyLocationCallback extends LocationCallback {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location location = locationResult.getLastLocation();
            if (location != null) {
                displayLocationInfo(location);
            }
        }

        @Override
        public void onLocationAvailability(LocationAvailability locationAvailability) {
            super.onLocationAvailability(locationAvailability);
        }
    }

    class GetAddressFromLatLon extends AsyncTask<Location, Void, List<Address>> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected List<Address> doInBackground(Location... locations) {
            Location location = locations[0];
            if (location != null) {
                Geocoder geocoder = new Geocoder(MainActivity.this);
                try {
                    return geocoder.getFromLocation(location.getLatitude(),
                            location.getLongitude(), 5);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {
            super.onPostExecute(addresses);
            progressBar.setVisibility(View.GONE);
            String newLineChar = System.getProperty("line.separator");
            if (addresses != null) {
                for (Address address : addresses) {
                    StringBuilder builder = new StringBuilder();
                    int maxIndex = address.getMaxAddressLineIndex();
                    for (int i = 0; i < maxIndex; i++) {
                        builder.append(address.getAddressLine(i));
                        builder.append(newLineChar);
                    }
                    Log.i("vipul", builder.toString() + newLineChar);
                }
            }
        }
    }

    class GetLatLonFromAddress extends AsyncTask<String, Void, List<Address>> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected List<Address> doInBackground(String... addresses) {
            String address = addresses[0];
            if (address != null) {
                Geocoder geocoder = new Geocoder(MainActivity.this);
                try {
                    return geocoder.getFromLocationName(address, 5);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {
            super.onPostExecute(addresses);
            progressBar.setVisibility(View.GONE);

            if (addresses != null) {
                for (Address address : addresses) {
                    displayMarkers(address.getLatitude(), address.getLongitude());
                }
            }
        }
    }
}
