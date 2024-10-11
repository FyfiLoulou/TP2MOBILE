package naji.ecole.TP23;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

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

public class MapsFragment extends FragmentActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;
    //private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Log.d("lol", googleMap.toString());
        LatLng limoilou = new LatLng(46.8301, -71.2277);
        mMap.addMarker(new MarkerOptions().position(limoilou).title("SA MARCHE PASSSASASASASAS :(((((("));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(limoilou));

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        LatLng limoilouQ = new LatLng(46.8301, -71.2277);
        LatLng limoilouC = new LatLng(46.8617, -71.2610);
        //Double distance = .computeDistanceBetween(limoilouQ, limoilouC);
        //Toast.makeText(this, "Distance entre Campus de Québec et Charsbourg est \n " + String.format("%.2f", distance / 1000) + "km", Toast.LENGTH_SHORT).show();
        //Log.d("D","Distance entre Campus de Québec et Charsbourg est \n " + String.format("%.2f", distance / 1000) + "km");
    }
}