package com.example.codepassword;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainAcitvity_zhuyemian extends AppCompatActivity implements View.OnClickListener {
    private MyDatabaseHelper dbHelper;
    private Buy byHelper;
    private Mydata myHelper;
    private BottomNavigationView navigation,navigation1;
    private List<persons> personsList=new ArrayList<persons>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initpersons();
        personsAdapter adapter=new personsAdapter(MainAcitvity_zhuyemian.this,R.layout.list,personsList);
        ListView listView=findViewById(R.id.listview);

        listView.setAdapter(adapter);
        navigation=findViewById(R.id.navigation);
        navigation1=findViewById(R.id.navigation1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                navigation.setVisibility(View.GONE);
                navigation1.setVisibility(View.VISIBLE);
                SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putInt("id", (int) l);
                editor.commit();
            }
        });
        LayoutInflater.from(MainAcitvity_zhuyemian.this).inflate(R.layout.buy,navigation1,true);
        LayoutInflater.from(MainAcitvity_zhuyemian.this).inflate(R.layout.low,navigation,true);
        Button shouye=navigation.findViewById(R.id.shouye);
        Button fabu=navigation.findViewById(R.id.fabu);
        Button geren=navigation.findViewById(R.id.geren);


        Button goumai=navigation1.findViewById(R.id.goumai);
        Button fan=navigation1.findViewById(R.id.fanh);
        shouye.setOnClickListener(this);
        fabu.setOnClickListener(this);
        geren.setOnClickListener(this);
        goumai.setOnClickListener(this);
        fan.setOnClickListener(this);
    }
    private void initpersons(){
        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,1);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("BOOK",null,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String money=cursor.getString(cursor.getColumnIndex("money"));
                String photo=cursor.getString(cursor.getColumnIndex("photo"));
                String time=cursor.getString(cursor.getColumnIndex("time"));
                byte[] image=cursor.getBlob(cursor.getColumnIndex("image"));
                Bitmap bitmap=BitmapFactory.decodeByteArray(image,0,image.length);
                persons a=new persons(name,money,photo,time,null,bitmap);
                personsList.add(a);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public void onClick(View view) {

        Intent get=getIntent();
        String user=get.getStringExtra("name");
        Log.d("Maindad",user);
        switch (view.getId()){
            case R.id.shouye:
                Intent intent=new Intent(MainAcitvity_zhuyemian.this, MainAcitvity_zhuyemian.class);
                intent.putExtra("name",user);
                startActivity(intent);
                finish();break;
            case R.id.fabu:
                Intent intent1=new Intent(MainAcitvity_zhuyemian.this, MainActivity_fabushangping.class);
                intent1.putExtra("name",user);
                startActivity(intent1);
                break;
            case R.id.geren:
                Intent intent2=new Intent(MainAcitvity_zhuyemian.this, MainActivity_gerenzhongx.class);
                intent2.putExtra("name",user);
            startActivity(intent2);
                break;
            case R.id.goumai:
                navigation1.setVisibility(View.GONE);
                navigation.setVisibility(View.VISIBLE);
                int flag=0;
                EditText shuliang=navigation1.findViewById(R.id.shuliang);
                byHelper=new Buy(this,"Buy.db",null,1);
               ContentValues values=new ContentValues();
                SQLiteDatabase by=byHelper.getWritableDatabase();
                SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
                int i=pref.getInt("id",0);
                Log.d("dad", String.valueOf(i+1));
                String I=String.valueOf(i+1);
                final String sl=shuliang.getText().toString().trim();
                dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,1);
                myHelper=new Mydata(this,"Geren.db",null,1);
                SQLiteDatabase my=myHelper.getWritableDatabase();
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                Cursor cursor1=my.query("Geren",null,null,null,null,null,null,null);
                Cursor cursor=db.query("BOOK",null,null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        if(I.equals(cursor.getString(cursor.getColumnIndex("id")))){
                            String g=cursor.getString(cursor.getColumnIndex("user"));
                            String b=cursor.getString(cursor.getColumnIndex("name"));
                            values.put("gg", g);
                            values.put("shouname",b);
                        }
                    }while (cursor.moveToNext());
                }
                cursor.close();
                if(cursor1.moveToFirst()){
                    do{
                        if(user.equals(cursor1.getString(cursor1.getColumnIndex("user")))){
                            flag=1;
                            if (!TextUtils.isEmpty(sl) ) {
                                values.put("buyer", user);
                                values.put("num", sl);
                                by.insert("buy", null, values);
                                Toast.makeText(MainAcitvity_zhuyemian.this,"购买成功！",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MainAcitvity_zhuyemian.this,"请输入你要购买的数量！",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }while (cursor1.moveToNext());
                }
                if(flag==0){
                Toast.makeText(MainAcitvity_zhuyemian.this,"请完善个人信息！",Toast.LENGTH_SHORT).show();
            }
                cursor1.close();
                break;
            case R.id.fanh:
                navigation1.setVisibility(View.GONE);
                navigation.setVisibility(View.VISIBLE);
                break;
        }
    }
}
