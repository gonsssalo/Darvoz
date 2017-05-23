package pt.ipbeja.pdm1.darvoz.Entidades.tipo_entidades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import pt.ipbeja.pdm1.darvoz.Entidades.local;
import pt.ipbeja.pdm1.darvoz.R;

public class Entidades_PSP extends AppCompatActivity {

    ImageButton imgBtn;
    int Bottonsize;
    int spacebethinbutons;
    android.widget.LinearLayout.LayoutParams lp;
    int bottonsviewid[] = new int[]{
            R.id.btn_colisao,
            R.id.btn_atropelamento,
            R.id.btn_capotamento,
            R.id.btn_assalto,
            R.id.btn_agressao,
            R.id.btn_violencia_domestica,
            R.id.btn_violasao,
            R.id.btn_sequestro,
            R.id.btn_burla,
            R.id.btn_f_residencia,
            R.id.btn_f_comercial,
            R.id.btn_f_automovel};


    Boolean checkButtons [] = new Boolean[]
            {false, false, false,
                    false, false, false,
                    false, false, false,
                    false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entidades__psp);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        spacebethinbutons = displayMetrics.widthPixels /  20;
        Bottonsize = displayMetrics.widthPixels /  3 -  spacebethinbutons;
        for(int i = 0; i < bottonsviewid.length; i++)
        {
            createBottons(bottonsviewid[i]);
        }
    }


    public void createBottons(int id){

        imgBtn = (ImageButton)findViewById(id);
        lp = new LinearLayout.LayoutParams(Bottonsize,Bottonsize);
        imgBtn.setLayoutParams(lp);


    }
    public void changeButton(int posicionArray,int idImageButton,int drawableNp, int drawableP ){

        ImageButton imgBtn = (ImageButton)findViewById(idImageButton);
        if(checkButtons[posicionArray] == false){

            imgBtn.setBackgroundResource(drawableP);
            checkButtons[posicionArray] = true;
        }

        else{

            imgBtn.setBackgroundResource(drawableNp);
            checkButtons[posicionArray] = false;

        }

    }

    public void btn_onde_onClick(View view) {

        Intent intent = new Intent(Entidades_PSP.this, local.class);
        startActivity(intent);

    }

    public void btn_gps_onClick(View view) {
    }

    public void bt_voltar_onClick(View view) {

        Entidades_PSP.super.onBackPressed();
    }

    public void btn_colisao_onClick(View view) {

        changeButton(0,bottonsviewid[0],R.drawable.btn_colisao_b, R.drawable.btn_colisao_p );

    }

    public void btn_atropelamento_onClick(View view) {

        changeButton(1,bottonsviewid[1],R.drawable.btn_atropelamento_b, R.drawable.btn_atropelamento_p );

    }

    public void btn_capotamento_onClick(View view) {

        changeButton(2,bottonsviewid[2],R.drawable.btn_capotamento_b, R.drawable.btn_capotamento_p );
    }

    public void btn_assalto_onClick(View view) {

        changeButton(3,bottonsviewid[3],R.drawable.btn_assalto_b, R.drawable.btn_assalto_p );

    }

    public void bt_agressao_onClick(View view) {

        changeButton(4,bottonsviewid[4],R.drawable.bt_agressao_b, R.drawable.btn_agressao_p );

    }

    public void btn_violencia_domestica_onClick(View view) {

        changeButton(5,bottonsviewid[5],R.drawable.btn_violencia_domestica_b, R.drawable.btn_violencia_domestica_p );
    }

    public void btn_violasao_onClick(View view) {

        changeButton(6,bottonsviewid[6],R.drawable.btn_violasao_b, R.drawable.btn_violasao_p );
    }

    public void btn_sequestro_onClick(View view) {

        changeButton(7,bottonsviewid[7],R.drawable.btn_sequestro_b, R.drawable.btn_sequestro_p );
    }

    public void btn_burla_onClick(View view) {

        changeButton(8,bottonsviewid[8],R.drawable.btn_burla_b, R.drawable.btn_burla_p );
    }

    public void btn_f_residencia_onClick(View view) {

        changeButton(9,bottonsviewid[9],R.drawable.btn_f_residencia_b, R.drawable.btn_furto_resi_p );
    }

    public void btn_f_comercial_onClick(View view) {

        changeButton(10,bottonsviewid[10],R.drawable.bt_f_comercial_b, R.drawable.bt_furto_comercial_p );
    }

    public void bnt_f_automovel_onClick(View view) {

        changeButton(11,bottonsviewid[11],R.drawable.bt_f_automovel_b, R.drawable.btn_furto_automovel_p );
    }
}
