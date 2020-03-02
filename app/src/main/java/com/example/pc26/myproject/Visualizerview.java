package com.example.pc26.myproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc26 on 2/13/2020.
 */


public class Visualizerview extends View {
    private static final int LINE_WIDTH = 5; // width of visualizer lines
    private static final int LINE_SCALE = 75; // scales visualizer lines
    private List<Float> amplitudes; // amplitudes for line lengths
    private int width; // width of this View
    private int height; // height of this View
    private Paint linePaint; // specifies line drawing characteristics

    // constructor
    public Visualizerview(Context context, AttributeSet attrs) {
        super(context, attrs); // call superclass constructor
        linePaint = new Paint(); // create Paint for lines
        linePaint.setColor(Color.BLACK); // set color to green
        linePaint.setStrokeWidth(LINE_WIDTH); // set stroke width
    }

    // called when the dimensions of the View change
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w; // new width of this View
        height = h; // new height of this View
        amplitudes = new ArrayList<Float>(width / LINE_WIDTH);
    }

    // clear all amplitudes to prepare for a new visualization
    public void clear() {
        amplitudes.clear();
    }

    // add the given amplitude to the amplitudes ArrayList
    public void addAmplitude(float amplitude) {
        Log.e("amplitude list",amplitudes.size()+"  size");
        amplitudes.add(amplitude); // add newest to the amplitudes ArrayList

        // if the power lines completely fill the VisualizerView
        if (amplitudes.size() * LINE_WIDTH >= width) {
            // amplitudes.remove(0); // remove oldest power value
        }
    }

    // draw the visualizer with scaled lines representing the amplitudes
    @Override
    public void onDraw(Canvas canvas) {
        int middle = height ; // get the middle of the View
        float curX = 0; // start curX at zero
        Path path=new Path();
        Paint black = new Paint();
        black.setColor(Color.BLACK);
        black.setStyle(Paint.Style.STROKE);
        black.setStrokeJoin(Paint.Join.ROUND);
        black.setStrokeCap(Paint.Cap.ROUND);
        black.setStrokeWidth(3);

        // for each item in the amplitudes ArrayList
        for (float power : amplitudes) {
            float scaledHeight = power / LINE_SCALE; // scale the power
            curX += LINE_WIDTH; // increase X by LINE_WIDTH

            Log.e("mypoints",height+"ampli="+power+"  starty="+middle+scaledHeight+"  yend"+(middle-scaledHeight));
            //  path.moveTo(curX,  middle + scaledHeight / 2);
            //  path.lineTo(curX,  middle + scaledHeight / 2);
            // draw a line representing this item in the amplitudes ArrayList
            canvas.drawLine(curX, height , curX, height-power , linePaint);

            // canvas.drawPath(path, black);


        }


    }

}

