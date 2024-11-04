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
import com.example.contenprovider.model.CallLog;

import java.util.List;

public class AdapterCallLog extends ArrayAdapter<CallLog> {
    Activity context;
    List<CallLog> dsCallLog;
    public AdapterCallLog(@NonNull Activity Context, int resource, @NonNull List<CallLog> objects) {
        super(Context, resource, objects);
        this.context = Context;
        dsCallLog = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView ==null){
            LayoutInflater layoutInflater =  this.context.getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.activity_item_call_log,parent,false); // gán view
        }

        TextView txt_name = convertView.findViewById(R.id.text_type);
        TextView txt_num = convertView.findViewById(R.id.text_phone);
        TextView txt_date = convertView.findViewById(R.id.text_date);
        TextView txt_time = convertView.findViewById(R.id.txt_time);


        CallLog callLog = dsCallLog.get(position);

        txt_name.setText(callLog.getType());
        txt_num.setText(callLog.getNumber());
        txt_date.setText(callLog.getDate());
        txt_time.setText(callLog.getDuration()+"");

        Animation animation = AnimationUtils.loadAnimation(context,R.anim.facein);
        convertView.startAnimation(animation);




        return convertView;
    }
}
