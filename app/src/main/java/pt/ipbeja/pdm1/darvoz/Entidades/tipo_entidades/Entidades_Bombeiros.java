package pt.ipbeja.pdm1.darvoz.Entidades.tipo_entidades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pt.ipbeja.pdm1.darvoz.R;

public class Entidades_Bombeiros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entidades__bombeiros);
    }

    public void btn_inc_florestal_onClick(View view) {
    }

    public void btn_inc_urbano_onClick(View view) {
    }

    public void btn_inund_onClick(View view) {
    }

    public void btn_colisao_onClick(View view) {
    }

    public void btn_atropelamento_onClick(View view) {
    }

    public void btn_capotamento_onClick(View view) {
    }

    public void btn_sem_chave_onClick(View view) {
    }

    public void btn_fuga_gas_onClick(View view) {
    }

    public void btn_toxico_onClick(View view) {
    }

    public void bt_voltar_onClick(View view) {

        Entidades_Bombeiros.super.onBackPressed();
    }

    public void btn_gps_onClick(View view) {
    }

    public void btn_onde_onClick(View view) {
    }
}
