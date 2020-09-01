package com.example.codepassword;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class personsAdapter extends ArrayAdapter<persons>
{
    private int resourceId;

    public personsAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<persons> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        persons persons=getItem(position);
        View view=LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView name=view.findViewById(R.id.sp_name1);
        TextView money=view.findViewById(R.id.sp_name2);
        TextView photo=view.findViewById(R.id.sp_name3);
        TextView time=view.findViewById(R.id.sp_name4);
        name.setText(persons.getName());
        money.setText(persons.getMoney());
        photo.setText(persons.getPhoto());
        time.setText(persons.getTime());
        return view;
    }
}
