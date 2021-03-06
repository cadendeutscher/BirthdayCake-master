package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class CakeView extends SurfaceView {

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();

    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 600.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 80.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;

    private cakeModel cakeInfo;



    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(0xFFC755B5);  //violet-red
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);

        setBackgroundColor(Color.WHITE);  //better than black default
        //Initialize cake model object
       cakeInfo = new cakeModel();
    }

    //public CakeModel getCakeModel(){
    //    return cakeInfo;
    //}

    ///LAB#3 checkpoint#2
    public void drawBalloon(Canvas canvas){

        Paint paintBalloon = new Paint();
        paintBalloon.setColor(0xff0000ff);
        Paint stringColor = new Paint();
        stringColor.setColor(0xff000000);

        canvas.drawOval(cakeInfo.bx-25f, cakeInfo.by-50f,cakeInfo.bx+25f, cakeInfo.by+50f, paintBalloon);

        canvas.drawLine(cakeInfo.bx,cakeInfo.by+50f, cakeInfo.bx, cakeInfo.by+150f, stringColor);


    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {
        if(cakeInfo.candles) {
            canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);
            //Check if you need to light candles
            if (cakeInfo.lit == true) {
                //draw the outer flame
                float flameCenterX = left + candleWidth / 2;
                float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

                //draw the inner flame
                flameCenterY += outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
            }
            //draw the wick
            float wickLeft = left + candleWidth / 2 - wickWidth / 2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);
        }

    }

    public void drawLoc(Canvas c){
        Paint textp = new Paint();
        textp.setColor(0xffff0000);
        textp.setTextSize(200f);
        c.drawText( "x: " + cakeInfo.tx + " " +"y: " + cakeInfo.ty, 400f,400f,textp);
    }

    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        //Now a candle in the center
        float middle =(cakeWidth/2);
        float divider = (cakeWidth/2)/(cakeInfo.candleNum - (cakeInfo.candleNum%2));
        for(int i = 0; i < cakeInfo.candleNum; i++){
            if(i == 0 && cakeInfo.candleNum % 2 == 1) {
                drawCandle(canvas, middle + cakeLeft, cakeTop);
            }
            else if(i%2 == 1){
                drawCandle(canvas,  cakeLeft + middle - divider, cakeTop);
                if(cakeInfo.candleNum%2 == 0) {
                    divider += divider;
                }
            }
            else if(i%2 == 0){
                drawCandle(canvas, cakeLeft + middle + divider, cakeTop);
                if(cakeInfo.candleNum%2 == 1) {
                    divider += divider;
                }
            }

        }


        //Draw ballons coordinate and shapes here
        if(cakeInfo.isBalloon) {
            drawBalloon(canvas);
        }
        if(cakeInfo.touch) {
            drawLoc(canvas);
        }
    }//onDraw

    //Getter method
    public cakeModel getCakeModel(){
        return cakeInfo;
    }


}//class CakeView

