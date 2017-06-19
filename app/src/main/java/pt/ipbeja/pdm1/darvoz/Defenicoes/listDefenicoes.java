package pt.ipbeja.pdm1.darvoz.Defenicoes;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import pt.ipbeja.pdm1.darvoz.R;
import pt.ipbeja.pdm1.darvoz.Defenicoes.defenicoes_tipo.DefenicoesEntidades;
import pt.ipbeja.pdm1.darvoz.Defenicoes.defenicoes_tipo.DefenicoesUtilizador;

public class listDefenicoes extends ListActivity {

    String[] D_List = {"Utilizador", "Entidades"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_list_defenisoes,D_List));

        final ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (position == 0)
                {
                    Intent intent = new Intent(listDefenicoes.this, DefenicoesUtilizador.class);
                    startActivity(intent);
                }
                else{

                    Intent intent = new Intent(listDefenicoes.this, DefenicoesEntidades.class);
                    startActivity(intent);
                }

            }
        });

    }


}
