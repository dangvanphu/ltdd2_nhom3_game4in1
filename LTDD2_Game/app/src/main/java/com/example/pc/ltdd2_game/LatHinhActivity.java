package com.example.pc.ltdd2_game;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
public class LatHinhActivity extends Activity {
    private static int ROW_COUNT = -1;
    private static int COL_COUNT = -1;
    private Context context;
    private Drawable backImage;
    private int [] [] cards;
    private List<Drawable> images;
    private Card firstCard;
    private Card seconedCard;
    private ButtonListener buttonListener;
    private TableLayout mainTable;
    private static final Object lock = new Object();

    int turns;
    private UpdateCardsHandler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        handler = new UpdateCardsHandler();
        loadImages();
        setContentView(R.layout.lat_hinh_layout);
        mainTable = (TableLayout)findViewById(R.id.TableLayout03);
        backImage =  getResources().getDrawable(R.drawable.icon);


        buttonListener = new ButtonListener();

       //

    }

    private void loadImages() {
        images = new ArrayList<Drawable>();

        images.add(getResources().getDrawable(R.drawable.a1));
        images.add(getResources().getDrawable(R.drawable.a2));
        images.add(getResources().getDrawable(R.drawable.a3));
        images.add(getResources().getDrawable(R.drawable.a4));
        images.add(getResources().getDrawable(R.drawable.a5));
        images.add(getResources().getDrawable(R.drawable.a6));
        images.add(getResources().getDrawable(R.drawable.a7));
        images.add(getResources().getDrawable(R.drawable.a8));
        images.add(getResources().getDrawable(R.drawable.a9));
        images.add(getResources().getDrawable(R.drawable.a10));
        images.add(getResources().getDrawable(R.drawable.a11));
        images.add(getResources().getDrawable(R.drawable.a12));
        images.add(getResources().getDrawable(R.drawable.a13));
        images.add(getResources().getDrawable(R.drawable.a14));
        images.add(getResources().getDrawable(R.drawable.a15));
        images.add(getResources().getDrawable(R.drawable.a16));
        images.add(getResources().getDrawable(R.drawable.a17));
        images.add(getResources().getDrawable(R.drawable.a18));
        images.add(getResources().getDrawable(R.drawable.a19));
        images.add(getResources().getDrawable(R.drawable.a20));
        images.add(getResources().getDrawable(R.drawable.a21));

    }
    private void loadCards(){
        try{
            int size = ROW_COUNT*COL_COUNT;

            Log.i("loadCards()","size=" + size);

            ArrayList<Integer> list = new ArrayList<Integer>();

            for(int i=0;i<size;i++){
                list.add(new Integer(i));
            }


            Random r = new Random();

            for(int i=size-1;i>=0;i--){
                int t=0;

                if(i>0){
                    t = r.nextInt(i);
                }

                t=list.remove(t).intValue();
                cards[i%COL_COUNT][i/COL_COUNT]=t%(size/2);

                Log.i("loadCards()", "card["+(i%COL_COUNT)+
                        "]["+(i/COL_COUNT)+"]=" + cards[i%COL_COUNT][i/COL_COUNT]);
            }
        }
        catch (Exception e) {
            Log.e("loadCards()", e+"");
        }

    }

    private TableRow createRow(int y){
        TableRow row = new TableRow(context);
        row.setHorizontalGravity(Gravity.CENTER);

        for (int x = 0; x < COL_COUNT; x++) {
            row.addView(createImageButton(x,y));
        }
        return row;
    }

    private View createImageButton(int x , int y){
        Button button = new Button(context);
        button.setBackgroundDrawable(backImage);
        button.setId(100*x+y);
        button.setOnClickListener(buttonListener);
        return button;
    }

    class ButtonListener implements OnClickListener {

        @Override
        public void onClick(View v) {

            synchronized (lock) {
                if(firstCard!=null && seconedCard != null){
                    return;
                }
                int id = v.getId();
                int x = id/100;
                int y = id%100;
                turnCard((Button)v,x,y);
            }

        }

        @SuppressLint("SetTextI18n")
        private void turnCard(Button button, int x, int y) {
            button.setBackgroundDrawable(images.get(cards[x][y]));

            if(firstCard==null){
                firstCard = new Card(button,x,y);
            }
            else{

                if(firstCard.x == x && firstCard.y == y){
                    return; //the user pressed the same card
                }

                seconedCard = new Card(button,x,y);

                int i = turns++;
                ((TextView)findViewById(R.id.tv1)).setText("Tries: "+turns);


                TimerTask tt = new TimerTask() {

                    @Override
                    public void run() {
                        try{
                            synchronized (lock) {
                                handler.sendEmptyMessage(0);
                            }
                        }
                        catch (Exception e) {
                            Log.e("E1", e.getMessage());
                        }
                    }
                };

                Timer t = new Timer(false);
                t.schedule(tt, 1300);
            }


        }

    }

    class UpdateCardsHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            synchronized (lock) {
                checkCards();
            }
        }
        public void checkCards(){
            if(cards[seconedCard.x][seconedCard.y] == cards[firstCard.x][firstCard.y]){
                firstCard.button.setVisibility(View.INVISIBLE);
                seconedCard.button.setVisibility(View.INVISIBLE);
            }
            else {
                seconedCard.button.setBackgroundDrawable(backImage);
                firstCard.button.setBackgroundDrawable(backImage);
            }

            firstCard=null;
            seconedCard=null;
        }
    }




}


