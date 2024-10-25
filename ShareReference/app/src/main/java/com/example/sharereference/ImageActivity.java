package com.example.sharereference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Collections;

public class ImageActivity extends Activity {
    TableLayout myTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        myTable = (TableLayout) findViewById(R.id.TbLayoutImage);

        int soDong = 4;
        int soCot = 3;
        Collections.shuffle(MainActivity.arrayName);
        for(int i = 1; i<=soDong;i++){
            TableRow row = new TableRow(this);
            for(int j = 1;j<=soCot;j++){
                ImageView imageView = new ImageView(this);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(450, 500);
                imageView.setLayoutParams(layoutParams);
                int viTri = soCot *(i-1)+j -1;
                int idHinh = getResources().getIdentifier(MainActivity.arrayName.get(viTri),"drawable",getPackageName());

                imageView.setImageResource(idHinh);

                // add image vao tbrow

                row.addView(imageView);

                // bat su kin
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent  intent = new Intent();
                        intent.putExtra("tenhinhchon",MainActivity.arrayName.get(viTri));
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });

            }
            // add row vao table
            myTable.addView(row);
        }
    }
}