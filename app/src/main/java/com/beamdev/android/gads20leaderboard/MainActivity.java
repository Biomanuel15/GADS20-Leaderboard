package com.beamdev.android.gads20leaderboard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;

import com.beamdev.android.gads20leaderboard.databinding.ActivityMainBinding;
import com.beamdev.android.gads20leaderboard.ui.submit.SubmitFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.beamdev.android.gads20leaderboard.ui.main.HomePagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mBinding;
    private boolean splashScreenShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) splashScreenShown = savedInstanceState.getBoolean("splashScreenOff");

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setClickListener(this);

        if (splashScreenShown) mBinding.splahScreen.setVisibility(View.GONE);
        else {
            animateSplashOff(mBinding.splahScreen);
            splashScreenShown = true;
        }

        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(this, getSupportFragmentManager());

        mBinding.viewPager.setAdapter(homePagerAdapter);

        mBinding.tabs.setupWithViewPager(mBinding.viewPager);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_submit){
            gotoSubmit();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("splashScreenOff", true);
    }

    private void gotoSubmit() {
        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();

        SubmitFragment submitFragment = SubmitFragment.newInstance();
        submitFragment.mIOnBackPressed = new SubmitFragment.IOnBackPressed() {
            @Override
            public boolean onBackPressed() {
                MainActivity.this.onBackPressed();
                return true;
            }
        };

        FT.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        FT.setCustomAnimations(R.anim.slide_in_bottom, android.R.anim.slide_out_right);
        FT.add(android.R.id.content, submitFragment).addToBackStack(null).commit();

    }

    public void animateSplashOff(final View v) {
        new CountDownTimer(5000, 1000){
            @Override
            public void onFinish() {
                v.setVisibility(View.VISIBLE);
                v.setAlpha(1.0f);
                v.setTranslationX(0);
                // Prepare the View for the animation
                v.animate()
                        .setDuration(1000)
                        .translationX(-v.getWidth())
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                            }
                        })
                        .alpha(0.0f)
                        .start();

            }

            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();
    }
}