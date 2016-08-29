package com.m4s2016.sensalon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private LatLng senegal = new LatLng(14.6928, -17.4467);

    private GoogleMap mMap;

    private final String TAG_IDSALON = "idSalon";
    private final String TAG_NOMSALON = "nomSalon";
    private final String TAG_LONGITUDE = "longitude";
    private final String TAG_LATITUDE = "latitude";
    private final String TAG_ADRESSE = "adresse";
    private final String TAG_TELEPHONE = "telephone";
    private final String TAG_TYPESALON = "typeSalon";
    private final String TAG_ALLLATITUDE = "allLatitudeSalon";
    private final String TAG_ALLLONGITUDE = "allLongitudeSalon";
    private final String TAG_ALLNOMSALON = "allNomSalon";

    private ArrayList<String> newListLatitude;
    private ArrayList<String> newListLongitude;
    private ArrayList<String> newListNomSalon;
    private String[] tabLatitude;
    private String[] tabLongitude;
    private String[] tabNomSalon;

    private String idSalon;
    private String nomSalon;
    private String longitude;
    private String latitude;
    private String adresse;
    private String telephone;
    private String typeSalon;
    private double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            newListLatitude = extras.getStringArrayList("TAG_ALLLATITUDE");
            newListLongitude = extras.getStringArrayList("TAG_ALLLONGITUDE");
            newListNomSalon = extras.getStringArrayList("TAG_ALLNOMSALON");
        }

        tabLatitude = newListLatitude.toArray(new String[newListLatitude.size()]);
        for (int i = 0; i < tabLatitude.length; i++) {
            System.out.println("tab-latitude" + i + " " + tabLatitude[i]);
        }

        tabLongitude = newListLongitude.toArray(new String[newListLongitude.size()]);
        for (int i = 0; i < tabLongitude.length; i++) {
            System.out.println("tab-longitude" + i + " " + tabLongitude[i]);
        }

        tabNomSalon = newListNomSalon.toArray(new String[newListNomSalon.size()]);
        for (int i = 0; i < tabNomSalon.length; i++) {
            System.out.println("tab-nomSalon" + i + " " + tabNomSalon[i]);
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(170));
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getMaxZoomLevel();

        for (int i = 0; i < tabLatitude.length; i++) {
            lng = Double.parseDouble(tabLongitude[i]);
            lat = Double.parseDouble(tabLatitude[i]);
            // Add a marker in Sydney and move the camera
            LatLng newMarker = new LatLng(lat, lng);
            Log.d("LAT-LONG", lat + "," + lng);
            mMap.addMarker(new MarkerOptions().position(newMarker).title("Salon de coiffure: " + tabNomSalon[i]));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(newMarker));
        }


    }
}
