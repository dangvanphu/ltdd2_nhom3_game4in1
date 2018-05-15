package com.example.pc.ltdd2_game;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import android.media.MediaPlayer;
import android.widget.ProgressBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import android.os.CountDownTimer;

public class LatHinhActivity extends Activity {
    private static int ROW_COUNT = -1;
    private static int COL_COUNT = -1;
    private Context context;
    private Drawable backImage;
    private int[][] cards;
    private List<Drawable> images;
    private Card firstCard;
    private Card seconedCard;
    private ButtonListener buttonListener;
    private static Object lock = new Object();
    ProgressBar progressBar;
    int turns;
    int score = 0;
    int level = 1;

    Button start_timer;
    Button button_exit;
    CountDownTimer myCountDownTimer;
    private TableLayout mainTable;
    private UpdateCardsHandler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new UpdateCardsHandler();
        loadImages();
        setContentView(R.layout.lat_hinh_layout);
        backImage = getResources().getDrawable(R.drawable.icon3);
        start_timer = (Button) findViewById(R.id.buttonStart);
        button_exit = (Button) findViewById(R.id.exit);
        button_exit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        start_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CountDownTimer countDownTimer = new CountDownTimer(100000, 1000) {
                    @Override
                    public void onTick(long l) {
                        int current = progressBar.getProgress();
                        if (current >= progressBar.getMax()) {
                            current = 0;
                        }

                        progressBar.setProgress(current + 1);
                    }

                    @Override
                    public void onFinish() {


                        Toast.makeText(LatHinhActivity.this, "qua man moi  ", Toast.LENGTH_SHORT).show();
                        newGame(4, 5);
                        int current = progressBar.getProgress();
                        if (current >= progressBar.getMax()) {
                            current = 0;
                        }
                        level++;
                        ((TextView) findViewById(R.id.level)).setText("Level : " + level);
                        progressBar.setProgress(current + 2);
                    }
                };
                countDownTimer.start();
            }


        });


        buttonListener = new ButtonListener();

        mainTable = (TableLayout) findViewById(R.id.TableLayout03);


        context = mainTable.getContext();

        Spinner s = (Spinner) findViewById(R.id.Spinner01);
        ArrayAdapter adapter;
        adapter = ArrayAdapter.createFromResource(
                this, R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        //MediaPlayer song = MediaPlayer.create(LatHinhActivity.this,R.);


        s.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(
                    android.widget.AdapterView<?> arg0,
                    View arg1, int pos, long arg3) {

                ((Spinner) findViewById(R.id.Spinner01)).setSelection(1);

                int x, y;
                newGame(x = 4, y = 4);

            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });
    }

    private void newGame(int c, int r) {
        ROW_COUNT = r;
        COL_COUNT = c;

        cards = new int[COL_COUNT][ROW_COUNT];


        TableRow tr = ((TableRow) findViewById(R.id.TableRow03));
        tr.removeAllViews();

        mainTable = new TableLayout(context);
        tr.addView(mainTable);

        for (int y = 0; y < ROW_COUNT; y++) {
            mainTable.addView(createRow(y));
        }

        firstCard = null;
        loadCards();

        turns = 0;



    }

    private void loadImages() {
        images = new ArrayList<Drawable>();

        images.add(getResources().getDrawable(R.drawable.b1));
        images.add(getResources().getDrawable(R.drawable.b2));
        images.add(getResources().getDrawable(R.drawable.b3));
        images.add(getResources().getDrawable(R.drawable.b4));
        images.add(getResources().getDrawable(R.drawable.b5));
        images.add(getResources().getDrawable(R.drawable.b6));
        images.add(getResources().getDrawable(R.drawable.b7));
        images.add(getResources().getDrawable(R.drawable.b8));
        images.add(getResources().getDrawable(R.drawable.b9));
        images.add(getResources().getDrawable(R.drawable.b10));
        //images.add(getResources().getDrawable(R.drawable.a21));

    }

    private void loadCards() {
        try {
            int size = ROW_COUNT * COL_COUNT;

            Log.i("loadCards()", "size=" + size);

            ArrayList<Integer> list = new ArrayList<Integer>();

            for (int i = 0; i < size; i++) {
                list.add(new Integer(i));

            }


            Random r = new Random();

            for (int i = size - 1; i >= 0; i--) {
                int t = 0;

                if (i > 0) {
                    t = r.nextInt(i);
                }

                t = list.remove(t).intValue();
                cards[i % COL_COUNT][i / COL_COUNT] = t % (size / 2);

                Log.i("loadCards()", "card[" + (i % COL_COUNT) +
                        "][" + (i / COL_COUNT) + "]=" + cards[i % COL_COUNT][i / COL_COUNT]);
            }
        } catch (Exception e) {
            Log.e("loadCards()", e + "");
        }

    }

    private TableRow createRow(int y) {
        TableRow row = new TableRow(context);
        row.setHorizontalGravity(Gravity.CENTER);

        for (int x = 0; x < COL_COUNT; x++) {
            row.addView(createImageButton(x, y));
        }
        return row;
    }

    private View createImageButton(int x, int y) {
        Button button = new Button(context);
        button.setBackgroundDrawable(backImage);
        button.setId(100 * x + y);
        button.setOnClickListener(buttonListener);
        return button;
    }

    class ButtonListener implements OnClickListener {

        @Override
        public void onClick(View v) {


            ;
            synchronized (lock) {
                if (firstCard != null && seconedCard != null) {
                    return;
                }
                int id = v.getId();
                int x = id / 100;
                int y = id % 100;
                turnCard((Button) v, x, y);
            }

        }

        private void turnCard(Button button, int x, int y) {
            button.setBackgroundDrawable(images.get(cards[x][y]));

            if (firstCard == null) {
                firstCard = new Card(button, x, y);
            } else {

                if (firstCard.x == x && firstCard.y == y) {
                    return; //the user pressed the same card
                }

                seconedCard = new Card(button, x, y);

                turns++;

                //((TextView) findViewById(R.id.tv1)).setText("score: " + turns);



                TimerTask tt = new TimerTask() {

                    @Override
                    public void run() {
                        try {
                            synchronized (lock) {
                                handler.sendEmptyMessage(0);
                            }
                        } catch (Exception e) {
                            Log.e("E1", e.getMessage());
                        }
                    }
                };

                Timer t = new Timer(false);
                t.schedule(tt, 1000);
            }


        }

    }

    class UpdateCardsHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            synchronized (lock) {
                checkCards();
            }
        }

        public void checkCards() {
            if (cards[seconedCard.x][seconedCard.y] == cards[firstCard.x][firstCard.y]) {
                firstCard.button.setVisibility(View.INVISIBLE);
                seconedCard.button.setVisibility(View.INVISIBLE);
                score++;
                ((TextView) findViewById(R.id.tv1)).setText("Score: " + score);
            } else {
                seconedCard.button.setBackgroundDrawable(backImage);
                firstCard.button.setBackgroundDrawable(backImage);
            }

            firstCard = null;
            seconedCard = null;
        }
    }


}


