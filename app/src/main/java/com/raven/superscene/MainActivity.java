package com.raven.superscene;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static Bitmap DRAWING_CACHE = null ;

    public static float START_X = 0 ;
    public static float START_Y = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildCache();
            }
        });
        findViewById(R.id.root).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    START_X = event.getX();
                    START_Y = event.getY();
                }


                return false;
            }
        });

    }


    private void buildCache(){
//        if(DRAWING_CACHE != null && !DRAWING_CACHE.isRecycled()){
//            DRAWING_CACHE.recycle();
//            DRAWING_CACHE = null ;
//        }
        DRAWING_CACHE = captureScreen(this);
        newPage();
    }


    /**

     * 截屏

     * @param  activity

     * @return

     */

    public Bitmap captureScreen(Activity activity) {

        findViewById(R.id.haha).setDrawingCacheEnabled(true);
//        findViewById(R.id.haha).buildDrawingCache();
//        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);

        Bitmap bmp=findViewById(R.id.haha).getDrawingCache();
//        Bitmap bmp=activity.getWindow().getDecorView().getDrawingCache();

        return bmp;

    }


    private void newPage(){
        Intent intent = new Intent();
        intent.setClass(this,Activity2.class);
        startActivity(intent);
    }

}
