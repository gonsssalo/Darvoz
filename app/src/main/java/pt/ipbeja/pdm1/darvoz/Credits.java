package pt.ipbeja.pdm1.darvoz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);

        getSupportActionBar().setTitle("Darvoz - Credits");
    }

    public void btn_back_onclick(View view) {
        super.onBackPressed();
    }
}
