package com.example.contenprovider;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.contenprovider.adapter.AdapterCallLog;
import com.example.contenprovider.model.CallLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DocCallLog extends AppCompatActivity {
    ListView  lvDsCalllog;
    ImageView btnbackCalllog;
    ArrayList<CallLog> dsCallLog;
    AdapterCallLog adapterCallLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doc_call_log);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addMapping();
        addControll();
        DocToanBoCallLog();
    }
    private void addControll(){
        btnbackCalllog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocCallLog.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void DocToanBoCallLog() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");
        Uri uri = Uri.parse("content://call_log/calls");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {

                int indexPhone = cursor.getColumnIndex("number");
                String phoneNumber = cursor.getString(indexPhone);
                int indexDate =cursor.getColumnIndex("date");
                long dateMillis = cursor.getLong(indexDate);
                String formattedTime = sdf.format(new Date(dateMillis));
                int indexDuration =cursor.getColumnIndex("duration");
                String duration = cursor.getString(indexDuration);
                int indexType=cursor.getColumnIndex("type");
                int callTypeCode = cursor.getInt(indexType);
                String callType;
                switch (callTypeCode) {
                    case 2:
                        callType = "Gọi đi";
                        break;
                    case 1:
                        callType = "Gọi đến";
                        break;
                    case 3:
                        callType = "Gọi nhỡ";
                        break;
                    default:
                        callType = "Khác";
                }

                dsCallLog.add(new CallLog( phoneNumber, formattedTime, duration, callType));
            }
            cursor.close();
        }

        adapterCallLog.notifyDataSetChanged();
    }

    private void addMapping(){
        lvDsCalllog = findViewById(R.id.lvDsCallLog);
        btnbackCalllog = findViewById(R.id.btnbackcallLog);
        dsCallLog = new ArrayList<>();
        adapterCallLog = new AdapterCallLog(this,R.layout.activity_item_call_log,dsCallLog);
        lvDsCalllog.setAdapter(adapterCallLog);
    }
}