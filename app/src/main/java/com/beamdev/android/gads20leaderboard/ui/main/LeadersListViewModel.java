package com.beamdev.android.gads20leaderboard.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.beamdev.android.gads20leaderboard.datamodel.Leader;
import com.beamdev.android.gads20leaderboard.repo.LeadersRepo;

import java.util.List;

public class LeadersListViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LeadersRepo mLeadersRepo;

    public LeadersListViewModel(){
        mLeadersRepo = new LeadersRepo(null);
    }

    private LiveData<List<Leader>> mLeadersList = Transformations.map(mIndex, new Function<Integer, List<Leader>>() {
        @Override
        public List<Leader> apply(Integer input) {
            switch (input){
                case 1:
                    return mLeadersRepo.getLeaderhList();
                case 2:
                    return mLeadersRepo.getLeadersList();
                default:
                    return mLeadersRepo.getLeaderhList();
            }
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<List<Leader>> getLeadersList() {
        return mLeadersList;
    }
}