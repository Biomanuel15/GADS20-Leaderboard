package com.beamdev.android.gads20leaderboard;

import android.os.Bundle;
import android.view.View;

import com.beamdev.android.gads20leaderboard.databinding.ActivityMainBinding;
import com.beamdev.android.gads20leaderboard.ui.submit.SubmitFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.beamdev.android.gads20leaderboard.ui.main.HomePagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setClickListener(this);

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

    private void gotoSubmit() {
        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();

        SubmitFragment submitFragment = SubmitFragment.newInstance(null, null);

        FT.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        FT.setCustomAnimations(R.anim.slide_in_bottom, android.R.anim.slide_out_right);
        FT.add(android.R.id.content, submitFragment).addToBackStack(null).commit();

    }
}