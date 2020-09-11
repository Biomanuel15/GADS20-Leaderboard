package com.beamdev.android.gads20leaderboard.ui.submit;

import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModel;

import com.beamdev.android.gads20leaderboard.R;
import com.beamdev.android.gads20leaderboard.services.form.FormService;
import com.beamdev.android.gads20leaderboard.services.form.FormServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitFragmentViewModel extends ViewModel {

    private static final String TAG = "SubmitFragmentViewModel";
    public String firstName = "";
    public String lastName = "";
    public String emailAddress = "";
    public String githubLink = "";
    public ResponseListener mResponseListener;
    public boolean submissionConfirmed;

    public SubmitFragmentViewModel() {
    }

    //Submit Form here
    public void onSubmit(View view){

        if (firstName.isEmpty() || lastName.isEmpty() || emailAddress.isEmpty() || githubLink.isEmpty()){
            if (mResponseListener != null) mResponseListener.onFailure("Some Fields are empty!");
            return;
        }

        if (!mResponseListener.confirmSubmission()){
            return;
        }

        else if (submissionConfirmed) {
            FormService ideaService = FormServiceBuilder.buildService(FormService.class);
            Call<Void> request = ideaService.submitForm(emailAddress, firstName, lastName, githubLink);

            request.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> request, @NonNull Response<Void> response) {
                    Log.d(TAG, "onResponse: Submit Response" + response);
                    if (response.isSuccessful() && mResponseListener != null)
                        mResponseListener.onSuccess();
                    else if (response.code() == 401 && mResponseListener != null)
                        mResponseListener.onFailure("Session Expired");
                }

                @Override
                public void onFailure(@NonNull Call<Void> request, @NonNull Throwable t) {
                    if (mResponseListener != null) mResponseListener.onFailure(t.getMessage());
                }
            });
        }
    }

    interface ResponseListener{
        boolean confirmSubmission();
        void onSuccess();
        void onFailure(String message);
    }

}
