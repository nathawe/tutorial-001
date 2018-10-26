package fr.nathalie.panjutorials;

import android.graphics.Canvas;

import fr.nathalie.panjutorials.GameView;


public class GameLoopThread extends Thread {

    private GameView theView;
    private boolean isRunning = false;
    static final long FPS = 10;

    public GameLoopThread(GameView theView) {
        this.theView = theView;
    }

    public void setRunning(boolean run) {
        isRunning = run;
    }


    @Override
    public void run() {

        long TPS = 1000 / FPS ;
        long sleepTime, startTime;

        while (isRunning) {
            Canvas theCanvas = null;
            startTime = System.currentTimeMillis();
            try {
                theCanvas = theView.getHolder().lockCanvas();
                synchronized (theView.getHolder()) {
                    theView.draw(theCanvas);
                }
            } finally {
                if (theCanvas != null) {
                    theView.getHolder().unlockCanvasAndPost(theCanvas);
                }
            }
            sleepTime = TPS -    ( System.currentTimeMillis() -startTime) ;
            try {
                if (sleepTime > 0) {
                    sleep(sleepTime);
                } else {
                    sleep(10);
                }
            }catch(Exception e) {};

             }

        }
}


