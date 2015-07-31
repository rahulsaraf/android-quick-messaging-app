package com.hackathon.hacktabapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
 
public class SongsActivity extends Activity {
    
	
	Button btnShowLocation;

    // GPSTracker class
    GPSTracker gps;
    
 
    
    private GoogleMap mMap;

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songs_layout);
        
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		
		
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		
		UiSettings settings = mMap.getUiSettings();
		settings.setZoomGesturesEnabled(true);
		settings.setZoomControlsEnabled(true);
		settings.setCompassEnabled(true);
		settings.setMyLocationButtonEnabled(true);
		settings.setIndoorLevelPickerEnabled(true);
		settings.setMapToolbarEnabled(true);
		
		btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

        // Show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Create class object
                gps = new GPSTracker(SongsActivity.this);
                btnShowLocation.setVisibility(View.INVISIBLE);
                // Check if GPS enabled
                if(gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    
                    
                    

                    final LatLng CIU = new LatLng(latitude, longitude);
                    final LatLng CIU1 = new LatLng(latitude+0.005, longitude+0.005);
                    final LatLng CIU2 = new LatLng(latitude-0.004, longitude+0.004);
                    final LatLng CIU3 = new LatLng(latitude-0.006, longitude+0.006);
                    final LatLng CIU4 = new LatLng(latitude+0.0045, longitude+0.0045);
                    final LatLng CIU5 = new LatLng(latitude-0.0055, longitude-0.0055);
                    Marker ciu = mMap.addMarker(new MarkerOptions().position(CIU).title(
            				"My Location"));
            		
            		Marker sec = mMap.addMarker(new MarkerOptions().position(CIU1).title(
            				"#angelinaJolie"));
            		Marker sec1 = mMap.addMarker(new MarkerOptions().position(CIU2).title(
            				"#rahul"));
            		Marker sec2 = mMap.addMarker(new MarkerOptions().position(CIU3).title(
            				"#keyneWest"));
            		Marker sec3 = mMap.addMarker(new MarkerOptions().position(CIU4).title(
            				"#Neel"));
            		Marker sec4 = mMap.addMarker(new MarkerOptions().position(CIU5).title(
            				"#Abhimanyu"));
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(CIU, 15);
                    mMap.animateCamera(yourLocation);            		

            		
                    // \n is for new line
            		//Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                } else {
                    // Can't get location.
                    // GPS or network is not enabled.
                    // Ask user to enable GPS/network in settings.
                    gps.showSettingsAlert();
                }
            }
        });
        
    }
}