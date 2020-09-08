package com.beamdev.android.gads20leaderboard.adpater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beamdev.android.gads20leaderboard.databinding.ItemLeaderLayoutBinding;
import com.beamdev.android.gads20leaderboard.datamodel.Leader;

import java.util.List;

public class LeaderRecyclerAdapter extends RecyclerView.Adapter<LeaderRecyclerAdapter.ViewHolder> {

    private List<Leader> mLeaderList;

    @NonNull
    @Override
    public LeaderRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLeaderLayoutBinding binding = ItemLeaderLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderRecyclerAdapter.ViewHolder holder, int position) {
        if (mLeaderList != null)holder.mBinding.setLeader(mLeaderList.get(position));
    }

    public void setLeaderList(List<Leader> leaderList) {
        mLeaderList = leaderList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mLeaderList == null)return 0;
        return mLeaderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemLeaderLayoutBinding mBinding;

        public ViewHolder(@NonNull ItemLeaderLayoutBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
