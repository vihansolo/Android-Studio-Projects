package ga.vihanggarud.www.gmapbasic;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    public void onSearch(View v) {

        EditText searchEntry = findViewById(R.id.searchBar);

        String location = searchEntry.getText().toString();
        List<Address> addressList = null;

        if (!location.equals("")) {

            Geocoder geocoder = new Geocoder(this);

            try {

                addressList = geocoder.getFromLocationName(location,3);
            }

            catch (IOException exception) {

                exception.printStackTrace();
            }
        }

        assert addressList != null;
        Address address = addressList.get(0);
        LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title(location));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    public void zoomFunc(View v) {

        if (v.getId() == R.id.zoomIn)
            mMap.animateCamera(CameraUpdateFactory.zoomIn());

        else
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
    }

    public void changeView(View v) {

        if (v.getId() == R.id.satellite) {

            if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

            else if (mMap.getMapType() == GoogleMap.MAP_TYPE_TERRAIN)
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

            else
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

        else if (v.getId() == R.id.terrain) {

            if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

            else if (mMap.getMapType() == GoogleMap.MAP_TYPE_SATELLITE)
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

            else
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

        else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}
