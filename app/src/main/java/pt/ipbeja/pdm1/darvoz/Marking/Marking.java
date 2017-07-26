package pt.ipbeja.pdm1.darvoz.Marking;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Objects;

import pt.ipbeja.pdm1.darvoz.Database.OperationsDatabase;
import pt.ipbeja.pdm1.darvoz.R;



public class Marking extends AppCompatActivity {
    DatePickerDialog datePickerDialog;
    TimePickerDialog mTimePicker;

    TextView txvw_Entidade;
    TextView txvw_Data;
    TextView txvw_Hora;
    String GetContact_1interpreter;
    String GetContact_2interpreter;

    int checkButtonStatus;
    Boolean checkButton [] = new Boolean[]{false, false, false, false, false};
    int bottonnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcasao);

        getSupportActionBar().setTitle("Darvoz - Marcação");

        txvw_Data = (TextView) findViewById(R.id.textViewData);
        txvw_Hora = (TextView) findViewById(R.id.textViewHora);
        txvw_Entidade = (TextView) findViewById(R.id.textViewEntidade);

    }

    public void btn_select_data_onClick(View view) {

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datePickerDialog = new DatePickerDialog(Marking.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        txvw_Data.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.setTitle("Selecione a Data");
        datePickerDialog.show();

    }

    public void btn_select_hora_onClick(View view) {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        mTimePicker = new TimePickerDialog(Marking.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                txvw_Hora.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Selecione a Hora");
        mTimePicker.show();

    }
    public void changebutton(int buttonArraynumber,int imageviewReference , int imageReferenceN1, int imageReferenceN2, String Entity)
    {
        for(int i = 0; i < checkButton.length; i++){

            if (!checkButton[i] && !checkButton[buttonArraynumber])
            {
                checkButtonStatus = 0;

            }

            if (!checkButton[i] && checkButton[buttonArraynumber])
            {
                checkButtonStatus = 1;

            }

            if (checkButton[i] && !checkButton[buttonArraynumber])
            {
                bottonnumber = i;
                checkButtonStatus = 2;
                i = checkButton.length;
            }

        }//fim ciclo for*/

        ImageButton imageButtonname = (ImageButton) findViewById(imageviewReference);
        if(checkButtonStatus == 0){

            imageButtonname.setBackgroundResource(imageReferenceN1);
            txvw_Entidade.setText(Entity);
            checkButton[buttonArraynumber] = true;
        }
        if(checkButtonStatus == 1){

            imageButtonname.setBackgroundResource(imageReferenceN2);
            txvw_Entidade.setText("Sem Entidade defenida");
            checkButton[buttonArraynumber] = false;
        }

        if(checkButtonStatus == 2){

            Toast.makeText(this, "Apenas deve estar selecionado uma entidade", Toast.LENGTH_SHORT).show();

        }

    }

    public void btn_ss_onClick(View view) {

        changebutton(0,R.id.imbtn_seguransa_social , R.drawable.btn_seguransa_social_p, R.drawable.btn_seguransa_social_b, "Segurança Social");

    }

    public void btn_iefp_onClick(View view) {

        changebutton(1,R.id.imbtn_iefp , R.drawable.btn_iefp_p, R.drawable.btn_iefp_b,"Instituto do Emprego e Formação Profissional");


    }

    public void btn_at_onClick(View view) {

        changebutton(2,R.id.imgbtn_at , R.drawable.btn_at_p, R.drawable.bt_at_b, "Autoridade Tributaria e Adoaneira");

    }

    public void btn_cs1_onClick(View view) {

        changebutton(3,R.id.imgbtn_cs1 , R.drawable.btn_cs1_p, R.drawable.bt_cs1_b, "Centro de Saúde 1");

    }

    public void btn_cs2_onClick(View view) {

        changebutton(4,R.id.imgbtn_cs2 , R.drawable.btn_cs2_p, R.drawable.bt_cs2_b, "Centro de Saúde 2");

    }



    public void btn_voltar_onClick(View view) {

        Marking.super.onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void btn_enviar_onClick(View view) {

        Boolean smsSend;
        String data = txvw_Data.getText().toString();
        String hora = txvw_Hora.getText().toString();
        String entidade = txvw_Entidade.getText().toString();

        if (Objects.equals(entidade, "Sem Entidade defenida")){

            Toast.makeText(this, "Selecione uma entidade", Toast.LENGTH_SHORT).show();
            smsSend = false;

        }
       else if (Objects.equals(data, "Sem Data defenida")){

            Toast.makeText(this, "Selecione uma data", Toast.LENGTH_SHORT).show();
            smsSend = false;

        }
       else if (Objects.equals(hora, "Sem Hora defenida")){

            Toast.makeText(this, "Selecione uma hora", Toast.LENGTH_SHORT).show();
            smsSend = false;

        }
        else {

            smsSend = true;

        }
       if(smsSend){

            String sms = "pede marcação para atendimento na " + entidade + " para o dia " + data + " às " + hora + "H";
           getEntityInterpreters ();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + GetContact_1interpreter + ";" + GetContact_2interpreter));
            intent.putExtra("sms_body", sms);
            startActivity(intent);

        }


    }

    public void getEntityInterpreters () {

        try {
            OperationsDatabase DOP = new OperationsDatabase(this);
            Cursor CR = DOP.getEntityInformation(DOP);
            CR.moveToFirst();

            GetContact_1interpreter = CR.getString(0);
            GetContact_2interpreter = CR.getString(1);


        }
        catch (Exception ignored)
        {}
    }
}
