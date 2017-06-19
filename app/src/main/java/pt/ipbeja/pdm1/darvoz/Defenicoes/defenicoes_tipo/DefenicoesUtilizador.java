package pt.ipbeja.pdm1.darvoz.Defenicoes.defenicoes_tipo;

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
import pt.ipbeja.pdm1.darvoz.R;

public class DefenicoesUtilizador extends AppCompatActivity {

    EditText USER_NAME, USER_ADRESS, CONTACT_PERSON, CELFONE_CONTACT_PERSON;
    String userName, userAdress, contactPerson, celfoneContacPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defenisoes_utilizador);

        USER_NAME = (EditText) findViewById(R.id.UserNameEdictText);
        USER_ADRESS = (EditText) findViewById(R.id.UserAdressEdictText);
        CONTACT_PERSON = (EditText) findViewById(R.id.ContactPersonEdictText);
        CELFONE_CONTACT_PERSON = (EditText) findViewById(R.id.CelfoneContactPersonEdictText);

        getUserInfo();

    }

    public void getUserInfo () {

        try {
            DatabaseOperacions DOP = new DatabaseOperacions(this);
            Cursor CR = DOP.getUserInformation(DOP);
            CR.moveToFirst();

            String GetName = CR.getString(0);
            String GetUserAdress = CR.getString(1);
            String GetCocntactPerson = CR.getString(2);
            String GetCelfoneContacPerson = CR.getString(3);

            USER_NAME.setText(GetName);
            USER_ADRESS.setText(GetUserAdress);
            CONTACT_PERSON.setText(GetCocntactPerson);
            CELFONE_CONTACT_PERSON.setText(GetCelfoneContacPerson);
        }
        catch (Exception ignored)
        {

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void btn_gravar_onclick(View view) {
        userName = USER_NAME.getText().toString();
        userAdress = USER_ADRESS.getText().toString();
        contactPerson = CONTACT_PERSON.getText().toString();
        celfoneContacPerson = CELFONE_CONTACT_PERSON.getText().toString();

        if (Objects.equals(userName, ""))
        {
            Toast.makeText(this, "User Name Empty", Toast.LENGTH_SHORT).show();
        }
        else if (Objects.equals(userAdress, ""))
        {
            Toast.makeText(this, "User Adress Empty", Toast.LENGTH_SHORT).show();
        }

        else if (Objects.equals(contactPerson, ""))
        {
            Toast.makeText(this, "Contact Person Empty", Toast.LENGTH_SHORT).show();
        }

        else if (Objects.equals(celfoneContacPerson, ""))
        {
            Toast.makeText(this, "Celfone Contact Person Empty", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(this, "Guardado com sucesso", Toast.LENGTH_SHORT).show();



            DatabaseOperacions DB = new DatabaseOperacions(this);
            DB.delete_inf_user(DB);
            DB.insertInfoUser(DB, userName, userAdress, contactPerson, celfoneContacPerson);

            DefenicoesUtilizador.super.onBackPressed();
        }
    }


}
