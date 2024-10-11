package naji.ecole.TP23;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends FragmentActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;
    private String adresse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        adresse = getIntent().getStringExtra("adresse");
    }

    private LatLng getAddressLatLng(String adresse) {
        Geocoder geocoder = new Geocoder(this);
        LatLng resoltatt = null;

        try {
            List<Address> addresses = geocoder.getFromLocationName(adresse, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                resoltatt = new LatLng(address.getLatitude(), address.getLongitude());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resoltatt;
    }

    private float getDist(String address1, String address2) {
        LatLng pos1 = getAddressLatLng(address1), pos2 = getAddressLatLng(address2);
            float[] results = new float[1];
            Location.distanceBetween(pos1.latitude, pos1.longitude, pos2.latitude, pos2.longitude, results);
            return results[0];
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng pos = getAddressLatLng(adresse);
        mMap.addMarker(new MarkerOptions().position(pos).title("SA MARCHE :)))))"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
    }
}