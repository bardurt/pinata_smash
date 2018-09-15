package com.zygne.bitelgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class RewardActivity extends AppCompatActivity {

    private Animation sgAnimation;
    private ImageView ivPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reward);

        ivPrice = findViewById(R.id.iv_price);

        sgAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shrink_grow);
        ivPrice.startAnimation(sgAnimation);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
