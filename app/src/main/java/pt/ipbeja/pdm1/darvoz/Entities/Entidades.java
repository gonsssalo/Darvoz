package pt.ipbeja.pdm1.darvoz.Entities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pt.ipbeja.pdm1.darvoz.Entities.tipo_entidades.Firemen;
import pt.ipbeja.pdm1.darvoz.Entities.tipo_entidades.GNR;
import pt.ipbeja.pdm1.darvoz.Entities.tipo_entidades.PSP;
import pt.ipbeja.pdm1.darvoz.R;

public class Entidades extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entidades);

        getSupportActionBar().setTitle("Darvoz - Entidades");
    }


    public void btn_GNR_onClick(View view) {

        Intent intent = new Intent(Entidades.this, GNR.class);
        startActivity(intent);


    }

    public void btn_PSP_onClick(View view) {
        Intent intent = new Intent(Entidades.this, PSP.class);
        startActivity(intent);
    }

    public void btn_Bombeiros_onClick(View view) {

        Intent intent = new Intent(Entidades.this, Firemen.class);
        startActivity(intent);
    }



    public void btn_Voltar_onClick(View view) {

        Entidades.super.onBackPressed();


    }



}
