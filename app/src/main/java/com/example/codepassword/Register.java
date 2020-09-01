package com.example.codepassword;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private  DBOpenHelper mDBOpenHelper;
    private  Button mregister;
    private EditText musername;
    private EditText mpassword;
    private EditText mpassword1;
    private EditText mphoto;
    private EditText mloca;
    private EditText msex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        mDBOpenHelper=new DBOpenHelper(this);
}

    @SuppressLint("WrongViewCast")
    private void initView() {
        mregister= findViewById(R.id.ir_Button);
        musername=findViewById(R.id.ir_account);
        mpassword=findViewById(R.id.ir_password);
        mpassword1=findViewById(R.id.ir_repassword);
        //mphoto=findViewById(R.id.ir_photo);
        //mloca=findViewById(R.id.ir_location);

        mregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ir_Button:
                String username=musername.getText().toString().trim();
                String password=mpassword.getText().toString().trim();
                String repassword=mpassword1.getText().toString().trim();
                if(!password.equals(repassword)){
                    Toast.makeText(this,"两次输入密码不同",Toast.LENGTH_SHORT).show();
                }
             if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)&& password.equals(repassword)) {

                        //将用户名和密码加入到数据库中
                        mDBOpenHelper.add(username, password);
                        Intent intent2 = new Intent(this, MainActivity.class);
                        startActivity(intent2);
                        finish();
                        Toast.makeText(this,  "注册成功", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(this, "未完善信息，注册失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
