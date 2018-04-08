package com.example.pc.ltdd2_game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionActivity extends AppCompatActivity {
private Button btnMusic;
    private Button btnSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_layout);
        btnMusic = (Button)findViewById(R.id.btnMusic);
        btnSound = (Button)findViewById(R.id.btnSound);
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnMusic.setText("music : off");
            }
        });
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSound.setText("sound : off");
            }
        });
    }
}
