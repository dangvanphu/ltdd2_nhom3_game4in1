package com.example.pc.ltdd2_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseGameActivity extends AppCompatActivity {
    ImageView gameFlappy, gameLathinh;
    TextView txtScore,txtUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_game_layout);

        gameFlappy = (ImageView) findViewById(R.id.gameFlappy);
        gameLathinh = (ImageView) findViewById(R.id.gameLatHinh);
        txtScore = (TextView) findViewById(R.id.txtScore);
        txtUser = (TextView) findViewById(R.id.txtUser);

        Intent intent = getIntent();
        txtUser.setText(intent.getStringExtra("USER"));
        txtScore.setText(intent.getStringExtra("SCORE"));
        gameFlappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseGameActivity.this, FlappyActivity.class);
                startActivity(intent);
            }
        });
        gameLathinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseGameActivity.this, LatHinhActivity.class);
                startActivity(intent);
            }
        });
    }
}
