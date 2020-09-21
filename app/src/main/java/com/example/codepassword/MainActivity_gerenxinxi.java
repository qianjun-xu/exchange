package com.example.codepassword;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_gerenxinxi extends AppCompatActivity implements View.OnClickListener {
    EditText e1,e2,e3,e4;
    Button b1,b2;
    private Mydata dbHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xinxi);
        e1=findViewById(R.id.yonghu);

        e2=findViewById(R.id.xinming);
        e3=findViewById(R.id.lianxi);
        e4=findViewById(R.id.dizhi);
        b1=findViewById(R.id.baochu);
        b2=findViewById(R.id.fanhui);
        Intent get=getIntent();
        String user1=get.getStringExtra("name");
        e1.setText(user1);
        dbHelper=new Mydata(this,"Geren.db",null,1);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
      Cursor cursor=db.query("Geren",null,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                if(user1.equals(cursor.getString(cursor.getColumnIndex("user")))) {
                    String name1 = cursor.getString(cursor.getColumnIndex("username"));
                    String lianxi1 = cursor.getString(cursor.getColumnIndex("lianxi"));
                    String dizhi1= cursor.getString(cursor.getColumnIndex("dizhi"));
                    e1.setText(user1);
                    e2.setText(name1);
                    e3.setText(lianxi1);
                    e4.setText(dizhi1);
                    Log.d("M","user"+name1);
                }
            }while (cursor.moveToNext());
        }cursor.close();
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Intent get=getIntent();
        String use=get.getStringExtra("name");
        switch (view.getId()){
            case R.id.baochu:
                int flag=0;
                dbHelper=new Mydata(this,"Geren.db",null,1);
                ContentValues values=new ContentValues();
                Cursor cursor=db.query("Geren",null,null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        if(use.equals(cursor.getString(cursor.getColumnIndex("user")))) {
                            String a2=e2.getText().toString();
                            String a3=e3.getText().toString();
                            String a4=e4.getText().toString();
                            values.put("username",a2);
                            values.put("lianxi",a3);
                            values.put("dizhi",a4);
                            db.update("Geren",values,"user=?",new String[]{use});
                            flag=1;
                        }
                    }while (cursor.moveToNext());
                }cursor.close();
                if(flag==0){
                String aa1=e1.getText().toString();
                String aa2=e2.getText().toString();
                String aa3=e3.getText().toString();
                String aa4=e4.getText().toString();
                values.put("user",aa1);
                values.put("username",aa2);
                values.put("lianxi",aa3);
                values.put("dizhi",aa4);
                db.insert("Geren",null,values);}
                Toast.makeText(MainActivity_gerenxinxi.this,"用户信息修改成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity_gerenxinxi.this,MainAcitvity_zhuyemian.class);
                intent.putExtra("name",use);
                startActivity(intent);
                break;
            case R.id.fanhui:
                Intent fan=new Intent(MainActivity_gerenxinxi.this,MainActivity_gerenzhongx.class);
                fan.putExtra("name",use);
               startActivity(fan);
                break;
        }
    }
}
