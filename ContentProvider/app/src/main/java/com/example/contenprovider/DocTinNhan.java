package com.example.contenprovider;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.contenprovider.adapter.AdapterTinNhan;
import com.example.contenprovider.model.TinNhan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DocTinNhan extends AppCompatActivity {

    private static final int REQUEST_SMS_ASK_PERMISSIONS=1002;
    ListView lvDanhSachTN;
    ImageView back ;
    ArrayList<TinNhan> dsTinNhan;
   AdapterTinNhan adapterTinNhan ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doc_tin_nhan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addMapping();
        addControll();
        docToanBoTinNhan();
    }
    private void docToanBoTinNhan(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor= getContentResolver().query(uri, null, null, null, null);

        dsTinNhan.clear();
        while (cursor.moveToNext()){
            int indexPhoneNumber = cursor.getColumnIndex("address");
            int indexTime = cursor.getColumnIndex("date");
            int indexBody = cursor.getColumnIndex("body");
            String phoneNumber = cursor.getString(indexPhoneNumber);
            String Time = cursor.getString(indexTime);
            String body = cursor.getString(indexBody);
            dsTinNhan.add(new TinNhan(phoneNumber,sdf.format(Long.parseLong(Time)),body));
            adapterTinNhan.notifyDataSetChanged();


        }
    }
    private void addControll(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocTinNhan.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void addMapping(){
        lvDanhSachTN = findViewById(R.id.lvDsTinhNhan);
        back = findViewById(R.id.btnbacktinnhan);
        dsTinNhan = new ArrayList<>();
        adapterTinNhan = new AdapterTinNhan(
                this, R.layout.activity_item_tinnhan,dsTinNhan
        );
        lvDanhSachTN.setAdapter(adapterTinNhan);
    }

}