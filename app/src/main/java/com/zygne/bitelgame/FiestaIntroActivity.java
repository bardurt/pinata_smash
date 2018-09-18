package com.zygne.bitelgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zygne.bitelgame.views.OnboardingThreeView;
import com.zygne.bitelgame.views.OnboardingTwoView;

public class FiestaIntroActivity extends AppCompatActivity {


    private ImageView btnNext;
    private LinearLayout container;

    private int currentScene = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro_activty);

        btnNext = findViewById(R.id.iv_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onContinue();
            }
        });
        container = findViewById(R.id.ll_intro_container);
    }

    private void onContinue(){

        currentScene++;

        if(currentScene == 1){
            container.removeAllViews();
            container.addView(new OnboardingTwoView(FiestaIntroActivity.this));
        } else if (currentScene == 2){
            container.removeAllViews();
            container.addView(new OnboardingThreeView(FiestaIntroActivity.this));
        } else if (currentScene > 2){
            startActivity(new Intent(FiestaIntroActivity.this, FiestaMenuActivity.class));
        }
    }
}
