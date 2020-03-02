package com.example.pc26.myproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TuningActivity extends AppCompatActivity implements View.OnClickListener {
    private Visualizerview mVisualizerView;
    private MediaPlayer mPlayer;
    private MediaPlayer mSilentPlayer;  /* to avoid tunnel player issue */

    View view;

    View TuninngLine;
    Button sendampliValue, getvoice;

    SeekBar seektuning;
    public static final int REPEAT_INTERVAL = 30;

    TuningBroadcast tuningBroadcast;
    IntentFilter intentFilter;
    ImageView increment, decrement;
    double percentagevalue = 2.857142857142857;
    double viewHeight = 0.0;
    List<Float> mAmplitudeValue;
    Button sendSAvecmd;
    int seekcount = 0;
    int i = 0;
    private boolean isRecording = false;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuning);

        TuninngLine = (View) findViewById(R.id.tuningline);
        sendampliValue = (Button) findViewById(R.id.sendtuningvalu);
        getvoice = (Button) findViewById(R.id.getvoice);
        getvoice.setOnClickListener(this);
        increment = (ImageView) findViewById(R.id.increment);
        decrement = (ImageView) findViewById(R.id.decrement);
        seektuning = (SeekBar) findViewById(R.id.seekbartuning);
        sendSAvecmd = (Button) findViewById(R.id.sendsaveaud);
        sendSAvecmd.setOnClickListener(this);
        decrement.setOnClickListener(this);
        increment.setOnClickListener(this);
        sendampliValue.setOnClickListener(this);
        TuninngLine.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                return false;
            }
        });
        handler = new Handler();
        seektuning.setMax(35);
        seektuning.incrementProgressBy(3);
        mAmplitudeValue = new ArrayList<>();
        tuningBroadcast = new TuningBroadcast();
