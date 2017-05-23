package pt.ipbeja.pdm1.darvoz.Entidades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import pt.ipbeja.pdm1.darvoz.R;

public class local extends AppCompatActivity {
    int bottonsviewid[] = new int[]{
            R.id.btn_casa,
            R.id.btn_perto,
            R.id.btn_mapa,
            R.id.btn_gps};

    Boolean checkButtons [] = new Boolean[]{false, false, false, false};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
    }

    public void changeButton(int posicionArray,int idImageButton,int drawableNp, int drawableP ) {

        ImageButton imgBtn = (ImageButton) findViewById(idImageButton);


        if (checkButtons[posicionArray] == false) {

            imgBtn.setBackgroundResource(drawableP);
            checkButtons[posicionArray] = true;
        } else {

            imgBtn.setBackgroundResource(drawableNp);
            checkButtons[posicionArray] = false;

        }
    }
    public void btn_casa_onClick(View view) {
        changeButton( 0,bottonsviewid[0],R.drawable.btn_casa_b, R.drawable.btn_casa_p );
    }

    public void btn_perto_onClick(View view) {

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.layout_pertoDe);
        if(checkButtons[1] == true){
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
        changeButton( 3,bottonsviewid[3],R.drawable.bt_gps_p, R.drawable.btn_gps_b );
    }
}
