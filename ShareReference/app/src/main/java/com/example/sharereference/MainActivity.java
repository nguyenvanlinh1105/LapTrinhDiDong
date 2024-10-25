package com.example.sharereference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> arrayName;
    ImageView imageViewGoc, imageViewNhan, reload;
    int Request_code_image = 12;
    String tenhinhGoc ;
    TextView txtDiem ;
    int total = 100;


    SharedPreferences luuDiemSo;

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

        imageViewGoc = findViewById(R.id.imageViewGoc);
        txtDiem = findViewById(R.id.txtDiem);
        imageViewNhan = findViewById(R.id.imageViewNhan);
        reload = findViewById(R.id.reload);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.shuffle(arrayName);
                tenhinhGoc = arrayName.get(4);
                int idHinh = getResources().getIdentifier(tenhinhGoc, "drawable", getPackageName());
                imageViewGoc.setImageResource(idHinh);  // Cập nhật hình ảnh
            }
        });

        luuDiemSo = getSharedPreferences("luudiem",MODE_PRIVATE);
        // lay diem
        total = luuDiemSo.getInt("diem",100);
        txtDiem.setText(total+"");

        String[] mangTen = getResources().getStringArray(R.array.list_name);

        arrayName = new ArrayList<>(Arrays.asList(mangTen));
        Collections.shuffle(arrayName);
        tenhinhGoc = arrayName.get(4);
        int idHinh = getResources().getIdentifier(tenhinhGoc,"drawable",getPackageName() );

        imageViewGoc.setImageResource(idHinh);

        imageViewNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, ImageActivity.class),Request_code_image);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(Request_code_image==requestCode&&resultCode==RESULT_OK && data!=null){
            String tenHinhNhan= data.getStringExtra("tenhinhchon");
            int idHinh = getResources().getIdentifier(tenHinhNhan,"drawable",getPackageName());
            imageViewNhan.setImageResource(idHinh);
            if(tenhinhGoc.equals(tenHinhNhan)){
                Toast.makeText(MainActivity.this,"Chính xác",Toast.LENGTH_SHORT).show();
                total+=15;
                LuuDiem();
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Collections.shuffle(arrayName);
                        tenhinhGoc = arrayName.get(4);
                        int idHinhGocMoi = getResources().getIdentifier(tenhinhGoc, "drawable", getPackageName());
                        imageViewGoc.setImageResource(idHinhGocMoi);
                    }
                }, 2000);
            }else{
                total-=10;
                LuuDiem();
                Toast.makeText(MainActivity.this,"Không chính xác",Toast.LENGTH_SHORT).show();

            }
            txtDiem.setText(total+"");

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

        private void LuuDiem(){
            SharedPreferences.Editor editor = luuDiemSo.edit();
            editor.putInt("diem",total);
            editor.commit();
        }

}