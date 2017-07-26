package pt.ipbeja.pdm1.darvoz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import pt.ipbeja.pdm1.darvoz.Entities.Entidades;
import pt.ipbeja.pdm1.darvoz.Defenicoes.DefinitionsList;
import pt.ipbeja.pdm1.darvoz.Marking.Marking;

public class MainActivity extends AppCompatActivity {
    ImageButton imgBtn;
    int BottonsizeHeight;
    int BottonsizeWidth;
    int spacebethinbutons;
    android.widget.LinearLayout.LayoutParams lp;

    int mainbottonsviewid[] = new int[]{

            R.id.btnAgenda,
            R.id.btnDefenicoes,
            R.id.btncreditos,
            R.id.btnsair};

    int bottonViewDoubleSize =  R.id.btnEntidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        spacebethinbutons = displayMetrics.widthPixels /  15;
        BottonsizeWidth = displayMetrics.widthPixels /  3 -  spacebethinbutons;
        BottonsizeHeight = BottonsizeWidth;

        for(int i = 0; i < mainbottonsviewid.length; i++)
        {
            createBottons(mainbottonsviewid[i],1);
        }

        createBottons(bottonViewDoubleSize,2);*/
    }



    public void createBottons(int id, int multiplier){

        imgBtn = (ImageButton)findViewById(id);
        lp = new LinearLayout.LayoutParams(BottonsizeWidth * multiplier,BottonsizeHeight);
        imgBtn.setLayoutParams(lp);


    }

    public void btnEntidades_onClick(View view) {

        Intent inten = new Intent(MainActivity.this, Entidades.class);
        startActivity(inten);
    }

    public void btnMarcação_onClick(View view) {
        Intent in = new Intent(this, Marking.class);
        startActivity(in);
    }

    public void btnDefenições_onClick(View view) {

        Intent in = new Intent(this, DefinitionsList.class);
        startActivity(in);


    }

    public void btnCreditos_onClick(View view) {

        Intent in = new Intent(this, Credits.class);
        startActivity(in);
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
