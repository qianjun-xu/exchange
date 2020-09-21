package com.example.codepassword;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_fabushangping extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private EditText name,money,photo,time,dizhi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        name=findViewById(R.id.sp_name);
        money=findViewById(R.id.sp_money);
        photo=findViewById(R.id.sp_photo);
        time=findViewById(R.id.sp_time);
final Button fan=findViewById(R.id.fh_Button);
        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,1);
        Button create=findViewById(R.id.ir_Button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String rname=name.getText().toString().trim();
                final String rmoney=money.getText().toString().trim();
                final String rphoto=photo.getText().toString().trim();
                final String rtime=time.getText().toString().trim();
                final String rdizhi=time.getText().toString().trim();
                if(!TextUtils.isEmpty(rname)&&!TextUtils.isEmpty(rmoney)&&!TextUtils.isEmpty(rphoto)&&!TextUtils.isEmpty(rtime)){
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                Intent int1=getIntent();
                String user=int1.getStringExtra("name");
                   // Log.d("main",user);
                values.put("user",user);
                values.put("name",rname);
                values.put("money",rmoney);
                values.put("photo",rphoto);
                values.put("time",rtime);
                db.insert("Book",null,values);
                Toast.makeText(MainActivity_fabushangping.this,"商品已发布",Toast.LENGTH_SHORT).show();
                Intent inten=new Intent(MainActivity_fabushangping.this, MainAcitvity_zhuyemian.class);
                inten.putExtra("name",user);
                startActivity(inten);

                finish();}
                else {
                    Toast.makeText(MainActivity_fabushangping.this,"请输入商品信息",Toast.LENGTH_SHORT).show();
                }
            }
        });
        fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1=getIntent();
                String user=int1.getStringExtra("name");
                Intent intent2=new Intent(MainActivity_fabushangping.this, MainAcitvity_zhuyemian.class);
                intent2.putExtra("name",user);
                startActivity(intent2);
            }
        });
    }
}