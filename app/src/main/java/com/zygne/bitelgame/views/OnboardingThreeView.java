package com.zygne.bitelgame.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.zygne.bitelgame.R;

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 16/09/2018.
 */
public class OnboardingThreeView extends LinearLayout {
    public OnboardingThreeView(Context context) {
        super(context);

        init();
    }

    public OnboardingThreeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public OnboardingThreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){
        inflate(getContext(), R.layout.layout_onboarding_three, this);
    }
}
