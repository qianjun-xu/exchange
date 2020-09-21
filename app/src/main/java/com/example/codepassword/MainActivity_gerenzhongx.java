package com.example.codepassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_gerenzhongx extends AppCompatActivity implements View.OnClickListener {
    Button b1,b2,b3,b4,b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geren);
        b1=findViewById(R.id.getid);
        b2=findViewById(R.id.getfabu);
        b3=findViewById(R.id.getxiugai);
        b4=findViewById(R.id.gettui);
        b5=findViewById(R.id.getfan);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent get=getIntent();
        String user=get.getStringExtra("name");
        switch (view.getId()){
            case R.id.getid:
        Intent xinxi=new Intent(MainActivity_gerenzhongx.this, MainActivity_gerenxinxi.class);
                xinxi.putExtra("name",user);
        startActivity(xinxi);
                break;
            case R.id.getfabu:
                Intent fabu=new Intent(MainActivity_gerenzhongx.this,MainActivity_wodefabu.class);
                fabu.putExtra("name",user);
                startActivity(fabu);
                break;
            case R.id.getxiugai:
               Intent xiugai=new Intent(MainActivity_gerenzhongx.this, MainActivity_xiugaimima.class);
               xiugai.putExtra("name",user);
               startActivity(xiugai);
                break;
            case R.id.gettui:
                Intent tuichu=new Intent(MainActivity_gerenzhongx.this, MainActivity_denglu.class);
                startActivity(tuichu);
                break;
            case R.id.getfan:
                Intent intent=new Intent(MainActivity_gerenzhongx.this, MainAcitvity_zhuyemian.class);
                intent.putExtra("name",user);
                startActivity(intent);
                break;
        }
    }
}