//        getActivity().registerReceiver(tuningBroadcast, intentFilter);
        intentFilter = new IntentFilter("TuneBroadcast");
       // getvoice.setBackground(getResources().getDrawable(R.drawable.alert_light_frame));
        getvoice.setTextColor(Color.parseColor("#FF0D0D0D"));
       //getActivity().registerReceiver(tuningBroadcast, intentFilter);
        seektuning.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                Log.e("hello", "gradded");
                return false;
            }
        });
        seektuning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("seekbar", ",, click");

            }
        });
        seektuning.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.e("seekbar", b + ",, " + i);
                viewHeight = mVisualizerView.getHeight();
                int percentvalue = 36 - i;
                double mperccentage = percentagevalue * percentvalue;
                double ee = (viewHeight * mperccentage) / 100;
                float a = (float) ee;
                TuninngLine.setY(a);
                /*  if (true) {
                    int h=mVisualizerView.getHeight();
                    double ee=(h*(100-i))/100;
                    float a=(float) ee;
                  //  a=a/2;

                 //   if ((a*i)<mVisualizerView.getHeight()&&(a*i)>0){
                    if (a<mVisualizerView.getHeight()&&a>=0){
                        Log.e("increm if"," "+a);
                      //  TuninngLine.setY(a);
                     }
                }
*/

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("seek", seekBar.getWidth() + "  width" + "  hight " + seekBar.getLeft());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public double getConvertedValue(int intVal) {
        double floatVal = 0.0;
        floatVal = percentagevalue * intVal;
        return floatVal;
    }


    @Override
    public void onResume() {
        super.onResume();
        init();

    }

    void updateTuningLine(boolean inc) {


        double percentagevalue = 2.857142857142857;
        int h = mVisualizerView.getHeight();

//  ViewPropertyAnimator.animate(view).translationYBy(-yourY).translationXBy(-yourX).setDuration(0);
        Log.e("tuingtoll", "hieghton viduslizer=" + mVisualizerView.getHeight() + "  " + mVisualizerView.getY());
        int yValue = 0;
        if (inc) {
            double ee = (h * percentagevalue) / 100;
            float a = (float) ee;
            Log.e("increm", " " + (TuninngLine.getY() - a));
            if ((TuninngLine.getY() - a) == 0) {
                TuninngLine.setY(0);

            } else {
                if ((TuninngLine.getY() - a) > 0) {
                    TuninngLine.setY(TuninngLine.getY() - a);

                }

            }


        } else {
            double ee = (h * percentagevalue) / 100;
            float a = (float) ee;
            if ((TuninngLine.getY() + a) == mVisualizerView.getHeight()) {
                // TuninngLine.setY(mVisualizerView.getHeight());

            } else {
                if ((TuninngLine.getY() + a) < mVisualizerView.getHeight()) {
                    TuninngLine.setY(TuninngLine.getY() + a);
                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onPause() {
        //cleanUp();

        super.onPause();

    }

    @Override
    public void onDestroy() {
        // cleanUp();

        super.onDestroy();
        unregisterReceiver(tuningBroadcast);

    }

    /**
     * Called when the activity is first created.
     */


    private void init() {
        // mPlayer = MediaPlayer.create(getActivity(), R.raw.lsf);
        //// mPlayer.setLooping(true);
        //  mPlayer.start();

        // We need to link the visualizer view to the media player so that
        // it displays something
        mVisualizerView = (Visualizerview)findViewById(R.id.visualizerView);
        // mVisualizerView.link(mPlayer);

        // Start with just line renderer
        //addLineRenderer();
    }

    private void cleanUp() {
        if (mPlayer != null) {
            //mVisualizerView.release();
            mPlayer.release();
            mPlayer = null;
        }

        if (mSilentPlayer != null) {
            mSilentPlayer.release();
            mSilentPlayer = null;
        }
    }

    // Workaround (for Galaxy S4)
    //
    // "Visualization does not work on the new Galaxy devices"
    //    https://github.com/felixpalmer/android-visualizer/issues/5
    //
    // NOTE:
    //   This code is not required for visualizing default "test.mp3" file,
    //   because tunnel player is used when duration is longer than 1 minute.
    //   (default "test.mp3" file: 8 seconds)
    //
   /* private void initTunnelPlayerWorkaround() {
        // Read "tunnel.decode" system property to determine
        // the workaround is needed
        if (TunnelPlayerWorkaround.isTunnelDecodeEnabled(getActivity())) {
            mSilentPlayer = TunnelPlayerWorkaround.createSilentMediaPlayer(getActivity());
        }
    }
*/
    // Methods for adding renderers to visualizer


    private void addLineRenderer() {
        Paint linePaint = new Paint();
        linePaint.setStrokeWidth(3f);
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.BLACK);
        //linePaint.setColor(Color.argb(200, 100, 255, 180));

        Paint lineFlashPaint = new Paint();
        lineFlashPaint.setStrokeWidth(3f);
        lineFlashPaint.setAntiAlias(true);
        //linePaint.setColor(Color.BLACK);

        // lineFlashPaint.setColor(Color.argb(100, 0, 0, 0));
      //  LineRenderer lineRenderer = new LineRenderer(linePaint, lineFlashPaint, false);
        //   mVisualizerView.addRenderer(lineRenderer);
    }

    // Actions for buttons defined in xml
    public void startPressed(View view) throws IllegalStateException, IOException {
        if (mPlayer.isPlaying()) {
            return;
        }
        mPlayer.prepare();
        mPlayer.start();
    }

    public void stopPressed(View view) {
        mPlayer.stop();
    }


    public void linePressed(View view) {
        //addBarGraphRenderers();
    }

    public void clearPressed(View view) {
        //  mVisualizerView.clearRenderers();
    }


    public void sendGetVoiceCommand() {

    }

    public void sendSaveVoiceCommand() {


    }

    public void sendingAmpliValue() {
        try {

            int seekprogrees = seektuning.getProgress();
            int amplitutdeVal = seekprogrees * 1000;
            String mjsonData = "Tag_locdata{\"result\":\"locadata\",\"U_Id\":\"" + "85959595899" + "\",\"apmlitutdevalue\":\"" + amplitutdeVal + "\"}";
            Toast.makeText(getApplicationContext(), "Set Amplitutde Value = " + amplitutdeVal, Toast.LENGTH_SHORT).show();
            Log.e("send amplitutde", " mampli=" + amplitutdeVal);


        } catch (Exception e) {

            e.printStackTrace();

            Log.e("hii", "ee");

        }
  /*double vall=  (((100*TuninngLine.getY())/mVisualizerView.getHeight())-100);
 double posVall=Math.abs(vall);
    double ininitialpercentzageofViewHieght=(mVisualizerView.getHeight()*percentagevalue)/100;

    //double mypercentage=(TuninngLine.getY()*100)/mVisualizerView.getHeight();
        Log.e("tuning percentage"," "+Math.abs(vall));
        double onePercentageVal=(1000/ininitialpercentzageofViewHieght);
    Log.e("tuning one percentage"," "+onePercentageVal);

        double mypercetnage=posVall*onePercentageVal;
double barkAmplitude=((35000*mypercetnage)/100);
Log.e("tuningg",barkAmplitude+"barkam[pli=="+mypercetnage);
*/
    }

    @Override
    public void onClick(View view) {

        if (view == increment) {
            int seekbarval = seektuning.getProgress();

            if (seekbarval != 35) {

                seektuning.setProgress(seekbarval + 1);
            }
        } else if (view == decrement) {
            int seekbarval = seektuning.getProgress();

            if (seekbarval >= 0) {

                seektuning.setProgress(seekbarval - 1);
            }
        } else if (view == sendampliValue) {
            sendingAmpliValue();
        } else if (view == getvoice) {
            if (mVisualizerView != null) {
                mVisualizerView.clear();
            }
            Toast.makeText(getApplicationContext(), "Requset Sent.! ", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Please wait", Toast.LENGTH_LONG).show();
            getvoice.setBackgroundColor(Color.parseColor("#FFE7E6E5"));
            getvoice.setTextColor(Color.parseColor("#E7C6C4C4"));
            sendGetVoiceCommand();

        } else if (view == sendSAvecmd) {
            sendSaveVoiceCommand();
        }
    }

    public class TuningBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("@@ampli=", " recieved broadcast");
           // getvoice.setBackground(getResources().getDrawable(R.drawable.alert_light_frame));
            getvoice.setTextColor(Color.parseColor("#FF0D0D0D"));
            int[] mamplitudes = intent.getExtras().getIntArray("ampliarray");
            if (mAmplitudeValue != null) {
                mAmplitudeValue.clear();
                mVisualizerView.invalidate(); // refresh the VisualizerView

            }
            if (mamplitudes != null) {
                for (int i = 0; i < mamplitudes.length; i++) {
                    Log.e("@@ampli=", mamplitudes[i] + " ");
                    mAmplitudeValue.add(getheightinview(mamplitudes[i]));

                }
                handler.post(updateVisualizer);

            }


        }
    }


    double getpercent(int val) {

        double mval = val;
        double devible = val;
        int balnc = val % 1000;
        int firstp = val / 1000;
        Log.e("@@ devic", "--" + balnc + "  cc" + firstp);
        String finalval = firstp + "." + balnc;
        double ff = Double.valueOf(finalval);
        return ff;
    }

    float getheightinview(int ampli) {
        viewHeight = mVisualizerView.getHeight();
        float mheight = 0;
        double mypercentvalue = getpercent(ampli);

        Log.e("@@ percent% =", mypercentvalue + "%");
        double mperccentage = percentagevalue * mypercentvalue;
        double Ypoints = (viewHeight * mperccentage) / 100;
        mheight = (float) Ypoints;
        Log.e("@@ height", "hh=" + mheight);
        return mheight;
    }

    void stopvisualizer() {

        handler.removeCallbacks(updateVisualizer);

    }

    Runnable updateVisualizer = new Runnable() {
        @Override
        public void run() {
            // if we are already recordin
            // get the current amplitude
            //int x = recorder.getMaxAmplitude();

            mVisualizerView.addAmplitude(mAmplitudeValue.get(i)); // update the VisualizeView
            mVisualizerView.invalidate(); // refresh the VisualizerView
            // update in 40 milliseconds
            Log.e("$$$$$$$$$", "i=" + i);
            handler.postDelayed(this, REPEAT_INTERVAL);
            if (i == mAmplitudeValue.size() - 1) {
                stopvisualizer();
                i = 0;
            } else {

                i++;
            }


        }
    };
}