package com.blogspot.androidpincode.xmlanimations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    private ImageButton imgCap;
    private AppCompatButton btnZoomPlus;
    private AppCompatButton btnZoomMinus;

    // Animation
    private Animation animZoomPlus;
    private Animation animZoomMinus;

    private Boolean btnZoomMinusStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgCap = findViewById(R.id.img_cap);
        btnZoomPlus = findViewById(R.id.btn_zoom_plus);
        btnZoomMinus = findViewById(R.id.btn_zoom_minus);

        // load the Zoom Plus animation
        animZoomPlus = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_plus);

        // load the Zoom Minus animation
        animZoomMinus = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_minus);

        // required for the methods onAnimationEnd, onAnimationRepeat, onAnimationStart
        animZoomMinus.setAnimationListener(MainActivity.this);
        animZoomPlus.setAnimationListener(MainActivity.this);

        // button click event
        btnZoomPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgCap.startAnimation(animZoomPlus);
            }
        });


        // button click event
        btnZoomMinus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // required to prevent unexpected Zoom Minus when Zoom Plus is not used yed
                btnZoomMinus.setClickable(btnZoomMinusStatus);

                if (btnZoomMinus.isClickable()) {
                    // start the animation
                    imgCap.startAnimation(animZoomMinus);
                    Toast.makeText(MainActivity.this, "Clickable",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Not Clickable",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnZoomPlus.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    // start the Zoom Minus animation
                    imgCap.startAnimation(animZoomMinus);
                    Toast.makeText(MainActivity.this, "Focus Lose",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // start the Zoom Plus animation
                    imgCap.startAnimation(animZoomPlus);
                    Toast.makeText(MainActivity.this, "Get Focus",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnZoomMinus.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Toast.makeText(MainActivity.this, "Focus Lose",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Get Focus",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // check for Zoom Plus animation
        if (animation == animZoomPlus) {
            Toast.makeText(getApplicationContext(), "Animation Zoom Plus Stopped",
                    Toast.LENGTH_SHORT).show();
            btnZoomPlus.setClickable(false);

            btnZoomMinusStatus = true;
            btnZoomMinus.setClickable(true);
        }

        // check for Zoom Minus animation
        if (animation == animZoomMinus) {
            Toast.makeText(getApplicationContext(), "Animation Zoom Minus Stopped",
                    Toast.LENGTH_SHORT).show();
            btnZoomPlus.setClickable(true);
            btnZoomMinus.setClickable(false);
        }

        // Take any action after completing the animation
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub
    }
}
