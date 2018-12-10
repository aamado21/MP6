package com.example.andreaamador.mp6;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private static final int Request_User_Location_Code = 23;
    protected double latitude;
    protected double longitude;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // sets layout life as the content view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        button = (Button) findViewById(R.id.morebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, DescriptionScreen.class);
                startActivity(intent);
            }
        });

        //shows message at startup
        //short description of app
        //button to continue
        AlertDialog.Builder welcomeBox = new AlertDialog.Builder(this);
        welcomeBox.setTitle("Welcome to AG Locator");
        welcomeBox.setMessage("This app allows you to locate the All-Gender restrooms in the UIUC campus");
        welcomeBox.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            //in order for the box to disappear after clicking continue
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        welcomeBox.show();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.AGLocator);
        //must check for null or else it will create a null pointer exception
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
    //called when the map is ready to be used
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //if the permission is granted the build the api client and enable the current location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();

            mMap.setMyLocationEnabled(true);
        }

        // Add a marker in locations with All-Gender bathrooms and move the camera

        //McKinley Health Center
        LatLng mckinley = new LatLng(40.102784, -88.219910);
        mMap.addMarker(new MarkerOptions().position(mckinley).title("McKinley Health Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mckinley));

        //Krannert Center
        LatLng krannertCenter = new LatLng(40.108045, -88.222917);
        mMap.addMarker(new MarkerOptions().position(krannertCenter).title("Krannert Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(krannertCenter));

        //Loomis Laboratory
        LatLng loomisLab = new LatLng(40.110870, -88.223100);
        mMap.addMarker(new MarkerOptions().position(loomisLab).title("Loomis Laboratory"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loomisLab));

        //Chemical and Life Sciences Laboratory
        LatLng chemicalSciencesLab = new LatLng(40.108002, -88.223976);
        mMap.addMarker(new MarkerOptions().position(chemicalSciencesLab).title("Chemical and Life Sciences Laboratory"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(chemicalSciencesLab));

        //ARC
        LatLng arCenter = new LatLng(40.101362, -88.237153);
        mMap.addMarker(new MarkerOptions().position(arCenter).title("Activities and Recreation Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(arCenter));

        //David Kinley Hall
        LatLng davidKinley = new LatLng(40.104252, -88.227219);
        mMap.addMarker(new MarkerOptions().position(davidKinley).title("David Kinley Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(davidKinley));

        //Mechanical Engineering Building
        LatLng mechEBuilding = new LatLng(40.110569, -88.225609);
        mMap.addMarker(new MarkerOptions().position(mechEBuilding).title("Mechanical Engineering Building"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mechEBuilding));

        //Bevier Hall
        LatLng bevierHall = new LatLng(40.104740, -88.224290);
        mMap.addMarker(new MarkerOptions().position(bevierHall).title("Bevier Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bevierHall));

        //Asian American Studies
        LatLng asianAmericanStudies = new LatLng(40.105930, -88.225530);
        mMap.addMarker(new MarkerOptions().position(asianAmericanStudies).title("Asian American Studies Building"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(asianAmericanStudies));

        //La Casa
        LatLng laCasa = new LatLng(40.105702, -88.224352);
        mMap.addMarker(new MarkerOptions().position(laCasa).title("La Casa Cultural Latina"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(laCasa));

        //Huff Hall
        LatLng huffHall = new LatLng(40.103857, -88.233318);
        mMap.addMarker(new MarkerOptions().position(huffHall).title("Huff Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(huffHall));

        //Memorial Stadium
        LatLng memorialStadium = new LatLng(40.099731, -88.238541);
        mMap.addMarker(new MarkerOptions().position(memorialStadium).title("Memorial Stadium"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(memorialStadium));

        //Newmark Civil Engineering Laboratory
        LatLng newmarkLab = new LatLng(40.113922, -88.226443);
        mMap.addMarker(new MarkerOptions().position(newmarkLab).title("Newmark Civil Engineering Laboratory"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newmarkLab));

        //University of Illinois Observatory
        LatLng uiucObservatory = new LatLng(40.105214, -88.225930);
        mMap.addMarker(new MarkerOptions().position(uiucObservatory).title("University of Illinois Observatory"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(uiucObservatory));

        //Main library
        LatLng mainLibrary = new LatLng(40.104684, -88.229120);
        mMap.addMarker(new MarkerOptions().position(mainLibrary).title("Main Library"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mainLibrary));

        //English Building
        LatLng englishBuilding = new LatLng(40.107551, -88.228198);
        mMap.addMarker(new MarkerOptions().position(englishBuilding).title("English Building"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(englishBuilding));

        //Freer Hall
        LatLng freerHall = new LatLng(40.104844, -88.222884);
        mMap.addMarker(new MarkerOptions().position(freerHall).title("Freer Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(freerHall));

        //Krannert Art Museum
        LatLng krannertMuseum = new LatLng(40.101910, -88.231323);
        mMap.addMarker(new MarkerOptions().position(krannertMuseum).title("Krannert Art Museum"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(krannertMuseum));

        //Veterinary Teaching Hospital
        LatLng vetHospital = new LatLng(40.092013, -88.223113);
        mMap.addMarker(new MarkerOptions().position(vetHospital).title("Veterinary Teaching Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vetHospital));

    }

    public boolean checkUserLocationPermission() {
        //checks if the user allows app to use their location
        //before calling an API it confirms the use of location
        //ACCESS_FINE_LOCATION: allows the API to calculate precisely the location of the user
        //ACCESS_COARSE_LOCATION: uses wifi to determine the location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] wasGranted) {
        switch (requestCode) {
            case Request_User_Location_Code:
                if (wasGranted.length > 0 && wasGranted[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                       //builds google api client when null
                        if (googleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        //enables the location to be shown
                        mMap.setMyLocationEnabled(true);
                        //current location button
                        mMap.getUiSettings().setMyLocationButtonEnabled(true);
                    }
                } else {
                    // if permission denied
                    Toast.makeText(this, "Permission Denied...", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    //called whenever the device is connected
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        //shows location in real time
        //4000 equals 4 seconds
        locationRequest.setInterval(4000);
        locationRequest.setFastestInterval(4000);
        //uses all location providers including GPS
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        //contextcompat accesses features of the application's environments
        //if the permission is granted then update the location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }

    }

    protected synchronized void buildGoogleApiClient() {
        //builder configures client
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
