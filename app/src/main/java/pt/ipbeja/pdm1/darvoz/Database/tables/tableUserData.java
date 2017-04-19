package pt.ipbeja.pdm1.darvoz.Database.tables;

import android.provider.BaseColumns;

/**
 * Created by Utilizador on 17/04/2017.
 */

public class tableUserData {

    public tableUserData(){

    }

    public  static abstract class TableInfo implements BaseColumns
    {


        public static final String TABLE_USER = "info_utilizador";

        public static final String NAME_USER = "Nome_utilizador";
        public static final String ADRESS_USER= "Morada_utilizador";
        public static final String CONTACT_PERSON = "Pessoa_de_contacto";
        public static final String CELFONE_CONTACT_PERSON = "Telefone_pessoa_de_contacto";
    }
}
