package com.example.codepassword;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Mydata extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public static final String CREATE_Geren="create table geren ("+
            "id integer primary key autoincrement, "
            +"user text, "
            +"username text, "
            +"lianxi text, "
            +"dizhi text)";
    private Context mContext;
    public Mydata(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_Geren);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS geren");
        onCreate(db);
    }
}
