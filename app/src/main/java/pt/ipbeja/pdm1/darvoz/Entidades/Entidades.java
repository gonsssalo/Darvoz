package pt.ipbeja.pdm1.darvoz.Entidades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pt.ipbeja.pdm1.darvoz.Entidades.tipo_entidades.Entidades_Bombeiros;
import pt.ipbeja.pdm1.darvoz.Entidades.tipo_entidades.Entidades_GNR;
import pt.ipbeja.pdm1.darvoz.Entidades.tipo_entidades.Entidades_PSP;
import pt.ipbeja.pdm1.darvoz.R;

public class Entidades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entidades);
    }


    public void btn_GNR_onClick(View view) {

        Intent intent = new Intent(Entidades.this, Entidades_GNR.class);
        startActivity(intent);
    }

    public void btn_PSP_onClick(View view) {
        Intent intent = new Intent(Entidades.this, Entidades_PSP.class);
        startActivity(intent);
    }

    public void btn_Bombeiros_onClick(View view) {

        Intent intent = new Intent(Entidades.this, Entidades_Bombeiros.class);
        startActivity(intent);
    }

    public void btn_GPS_onClick(View view) {


    }

    public void btn_Voltar_onClick(View view) {

        Entidades.super.onBackPressed();
    }

}
