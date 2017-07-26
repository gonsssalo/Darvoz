package pt.ipbeja.pdm1.darvoz.Entities;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
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

import pt.ipbeja.pdm1.darvoz.R;


public class Mapa extends AppCompatActivity implements OnMapReadyCallback {

    double latitude;
    double longitude;
    String streetAdress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);


        getSupportActionBar().setTitle("Darvoz - Mapa");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(latitude = point.latitude, longitude = point.longitude));
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
                        Toast.makeText(Mapa.this, "Adereço encontrado " + strReturnedAddress.toString(), Toast.LENGTH_SHORT).show();
                        // et_lugar.setText(strReturnedAddress.toString());
                    } else {
                        Toast.makeText(Mapa.this, "Adereço não encontrado!", Toast.LENGTH_SHORT).show();

                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Toast.makeText(Mapa.this, "Adereco não encontrado!", Toast.LENGTH_SHORT).show();

                }


                Toast.makeText(Mapa.this, latitude + "," + longitude, Toast.LENGTH_SHORT).show();
            }
        });
        LatLng  TutorialsPoint = new LatLng(38.0167055, -7.8656269);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TutorialsPoint, 15f));
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

    }


    public void btn_ok_OnClick(View view) {


        if (streetAdress != null) {
            String sms3 =  streetAdress + " nas coordenadas "+ latitude + ", " + longitude;
            Intent in = new Intent();
            in.putExtra("lat", latitude);
            in.putExtra("streetAdress", streetAdress);
            in.putExtra("lon", longitude);
            in.putExtra("sms",sms3 );
            setResult(Activity.RESULT_OK, in);
            finish();
        }
        else
        {
            Toast.makeText(this, "Selecione um lugar", Toast.LENGTH_SHORT).show();
        }

    }

    public void btn_voltar_OnClick(View view) {
        super.onBackPressed();
    }
}
