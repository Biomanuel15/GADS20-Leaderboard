package com.beamdev.android.gads20leaderboard.repo;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.beamdev.android.gads20leaderboard.datamodel.Leader;
import com.beamdev.android.gads20leaderboard.services.leader.LeaderService;
import com.beamdev.android.gads20leaderboard.services.leader.LeaderServiceBuilder;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeadersRepo {

    Context mContext;
    MutableLiveData<List<Leader>> mLeaderhList;
    MutableLiveData<List<Leader>> mLeadersList;

    public LeadersRepo(@Nullable Application application) {
        mContext = application;
        LeaderService leaderService = LeaderServiceBuilder.buildService(LeaderService.class);

        getLeaderhs(leaderService);

        getLeaders(leaderService);
    }

    private void getLeaderhs(LeaderService leaderService) {
        mLeaderhList = new MutableLiveData<>();
        Call<List<Leader>> request = leaderService.getLeaderh();

        request.enqueue(new Callback<List<Leader>>() {
            @Override
            public void onResponse(@NonNull Call<List<Leader>> request, @NonNull Response<List<Leader>> response) {
                if(response.isSuccessful()){
                    //recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(response.body()));
                    List<Leader> leaderhList = response.body();
                    mLeaderhList.setValue(leaderhList);
                } else if(response.code() == 401) {
                    if (mContext != null) Toast.makeText(mContext, "Your session has expired", Toast.LENGTH_LONG).show();
                } else {
                    if (mContext != null) Toast.makeText(mContext, "Failed to retrieve items", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Leader>> request, @NonNull Throwable t) {
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
        mLeadersList = new MutableLiveData<>();

        request.enqueue(new Callback<List<Leader>>() {
            @Override
            public void onResponse(@NonNull Call<List<Leader>> request, @NonNull Response<List<Leader>> response) {
                if(response.isSuccessful()){
                    //recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(response.body()));
                    List<Leader> leaderhList = response.body();
                    mLeadersList.setValue(leaderhList);
                } else if(response.code() == 401) {
                    if (mContext != null) Toast.makeText(mContext, "Your session has expired", Toast.LENGTH_LONG).show();
                } else {
                    if (mContext != null) Toast.makeText(mContext, "Failed to retrieve items", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Leader>> request, @NonNull Throwable t) {
                if (t instanceof IOException){
                    if (mContext != null) Toast.makeText(mContext, "A connection error occured", Toast.LENGTH_LONG).show();
                } else {
                    if (mContext != null) Toast.makeText(mContext, "Failed to retrieve items", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public MutableLiveData<List<Leader>> getLeaderhList() {
        return mLeaderhList;
    }

    public MutableLiveData<List<Leader>> getLeadersList() {
        return mLeadersList;
    }
}

