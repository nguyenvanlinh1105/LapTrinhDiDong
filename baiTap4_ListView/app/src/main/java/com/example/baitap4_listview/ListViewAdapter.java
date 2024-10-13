package com.example.baitap4_listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Random;

public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    final ArrayList<String> nName= new ArrayList<String>();
    private Random random = new Random();

    public ListViewAdapter(Context context ){

        mContext = context;
        for(int i =1 ;i<5;i++){
            nName.Add(getRandomName());
        }
    }

    private String getRandomName(){
        String[] names = new String[]{
                "Linh", "Hòa","Ngân", "Tuyết", "Khương", "Toàn", "Hồng", "Dương",
                "Quân", "Cừ", "Ly", "Lý", "Thái", "Như", "Nguyệt", "Hiếu",
        };
        return names[random.nextInt(names.length)];
    }
    @Override
    public int getCount() {
        return nName.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
