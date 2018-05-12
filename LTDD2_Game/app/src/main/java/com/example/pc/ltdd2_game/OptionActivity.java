package com.example.pc.ltdd2_game;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.pc.ltdd2_game.data_access.MusicService;

public class OptionActivity extends AppCompatActivity {
    private Button btnMusic;
    private Button btnSound;
    public boolean isPlaying;
    Intent playbackServiceIntent;
    static final String TITLE_OPION = "isPlaying_option";
    static final String BUNDLE_OPTION = "bundle_option";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_layout);
        btnMusic = (Button) findViewById(R.id.btnMusic);
        btnSound = (Button) findViewById(R.id.btnSound);

//        nhận dữ liệu tù starActivity
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(StartActivity.BUNDLE);
        Log.d("budel: ", String.valueOf(bundle.getBoolean(StartActivity.TITLE)));
        isPlaying = bundle.getBoolean(StartActivity.TITLE);

//        Toast.makeText(this,String.valueOf(isPlaying),Toast.LENGTH_LONG).show();
//        Log.d("isplay", String.valueOf(isPlaying));

//        kiểm tra isPlaying ==> setText
        String blean = String.valueOf(isPlaying);
        if (blean.equals("false")) {
            btnMusic.setText("music : off");
        }
        if (blean.equals("true")) {
            btnMusic.setText("music : on");
        }

        playbackServiceIntent = new Intent(this, MusicService.class);
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlaying) {
                    startService(playbackServiceIntent);
                    btnMusic.setText("music : on");
                } else {
                    btnMusic.setText("music : off");
                    stopService(playbackServiceIntent);
                }
                isPlaying = !isPlaying;

            }
        });

//        btnSound.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (isPlaying) {
//                    stopService(playbackServiceIntent);
//                    btnSound.setText("sound : off");
//                } else {
//                    btnSound.setText("sound : on");
//                    startService(playbackServiceIntent);
//                }
//                isPlaying = !isPlaying;
//            }
//        });
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Really Save?")
                .setMessage("Are you sure you want to save?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent_o_s = new Intent(OptionActivity.this, StartActivity.class);
                        Bundle bundle_o_s = new Bundle();
                        bundle_o_s.putBoolean(TITLE_OPION, isPlaying);
                        setResult(RESULT_OK, intent_o_s.putExtra(BUNDLE_OPTION, bundle_o_s));
                        OptionActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}
