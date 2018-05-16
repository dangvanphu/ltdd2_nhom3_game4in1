package com.example.pc.ltdd2_game.data_access;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.pc.ltdd2_game.R;

public class SounPoolPlayer {
    private static SoundPool soundPool;
    private static int clikLatHinh;
    private static int rightChose;
    private static int clickFlappy;

    public SounPoolPlayer(Context context){
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        clikLatHinh = soundPool.load(context, R.raw.hit,1);
        rightChose = soundPool.load(context, R.raw.wingame,1);
        clickFlappy = soundPool.load(context, R.raw.mouseclick,1);
    }
    public  void playClikLatHinh(){
        soundPool.play(clikLatHinh,1.0f,1.0f,1,0,1.0f);
    }
    public  void choosenRigth(){
        soundPool.play(rightChose,1.0f,1.0f,1,0,1.0f);
    }
    public  void mouseClickFlappy(){
        soundPool.play(clickFlappy,1.0f,1.0f,1,0,1.0f);
    }
}
