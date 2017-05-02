package pt.ipbeja.pdm1.darvoz.Marcasao;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import pt.ipbeja.pdm1.darvoz.R;



public class Marcasao extends AppCompatActivity {
    DatePickerDialog datePickerDialog;
    TimePickerDialog mTimePicker;

    TextView txvw_Entidade;
    TextView txvw_Data;
    TextView txvw_Hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcasao);

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
        datePickerDialog = new DatePickerDialog(Marcasao.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        txvw_Data.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();

    }

    public void btn_ss_onClick(View view) {
    }

    public void btn_iefp_onClick(View view) {
    }

    public void btn_at_onClick(View view) {
    }

    public void btn_cs1_onClick(View view) {
    }

    public void btn_cs2_onClick(View view) {
    }

    public void btn_select_hora_onClick(View view) {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        mTimePicker = new TimePickerDialog(Marcasao.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                txvw_Hora.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }

    public void btn_voltar_onClick(View view) {

        Marcasao.super.onBackPressed();
    }

    public void btn_enviar_onClick(View view) {
    }
}
