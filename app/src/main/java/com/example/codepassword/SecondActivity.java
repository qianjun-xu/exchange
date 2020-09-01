package com.example.codepassword;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private MyDatabaseHelper dbHelper;
    private BottomNavigationView navigation;
    private List<persons> personsList=new ArrayList<persons>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initpersons();
        personsAdapter adapter=new personsAdapter(SecondActivity.this,R.layout.list,personsList);
        ListView listView=findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        navigation=findViewById(R.id.navigation);
        LayoutInflater.from(SecondActivity.this).inflate(R.layout.low,navigation,true);
        Button shouye=navigation.findViewById(R.id.shouye);
        Button fabu=navigation.findViewById(R.id.fabu);
        Button geren=navigation.findViewById(R.id.geren);
        shouye.setOnClickListener(this);
        fabu.setOnClickListener(this);
        geren.setOnClickListener(this);
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
                money=money+"￥";
                persons a=new persons(name,money,photo,time);
                personsList.add(a);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shouye:
                Intent intent=new Intent(SecondActivity.this,SecondActivity.class);
                startActivity(intent);break;
            case R.id.fabu:
                Intent intent1=new Intent(SecondActivity.this,MainActivity2.class);
                startActivity(intent1);break;
            case R.id.geren:
        }
    }
}
