package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String Database = "Client Information";
    public static final String Table= "Client";
    public static final String Column="SNO";
    public static final String Column1="LEAD";
    public static final String Column2= "NAME";
    public static final String Column3="COMPANY_NAME";
    public static final String Column4="MOBILE_NO";
    public static final String Column5="ADDRESS";

    private static final String CREATE_TABLE = " CREATE TABLE "
            + Table + "(" + Column + " INTEGER PRIMARY KEY AUTOINCREMENT," + Column1 +
            " VARCHAR," + Column2 + " VARCHAR," + Column3 + " VARCHAR,"
            + Column4 + " INTEGER," + Column5 + " VARCHAR "+")";


    public DatabaseHelper(Context context) {
        super(context, Database, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL("CREATE TRIGGER If NOT EXISTS increment_lead_no AFTER INSERT ON Client " +
                "BEGIN UPDATE Client SET LEAD = 'Lead '||new.SNO WHERE SNO =  new.SNO; " +
                "END");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table);
        onCreate(sqLiteDatabase);
    }

    public boolean insertdata(String Name, String Company, String Postaladdress, String Mobile)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Column2,Name);
        contentValues.put(Column3,Company);
        contentValues.put(Column4,Mobile);
        contentValues.put(Column5,Postaladdress);
        long result =sqLiteDatabase.insert(Table,null,contentValues);
        if(result ==  -1)
            return false;
        else
            return true;
    }

    public Cursor getdata()
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor result=sqLiteDatabase.rawQuery("SELECT * FROM "+Table,null);
        return result;
    }

    public boolean update(String Lead,String Name, String Company, String Mobile, String Postaladdress)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Column1,Lead);
        contentValues.put(Column2,Name);
        contentValues.put(Column3,Company);
        contentValues.put(Column4,Mobile);
        contentValues.put(Column5,Postaladdress);
        sqLiteDatabase.update(Table,contentValues,"LEAD = ?",new String[] {Lead});
     return true;
    }
}
