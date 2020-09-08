package com.beamdev.android.gads20leaderboard.ui.submit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.beamdev.android.gads20leaderboard.R;
import com.beamdev.android.gads20leaderboard.databinding.FragmentSubmitBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubmitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubmitFragment extends Fragment implements SubmitFragmentViewModel.ResponseListener {

    public IOnBackPressed mIOnBackPressed;

    public SubmitFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SubmitFragment newInstance() {
        SubmitFragment fragment = new SubmitFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SubmitFragmentViewModel fragmentViewModel = new ViewModelProvider(this).get(SubmitFragmentViewModel.class);
        FragmentSubmitBinding binding = FragmentSubmitBinding.inflate(inflater,container,false);

        fragmentViewModel.mResponseListener = this;
        binding.setViewModel(fragmentViewModel);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onSuccess() {
        if (mIOnBackPressed != null) mIOnBackPressed.onBackPressed();
        Toast.makeText(requireContext(), "Submited Successfully!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
        if (mIOnBackPressed != null) mIOnBackPressed.onBackPressed();
    }

    public interface IOnBackPressed {
        /**
         * If you return true the back press will not be taken into account, otherwise the activity will act naturally
         * @return true if your processing has priority if not false
         */
        boolean onBackPressed();
    }
}