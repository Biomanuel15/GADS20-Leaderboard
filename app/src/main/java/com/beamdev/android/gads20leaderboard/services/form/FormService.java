package com.beamdev.android.gads20leaderboard.services.form;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FormService {

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<Void> submitForm(
            @Field("entry.1824927963")String email,
            @Field("entry.1877115667")String name,
            @Field("entry.2006916086")String lastName,
            @Field("entry.284483984")String projectLink
    );

}
