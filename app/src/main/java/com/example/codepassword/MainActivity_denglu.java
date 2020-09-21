package com.example.codepassword;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity_denglu extends AppCompatActivity {
    private Boolean bPwdSwitch=false;
    private DBOpenHelper mDBOpenHelper;
    private EditText etPwd;
    private EditText etAccount;
    private CheckBox rememberpass;
    private Button sign;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Override
  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sign=findViewById(R.id.tv_sign_up);
        etPwd=findViewById(R.id.et_pwd);
        etAccount=findViewById(R.id.et_account);
        rememberpass=findViewById(R.id.cb_remember_pwd);
        final ImageView ivPwdSwitch=findViewById(R.id.iv_psw_switch);
        Button buttonLogin=findViewById(R.id.bt_lgoin);
        mDBOpenHelper = new DBOpenHelper(this);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember=pref.getBoolean("cb_remember_pwd",false);
        if(isRemember){
            String account=pref.getString("etAccount","");
            String password=pref.getString("etPwd","");
            etAccount.setText(account);
            etPwd.setText(password);
            rememberpass.setChecked(true);
        }
sign.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent1=new Intent(MainActivity_denglu.this, MainActivity_zuce.class);//跳转页面
        startActivity(intent1);
    }
});//注册点击事件
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=etAccount.getText().toString().trim();
                String password=etPwd.getText().toString().trim();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mDBOpenHelper.getAllData();
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                            match = true;
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        editor=pref.edit();
                        if(rememberpass.isChecked()){
                            editor.putBoolean("cb_remember_pwd",true);
                            editor.putString("etAccount", name);
                            editor.putString("etPwd",password);
                        }else {
                            editor.clear();
                        }
                        editor.commit();

                        Toast.makeText(MainActivity_denglu.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity_denglu.this, MainAcitvity_zhuyemian.class);
                        intent.putExtra("name",name);
                        intent.putExtra("password",password);
                        startActivity(intent);
                       //销毁此Activity
                    } else {
                        Toast.makeText(MainActivity_denglu.this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity_denglu.this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ivPwdSwitch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                bPwdSwitch=!bPwdSwitch;
                if(bPwdSwitch==true){
                    ivPwdSwitch.setImageResource(R.drawable.ic_baseline_visibility_24);
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    ivPwdSwitch.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPwd.setTypeface(Typeface.DEFAULT);
                }
            }
        });

    }


}