package pt.ipbeja.pdm1.darvoz.Entities;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import pt.ipbeja.pdm1.darvoz.Database.OperationsDatabase;
import pt.ipbeja.pdm1.darvoz.Entities.tipo_entidades.Firemen;
import pt.ipbeja.pdm1.darvoz.Entities.tipo_entidades.GNR;
import pt.ipbeja.pdm1.darvoz.Entities.tipo_entidades.PSP;
import pt.ipbeja.pdm1.darvoz.R;

public class Local extends AppCompatActivity implements LocationListener {
    double latitude;
    double longitude ;
    String streetAdress;
    String sms3;
    EditText ed;
    TextView tv;
    Location location;
   public int EntityNumber;
    public int EntityNumber2 = 0;
    int entity;
    LocationManager locationManager;
    String mprovider;
    String GetCelfoneContacPerson;
    public static final int REQUEST_CODE = 1;


    private static final int MAX_SMS_MESSAGE_LENGTH = 160;
    private static final int SMS_PORT = 8901;
    private static final String SMS_DELIVERED = "SMS_DELIVERED";
    private static final String SMS_SENT = "SMS_SENT";

    private void sendSms(String phonenumber,String message) {

        SmsManager manager = SmsManager.getDefault();
        PendingIntent piSend = PendingIntent.getBroadcast(this, 0, new Intent(SMS_SENT), 0);
        PendingIntent piDelivered = PendingIntent.getBroadcast(this, 0, new Intent(SMS_DELIVERED), 0);

        byte[] data = new byte[message.length()];

        for(int index=0; index<message.length() && index < MAX_SMS_MESSAGE_LENGTH; ++index)
        {
            data[index] = (byte)message.charAt(index);
        }

        manager.sendDataMessage(phonenumber, null, (short) SMS_PORT, data,piSend, piDelivered);

    }



    private BroadcastReceiver sendreceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String info = "Send information: ";

            switch(getResultCode())
            {
                case Activity.RESULT_OK: info += "send successful"; break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE: info += "send failed, generic failure"; break;
                case SmsManager.RESULT_ERROR_NO_SERVICE: info += "send failed, no service"; break;
                case SmsManager.RESULT_ERROR_NULL_PDU: info += "send failed, null pdu"; break;
                case SmsManager.RESULT_ERROR_RADIO_OFF: info += "send failed, radio is off"; break;
            }

