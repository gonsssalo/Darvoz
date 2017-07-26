package pt.ipbeja.pdm1.darvoz.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import pt.ipbeja.pdm1.darvoz.Database.tables.tableUserData;
import pt.ipbeja.pdm1.darvoz.Database.tables.tableEntityData;

/**
 * Created by Utilizador on 17/04/2017.
 */

public class OperationsDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Database";
    public static int database_version = 17;

    public  String TableUser =
            "CREATE TABLE " + tableUserData.TableInfo.TABLE_USER + "("
                    + tableUserData.TableInfo.NAME_USER + " TEXT, "
                    + tableUserData.TableInfo.ADRESS_USER + " TEXT, "
                    + tableUserData.TableInfo.CONTACT_PERSON + " TEXT, "
                    + tableUserData.TableInfo.CELFONE_CONTACT_PERSON + " TEXT );";



    public  String TableEntity =
            "CREATE TABLE " + tableEntityData.TableInfo.TABLE_Entitys + "("
                    + tableEntityData.TableInfo.CONTACT_FIREFIGHTERS + " TEXT, "
                    + tableEntityData.TableInfo.CONTACT_PSP + " TEXT, "
                    + tableEntityData.TableInfo.CONTACT_1GNR + " TEXT, "
                    + tableEntityData.TableInfo.CONTACT_2GNR + " TEXT, "
                    + tableEntityData.TableInfo.CONTACT_1INTERPRETER + " TEXT, "
                    + tableEntityData.TableInfo.CONTACT_2INTERPRETER + " TEXT );";


    @Override
    public void onCreate(SQLiteDatabase sqldb) {

        sqldb.execSQL(TableEntity);
        sqldb.execSQL(TableUser);

    }
    public OperationsDatabase(Context context) {
        super(context, DATABASE_NAME, null, database_version);


    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableEntityData.TableInfo.TABLE_Entitys);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableUserData.TableInfo.TABLE_USER);
        // create new tables
        onCreate(sqLiteDatabase);

    }


    public void insertInfoUser(OperationsDatabase dop, String UserName, String UserAdress, String ContactPerson,
                               String CelfoneContactPerson )
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(tableUserData.TableInfo.NAME_USER, UserName);
        cv.put(tableUserData.TableInfo.ADRESS_USER, UserAdress);
        cv.put(tableUserData.TableInfo.CONTACT_PERSON, ContactPerson);
        cv.put(tableUserData.TableInfo.CELFONE_CONTACT_PERSON, CelfoneContactPerson);

       long k = SQ.insert(tableUserData.TableInfo.TABLE_USER, null, cv);

        Log.d("Database Operacions", "one raw inserted");
    }

    public void insertInfoEntity(OperationsDatabase dop, String contact_firifighters,
                                 String contact_PSP, String contact_1GNR, String contact_2GNR,
                                 String contact_1interpreter, String contact_2interpreter)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(tableEntityData.TableInfo.CONTACT_FIREFIGHTERS, contact_firifighters);
        cv.put(tableEntityData.TableInfo.CONTACT_PSP, contact_PSP);
        cv.put(tableEntityData.TableInfo.CONTACT_1GNR, contact_1GNR);
        cv.put(tableEntityData.TableInfo.CONTACT_2GNR, contact_2GNR);
        cv.put(tableEntityData.TableInfo.CONTACT_1INTERPRETER, contact_1interpreter);
        cv.put(tableEntityData.TableInfo.CONTACT_2INTERPRETER, contact_2interpreter);

        long k = SQ.insert(tableEntityData.TableInfo.TABLE_Entitys, null, cv);

        Log.d("Database Operacions", "one raw inserted");
    }


    public Cursor getUserInformation(OperationsDatabase dop)
    {

        SQLiteDatabase SQ = dop.getReadableDatabase();

        String[] coloums = {
                tableUserData.TableInfo.NAME_USER,
                tableUserData.TableInfo.ADRESS_USER,
                tableUserData.TableInfo.CONTACT_PERSON,
                tableUserData.TableInfo.CELFONE_CONTACT_PERSON};

        Cursor CR = SQ.query(tableUserData.TableInfo.TABLE_USER,coloums,null, null, null, null, null);
        return CR;
    }

    public Cursor getEntityInformation(OperationsDatabase dop)
    {

        SQLiteDatabase SQ = dop.getReadableDatabase();

        String[] coloums = {
                tableEntityData.TableInfo.CONTACT_FIREFIGHTERS,
                tableEntityData.TableInfo.CONTACT_PSP,
                tableEntityData.TableInfo.CONTACT_1GNR,
                tableEntityData.TableInfo.CONTACT_2GNR,
                tableEntityData.TableInfo.CONTACT_1INTERPRETER,
                tableEntityData.TableInfo.CONTACT_2INTERPRETER};

        Cursor CR = SQ.query(tableEntityData.TableInfo.TABLE_Entitys,coloums,null, null, null, null, null);
        return CR;
    }
    public Cursor getUserAdress(OperationsDatabase dop)
    {

        SQLiteDatabase SQ = dop.getReadableDatabase();

        String[] coloums = {tableUserData.TableInfo.ADRESS_USER};

        Cursor CR = SQ.query(tableUserData.TableInfo.TABLE_USER,coloums,null, null, null, null, null);
        return CR;
    }
    public Cursor getEntityInterpreters(OperationsDatabase dop)
    {

        SQLiteDatabase SQ = dop.getReadableDatabase();

        String[] coloums = {
                tableEntityData.TableInfo.CONTACT_1INTERPRETER,
                tableEntityData.TableInfo.CONTACT_2INTERPRETER};

        Cursor CR = SQ.query(tableEntityData.TableInfo.TABLE_Entitys,coloums,null, null, null, null, null);
        return CR;
    }

    public void delete_inf_user (OperationsDatabase dop){

        SQLiteDatabase SQ = dop.getWritableDatabase();
        SQ.execSQL("delete from "+ tableUserData.TableInfo.TABLE_USER);

    }

    public void delete_inf_Entity (OperationsDatabase dop){

        SQLiteDatabase SQ = dop.getWritableDatabase();
        SQ.execSQL("delete from "+ tableEntityData.TableInfo.TABLE_Entitys);

    }
}
