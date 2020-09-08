package com.beamdev.android.gads20leaderboard.adpater;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.beamdev.android.gads20leaderboard.datamodel.Leader;
import com.squareup.picasso.Picasso;

public class ItemLeaderBindingAdapter {

    @BindingAdapter("subtitle")
    public static void setSubtitle(TextView view, Leader leader){
        if (leader == null) return;
        String subtitleText = "";
        if (leader.hours != 0) subtitleText = leader.hours + "learning hours, " + leader.country;
        if (leader.score != 0) subtitleText = leader.score + "skill IQ score, " + leader.country;
        view.setText(subtitleText);
    }

    @BindingAdapter("leaderIcon")
    public static void setLeaderIcon(ImageView view, String imageUrl){
        if (imageUrl != null && imageUrl != "")Picasso.get().load(imageUrl).into(view);
    }
}
