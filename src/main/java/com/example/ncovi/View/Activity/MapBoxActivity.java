package com.example.ncovi.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.ncovi.R;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;
import com.mapbox.maps.Style;

public class MapBoxActivity extends AppCompatActivity {
private MapView mapView ;
    PermissionsManager permissionsManager;
    private MapboxMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_box);
        mapView.findViewById(R.id.map);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);
    }

    @SuppressLint("Lifecycle")
    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @SuppressLint("Lifecycle")
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @SuppressLint("Lifecycle")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

}