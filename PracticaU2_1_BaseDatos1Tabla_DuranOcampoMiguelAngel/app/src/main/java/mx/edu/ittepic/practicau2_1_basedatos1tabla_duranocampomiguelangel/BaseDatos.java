package mx.edu.ittepic.practicau2_1_basedatos1tabla_duranocampomiguelangel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {
    public BaseDatos(Context context, String name,SQLiteDatabase.CursorFactory cursor,int version){
        super(context,name,cursor,version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE RUTINA (ID INTEGER PRIMARY KEY AUTOINCREMENT,DIAS VARCHAR(200),DESCRIPCION VARCHAR(500), CALORIASQUEMADAS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
