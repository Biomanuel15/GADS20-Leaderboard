package com.beamdev.android.gads20leaderboard.ui.submit;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.beamdev.android.gads20leaderboard.R;
import com.beamdev.android.gads20leaderboard.databinding.DialogViewLayoutBinding;
import com.beamdev.android.gads20leaderboard.databinding.FragmentSubmitBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubmitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubmitFragment extends Fragment implements SubmitFragmentViewModel.ResponseListener {

    public IOnBackPressed mIOnBackPressed;
    private SubmitFragmentViewModel mFragmentViewModel;

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
        mFragmentViewModel = new ViewModelProvider(this).get(SubmitFragmentViewModel.class);
        FragmentSubmitBinding binding = FragmentSubmitBinding.inflate(inflater,container,false);

        mFragmentViewModel.mResponseListener = this;
        binding.setViewModel(mFragmentViewModel);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onSuccess() {
        if (mIOnBackPressed != null) mIOnBackPressed.onBackPressed();
        Toast.makeText(requireContext(), "Submited Successfully!", Toast.LENGTH_LONG).show();
        showCustomDialog(true, null);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
        if (mIOnBackPressed != null) mIOnBackPressed.onBackPressed();
        showCustomDialog(false, null);
    }

    @Override
    public boolean confirmSubmission() {
        showCustomDialog(null, true);
        return true;
    }

    private void showCustomDialog(Boolean isSuccessful, Boolean confirmSubmission) {
        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before

        DialogViewLayoutBinding dialogBinding = DialogViewLayoutBinding.inflate(dialog.getLayoutInflater());
        if (isSuccessful != null) dialogBinding.setIsSuccessful(isSuccessful);
        else if (confirmSubmission != null) dialogBinding.setConfirmSubmission(confirmSubmission);

        dialog.setContentView(dialogBinding.getRoot());
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialogBinding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentViewModel.submissionConfirmed = true;
                mFragmentViewModel.onSubmit(v);
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    public interface IOnBackPressed {
        /**
         * If you return true the back press will not be taken into account, otherwise the activity will act naturally
         * @return true if your processing has priority if not false
         */
        boolean onBackPressed();
    }
}