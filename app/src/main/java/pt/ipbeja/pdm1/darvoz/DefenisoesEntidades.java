package pt.ipbeja.pdm1.darvoz;

import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import pt.ipbeja.pdm1.darvoz.Database.DatabaseOperacions;


public class DefenisoesEntidades extends AppCompatActivity {

    EditText CONTACT_FIREFIGHTERS, CONTACT_PSP, CONTACT_1GNR, CONTACT_2GNR,CONTACT_1INTERPRETER,CONTACT_2INTERPRETER;
    String contact_firifighters, contact_PSP, contact_1GNR, contact_2GNR, contact_1interpreter, contact_2interpreter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defenisoes_entidades);

        CONTACT_FIREFIGHTERS = (EditText) findViewById(R.id.editTextBombeiros);
        CONTACT_PSP = (EditText) findViewById(R.id.editTextPSP);
        CONTACT_1GNR = (EditText) findViewById(R.id.editText1GNR);
        CONTACT_2GNR = (EditText) findViewById(R.id.editText2GNR);
        CONTACT_1INTERPRETER = (EditText) findViewById(R.id.editText1Intérprete);
        CONTACT_2INTERPRETER = (EditText) findViewById(R.id.editText2Intérprete);


        getEntityInfo();
    }
    public void getEntityInfo () {

        try {
            DatabaseOperacions DOP = new DatabaseOperacions(this);
            Cursor CR = DOP.getEntityInformation(DOP);
            CR.moveToFirst();

            String GetContact_firifighters = CR.getString(0);
            String GetContact_PSP = CR.getString(1);
            String GetContact_1GNR = CR.getString(2);
            String GetContact_2GNR = CR.getString(3);
            String GetContact_1interpreter = CR.getString(4);
            String GetContact_2interpreter = CR.getString(5);

            CONTACT_FIREFIGHTERS.setText(GetContact_firifighters);
            CONTACT_PSP.setText(GetContact_PSP);
            CONTACT_1GNR.setText(GetContact_1GNR);
            CONTACT_2GNR.setText(GetContact_2GNR);
            CONTACT_1INTERPRETER.setText(GetContact_1interpreter);
            CONTACT_2INTERPRETER.setText(GetContact_2interpreter);
        }
        catch (Exception ignored)
        {}
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void btn_gravar_onclick(View view) {
        contact_firifighters = CONTACT_FIREFIGHTERS.getText().toString();
        contact_PSP = CONTACT_PSP.getText().toString();
        contact_1GNR = CONTACT_1GNR.getText().toString();
        contact_2GNR = CONTACT_2GNR.getText().toString();
        contact_1interpreter = CONTACT_1INTERPRETER.getText().toString();
        contact_2interpreter = CONTACT_2INTERPRETER.getText().toString();

        if (Objects.equals(contact_firifighters, ""))
        {
            Toast.makeText(this, "Contacto bombeiros vazio", Toast.LENGTH_SHORT).show();
        }
        else if (Objects.equals(contact_PSP, ""))
        {
            Toast.makeText(this, "Contacto PSP vazio", Toast.LENGTH_SHORT).show();
        }

        else if (Objects.equals(contact_1GNR, ""))
        {
            Toast.makeText(this, "Contacto 1GNR vazio", Toast.LENGTH_SHORT).show();
        }

        else if (Objects.equals(contact_2GNR, ""))
        {
            Toast.makeText(this, "Contacto 2GNR vazio", Toast.LENGTH_SHORT).show();
        }
        else if (Objects.equals(contact_1interpreter, ""))
        {
            Toast.makeText(this, "Contacto 1interpreter vazio", Toast.LENGTH_SHORT).show();
        }
        else if (Objects.equals(contact_2interpreter, ""))
        {
            Toast.makeText(this, "Contacto 2interpreter vazio", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(this, "Guardado com sucesso", Toast.LENGTH_SHORT).show();



            DatabaseOperacions DB = new DatabaseOperacions(this);
            DB.delete_inf_Entity(DB);
            DB.insertInfoEntity(DB, contact_firifighters, contact_PSP, contact_1GNR,
                    contact_2GNR,contact_1interpreter, contact_2interpreter);
        }
    }
}
