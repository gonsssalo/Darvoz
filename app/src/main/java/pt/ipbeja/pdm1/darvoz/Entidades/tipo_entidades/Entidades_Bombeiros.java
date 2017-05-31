package pt.ipbeja.pdm1.darvoz.Entidades.tipo_entidades;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import pt.ipbeja.pdm1.darvoz.Entidades.Entidades;
import pt.ipbeja.pdm1.darvoz.Entidades.local;
import pt.ipbeja.pdm1.darvoz.R;

public class Entidades_Bombeiros extends AppCompatActivity {
    ImageButton imgBtn;
    int Bottonsize;
    int spacebethinbutons;
    android.widget.LinearLayout.LayoutParams lp;
    Boolean checkButtons [] = new Boolean[]{false, false, false, false, false, false, false, false, false};
    int bottonsviewid[] = new int[]{
            R.id.btn_inc_florestal,
            R.id.btn_inc_urbano,
            R.id.btn_inund,
            R.id.btn_colisao,
            R.id.btn_atropelamento,
            R.id.btn_capotamento,
            R.id.btn_sem_chave,
            R.id.btn_fuga_gas,
            R.id.btn_toxico};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entidades__bombeiros);


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

    public void btn_inc_florestal_onClick(View view) {

        changeButton( 0,bottonsviewid[0],R.drawable.btn_inc_florestal_b, R.drawable.btn_inc_florestal_p );

    }

    public void btn_inc_urbano_onClick(View view) {

        changeButton( 1,bottonsviewid[1],R.drawable.btn_inc_urbano_b, R.drawable.btn_inc_urbano_p );
    }

    public void btn_inund_onClick(View view) {

        changeButton( 2,bottonsviewid[2],R.drawable.btn_inund_b, R.drawable.btn_inundasao_p );
    }

    public void btn_colisao_onClick(View view) {

        changeButton( 3,bottonsviewid[3],R.drawable.btn_colisao_b, R.drawable.btn_colisao_p );
    }

    public void btn_atropelamento_onClick(View view) {

        changeButton( 4,bottonsviewid[4],R.drawable.btn_atropelamento_b, R.drawable.btn_atropelamento_p );
    }

    public void btn_capotamento_onClick(View view) {

        changeButton( 5,bottonsviewid[5],R.drawable.btn_capotamento_b, R.drawable.btn_capotamento_p );
    }

    public void btn_sem_chave_onClick(View view) {

        changeButton( 6,bottonsviewid[6],R.drawable.btn_sem_chave_b, R.drawable.btn_sem_chave_p );
    }

    public void btn_fuga_gas_onClick(View view) {

        changeButton( 7,bottonsviewid[7],R.drawable.btn_fuga_gas_b, R.drawable.btn_fuga_gas_p );
    }

    public void btn_toxico_onClick(View view) {

        changeButton( 8,bottonsviewid[8],R.drawable.btn_toxico_b, R.drawable.btn_toxico_p );
    }

    public void bt_voltar_onClick(View view) {

        Entidades_Bombeiros.super.onBackPressed();
    }

    public void btn_gps_onClick(View view) {
    }

    public void btn_onde_onClick(View view) {

       /* for(int i = 0; i < checkButtons.length ; i++){



            if (checkButtons[i])
            {*/
                Intent intent = new Intent(Entidades_Bombeiros.this, local.class);
                startActivity(intent);
           /* }

            if(!checkButtons[checkButtons.length-1])
            {

                if(i == checkButtons.length -1){
                    Toast.makeText(this, "selecione", Toast.LENGTH_SHORT).show();
                }
                else{

                }

            }


            }//ciclo for*/



    }
}
