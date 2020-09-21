package com.example.codepassword;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Buy extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public static final String CREATE_Buy="create table buy ("+
            "id integer primary key autoincrement, "
            +"buyer text, "
            +"shouname text,"
            +"gg text, "
            +"num text)";
    private Context mContext;
    public Buy(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_Buy);
        Toast.makeText(mContext,"succeed",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS buy");
        onCreate(db);
    }
}
