package com.example.codepassword;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;

public class MainActivity_fabushangping extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private EditText name,money,photo,time,dizhi;
    ImageButton ivpicture;
    ImageView picture;
    public String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        name=findViewById(R.id.sp_name);
        money=findViewById(R.id.sp_money);
        photo=findViewById(R.id.sp_photo);
        time=findViewById(R.id.sp_time);
        ivpicture=findViewById(R.id.pButton);
        picture=findViewById(R.id.sp_picture);
        final Button fan=findViewById(R.id.fh_Button);
        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,1);
        final Button create=findViewById(R.id.ir_Button);


        ivpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 2);
            }
        });

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
                    //把图片先转化成bitmap格式
                BitmapDrawable drawable = (BitmapDrawable) picture.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                    //二进制数组输出流
                ByteArrayOutputStream byStream = new ByteArrayOutputStream();
                    //将图片压缩成质量为100的PNG格式图片
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byStream);
                    //把输出流转换为二进制数组
                byte[] byteArray = byStream.toByteArray();

                values.put("image",byteArray);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri=data.getData();
                picture.setImageURI(uri);
            }
        }
    }



}