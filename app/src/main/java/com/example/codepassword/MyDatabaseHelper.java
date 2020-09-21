package com.example.codepassword;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public static final String CREATE_BOOK="create table book ("+
            "id integer primary key autoincrement, "
            +"user text, "
            +"password text, "
            +"buyer text, "
            +"num text,"
            +"loacd text, "
            +"name text, "
            +"money text, "
            +"photo text, "
            +"time text)";
    private Context mContext;
    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mContext,"succeed",Toast.LENGTH_SHORT).show();
    }

    public void add(String name,String password){
        db.execSQL("INSERT INTO user(name,password) VALUES(?,?)",new Object[]{name,password});
    }

    public void delete(String use,String name,String money,String photo,String time){
        db.execSQL("DELETE FROM user WHERE user = AND name = AND money = AND photo = AND time ="+use+name+money+photo+time);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
