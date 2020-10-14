package com.entregasapp.rapidex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="cadastrase.db";
    public static final String TABLE_NAME ="cadastrarusuario";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="usuario";
    public static final String COL_3 ="senha";

    public DatabaseHelper(Context context) {
        super (context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE cadastrarusuario (ID INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT, senha TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    public long addUser (String user, String senha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("usuario", user);
        contentValues.put("senha", senha);
        long res = db.insert("cadastrarusuario", null,contentValues);
        db.close();
        return res;


    }

    public boolean checkUser (String usuario, String senha){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { usuario, senha };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount ();
        cursor.close();
        db.close();

        if (count>0)
            return true;
        else
            return false;
    }
}
