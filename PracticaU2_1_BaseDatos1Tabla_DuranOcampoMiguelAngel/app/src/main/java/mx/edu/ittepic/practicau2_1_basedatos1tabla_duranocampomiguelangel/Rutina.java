package mx.edu.ittepic.practicau2_1_basedatos1tabla_duranocampomiguelangel;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Rutina {
    BaseDatos base;
    int id,calorias;
    String dias,descripcion;
    public Rutina(Activity activity){
        base=new BaseDatos(activity,"gimnasio",null,1);
    }
    public Rutina(int i,String dias,String descripcion,int calorias){
        id=i;
        this.dias=dias;
        this.descripcion=descripcion;
        this.calorias=calorias;
    }
    public boolean insertar(Rutina rutina){
        try{
            SQLiteDatabase db=base.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put("DIAS",rutina.dias);
            values.put("DESCRIPCION",rutina.descripcion);
            values.put("CALORIASQUEMADAS",rutina.calorias);
            long resul=db.insert("RUTINA","id",values);
            if (resul<0)return false;
            return true;
        }catch (SQLiteException e){
            e.printStackTrace();
            return false;
        }
    }
    public Rutina[] consultar(){
        try{
            SQLiteDatabase db=base.getReadableDatabase();
            Cursor c=db.rawQuery("SELECT * FROM RUTINA",null);
            if (c.moveToFirst()) {
                Rutina[] rutinas = new Rutina[c.getCount()];
                int pos = 0;
                do {
                    rutinas[pos] = new Rutina(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3));
                    pos++;
                } while (c.moveToNext());
                return rutinas;
            }
        }catch(SQLiteException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public boolean actualizar(Rutina dato){
        try{
            SQLiteDatabase db=base.getWritableDatabase();
            ContentValues valores=new ContentValues();
            valores.put("DIAS",dato.dias);
            valores.put("DESCRIPCION",dato.descripcion);
            valores.put("CALORIASQUEMADAS",dato.calorias);
            String []campo={dato.id +""};
            long resultado=db.update("RUTINA",valores,"ID=?",campo);
            if (resultado<0)return false;
            return true;
        }catch(SQLiteException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminar(Rutina dato){
        try{
            SQLiteDatabase db=base.getWritableDatabase();
            String[] campo={dato.id+""};
            long resultado=db.delete("RUTINA","ID=?",campo);
            if (resultado<0)return false;
            return true;
        }catch(SQLiteException e){
            e.printStackTrace();
            return false;
        }
    }
}
