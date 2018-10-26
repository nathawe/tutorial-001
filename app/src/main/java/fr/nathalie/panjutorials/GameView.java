package fr.nathalie.panjutorials;


//import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import fr.nathalie.panjutorials.GameLoopThread;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder;
    private Bitmap bmp;
   /* private int y=0;
    private int ySpeed ;*/

    private Sprite theSprite, theSprite2;

    private GameLoopThread theGameLoopThread;


    // @SuppressLint("WrongCall") - neu hinzugefügt am 14.02.14
    //@SuppressLint("WrongCall")public GameView(Context context) {
    public GameView(Context context) {
        super(context);
        theSprite = new Sprite(this, bmp);
        theSprite2 = new Sprite(this, bmp);

        theGameLoopThread = new GameLoopThread(this);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        bmp = BitmapFactory.decodeResource(getResources(),
                R.drawable.poussin);
    }

    @Override
    public void draw(Canvas canvas) {
       /* super.draw(canvas);
        canvas.drawColor(Color.DKGRAY);
        canvas.drawBitmap(bmp, 100,
                66, null);*/

       /*
        super.draw(canvas);
        canvas.drawColor(Color.DKGRAY);
        if(y <= getHeight() - bmp.getHeight()){
            ySpeed = - 10 ;
        } else if (y == 0 ) {ySpeed = 10;}

        y = y + ySpeed;

        canvas.drawBitmap(bmp, 25, y, null);
        */

        super.draw(canvas);
        canvas.drawColor(Color.DKGRAY);
        theSprite.draw(canvas);
        theSprite2.draw(canvas);

    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        theGameLoopThread.setRunning(false);
        while(retry){
            try {
                theGameLoopThread.join();
                retry=false;
            }catch(InterruptedException e){

            }
        }

    }


    @Override
    /*public void surfaceCreated(SurfaceHolder holder) {
        Canvas theCanvas = surfaceHolder.lockCanvas(null);
        draw(theCanvas);
        surfaceHolder.unlockCanvasAndPost(theCanvas);
    }*/
    public void surfaceCreated(SurfaceHolder holder) {
        theGameLoopThread.setRunning(true);
        theGameLoopThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format,
                               int width, int height) {
        // TODO Auto-generated method stub
        ;

    }
}
