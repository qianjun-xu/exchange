package com.example.codepassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity_xiugaimima extends AppCompatActivity {
    private DBOpenHelper mDBOpenHelper;
    EditText word,word1,word2;Button Button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changce);
        word=findViewById(R.id.userpassword);
        word1=findViewById(R.id.userpassword1);
        word2=findViewById(R.id.userpassword2);
        Button=findViewById(R.id.ir_Button);
        mDBOpenHelper = new DBOpenHelper(this);
        Intent get=getIntent();
        final String use=get.getStringExtra("name");
Button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        ArrayList<User> data = mDBOpenHelper.getAllData();
        String password=word.getText().toString().trim();
        String password1=word1.getText().toString().trim();
        String password2=word2.getText().toString().trim();
        Boolean match;
        for (int i = 0; i < data.size(); i++){
            User user = data.get(i);
            if (use.equals(user.getName()) && password.equals(user.getPassword())) {
                match = true;
            } else {
                match = false;

            }
        if(match){
            Toast.makeText(MainActivity_xiugaimima.this,"密码修改成功",Toast.LENGTH_SHORT).show();
            if(password1.equals(password2)){
                mDBOpenHelper.updata(password1);
                Intent intent=new Intent(MainActivity_xiugaimima.this, MainActivity_denglu.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(MainActivity_xiugaimima.this,"两次输入密码不正确",Toast.LENGTH_SHORT).show();
            }
            break;
        }else {
            Toast.makeText(MainActivity_xiugaimima.this,"旧密码不正确",Toast.LENGTH_SHORT).show();
        }
        }

    }
});

    }
}
