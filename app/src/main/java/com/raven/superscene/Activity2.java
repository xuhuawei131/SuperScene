package com.raven.superscene;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;


public class Activity2 extends AppCompatActivity {


    public static float START_X = 0;
    public static float START_Y = 0;

    private ImageView drawingCache;
    private ObjectAnimator transYAnimation;
    private ObjectAnimator alphaAnimation;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(Animation.INFINITE, Animation.INFINITE);
        setContentView(R.layout.activity_main2);

        drawingCache = (ImageView) findViewById(R.id.draw_cache);
        textView = (TextView) findViewById(R.id.ac2);
        textView.setVisibility(View.INVISIBLE);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    START_X = event.getX();
                    START_Y = event.getY();
                }
                return false;
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
//        if(MainActivity.DRAWING_CACHE != null && !MainActivity.DRAWING_CACHE.isRecycled()){
//            drawingCache.setImageBitmap(MainActivity.DRAWING_CACHE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                show();
            }
        }, 300);
//        }
    }


    private void close() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        textView.setVisibility(View.VISIBLE);
        Animator anim = ViewAnimationUtils.createCircularReveal(textView,
                (int) START_X, (int) START_Y, textView.getHeight(), 0);

        anim.setDuration(500);
        anim.setInterpolator(new AccelerateInterpolator());
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                drawingCache.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();


    }


    private void show() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        textView.setVisibility(View.VISIBLE);
        Animator anim = ViewAnimationUtils.createCircularReveal(textView,
                (int) MainActivity.START_X, (int) MainActivity.START_Y, 0, textView.getHeight());

        anim.setDuration(500);
        anim.setInterpolator(new AccelerateInterpolator());
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                drawingCache.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();


    }

    private void animate() {


        transYAnimation = ObjectAnimator.ofFloat(drawingCache, "translationX", 0, 50, 0, -50, 0);
        transYAnimation.setInterpolator(null);
        transYAnimation.setRepeatCount(5);
        transYAnimation.setDuration(200);
        transYAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animate2();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        transYAnimation.start();


    }


    private void animate2() {
        alphaAnimation = ObjectAnimator.ofFloat(drawingCache, "alpha", 1.0f, 0.0f);
        alphaAnimation.setInterpolator(null);
        alphaAnimation.setDuration(200);
        alphaAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                drawingCache.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        alphaAnimation.start();
    }

}
