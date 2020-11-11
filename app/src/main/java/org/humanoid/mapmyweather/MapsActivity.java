package org.humanoid.mapmyweather;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        View.OnClickListener {

    private GoogleMap mMap;
    private TextInputEditText mLocationInput;
    private AppCompatImageButton mSearchButton;
    private FloatingActionButton mFloatingActionButton;
    private String mAddress = " ";
    private LatLng mLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mLocationInput = findViewById(R.id.location_input);
        mSearchButton = findViewById(R.id.btn_search);
        mFloatingActionButton = findViewById(R.id.fab);

        mSearchButton.setOnClickListener(this);
        mFloatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private void searchLocation(String location) {
        if (location != null && location.length() != 0) {
            Toast.makeText(MapsActivity.this, "Searching", Toast.LENGTH_SHORT).show();
            Geocoder geocoder = new Geocoder(this);
            if (geocoder.isPresent()) {
                List<Address> addressList = null;
                mAddress = location;
                mMap.clear();
                try {
                    addressList = geocoder.getFromLocationName(location, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (Address address : addressList) {
                    mLatLng = new LatLng(address.getLatitude(), address.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(mLatLng).title(location);

                    mMap.addMarker(markerOptions);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(mLatLng));
//                    mMap.animateCamera(CameraUpdateFactory.zoomBy(4));
                }
            } else {
                Toast.makeText(this, "GeoCoder Not Present", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MapsActivity.this, "No Such Address", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                searchLocation(mLocationInput.getText().toString());
                break;
            case R.id.fab: {
                if (mLatLng != null) {
                    WeatherBottomSheetDialogFragment bottomSheet =
                            new WeatherBottomSheetDialogFragment();
                    bottomSheet.getData(mAddress, mLatLng);
                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                }
                break;
            }
        }
    }
}
