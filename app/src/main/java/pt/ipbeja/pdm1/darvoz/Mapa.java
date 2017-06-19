package pt.ipbeja.pdm1.darvoz;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class Mapa extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitude;
    double longitude;
    String streetAdress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        }

    List<Address> addresses = null;
    @Override
    public void onMapReady(final GoogleMap googleMap) {



        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {




                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(latitude = point.latitude,longitude = point.longitude));
                googleMap.clear();

                Geocoder geocoder = new Geocoder(Mapa.this, Locale.getDefault());

                try {
                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

                    if (addresses != null) {
                        Address returnedAddress = addresses.get(0);
                        StringBuilder strReturnedAddress = new StringBuilder();
                        for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                            strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("");
                        }
                        streetAdress = strReturnedAddress.toString();
                        marker.title(streetAdress);
                        googleMap.addMarker(marker);
                        Toast.makeText(Mapa.this, "Adresse Found " + strReturnedAddress.toString(), Toast.LENGTH_SHORT).show();
                       // et_lugar.setText(strReturnedAddress.toString());
                    }
                    else {
                        Toast.makeText(Mapa.this, "No Address returned!", Toast.LENGTH_SHORT).show();

                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Toast.makeText(Mapa.this, "Canont get Address!", Toast.LENGTH_SHORT).show();

                }



                Toast.makeText(Mapa.this, latitude + "," + longitude , Toast.LENGTH_SHORT).show();
            }
        });
        LatLng TutorialsPoint = new LatLng(38.0167055,-7.8656269);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TutorialsPoint,15f));
    }


    public void btn_ok_OnClick(View view) {

        Intent in = new Intent();
        in.putExtra("streetAdress", streetAdress);
        in.putExtra("latitude",latitude);
        in.putExtra("longitude",longitude);
        startActivity(in);


    }
}
