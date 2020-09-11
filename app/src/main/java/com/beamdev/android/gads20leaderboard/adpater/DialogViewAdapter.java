package com.beamdev.android.gads20leaderboard.adpater;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.beamdev.android.gads20leaderboard.R;

public class DialogViewAdapter {

    @BindingAdapter("iconType")
    public static void setIconType(ImageView view, boolean isSuccessful){
        Context vContext = view.getContext();
        if (isSuccessful){
            view.setImageDrawable(vContext.getResources().getDrawable(R.drawable.ic_check_circle));
        }else
            view.setImageDrawable(vContext.getResources().getDrawable(R.drawable.ic_warning));
    }

    @BindingAdapter("textType")
    public static void setText(TextView view, boolean isSuccessful){
        if (isSuccessful){
            view.setText("Submission Successful");
        }else
            view.setText("Submission not Successful");
    }
}
