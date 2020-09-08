package com.beamdev.android.gads20leaderboard.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.beamdev.android.gads20leaderboard.R;
import com.beamdev.android.gads20leaderboard.adpater.LeaderRecyclerAdapter;
import com.beamdev.android.gads20leaderboard.datamodel.Leader;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class LeadersListFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private LeadersListViewModel mLeadersListViewModel;

    public static LeadersListFragment newInstance(int index) {
        LeadersListFragment fragment = new LeadersListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLeadersListViewModel = new ViewModelProvider(this).get(LeadersListViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        mLeadersListViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        final RecyclerView leadersListRv = root.findViewById(R.id.leadersListRv);
        final LeaderRecyclerAdapter adapter = new LeaderRecyclerAdapter();
        leadersListRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        leadersListRv.setAdapter(adapter);

        mLeadersListViewModel.getLeadersList().observe(getViewLifecycleOwner(), new Observer<List<Leader>>() {
            @Override
            public void onChanged(@Nullable List<Leader> leaderList) {
                adapter.setLeaderList(leaderList);
            }
        });
        return root;
    }
}