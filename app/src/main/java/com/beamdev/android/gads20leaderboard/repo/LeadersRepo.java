package com.beamdev.android.gads20leaderboard.repo;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.beamdev.android.gads20leaderboard.datamodel.Leader;
import com.beamdev.android.gads20leaderboard.services.LeaderService;
import com.beamdev.android.gads20leaderboard.services.ServiceBuilder;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeadersRepo {

    Context mContext;
    List<Leader> mLeaderhList;
    List<Leader> mLeadersList;

    public LeadersRepo(@Nullable Application application) {
        mContext = application;
        LeaderService leaderService = ServiceBuilder.buildService(LeaderService.class);

        getLeaderhs(leaderService);

        getLeaders(leaderService);
    }

    private void getLeaderhs(LeaderService leaderService) {
        Call<List<Leader>> request = leaderService.getLeaderh();

        request.enqueue(new Callback<List<Leader>>() {
            @Override
            public void onResponse(Call<List<Leader>> request, Response<List<Leader>> response) {
                if(response.isSuccessful()){
                    //recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(response.body()));
                    mLeaderhList = response.body();
                } else if(response.code() == 401) {
                    if (mContext != null) Toast.makeText(mContext, "Your session has expired", Toast.LENGTH_LONG).show();
                } else {
                    if (mContext != null) Toast.makeText(mContext, "Failed to retrieve items", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Leader>> request, Throwable t) {
                if (t instanceof IOException){
                    if (mContext != null) Toast.makeText(mContext, "A connection error occured", Toast.LENGTH_LONG).show();
                } else {
                    if (mContext != null) Toast.makeText(mContext, "Failed to retrieve items", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getLeaders(LeaderService leaderService) {
        Call<List<Leader>> request = leaderService.getLeaders();

        request.enqueue(new Callback<List<Leader>>() {
            @Override
            public void onResponse(Call<List<Leader>> request, Response<List<Leader>> response) {
                if(response.isSuccessful()){
                    //recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(response.body()));
                    mLeadersList = response.body();
                } else if(response.code() == 401) {
                    if (mContext != null) Toast.makeText(mContext, "Your session has expired", Toast.LENGTH_LONG).show();
                } else {
                    if (mContext != null) Toast.makeText(mContext, "Failed to retrieve items", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Leader>> request, Throwable t) {
                if (t instanceof IOException){
                    if (mContext != null) Toast.makeText(mContext, "A connection error occured", Toast.LENGTH_LONG).show();
                } else {
                    if (mContext != null) Toast.makeText(mContext, "Failed to retrieve items", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public List<Leader> getLeaderhList() {
        return mLeaderhList;
    }

    public List<Leader> getLeadersList() {
        return mLeadersList;
    }
}

