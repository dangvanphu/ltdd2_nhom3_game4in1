package com.example.pc.ltdd2_game.FlappyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.SensorManager;



public class CharacterSprite {


    private Bitmap image;
    public int x, y;
    private int xVelocity = 10;
    public int yVelocity = 5;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;




    public CharacterSprite (Bitmap bmp) {
        image = bmp;
        x = 100;
        y = 700;
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);


    }
    public void update() {
        y += yVelocity;
    }


}