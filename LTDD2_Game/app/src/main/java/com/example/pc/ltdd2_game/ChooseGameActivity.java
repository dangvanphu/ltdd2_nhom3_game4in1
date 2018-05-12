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
        txtScore = (TextView) findViewById(R.id.txtScoreS);
        txtUser = (TextView) findViewById(R.id.txtUser);

        Intent intent = getIntent();
        if (intent.getStringExtra("USER") == null){
            txtUser.setVisibility(View.INVISIBLE);
            txtScore.setText("Score : 0" );
        }else {
            txtUser.setText("User :" + intent.getStringExtra("USER"));
            txtScore.setText("Score : " + intent.getStringExtra("SCORE"));
        }
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