            Toast.makeText(getBaseContext(), info, Toast.LENGTH_SHORT).show();

        }
    };

    private BroadcastReceiver deliveredreceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String info = "Delivery information: ";

            switch(getResultCode())
            {
                case Activity.RESULT_OK: info += "delivered"; break;
                case Activity.RESULT_CANCELED: info += "not delivered"; break;
            }

            Toast.makeText(getBaseContext(), info, Toast.LENGTH_SHORT).show();
        }
    };






    ArrayList<String> AcidentsList;
    int checkButtonStatus;
    Boolean checkButtonLocation[] = new Boolean[]{false, false, false, false, false,
                                                  false, false, false, false, false,
                                                  false, false, false};



    String places[] = new String[]{
            "da PSP","dos Bombeiros", " do Teatro", " do Cinema", " da Estação de Comboios",
            " da Ródoviaria", " da Prisão", " do Castelo", " da Escola Dom Manuel I",
            " da Escola Mário Beirão", " da Escola Diogo Gouveia", "do Continente", "do IPBeja"
    };
    Boolean checkButtons [] = new Boolean[]{false, false, false, false};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        getSupportActionBar().setTitle("Darvoz - Localização");

         tv = (TextView)findViewById(R.id.TextViewSelectAdress);
         ed = (EditText)findViewById(R.id.EditTextAdress);

        Intent inten = getIntent();
        entity = inten.getIntExtra("entity",0);

    }

    public boolean isGPSEnabled (Context mContext){
        LocationManager locationManager = (LocationManager)
                mContext.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE) {

            if (resultCode == Activity.RESULT_OK) {

                 latitude = data.getDoubleExtra("lat",0);
                 longitude = data.getDoubleExtra("lon",0);
                 streetAdress = data.getStringExtra("streetAdress");
                sms3 = data.getStringExtra("sms");

                ed.setText(streetAdress + "\n" + latitude + ", " + longitude);
                Toast.makeText(this, ""+latitude+longitude+streetAdress, Toast.LENGTH_SHORT).show();
                // do something with the result

            } else if (resultCode == Activity.RESULT_CANCELED) {
                // some stuff that will happen if there's no result
            }
        }
    }
    int bottonsviewid[] = new int[]{
            R.id.btn_casa,
            R.id.btn_perto,
            R.id.btn_mapa,
            R.id.btn_gps};

    int bottonimageP[] = new int[]{
            R.drawable.btn_casa_p,
            R.drawable.btn_perto_p,
            R.drawable.btn_mapa_p,
            R.drawable.btn_gps_b};

    int bottonimageNP[] = new int[]{
            R.drawable.btn_casa_b,
            R.drawable.btn_perto_b,
            R.drawable.btn_mapa_b,
            R.drawable.bt_gps_p};

    public void changeButton(int posicionArray,int idImageButton,int drawableNp, int drawableP ) {

        ImageButton imgBtn = (ImageButton) findViewById(idImageButton);

        for(int i = 0; i < checkButtons.length; i++){

            if (!checkButtons[i] && !checkButtons[posicionArray])
            {
                checkButtonStatus = 0;

            }

            if (!checkButtons[i] && checkButtons[posicionArray])
            {
                checkButtonStatus = 1;

            }

            if (checkButtons[i] && !checkButtons[posicionArray])
            {
               replaceBtn(posicionArray,i,bottonsviewid[posicionArray],bottonsviewid[i],bottonimageP[posicionArray],bottonimageNP[i] );
                i = 4;
            }

        }//fim ciclo for*/

        if(checkButtonStatus == 0){

            imgBtn.setBackgroundResource(drawableP);
            checkButtons[posicionArray] = true;
        }
        if(checkButtonStatus == 1){

            imgBtn.setBackgroundResource(drawableNp);
            checkButtons[posicionArray] = false;
            tv.setText("");
        }

    }


    public void replaceBtn(int imagePressedNumber, int imageNotPressedNumber,int imagePressedReference, int imageNotPressedReference,int imagePressed, int imageNotPressed ){

        for(int i = 0; i < checkButtons.length; i++){



            if(checkButtons[i] == true){

                LinearLayout mainLayout = (LinearLayout) findViewById(R.id.layout_pertoDe);
                ImageButton imageButtonname = (ImageButton) findViewById(imagePressedReference);
                ImageButton imageButtonname2 = (ImageButton) findViewById(imageNotPressedReference);

                imageButtonname.setBackgroundResource(imagePressed);
                checkButtons[imagePressedNumber] = true;

                imageButtonname2.setBackgroundResource(imageNotPressed);
                checkButtons[imageNotPressedNumber] = false;


                if(imageNotPressedNumber == 1){

                    mainLayout.setVisibility(LinearLayout.GONE);
                }

                if (imagePressedNumber == 1) {

                    mainLayout.setVisibility(LinearLayout.VISIBLE);
                }

            }
        }

    }





    public void changebuttonlocation(int Nbuttons ,int buttonArraynumber,int imageviewReference , int imageReferenceN1, int imageReferenceN2)
    {
        for(int i = 0; i < checkButtonLocation.length; i++){

            if (!checkButtonLocation[i] && !checkButtonLocation[buttonArraynumber])
            {
                checkButtonStatus = 0;

            }

            if (!checkButtonLocation[i] && checkButtonLocation[buttonArraynumber])
            {
                checkButtonStatus = 1;

            }

            if (checkButtonLocation[i] && !checkButtonLocation[buttonArraynumber])
            {
                checkButtonStatus = 2;
                i = Nbuttons;
            }

        }//fim ciclo for*/

        Button imageButtonname = (Button) findViewById(imageviewReference);
        if(checkButtonStatus == 0){

            imageButtonname.setBackgroundResource(imageReferenceN1);
            checkButtonLocation[buttonArraynumber] = true;
            ed.setText(places[buttonArraynumber]);
            sms3 = "perto " + places[buttonArraynumber];
        }
        if(checkButtonStatus == 1){

            imageButtonname.setBackgroundResource(imageReferenceN2);
            checkButtonLocation[buttonArraynumber] = false;
            sms3 = null;
            ed.setText("");

        }

        if(checkButtonStatus == 2){

            Toast.makeText(this, "Apenas deve estar selecionado um Local", Toast.LENGTH_SHORT).show();

        }

    }



    public void btn_casa_onClick(View view) {

        String sitio = "Casa";
        getdata (sitio);
        if(checkButtons[0] == false){
            getEntityAdress();

        }
        else{
            ed.setText("");
            sms3 = null;
        }
        changeButton( 0,bottonsviewid[0],R.drawable.btn_casa_b, R.drawable.btn_casa_p );
    }

    public void getdata (String sitio){

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());

        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
        String formattedDate2 = df2.format(c.getTime());
        tv.setText(sitio  + " Data: " + formattedDate + " Hora: " + formattedDate2);
    }

    public void btn_perto_onClick(View view) {

        String sitio = "Perto";
        getdata (sitio);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.layout_pertoDe);
        if(checkButtons[1] == true){
            mainLayout.setVisibility(LinearLayout.GONE);
            ed.setText("");
            sms3 = null;
            for(int i = 0; i < checkButtonLocation.length; i++){

                if(checkButtonLocation[i] == true)
                {
                    checkButtonLocation[i] = false;
                }
            }
        }
        if(checkButtons[1] == false && checkButtons[0] == false && checkButtons[2] == false && checkButtons[3] == false){
            mainLayout.setVisibility(LinearLayout.VISIBLE);
        }

        changeButton( 1,bottonsviewid[1],R.drawable.btn_perto_b, R.drawable.btn_perto_p );
    }

    public void btn_mapa_onClick(View view) {

        String sitio = "Mapa";
        getdata (sitio);

        changeButton( 2,bottonsviewid[2],R.drawable.btn_mapa_b, R.drawable.btn_mapa_p );
        if (checkButtons[2]==true)
        {
    Intent in = new Intent(Local.this, Mapa.class);
    startActivityForResult(in, REQUEST_CODE);
}
        if(checkButtons[2]==false)
        {
    sms3 = null;
            ed.setText("");
}
        }


    public void btn_gps_onclick(View view) {
        String sitio = "GPS";
        getdata(sitio);
        changeButton(3, bottonsviewid[3], R.drawable.bt_gps_p, R.drawable.btn_gps_b);

         locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria,true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        location = locationManager.getLastKnownLocation(bestProvider);
        if (location == null) {
            onLocationChanged(location);
        }

        locationManager.requestLocationUpdates(bestProvider,5000,0,this);
        if(checkButtons[3] == false) {
            locationManager.removeUpdates(this);
            ed.setText("");
        }
        else{
            getLocations();
        }

    }

    public void getLocations() {

        if (isGPSEnabled(this) == true) {
            if (location == null) {
                Toast.makeText(this, "No Location found", Toast.LENGTH_SHORT).show();

            } else {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                Geocoder geocoder = new Geocoder(Local.this, Locale.getDefault());

                try {
                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

                    if (addresses != null) {
                        Address returnedAddress = addresses.get(0);
                        StringBuilder strReturnedAddress = new StringBuilder();
                        for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                            strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("");
                        }
                        streetAdress = strReturnedAddress.toString();
                        Toast.makeText(Local.this, "Adresse Found " + strReturnedAddress.toString(), Toast.LENGTH_SHORT).show();
                        // et_lugar.setText(strReturnedAddress.toString());
                        ed.setText("" + strReturnedAddress.toString() + "\n" + latitude + longitude);
                    } else {
                        Toast.makeText(Local.this, "No Address returned!", Toast.LENGTH_SHORT).show();

                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Toast.makeText(Local.this, "Canont get Address!", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
    public void btn_pspOnClick(View view) {
        changebuttonlocation(13, 0,R.id.btn_psp , R.drawable.btn_psp_p, R.drawable.btn_psp_b);


    }

    public void btn_bombeirosOnClick(View view) {
        changebuttonlocation(13, 1,R.id.btn_bomb , R.drawable.btn_bomb_p, R.drawable.btn_bomb_b);

    }

    public void btn_teatroOnClick(View view) {
        changebuttonlocation(13, 2,R.id.btn_teatro , R.drawable.btn_teatro_p, R.drawable.btn_teatro_b);

    }

    public void btn_cinemaOnClick(View view) {
        changebuttonlocation(13, 3,R.id.btn_cine , R.drawable.btn_cinema_p, R.drawable.btn_cinema_b);


    }

    public void btn_comboiosOnClick(View view) {
        changebuttonlocation(13, 4,R.id.btn_est_combo , R.drawable.btn_train_sta_p, R.drawable.btn_train_sta_b);

    }

    public void btn_rodoviariaOnClick(View view) {
        changebuttonlocation(13, 5,R.id.btn_rodov , R.drawable.btn_bus_sta_p, R.drawable.btn_bus_sta_b);


    }

    public void btn_prisaoOnClick(View view) {
        changebuttonlocation(13, 6,R.id.btn_prisao , R.drawable.btn_prisao_p, R.drawable.btn_prisao_b);


    }

    public void btn_casteloOnClick(View view) {
        changebuttonlocation(13, 7,R.id.btn_castelo , R.drawable.btn_castelo_p, R.drawable.btn_castelo_b);


    }

    public void btn_DmanuelOnClick(View view) {
        changebuttonlocation(13, 8,R.id.btn_dm , R.drawable.btn_dmi_p, R.drawable.btn_dmi_b);


    }

    public void btn_MarioBeiraoOnClick(View view) {
        changebuttonlocation(13, 9,R.id.btn_mb , R.drawable.btn_mb_p, R.drawable.btn_mb_b);

    }

    public void btn_DiogoGouveiaOnClick(View view) {
        changebuttonlocation(13, 10,R.id.btn_dg_b , R.drawable.btn_dg_p, R.drawable.btn_dg_b);


    }

    public void btn_ContineteOnClick(View view) {
        changebuttonlocation(13, 11,R.id.btn_cont , R.drawable.btn_cont_p, R.drawable.btn_cont_b);



    }

    public void btn_ipBejaOnClick(View view) {
        changebuttonlocation(13, 12,R.id.btn_ipbeja , R.drawable.btn_ipbeja_p, R.drawable.btn_ipbeja_b);


    }

    public void getEntityAdress() {

        try {
            OperationsDatabase DOP = new OperationsDatabase(this);
            Cursor CR = DOP.getUserAdress(DOP);
            CR.moveToFirst();

            String GetUserAdress = CR.getString(0);
            sms3 = "em " + GetUserAdress;
            ed.setText(GetUserAdress);
        }
        catch (Exception ignored)
        {}
    }
    public void getEntityInfo () {

        if(entity == 2){
            try {
                OperationsDatabase DOP = new OperationsDatabase(this);
                Cursor CR = DOP.getEntityInformation(DOP);
                CR.moveToFirst();

                EntityNumber = CR.getInt(entity);
                EntityNumber2 = CR.getInt(entity + 1);


            } catch (Exception ignored) {
            }
        }
        else {
            try {
                OperationsDatabase DOP = new OperationsDatabase(this);
                Cursor CR = DOP.getEntityInformation(DOP);
                CR.moveToFirst();

                EntityNumber = CR.getInt(entity);


            } catch (Exception ignored) {
            }
        }
    }
    public void btn_enviar_onClick(View view) {

        if(sms3 == null){
            Toast.makeText(this, "Selecione uma morada", Toast.LENGTH_SHORT).show();
        }
        else {


            Intent intn = getIntent();
            AcidentsList = intn.getStringArrayListExtra("acidents");

            String sms2 = "";
            for (int i = 0; i < AcidentsList.size(); i++) {

                sms2 += AcidentsList.get(i) + ", ";


            }

            getEntityInfo();

            String finalSms = "pede ajuda para situação de " + sms2 + " e está " + sms3;
            Toast.makeText(this, finalSms, Toast.LENGTH_LONG).show();

            getUserInfo();
            if(EntityNumber2 == 0) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + EntityNumber + ";" + GetCelfoneContacPerson));
                intent.putExtra("sms_body", finalSms);
                startActivity(intent);


            }
            else{
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + EntityNumber + ";" + EntityNumber2 + ";" + GetCelfoneContacPerson));
                intent.putExtra("sms_body", finalSms);
                startActivity(intent);

            }
        }

    }

    public void getUserInfo () {

        try {
            OperationsDatabase DOP = new OperationsDatabase(this);
            Cursor CR = DOP.getUserInformation(DOP);
            CR.moveToFirst();


           GetCelfoneContacPerson = CR.getString(3);


        }
        catch (Exception ignored)
        {

        }

    }

    public void btn_voltar_onClick(View view) {
        super.onBackPressed();
        locationManager.removeUpdates(this);
         Firemen.next = true;
        PSP.next = true;
        GNR.next = true;
    }


    @Override
    public void onLocationChanged(Location location) {

        if(checkButtons[3] == false) {
            locationManager.removeUpdates(this);
            ed.setText("");
        }
        else{
            getLocations();
        }

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
}
