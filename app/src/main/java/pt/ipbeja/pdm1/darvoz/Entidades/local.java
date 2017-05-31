package pt.ipbeja.pdm1.darvoz.Entidades;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import pt.ipbeja.pdm1.darvoz.R;

public class local extends AppCompatActivity {
    int bottonsviewid[] = new int[]{
            R.id.btn_casa,
            R.id.btn_perto,
            R.id.btn_mapa,
            R.id.btn_gps};

    int checkButtonStatus;
    Boolean checkButtonLocation[] = new Boolean[]{false, false, false, false, false,
                                                  false, false, false, false, false,
                                                  false, false, false};

    Boolean checkButtons [] = new Boolean[]{false, false, false, false};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
    }

    public void changeButton(int posicionArray,int idImageButton,int drawableNp, int drawableP ) {

        ImageButton imgBtn = (ImageButton) findViewById(idImageButton);


        if (!checkButtons[posicionArray]) {

            imgBtn.setBackgroundResource(drawableP);
            checkButtons[posicionArray] = true;
        } else {

            imgBtn.setBackgroundResource(drawableNp);
            checkButtons[posicionArray] = false;

        }
    }


    public void changebuttonlocation(int buttonArraynumber,int imageviewReference , int imageReferenceN1, int imageReferenceN2)
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
                i = 13;
            }

        }//fim ciclo for*/

        Button imageButtonname = (Button) findViewById(imageviewReference);
        if(checkButtonStatus == 0){

            imageButtonname.setBackgroundResource(imageReferenceN1);
            checkButtonLocation[buttonArraynumber] = true;
        }
        if(checkButtonStatus == 1){

            imageButtonname.setBackgroundResource(imageReferenceN2);
            checkButtonLocation[buttonArraynumber] = false;
        }

        if(checkButtonStatus == 2){

            Toast.makeText(this, "Apenas deve estar selecionado um local", Toast.LENGTH_SHORT).show();

        }

    }
    public void btn_casa_onClick(View view) {
        changeButton( 0,bottonsviewid[0],R.drawable.btn_casa_b, R.drawable.btn_casa_p );
    }

    public void btn_perto_onClick(View view) {

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.layout_pertoDe);
        if(checkButtons[1]){
            mainLayout.setVisibility(LinearLayout.GONE);
        }
        else{
            mainLayout.setVisibility(LinearLayout.VISIBLE);
        }

        changeButton( 1,bottonsviewid[1],R.drawable.btn_perto_b, R.drawable.btn_perto_p );
    }

    public void btn_mapa_onClick(View view) {


        changeButton( 2,bottonsviewid[2],R.drawable.btn_mapa_b, R.drawable.btn_mapa_p );
    }

    public void btn_gps_onclick(View view) {
        changeButton( 3,bottonsviewid[3],R.drawable.btn_gps_b, R.drawable.bt_gps_p );
    }

    public void btn_pspOnClick(View view) {
        changebuttonlocation(0,R.id.btn_psp , R.drawable.btn_psp_p, R.drawable.btn_psp_b);

    }

    public void btn_bombeirosOnClick(View view) {
        changebuttonlocation(1,R.id.btn_bomb , R.drawable.btn_bomb_p, R.drawable.btn_bomb_b);
    }

    public void btn_teatroOnClick(View view) {
        changebuttonlocation(2,R.id.btn_teatro , R.drawable.btn_teatro_p, R.drawable.btn_teatro_b);
    }

    public void btn_cinemaOnClick(View view) {
        changebuttonlocation(3,R.id.btn_cine , R.drawable.btn_cinema_p, R.drawable.btn_cinema_b);

    }

    public void btn_comboiosOnClick(View view) {
        changebuttonlocation(4,R.id.btn_est_combo , R.drawable.btn_train_sta_p, R.drawable.btn_train_sta_b);

    }

    public void btn_rodoviariaOnClick(View view) {
        changebuttonlocation(5,R.id.btn_rodov , R.drawable.btn_bus_sta_p, R.drawable.btn_bus_sta_b);

    }

    public void btn_prisaoOnClick(View view) {
        changebuttonlocation(6,R.id.btn_prisao , R.drawable.btn_prisao_p, R.drawable.btn_prisao_b);

    }

    public void btn_casteloOnClick(View view) {
        changebuttonlocation(7,R.id.btn_castelo , R.drawable.btn_castelo_p, R.drawable.btn_castelo_b);

    }

    public void btn_DmanuelOnClick(View view) {
        changebuttonlocation(8,R.id.btn_dm , R.drawable.btn_dmi_p, R.drawable.btn_dmi_b);

    }

    public void btn_MarioBeiraoOnClick(View view) {
        changebuttonlocation(9,R.id.btn_mb , R.drawable.btn_mb_p, R.drawable.btn_mb_b);

    }

    public void btn_DiogoGouveiaOnClick(View view) {
        changebuttonlocation(10,R.id.btn_dg_b , R.drawable.btn_dg_p, R.drawable.btn_dg_b);

    }

    public void btn_ContineteOnClick(View view) {
        changebuttonlocation(11,R.id.btn_cont , R.drawable.btn_cont_p, R.drawable.btn_cont_b);

    }

    public void btn_ipBejaOnClick(View view) {
        changebuttonlocation(12,R.id.btn_ipbeja , R.drawable.btn_ipbeja_p, R.drawable.btn_ipbeja_b);

    }

    public void btn_enviar_onClick(View view) {
    }

    public void btn_voltar_onClick(View view) {
        super.onBackPressed();
    }



}
