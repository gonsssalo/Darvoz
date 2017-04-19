package pt.ipbeja.pdm1.darvoz.Database.tables;

import android.provider.BaseColumns;

/**
 * Created by Utilizador on 19/04/2017.
 */

public class tableEntityData {

    public tableEntityData(){

    }

    public  static abstract class TableInfo implements BaseColumns
    {


        public static final String TABLE_Entitys = "info_utilizador";


        public static final String CONTACT_FIREFIGHTERS = "contacto_bombeiros";
        public static final String CONTACT_PSP= "contacto PSP";
        public static final String CONTACT_1GNR = "contacto_1GNR";
        public static final String CONTACT_2GNR = "contacto_2GNR";
        public static final String CONTACT_1INTERPRETER = "contacto_1interprete";
        public static final String CONTACT_2INTERPRETER = "contacto_2interprete";

    }
}