package com.example.codepassword;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private EditText name,money,photo,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        name=findViewById(R.id.sp_name);
        money=findViewById(R.id.sp_money);
        photo=findViewById(R.id.sp_photo);
        time=findViewById(R.id.sp_time);

        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,1);
        Button create=findViewById(R.id.ir_Button);
        Button button=findViewById(R.id.sp_Button);
        Button Dl=findViewById(R.id.dl_Button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String rname=name.getText().toString();
                final String rmoney=money.getText().toString();
                final String rphoto=photo.getText().toString();
                final String rtime=time.getText().toString();
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("name",rname);
                values.put("money",rmoney);
                values.put("photo",rphoto);
                values.put("time",rtime);
                db.insert("Book",null,values);
                Toast.makeText(MainActivity2.this,"商品已发布",Toast.LENGTH_SHORT).show();
                Intent inten=new Intent(MainActivity2.this,SecondActivity.class);
                startActivity(inten);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                Cursor cursor=db.query("BOOK",null,null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String money=cursor.getString(cursor.getColumnIndex("money"));
                        String photo=cursor.getString(cursor.getColumnIndex("photo"));
                        String time=cursor.getString(cursor.getColumnIndex("time"));
                    }while (cursor.moveToNext());
                }
                cursor.close();
                Intent inten=new Intent(MainActivity2.this,SecondActivity.class);
                startActivity(inten);
            }
        });
        Dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
               // db.delete("Book","pages>?",new String[]{"500"});
            }
        });
    }
}