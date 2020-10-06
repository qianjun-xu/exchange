package com.example.codepassword;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_wodefabu extends AppCompatActivity implements View.OnClickListener{
    private MyDatabaseHelper dbHelper;
    private Buy dyHelper;
    private Mydata myHelper;
    private BottomNavigationView navigation;
    private ImageView lpicture;
    private List<persons> personsList=new ArrayList<persons>();
    private List<persons> personsList1=new ArrayList<persons>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initpersons();
        personsAdapter adapter=new personsAdapter(MainActivity_wodefabu.this,R.layout.list,personsList);
        ListView listView=findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences.Editor editor=getSharedPreferences("data1",MODE_PRIVATE).edit();
                editor.putInt("id", (int) l);
                editor.commit();
            }
        });
        navigation=findViewById(R.id.navigation);
        LayoutInflater.from(MainActivity_wodefabu.this).inflate(R.layout.fabu,navigation,true);

        Button f=navigation.findViewById(R.id.fabushangp);
        Button s=navigation.findViewById(R.id.sanchushangp);
        Button fh=navigation.findViewById(R.id.fanhuiz);
        Button chakan=navigation.findViewById(R.id.chakan);
        f.setOnClickListener(this);
        s.setOnClickListener(this);
        fh.setOnClickListener(this);
        chakan.setOnClickListener(this);
    }
    private void initpersons(){
        Intent get=getIntent();
        String use=get.getStringExtra("name");
        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,1);

        SQLiteDatabase db=dbHelper.getWritableDatabase();

        Cursor cursor=db.query("BOOK",null,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
      if(use.equals(cursor.getString(cursor.getColumnIndex("user")))) {
    String name = cursor.getString(cursor.getColumnIndex("name"));
    String money = cursor.getString(cursor.getColumnIndex("money"));
    String photo = cursor.getString(cursor.getColumnIndex("photo"));
    String time = cursor.getString(cursor.getColumnIndex("time"));
    byte[] image=cursor.getBlob(cursor.getColumnIndex("image"));
    Bitmap bitmap=BitmapFactory.decodeByteArray(image,0,image.length);
    money = money + "￥";
    persons a = new persons(name, money, photo, time,null,bitmap);
    personsList.add(a);
          }
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
    private void initpersons1(){
        Intent get=getIntent();
        String buyer ,shouname,num;
        String use=get.getStringExtra("name");
        dyHelper=new Buy(this,"Buy.db",null,1);
        SQLiteDatabase db=dyHelper.getWritableDatabase();
        myHelper=new Mydata(this,"Geren.db",null,1);
        SQLiteDatabase my=myHelper.getWritableDatabase();
        Cursor cursor1=my.query("Geren",null,null,null,null,null,null,null);
        Cursor cursor=db.query("Buy",null,null,null,null,null,null,null);




        if(cursor.moveToFirst()){
            do{
                if(use.equals(cursor.getString(cursor.getColumnIndex("gg")))) {
                     buyer = cursor.getString(cursor.getColumnIndex("buyer"));
                     shouname = cursor.getString(cursor.getColumnIndex("shouname"));
                    num = cursor.getString(cursor.getColumnIndex("num"));
                    if(cursor1.moveToFirst()){
                        do{
                            if(buyer.equals(cursor1.getString(cursor1.getColumnIndex("user")))) {
                                String photo = cursor1.getString(cursor1.getColumnIndex("lianxi"));
                                String dizhi = cursor1.getString(cursor1.getColumnIndex("dizhi"));
                                byte[] image=cursor.getBlob(cursor.getColumnIndex("image"));
                                Bitmap bitmap=BitmapFactory.decodeByteArray(image,0,image.length);
                                persons a = new persons(buyer, shouname, num, photo,dizhi,bitmap);
                                personsList1.add(a);
                            }
                        }while (cursor1.moveToNext());
                    }

                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        cursor1.close();
    }
    @Override
    public void onClick(View view) {
        Intent get=getIntent();
        String user=get.getStringExtra("name");
        switch (view.getId()){
            case R.id.chakan:
                initpersons1();
                personsAdapter adapter=new personsAdapter(MainActivity_wodefabu.this,R.layout.goumai,personsList1);
                ListView listView=findViewById(R.id.listview);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    }
                });
                break;
            case R.id.fabushangp:
                Intent intent3=new Intent(MainActivity_wodefabu.this,MainActivity_fabushangping.class);
                intent3.putExtra("name",user);
                startActivity(intent3);
                break;
            case R.id.sanchushangp:
                dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,1);
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                SharedPreferences pref=getSharedPreferences("data1",MODE_PRIVATE);
                int i=pref.getInt("id",0);
                Log.d("dasdsad", String.valueOf(i));
                int a=0;
                Cursor cursor=db.query("BOOK",null,null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        if(user.equals(cursor.getString(cursor.getColumnIndex("user")))) {
                            if(a==i){
                            String id = cursor.getString(cursor.getColumnIndex("id"));
                                db.delete("Book","id=?",new String[]{id});
                                Toast.makeText(MainActivity_wodefabu.this,"该商品已成功删除",Toast.LENGTH_SHORT).show();
                           } a++;
                        }
                    }while (cursor.moveToNext());
                }
                cursor.close();
                break;
            case R.id.fanhuiz:
                Intent intent4=new Intent(MainActivity_wodefabu.this,MainActivity_gerenzhongx.class);
                intent4.putExtra("name",user);
                startActivity(intent4);break;
        }
    }
}
