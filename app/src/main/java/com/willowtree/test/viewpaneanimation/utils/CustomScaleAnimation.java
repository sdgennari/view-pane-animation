package com.willowtree.test.viewpaneanimation.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CustomScaleAnimation extends Animation {
    private View view;
    private float startWidth;
    private float endWidth;

    public CustomScaleAnimation(View v, float startWidth, float endWidth) {
        this.view = v;
        this.startWidth = startWidth;
        this.endWidth = endWidth;
        setDuration(500);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float width = (endWidth - startWidth) * interpolatedTime + startWidth;
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = (int) width;
        view.requestLayout();
    }
}
