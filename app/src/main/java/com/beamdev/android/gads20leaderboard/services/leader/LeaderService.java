package com.beamdev.android.gads20leaderboard.services.leader;

import com.beamdev.android.gads20leaderboard.datamodel.Leader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LeaderService {

    @GET("hours")
    Call<List<Leader>> getLeaderh();

    @GET("skilliq")
    Call<List<Leader>> getLeaders();


}
