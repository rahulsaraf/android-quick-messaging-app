package com.hackathon.hacktabapp;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
 
public class AndroidTabLayoutActivity extends TabActivity {
    /** Called when the activity is first created. */

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        
        
        TabHost tabHost = getTabHost();
        
        // Tab for Photos
        TabSpec photospec = tabHost.newTabSpec("Messages");
        // setting Title and Icon for the Tab
        photospec.setIndicator("Messages", getResources().getDrawable(R.drawable.ic_launcher));
        Intent photosIntent = new Intent(this, PhotosActivity.class);
        photospec.setContent(photosIntent);
        
        // Tab for Songs
        TabSpec songspec = tabHost.newTabSpec("Map");        
        songspec.setIndicator("Map", getResources().getDrawable(R.drawable.ic_launcher));
        Intent songsIntent = new Intent(this, SongsActivity.class);
        songspec.setContent(songsIntent);
        
        
        
        // Tab for Videos
        TabSpec videospec = tabHost.newTabSpec("Contacts");
        videospec.setIndicator("Contacts", getResources().getDrawable(R.drawable.ic_launcher));
        Intent videosIntent = new Intent(this, VideosActivity.class);
        videospec.setContent(videosIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab
    }
}