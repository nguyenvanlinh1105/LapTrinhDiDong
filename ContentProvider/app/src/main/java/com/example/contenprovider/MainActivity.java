package com.example.contenprovider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ASK_PERMISSIONS=1001;
    private static final int REQUEST_SMS_ASK_PERMISSIONS=1002;
    private static final int REQUEST_CALLLOG_PERMISSIONS = 1;
    Button btn1, btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addMapping();
        addEvent();
    }
    private void addEvent(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLiManHinhDanhBa();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLiManHinhTinNhan();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLiManHinhCallLog();
            }
        });
    }
    private void xuLiManHinhDanhBa(){
        Intent intent = new Intent(MainActivity.this, DanhBa.class);
        intent.setClassName("com.example.contenprovider","com.example.contenprovider.DanhBa");
        startActivity(intent);

    }
    public void xuLiManHinhCallLog(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, REQUEST_CALLLOG_PERMISSIONS);
        }else{
            Intent intent = new Intent(MainActivity.this, DocCallLog.class);
            intent.setClassName("com.example.contenprovider","com.example.contenprovider.DocCallLog");
            startActivity(intent);
        }
    }
    private void xuLiManHinhTinNhan(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, REQUEST_SMS_ASK_PERMISSIONS);
        } else {
            Intent intent = new Intent(MainActivity.this, DocTinNhan.class);
            intent.setClassName("com.example.contenprovider","com.example.contenprovider.DocTinNhan");
            startActivity(intent);
        }
    }
    private void addMapping(){
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3= findViewById(R.id.btn3);
    }
}