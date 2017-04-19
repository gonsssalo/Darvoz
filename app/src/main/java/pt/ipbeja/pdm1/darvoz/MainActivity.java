package pt.ipbeja.pdm1.darvoz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
    }

    public void btnDefenições_onClick(View view) {

        Intent in = new Intent(this, listDefenisoes.class);
        startActivity(in);


    }

    public void btnCreditos_onClick(View view) {
    }

    public void btnSair_onClick(View view) {
    }
}
