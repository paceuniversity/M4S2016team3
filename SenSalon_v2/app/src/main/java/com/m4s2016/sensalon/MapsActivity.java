package com.m4s2016.sensalon;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private LocationManager mLocationManager;
    static final LatLng SALON1 = new LatLng(14.720010, -17.448983);
    static final LatLng SALON2 = new LatLng(14.695786, -17.447393);
    static final LatLng SALON3 = new LatLng(14.729541, -17.460921);
    static final LatLng SALON4 = new LatLng(14.717703, -17.466275);
    static final LatLng SALON5 = new LatLng(14.694985, -17.461908);
    static final LatLng SALON6 = new LatLng(14.668914, -17.434271);
    static final LatLng SALON7 = new LatLng(14.680123, -17.462509);
    static final LatLng SALON8 = new LatLng(14.667731, -17.431310);
    static final LatLng SALON9 = new LatLng(14.666984, -14.435430);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker and move the camera
        mMap.addMarker(new MarkerOptions()
                .position(SALON1)
                .title("Latifah Coiffure")
                .snippet("Boulevard du Président Habib Bouguiba (Femme)")
                .alpha(10.0f));

        mMap.addMarker(new MarkerOptions()
                .position(SALON2)
                .title("Sope Khadim Coiffure")
                .snippet("Boulevard du General de Gaulle (Homme)")
                .alpha(10.0f));
        mMap.addMarker(new MarkerOptions()
                .position(SALON3)
                .title("Beauty Coiffure")
                .snippet("Rte du Front de Terre (Homme-Femme)")
                .alpha(10.0f));
        mMap.addMarker(new MarkerOptions()
                .position(SALON4)
                .title("Siby Coiffure")
                .snippet("Sicap Sacre coeur (Homme)")
                .alpha(10.0f));
        mMap.addMarker(new MarkerOptions()
                .position(SALON5)
                .title("Michele Ka Coiffure")
                .snippet("Point E (Homme)")
                .alpha(10.0f));
        mMap.addMarker(new MarkerOptions()
                .position(SALON6)
                .title("Hair Studio Coiffure")
                .snippet("Rue Mohamed V (Homme-Femme)")
                .alpha(10.0f));
        mMap.addMarker(new MarkerOptions()
                .position(SALON7)
                .title("Coupe De Tête Coiffure")
                .snippet("10 boulevard Martin Luther King, Corniche Ouest FANN HOCK (Homme-Femme)")
                .alpha(10.0f));
        mMap.addMarker(new MarkerOptions()
                .position(SALON8)
                .title("Pullman Téranga Coiffure")
                .snippet("Place De L'Indépendance, 3 Rue Parent X Rue Carnot (Homme-Femme)")
                .alpha(10.0f));
        mMap.addMarker(new MarkerOptions()
                .position(SALON9)
                .title("Bruch color Coiffure")
                .snippet("Rue Felix Faure (Homme-Femme)")
                .alpha(10.0f));

        float zoomLevel = 12.5f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SALON1, zoomLevel));
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public static String getBestProvider(LocationManager locationManager){
        Criteria criteria = new Criteria();
        criteria.setAccuracy(criteria.ACCURACY_COARSE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setCostAllowed(true);
        return locationManager.getBestProvider(criteria, true);
    }
}
