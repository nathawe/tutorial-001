package fr.nathalie.panjutorials;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;


public class Sprite {

    private int x = 0;
    private int y = 0;
    private int xSpeed;
    private int ySpeed;
    private int width;
    private int height;
    private Bitmap bmp;
    private GameView theGameView;


    public Sprite (GameView theGameView, Bitmap bmp) {
        this.theGameView = theGameView;
        this.bmp = bmp;
        this.width = bmp.getWidth();
        this.height = bmp.getHeight();


        Random rnd = new Random();
        ySpeed = rnd.nextInt(10) - 4;
        xSpeed = rnd.nextInt(10) - 4;


    }

    private void bounceOff() {
        if (x > theGameView.getWidth() - width - xSpeed || x + xSpeed < 0) {
            xSpeed = -xSpeed;
        }
        x = x + xSpeed;
        if (y > theGameView.getHeight() - height - ySpeed || y + ySpeed < 0) {
            ySpeed = -ySpeed;
        }
        y = y + ySpeed;
    }

    public void draw(Canvas canvas){
        bounceOff();
        canvas.drawBitmap(bmp, x, y, null);
    }

}
