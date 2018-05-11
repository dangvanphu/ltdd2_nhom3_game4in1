package com.example.pc.ltdd2_game;

        import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
private TextView txtScore;
private TextView txtBest;
private Button btnAgain;
private Button btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over_layout);
        txtScore = (TextView)findViewById(R.id.txtScore);
        txtBest = (TextView)findViewById(R.id.txtBest);
        btnAgain = (Button)findViewById(R.id.btnAgain);
        btnExit = (Button)findViewById(R.id.btnExit);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GameOverActivity.super.onBackPressed();
//                Intent intent = new Intent(GameOverActivity.this, StartActivity.class);
//                startActivity(intent);
            }
        });
    }
}
