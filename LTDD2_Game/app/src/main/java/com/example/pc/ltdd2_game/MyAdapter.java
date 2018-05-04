package com.example.pc.ltdd2_game;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.pc.ltdd2_game.data_models.ItemContinue;


public class MyAdapter extends ArrayAdapter<ItemContinue> {
    AppCompatActivity context;
    ArrayList<ItemContinue> myArray;
    int layoutID;


    public MyAdapter(@NonNull AppCompatActivity context, @LayoutRes int resource, @NonNull ArrayList<ItemContinue> objects) {
        super(context,resource,objects);
        this.context = context;
        this.myArray = objects;
        this.layoutID = resource;
    }

    public static class ViewHolder{
        TextView txtTen;
        TextView txtDiem;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView== null)
        {
            convertView = inflater.inflate(layoutID,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txtTen = (TextView) convertView.findViewById(R.id.txtUser);
            viewHolder.txtDiem = (TextView) convertView.findViewById(R.id.txtScore);
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ItemContinue item = myArray.get(position);
        viewHolder.txtTen.setText(item.getUserName());
        viewHolder.txtDiem.setText(item.getScore());
        return convertView;
    }
}
