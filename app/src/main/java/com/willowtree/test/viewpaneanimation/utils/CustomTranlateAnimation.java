package com.willowtree.test.viewpaneanimation.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;

public class CustomTranlateAnimation extends Animation {

    private View view;
    private float startX;
    private float endX;

    public CustomTranlateAnimation(View v, float startX, float endX) {
        this.view = v;
        this.startX = startX;
        this.endX = endX;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float xPos = (endX - startX) * interpolatedTime + startX;
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
        lp.setMargins((int) xPos, lp.topMargin, lp.rightMargin, lp.bottomMargin);
        view.requestLayout();
    }
}
