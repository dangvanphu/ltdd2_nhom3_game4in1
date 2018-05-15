package com.example.pc.ltdd2_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.ltdd2_game.data_access.MySQLiteHelper;
import com.example.pc.ltdd2_game.data_models.ItemContinue;

import java.util.ArrayList;
import java.util.List;

public class ContinueActivity extends AppCompatActivity {
    ArrayList<ItemContinue> items = new ArrayList<ItemContinue>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continue_layout);

        final ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new MyAdapter(ContinueActivity.this, R.layout.item_continute, items);
        listView.setAdapter(adapter);

        ItemContinue item = new ItemContinue("Người chơi : A", "Điểm sô : 200");
        items.add(item);
        adapter.notifyDataSetChanged();

        //loadItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TO DO
                Intent intent = new Intent(ContinueActivity.this, ChooseGameActivity.class);
                intent.putExtra("SCORE",items.get(i).getScore());
                intent.putExtra("USER",items.get(i).getUserName());
                startActivity(intent);
            }
        });
    }

    private void loadItem() {
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(this);
        items.clear();
        mySQLiteHelper.loadDatabase(items);
        adapter.notifyDataSetChanged();
    }
}
