package com.example.contenprovider.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.contenprovider.R;
import com.example.contenprovider.model.TinNhan;

import java.util.List;

public class AdapterTinNhan extends ArrayAdapter<TinNhan> {
    Activity context;
    Integer resource;
    @NonNull List<TinNhan> dsTinNhan;

    public AdapterTinNhan(@NonNull Activity context, int resource, @NonNull List<TinNhan> objects) {
        super(context, resource, objects);
        this.context = context;
        dsTinNhan = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Kiểm tra nếu convertView là null, nếu có thì inflate một view mới
        if (convertView == null) {
            LayoutInflater layoutInflater = this.context.getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.activity_item_tinnhan, parent, false);
        }

        // Tìm TextView và gán giá trị
        TextView textnum = convertView.findViewById(R.id.txt_phone);
        TextView texttime = convertView.findViewById(R.id.txt_time);
        TextView textBody = convertView.findViewById(R.id.txt_body);



        TinNhan tinNhan = dsTinNhan.get(position);
        textnum.setText(tinNhan.getNumber());
        texttime.setText(tinNhan.getTime());
        textBody.setText(tinNhan.getBody());

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.facein);
        convertView.startAnimation(animation);

        return convertView; // Trả về convertView đã được cập nhật
    }
}
