package com.willowtree.test.viewpaneanimation.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.willowtree.test.viewpaneanimation.R;
import com.willowtree.test.viewpaneanimation.fragment.PrimaryListFragment;
import com.willowtree.test.viewpaneanimation.fragment.SecondaryListFragment;
import com.willowtree.test.viewpaneanimation.fragment.WebViewFragment;
import com.willowtree.test.viewpaneanimation.utils.CustomScaleAnimation;
import com.willowtree.test.viewpaneanimation.utils.CustomTranlateAnimation;

public class AnimationActivity extends FragmentActivity implements
        PrimaryListFragment.OnPrimaryListItemClickedListener,
        SecondaryListFragment.OnSecondaryListItemClickedListener {

    private static final int ANIM_DURATION = 200;

    // Set the ratio of the screen devoted to the left frame; everything else is allocated to the right frame
    private static final double RELATIVE_WEIGHT = .3;

    private Interpolator interpolator = new AccelerateDecelerateInterpolator();

    private int screenWidth;
    private int smallPart;
    private int largePart;
    private boolean isFrameTwoOpen = false;
    private WebViewFragment webViewFragment;

    RelativeLayout mainContainer;
    FrameLayout frameZero;
    FrameLayout frameOne;
    FrameLayout frameTwo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animations);

        // Get the views
        mainContainer = (RelativeLayout) findViewById(R.id.main_container);
        frameZero = (FrameLayout) findViewById(R.id.frame_zero);
        frameOne = (FrameLayout) findViewById(R.id.frame_one);
        frameTwo = (FrameLayout) findViewById(R.id.frame_two);

        // Set the fragments in the frames
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame_zero, new PrimaryListFragment());
        ft.add(R.id.frame_one, new SecondaryListFragment());
        webViewFragment = new WebViewFragment();
        ft.add(R.id.frame_two, webViewFragment);
        ft.commit();

        // Wait until the container has been laid out to make all measurements
        mainContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                screenWidth = mainContainer.getWidth();
                smallPart = (int)(screenWidth * RELATIVE_WEIGHT);
                largePart = screenWidth - smallPart;

                // Make it so all three views can fit in the parent container without being recreated
                // NOTE: This addition prevents the WebView from having to constantly resize
                FrameLayout.LayoutParams lpMain = (FrameLayout.LayoutParams) mainContainer.getLayoutParams();
                lpMain.setMargins(lpMain.leftMargin, lpMain.topMargin, -1 * largePart, lpMain.bottomMargin);

                // Set the widths of the frames
                frameZero.getLayoutParams().width = smallPart;
                frameOne.getLayoutParams().width = largePart;
                frameTwo.getLayoutParams().width = largePart;

                // Unbind the listener at the end to prevent it from being recalled
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mainContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mainContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    private void revealView() {
        // Slide the left list out
        // NOTE: Use a translate animation here for better performance, that way the primary
        // list of TextViews does not get redrawn multiple times
        Animation animZero = new CustomTranlateAnimation(frameZero, 0, -1 * smallPart);
        animZero.setInterpolator(interpolator);
        animZero.setDuration(ANIM_DURATION);

        // Scale the right list. The translate animation above causes the right frame to shift
        // to the left since it is 'layout_toRightOf'
        final Animation animOne = new CustomScaleAnimation(frameOne, largePart, smallPart);
        animOne.setInterpolator(interpolator);
        animOne.setDuration(ANIM_DURATION);

        frameZero.startAnimation(animZero);
        frameOne.startAnimation(animOne);

        isFrameTwoOpen = true;
    }

    private void hideView() {
        // NOTE: Use a translate animation here for better performance, that way the primary
        // list of TextViews does not get redrawn multiple times
        Animation animZero = new CustomTranlateAnimation(frameZero, -1 * smallPart, 0);
        animZero.setInterpolator(interpolator);
        animZero.setDuration(ANIM_DURATION);

        Animation animOne = new CustomScaleAnimation(frameOne, smallPart, largePart);
        animOne.setInterpolator(interpolator);
        animOne.setDuration(ANIM_DURATION);

        frameZero.startAnimation(animZero);
        frameOne.startAnimation(animOne);

        isFrameTwoOpen = false;
    }

    @Override
    public void onPrimaryListItemClick(int id) {
        // TODO CHANGE CONTENT OF THE frameOne LIST HERE
    }

    @Override
    public void onSecondaryListItemClick(int id, String url) {
        if (!isFrameTwoOpen) {
            webViewFragment.setContent(url);
            revealView();
        }
    }

    @Override
    public void onBackPressed() {
        if (isFrameTwoOpen) {
            hideView();
        } else {
            super.onBackPressed();
        }
    }
}
