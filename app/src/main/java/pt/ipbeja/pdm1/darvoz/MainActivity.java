package pt.ipbeja.pdm1.darvoz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pt.ipbeja.pdm1.darvoz.Entidades.Entidades;
import pt.ipbeja.pdm1.darvoz.Defenisoes.listDefenisoes;
import pt.ipbeja.pdm1.darvoz.Marcasao.Marcasao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnEntidades_onClick(View view) {

        Intent inten = new Intent(MainActivity.this, Entidades.class);
        startActivity(inten);
    }

    public void btnMarcação_onClick(View view) {
        Intent in = new Intent(this, Marcasao.class);
        startActivity(in);
    }

    public void btnDefenições_onClick(View view) {

        Intent in = new Intent(this, listDefenisoes.class);
        startActivity(in);


    }

    public void btnCreditos_onClick(View view) {
    }

    public void btnSair_onClick(View view) {

        new AlertDialog.Builder(this)
                .setMessage("Deseja sair da aplicação?")
                .setCancelable(false)
                .setTitle("Sair da aplicação")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Não", null)
                .show();

    }
}
