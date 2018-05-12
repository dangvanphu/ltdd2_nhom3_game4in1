package com.example.pc.ltdd2_game;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pc.ltdd2_game.data_access.MusicService;

public class StartActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnOption;
    private Button btnContinue;
    private Button btnExit;
    private ImageButton btnInfo;
    private Dialog dialog;
    public static final int HINHCHUNHAT = 1;
    static final String TITLE = "isPlaying";
    static final String BUNDLE = "bundle";
    // MediaPlayer mediaPlayer;
    boolean isPlaying = true;
    Intent playbackServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
        // mediaPlayer
        playbackServiceIntent = new Intent(this, MusicService.class);
        if (isPlaying) {
            startService(playbackServiceIntent);
            //  btnMusic.setText("music : on");
        } else {
            //  btnMusic.setText("music : off");
            stopService(playbackServiceIntent);

        }

        btnStart = (Button) findViewById(R.id.btnStart);
        btnOption = (Button) findViewById(R.id.btnOptions);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnInfo = (ImageButton) findViewById(R.id.ibtnInformation);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_infomation);
        dialog.setTitle("About game");

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mediaPlayer.start();
            }
        });

        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, OptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean(TITLE, isPlaying);
                intent.putExtra(BUNDLE, bundle);
//                startActivity(intent);
                startActivityForResult(intent, HINHCHUNHAT);
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, ContinueActivity.class);
                startActivity(intent);
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, ChooseGameActivity.class);
                startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StartActivity.this, R.style.MyAlertDialogStyle);
                builder.setTitle(R.string.app_name);
                //builder.setIcon(R.drawable.logo);
                builder.setMessage("Do you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                StartActivity.super.onBackPressed();
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });


        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            Toast.makeText(this, "data null: " + String.valueOf(isPlaying), Toast.LENGTH_LONG).show();
            return;
        }
        if (requestCode == HINHCHUNHAT) {
            // Intent intent = getIntent();
            Bundle bundle = data.getBundleExtra(OptionActivity.BUNDLE_OPTION);
//            Log.d("budeloption: ", String.valueOf(bundle.getBoolean(OptionActivity.TITLE_OPION)));
            isPlaying = bundle.getBoolean(OptionActivity.TITLE_OPION);
            isPlaying = !isPlaying;
//            Toast.makeText(this, String.valueOf(isPlaying), Toast.LENGTH_LONG).show();
        }

//        btnInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog alert = new AlertDialog.Builder(StartActivity.this).create();
//                alert.setTitle("About Us");
//                alert.setMessage("Nhóm 3: Game 2 in 1\nVersion: 1.0\nThành viên: Phú, Vinh, Pháp, Toàn, Quang\nGVHD: Thầy Trương Bá Thái");
//                alert.show();
//            }
//        });
    }
}

